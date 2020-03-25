package com.github.vitorgcarrilho.kafka;

import com.github.vitorgcarrilho.util.ApplicationPropertiesUtils;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.io.IOException;
import java.util.Properties;

/**
 * Created by IntelliJ IDEA.
 *
 * @author Vitor Carrilho
 * @since 24/03/2020
 */
public class ProducerDemo {

    public static void main(String[] args) throws IOException {
        ApplicationPropertiesUtils applicationPropertiesUtils = new ApplicationPropertiesUtils();
        Properties properties = applicationPropertiesUtils.getProperties();

        KafkaProducer<String, String> producer = new KafkaProducer<String, String>(properties);
        ProducerRecord<String, String> record =
                new ProducerRecord<String, String>(properties.getProperty("app.topic"), "hello world");

        producer.send(record);
        producer.close();
    }


}
