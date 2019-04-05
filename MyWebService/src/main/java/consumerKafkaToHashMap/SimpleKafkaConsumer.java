package consumerKafkaToHashMap;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.Properties;
import java.util.Set;
import java.util.Map.Entry;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import HbaseUtil.HbaseUtil;
import Properties.PM;
import readFileAndSendKafka.ReadFileSendKafka;
import static time.Time.getdispart;

public class SimpleKafkaConsumer {
	public static Logger logger = Logger.getLogger("consumerKafkaToHashMap.SimpleKafkaConsumer");
	public static Hashtable<String, String> position = new Hashtable<String, String>();
	public static String json = "";
	
	private static void getPosition() {
		Set<Entry<String, String>> entrySet = position.entrySet();
		StringBuilder stringBuilder = new StringBuilder();
		int flag = 0;
		ArrayList<String> list = new ArrayList<String>();
		for (Entry<String, String> entry : entrySet) {
			if (flag!=0) {
				stringBuilder.append(",");
			}
			flag = 1;
			String tmpString = entry.getValue();
			Long valueOf = Long.valueOf(tmpString.split(",")[2]);
			if(System.currentTimeMillis()/1000-getdispart()-3600>valueOf)
				list.add(entry.getKey());
			stringBuilder.append("\""+tmpString+"\"");
		}
		for (String string : list) {
			position.remove(string);
		}
		json = "{\"position\":["+stringBuilder.toString()+"]}";
	}
	
	public static String getPositionString() {
		return json;
	}
	
	public static void consumerKafka(){
		//logger设置
		FileHandler fileHandler;
		try {
			fileHandler = new FileHandler(PM.pps.getProperty("ConsumerKafkaToHashMap.logpath"));
			SimpleFormatter sf = new SimpleFormatter();
			fileHandler.setFormatter(sf);
			logger.addHandler(fileHandler);
		} catch (SecurityException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
        Properties properties = new Properties();
        properties.put("bootstrap.servers", PM.pps.getProperty("ServiceIP")+":9092");
        properties.put("group.id", PM.pps.getProperty("ConsumerKafkaToHashMap.groupid"));
        properties.put("enable.auto.commit", "false");
        properties.put("auto.commit.interval.ms", "1000");
        properties.put("auto.offset.reset", "latest");
        properties.put("session.timeout.ms", "30000");
        properties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

        KafkaConsumer<String, String> kafkaConsumer = new KafkaConsumer<String, String>(properties);
        kafkaConsumer.subscribe(Arrays.asList(PM.pps.getProperty("ReadFileSendKafka.KafkaTopic")));
        while (true) {
            System.out.println("while true");
        	ConsumerRecords<String, String> records = kafkaConsumer.poll(100);
            for (ConsumerRecord<String, String> record : records) {
            	String value = record.value();
            	System.out.println(value);
            	if("yes".equals(PM.pps.getProperty("HbaseUtil.iswrite"))) {
            		HbaseUtil.sendInfo(value);
            		logger.log(Level.INFO, value);
            	}
                String[] split = value.split(",");
                position.put(split[1], value);
                if(split[5].equals("2"))
                	position.remove(split[1]);
            }
    		getPosition();
        }
    }
}
