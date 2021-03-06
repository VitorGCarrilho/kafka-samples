package com.github.vitorgcarrilho.kafka;

import com.github.vitorgcarrilho.service.SiteAccessInfoReader;
import com.github.vitorgcarrilho.util.ApplicationPropertiesUtils;
import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Properties;

/**
 * Created by IntelliJ IDEA.
 *
 * @author Vitor Carrilho
 * @since 24/03/2020
 */
public class ProducerDemoWithCallback {

    private static Logger logger = LoggerFactory.getLogger(ProducerDemoWithCallback.class);

    private final ApplicationPropertiesUtils applicationPropertiesUtils;

    private final Properties properties;

    private final SiteAccessInfoReader siteAccessInfoReader;

    private final KafkaProducer<String, String> producer;

    public ProducerDemoWithCallback() throws IOException {
        this.applicationPropertiesUtils = new ApplicationPropertiesUtils();
        this.properties = applicationPropertiesUtils.getProperties();
        this.siteAccessInfoReader = new SiteAccessInfoReader(properties);
        this.producer = new KafkaProducer<String, String>(properties);
    }

    public void produceDataFromFile() throws IOException {
        siteAccessInfoReader
                .getDataAsStream()
                .forEach(this::produceData);
        producer.flush();
    }

    private void produceData(String data) {
        ProducerRecord<String, String> record = new ProducerRecord<String, String>(properties.getProperty("app.topic"), data);
        producer.send(record, (recordMetadata, e) -> {
            if (e == null) {
                logger.info("Received new metadata: topic={} partition={} offset={} timestamp={}"
                        , recordMetadata.topic(), recordMetadata.partition(), recordMetadata.offset(), recordMetadata.timestamp());
            } else {
                logger.error("Error while producing", e);
            }
        });
    }

    public static void main(String[] args) throws IOException {
        ProducerDemoWithCallback producerDemoWithCallback = new ProducerDemoWithCallback();
        producerDemoWithCallback.produceDataFromFile();
    }
}
