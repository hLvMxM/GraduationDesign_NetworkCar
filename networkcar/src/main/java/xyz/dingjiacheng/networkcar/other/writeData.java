package xyz.dingjiacheng.networkcar.other;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.TopicPartition;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.FlatMapFunction;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.streaming.Duration;
import org.apache.spark.streaming.Durations;
import org.apache.spark.streaming.api.java.JavaDStream;
import org.apache.spark.streaming.api.java.JavaInputDStream;
import org.apache.spark.streaming.api.java.JavaPairDStream;
import org.apache.spark.streaming.api.java.JavaPairReceiverInputDStream;
import org.apache.spark.streaming.api.java.JavaStreamingContext;
import org.apache.spark.streaming.kafka010.ConsumerStrategies;
import org.apache.spark.streaming.kafka010.KafkaUtils;
import org.apache.spark.streaming.kafka010.LocationStrategies;

import scala.Tuple2;

public class writeData {
	static final String ZK_QUORUM = "192.168.163.128:2181/kafka";
    static final String GROUP = "test-consumer-group";
    static final String TOPICSS = "test";
    static final String NUM_THREAD = "64";

	public static void main(String[] args) throws InterruptedException {
		SparkConf sparkConf = new SparkConf().setMaster("local[2]").setAppName("main.java.computingCenter")
				.set("spark.serializer", "org.apache.spark.serializer.KryoSerializer");
        // Create the context with 2 seconds batch size
        //每两秒读取一次kafka
        JavaStreamingContext jssc = new JavaStreamingContext(sparkConf, new Duration(2000));

        int numThreads = Integer.parseInt(NUM_THREAD);
        Map<String, Object> kafkaParams = new HashMap<String, Object>();
        kafkaParams.put("metadata.broker.list","192.168.163.128:9092");
        kafkaParams.put("bootstrap.servers", "192.168.163.128:9092");
        kafkaParams.put("group.id", "group2");
        kafkaParams.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        kafkaParams.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        kafkaParams.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        Collection<String> topicsSet = new HashSet<>(Arrays.asList("test".split(",")));
        Map<TopicPartition, Long> offsets = new HashMap<TopicPartition, Long>();
        offsets.put(new TopicPartition("test", 0), 0L);
        JavaInputDStream<ConsumerRecord<Object, Object>> messages =
                KafkaUtils.createDirectStream(jssc,
                		LocationStrategies.PreferConsistent(),
                        ConsumerStrategies.Subscribe(topicsSet, kafkaParams, offsets));
        JavaDStream<Object> result = messages.map(x -> x.value());
        result.print();
        jssc.start();
        jssc.awaitTermination();

	}
}
