package SparkCount;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.hadoop.hbase.KeyValue;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.function.FlatMapFunction;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.api.java.function.Function2;
import org.apache.spark.api.java.function.PairFunction;
import org.apache.spark.api.java.function.VoidFunction;
import org.apache.spark.streaming.Durations;
import org.apache.spark.streaming.api.java.JavaDStream;
import org.apache.spark.streaming.api.java.JavaInputDStream;
import org.apache.spark.streaming.api.java.JavaPairDStream;
import org.apache.spark.streaming.api.java.JavaStreamingContext;
import org.apache.spark.streaming.kafka010.ConsumerStrategies;
import org.apache.spark.streaming.kafka010.KafkaUtils;
import org.apache.spark.streaming.kafka010.LocationStrategies;
import org.bouncycastle.crypto.paddings.TBCPadding;

import com.google.common.primitives.Bytes;

import HbaseUtil.HbaseUtil;
import Properties.PM;
import readFileAndSendKafka.ReadFileSendKafka;
import scala.Tuple2;
import static time.Time.getdispart;

public class SparkCount {

	private static Logger logger = Logger.getLogger(SparkCount.class);
	//  https://blog.csdn.net/lwb314/article/details/79408201
	private static Long disparity;
	public static void main(String[] args) throws InterruptedException {
		File file = new File(PM.pps.getProperty("ReadFileSendKafka.writeProgress"));
		disparity = getdispart();
		SparkConf conf = new SparkConf().setAppName("demo").setMaster("local[2]");
		conf.set("spark.serializer", "org.apache.spark.serializer.KryoSerializer");
		JavaStreamingContext streamingContext = new JavaStreamingContext(conf,Durations.seconds(10));
		Map<String, Object> kafkaParams = new HashMap<String, Object>();
		kafkaParams.put("bootstrap.servers", "192.168.1.100:9092");
		kafkaParams.put("key.deserializer", StringDeserializer.class);
		kafkaParams.put("value.deserializer", StringDeserializer.class);
		kafkaParams.put("group.id","sparktest");
		kafkaParams.put("auto.offset.reset","latest");
		kafkaParams.put("enable.auto.commit",false);
		Collection<String> topics = Arrays.asList("networktest03181722");
		
		JavaInputDStream<ConsumerRecord<String, String>> stream = 
				KafkaUtils.createDirectStream(streamingContext, LocationStrategies.PreferConsistent(), 
						ConsumerStrategies.<String,String>Subscribe(topics, kafkaParams));
		
		//--------------流量统计----------------------
		JavaDStream<String> map = stream.map(new Function<ConsumerRecord<String,String>, String>() {
			private static final long serialVersionUID = 1L;
			public String call(ConsumerRecord<String, String> arg0) throws Exception {
				return arg0.value().split(",")[0];
			}
		});
		JavaPairDStream<String, Integer> wordcounts = map.mapToPair(new PairFunction<String, String, Integer>() {
			private static final long serialVersionUID = 1L;
			public Tuple2<String, Integer> call(String arg0) throws Exception {
				return new Tuple2<String, Integer>("allcount", 1);
			}
		});
		JavaPairDStream<String, Integer> reduceByKey = wordcounts.reduceByKey(new Function2<Integer, Integer, Integer>() {
			private static final long serialVersionUID = 1L;
			public Integer call(Integer arg0, Integer arg1) throws Exception {
				return arg0 + arg1;
			}
		});
		reduceByKey.foreachRDD(new VoidFunction<JavaPairRDD<String,Integer>>() {
			@Override
			public void call(JavaPairRDD<String, Integer> arg0) throws Exception {
				for (Tuple2<String, Integer> tmp : arg0.collect()) {
					Long time = (System.currentTimeMillis()/1000-disparity);
					HTable table = HbaseUtil.countTable;
					Put put = new Put((time+"_count").getBytes());
					put.add("count".getBytes(), null, (""+tmp._2).toString().getBytes());
					put.add("type".getBytes(), null, "count".getBytes());
					table.put(put);
				}
			}
		});
		//------------------------------------------------------
		

		//---------------------新增司机--------------------------
		JavaDStream<String> newdriver = stream.map(new Function<ConsumerRecord<String,String>, String>() {
			private static final long serialVersionUID = 1L;
			public String call(ConsumerRecord<String, String> arg0) throws Exception {
				return arg0.value().split(",")[0];
			}
		});
		JavaDStream<String> filter = newdriver.filter(new Function<String, Boolean>() {
			public Boolean call(String arg0) throws Exception {
				return !"1".equals(HbaseUtil.getDistinctInfo(arg0));
			}
		});
		JavaDStream<Long> count = filter.count();//统计新增司机
		count.foreachRDD(new VoidFunction<JavaRDD<Long>>() {
			@Override
			public void call(JavaRDD<Long> arg0) throws Exception {
				for (Long long1 : arg0.collect()) {
					HTable table = HbaseUtil.countTable;
					Put put = new Put((System.currentTimeMillis()/1000-disparity+"_driver").getBytes());
					put.add("count".getBytes(), null, (long1+"").toString().getBytes());
					put.add("type".getBytes(), null, "driver".getBytes());
					table.put(put);
				}
			}
		});
		filter.foreachRDD(new VoidFunction<JavaRDD<String>>() {
			private static final long serialVersionUID = 1L;
			@Override
			public void call(JavaRDD<String> arg0) throws Exception {
				for (String string : arg0.collect()) {
					HbaseUtil.updateDistinctInfo(string);
				}
			}
		});
		//-------------------正在进行订单数---------------------------
		JavaDStream<ConsumerRecord<String, String>> filter2 = stream.filter(new Function<ConsumerRecord<String,String>, Boolean>() {

			@Override
			public Boolean call(ConsumerRecord<String, String> arg0) throws Exception {
				if("0".equals(arg0.value().split(",")[5])) {
					return true;
				}else {
					return false;
				}
			}
		});
		filter2.count().foreachRDD(new VoidFunction<JavaRDD<Long>>() {
			@Override
			public void call(JavaRDD<Long> arg0) throws Exception {
				for (Long l : arg0.collect()) {
					HTable countTable = HbaseUtil.countTable;
					Get get = new Get("doing".getBytes());
					get.addColumn("count".getBytes(), null);
					Result result = countTable.get(get);
					String value  = org.apache.hadoop.hbase.util.Bytes.toString(result.getValue("count".getBytes(), null));
					Long valueOf;
					if (value==null||"".equals(value)) {
						valueOf = 0L;
					}else {
						valueOf = Long.valueOf(value);
					}
					valueOf+=l;
					Put put = new Put("doing".getBytes());
					put.addColumn("count".getBytes(), null, (valueOf+"").getBytes());
					countTable.put(put);
				}
			}
		});;
		//------------------------已完结------------------------
		JavaDStream<ConsumerRecord<String, String>> filter3 = stream.filter(new Function<ConsumerRecord<String,String>, Boolean>() {

			@Override
			public Boolean call(ConsumerRecord<String, String> arg0) throws Exception {
				if("2".equals(arg0.value().split(",")[5])) {
					return true;
				}else {
					return false;
				}
			}
		});
		filter3.count().foreachRDD(new VoidFunction<JavaRDD<Long>>() {
			@Override
			public void call(JavaRDD<Long> arg0) throws Exception {
				for (Long long1 : arg0.collect()) {
					HTable countTable = HbaseUtil.countTable;
					Get get = new Get("doing".getBytes());
					get.addColumn("count".getBytes(), null);
					Result result = countTable.get(get);
					String value  = org.apache.hadoop.hbase.util.Bytes.toString(result.getValue("count".getBytes(), null));
					Long valueOf;
					if (value==null||"".equals(value)) {
						valueOf = 0L;
					}else {
						valueOf = Long.valueOf(value);
					}
					valueOf-=long1;
					if(valueOf<0) valueOf = 0L; 
					Put put = new Put("doing".getBytes());
					put.addColumn("count".getBytes(), null, (valueOf+"").getBytes());
					countTable.put(put);
					Put put2 = new Put((System.currentTimeMillis()/1000-disparity+"_done").getBytes());
					put2.addColumn("count".getBytes(), null, (valueOf+"").getBytes());
					put2.addColumn("type".getBytes(), null, ("done").getBytes());
					countTable.put(put2);
					
				}
			}
		});
		//----------------------------------------------------
		streamingContext.start();
		streamingContext.awaitTermination();
	}

	public long daytime() {
		return System.currentTimeMillis()/1000/3600/24*24*3600;
	}
	
}

