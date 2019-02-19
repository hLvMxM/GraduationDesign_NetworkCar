package consumer.kafka;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

public class JavaKafkaProducerHighAPI {

	public static void main(String[] args) throws Exception{
		int count = 0;
        int events = 100;
        Properties props = new Properties();
        //集群地址，多个服务器用"，"分隔
        props.put("bootstrap.servers", "192.168.201.2:9092");
        //key、value的序列化，此处以字符串为例，使用kafka已有的序列化类
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        //props.put("partitioner.class", "com.kafka.demo.Partitioner");//分区操作，此处未写
        props.put("request.required.acks", "1");
        //创建生产者
        Producer<String, String> producer = new KafkaProducer<String, String>(props);
        File file = new File(args[0]);
        InputStreamReader isr = new InputStreamReader(new FileInputStream(file));
        BufferedReader br = new BufferedReader(isr);
        String str = "";
        while((str=br.readLine())!=null){
        	//System.out.println(str);
	        count++;
        	ProducerRecord<String, String> producerRecord = new ProducerRecord<String, String>("network", str);
	        producer.send(producerRecord);
	        if(count % 1000000 == 0)
	        	System.out.println(count);
        }
        producer.close();
    }
}
