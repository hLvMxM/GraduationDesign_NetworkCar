package readFileAndSendKafka;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import Properties.PM;

public class SimpleKafkaProducer {
	private static KafkaProducer<String, String> producer;
    private static String topic;
    public SimpleKafkaProducer(){
        Properties props = new Properties();
        props.put("bootstrap.servers", PM.pps.getProperty("ServiceIP")+":9092");
        props.put("acks", "all");
        props.put("retries", 0);
        props.put("batch.size", 16384);
        props.put("linger.ms", 1);
        props.put("buffer.memory", 33554432);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        topic = PM.pps.getProperty("ReadFileSendKafka.KafkaTopic");
        //设置分区类,根据key进行数据分区
        producer = new KafkaProducer<String, String>(props);
    }
    public void produce(String msg){
        producer.send(new ProducerRecord<String, String>(topic,null,msg));
    }
    
    public void close() {
        producer.close();
    }
}
