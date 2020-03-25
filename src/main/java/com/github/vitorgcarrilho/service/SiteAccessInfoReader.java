package com.github.vitorgcarrilho.service;

import org.apache.kafka.common.protocol.types.Field;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;
import java.util.stream.Stream;

/**
 * Created by IntelliJ IDEA.
 *
 * @author Vitor Carrilho
 * @since 24/03/2020
 */
public class SiteAccessInfoReader {

    private final Properties properties;

    public SiteAccessInfoReader(Properties properties) {
        this.properties = properties;
    }

    public Stream<String> getDataAsStream() throws IOException {
        Stream<String> stream = Files.lines(Paths.get(properties.getProperty("file.address")));
        return stream;
    }
}
