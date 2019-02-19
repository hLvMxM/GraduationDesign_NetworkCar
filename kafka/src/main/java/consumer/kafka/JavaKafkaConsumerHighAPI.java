package consumer.kafka;

import java.rmi.RemoteException;
import java.util.Arrays;
import java.util.Properties;

import javax.xml.rpc.ServiceException;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import xyz.dingjiacheng.hbaseWebService.service.impl.WebServiceImpl;
import xyz.dingjiacheng.hbaseWebService.service.impl.WebServiceImplService;
import xyz.dingjiacheng.hbaseWebService.service.impl.WebServiceImplServiceLocator;

public class JavaKafkaConsumerHighAPI{
	
	public static void main(String[] args) throws ServiceException, RemoteException {

        Properties props = new Properties();
        props.put("bootstrap.servers", "192.168.201.2:9092");       
        props.put("group.id", "group1");//消费者的组id
        props.put("enable.auto.commit", "true");
        props.put("auto.commit.interval.ms", "1000");
        props.put("session.timeout.ms", "30000");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

        KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(props);
        //订阅主题列表topic
        consumer.subscribe(Arrays.asList("network"));

        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(100);
            for (ConsumerRecord<String, String> record : records) {
                System.out.printf("offset = %d, key = %s, value = %s", record.offset(), record.key(), record.value()+"\n");
                WebServiceImplService ws = new WebServiceImplServiceLocator();
                WebServiceImpl wsp = ws.getWebServiceImplPort();
                wsp.updateData(record.value());
            }
        }
    }
}
