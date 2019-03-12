package writeReadDataToKafka.writeReadDataToKafka;

import java.util.Arrays;
import java.util.Hashtable;
import java.util.Properties;
import java.util.Set;
import java.util.Map.Entry;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import xyz.dingjiacheng.hbasewebservice.service.impl.WebServiceImpl;
import xyz.dingjiacheng.hbasewebservice.service.impl.WebServiceImplService;



public class SimpleKafkaConsumer {
	public static Hashtable<String, String> position = new Hashtable<String, String>();
	public static String json = "";
	
	public static void getPosition() {
		Set<Entry<String, String>> entrySet = position.entrySet();
		StringBuilder stringBuilder = new StringBuilder();
		int flag = 0;
		for (Entry<String, String> entry : entrySet) {
			if (flag!=0) {
				stringBuilder.append(",");
			}
			flag = 1;
			String tmpString = entry.getValue();
			String[] split = tmpString.split(",");
			MapCordinatesVo mcv = CoordinatesConvertUtil.bd_encrypt(Double.valueOf(split[3]), Double.valueOf(split[4]));
			String stringappend = split[0]+","+split[1]+","+split[2]+","+mcv.getLat()+","+mcv.getLon()+","+split[5];
			stringBuilder.append("\""+stringappend+"\"");
		}
		json = "{\"position\":["+stringBuilder.toString()+"]}";
	}
	
	public static String getPositionString() {
		getPosition();
		return json;
	}
	
	public static void main(String[] args){
        Properties properties = new Properties();
        properties.put("bootstrap.servers", "192.168.1.100:9092");
        properties.put("group.id", "group");
        properties.put("enable.auto.commit", "false");
        properties.put("auto.commit.interval.ms", "1000");
        properties.put("auto.offset.reset", "latest");
        properties.put("session.timeout.ms", "30000");
        properties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

        KafkaConsumer<String, String> kafkaConsumer = new KafkaConsumer<String, String>(properties);
        kafkaConsumer.subscribe(Arrays.asList("network3"));
        WebServiceImplService wsi = new WebServiceImplService();
        WebServiceImpl wsimpl = wsi.getWebServiceImplPort();
        while (true) {
            ConsumerRecords<String, String> records = kafkaConsumer.poll(100);
            for (ConsumerRecord<String, String> record : records) {
                String value = record.value();
                wsimpl.updateData(value);
                String[] split = value.split(",");
                position.put(split[1], value);
                if(split[5].equals("2"))
                	position.remove(split[1]);
            }
        }

    }
}
