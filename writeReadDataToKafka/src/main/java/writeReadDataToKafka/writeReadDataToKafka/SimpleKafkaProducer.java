package writeReadDataToKafka.writeReadDataToKafka;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Properties;

import javax.sound.midi.Patch;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

public class SimpleKafkaProducer {
	private static KafkaProducer<String, String> producer;
    private final static String TOPIC = "network3";
    public SimpleKafkaProducer(){
        Properties props = new Properties();
        props.put("bootstrap.servers", "192.168.1.100:9092");
        props.put("acks", "all");
        props.put("retries", 0);
        props.put("batch.size", 16384);
        props.put("linger.ms", 1);
        props.put("buffer.memory", 33554432);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        //设置分区类,根据key进行数据分区
        producer = new KafkaProducer<String, String>(props);
    }
    public void produce(String msg){
        producer.send(new ProducerRecord<String, String>(TOPIC,null,msg));
    }
    
    public void close() {
        producer.close();
    }

    public static Long[] getTimeAndNum(String path) throws IOException {
    	File file = new File(path+"tmp.txt");
    	Long[] result = {1L,0L};
    	if(file.exists()) {
    		FileReader fileReader = new FileReader(file);
			BufferedReader br = new BufferedReader(fileReader);
			String str = br.readLine();
			String[] split = str.split(",");
			result[0] = Long.valueOf(split[0]);
			result[1] = Long.valueOf(split[1]);
    	}
    	return result;
    }

    public static void main(String[] args) throws Exception {
        SimpleKafkaProducer skp = new SimpleKafkaProducer();
    	Long startTime = System.currentTimeMillis()/1000;
    	Long firstTime = null;
    	String path = "C:\\Users\\30837\\Desktop\\bak\\#根目录\\networkcardata\\gps_20161001";
    	if (args.length!=0) {
			path = args[0];
		}
    	Long[] timeAndNum = getTimeAndNum(path);
    	int num = timeAndNum[0].intValue();
    	Long laststarttime = timeAndNum[1];
    	for (int i = num; i <= 20; i++) {
    		File file = new File(path+"/gps_201610"+String.format("%02d", i));
			FileReader fileReader = new FileReader(file);
			BufferedReader br = new BufferedReader(fileReader);
			String str = null;
			while ((str = br.readLine()) != null) {
				String[] split = str.split(",");
				if(Long.valueOf(split[2])<=laststarttime)continue;
				if (firstTime==null) {
					firstTime = Long.valueOf(split[2]);
				}
				Long nowTime = System.currentTimeMillis()/1000;
				Long nowTimeInPast = nowTime - startTime + firstTime; //过去的当前时间
				try {
					Writer writer = new FileWriter(path+"/tmp.txt",false);
					writer.write(i+","+nowTimeInPast.toString());
					writer.close();
				} catch (Exception e) {e.printStackTrace();}
				skp.produce(str);
				if (nowTimeInPast<=Long.valueOf(split[2])) {
					Thread.sleep(1000);
				}
			}
		}
    	System.out.println("Done");
    }

}
