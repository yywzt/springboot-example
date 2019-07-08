package com.example.yyw.common;

import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

/**
 * @author ywyw2424@foxmail.com
 * @date 2019/4/28 14:10
 * @describe
 */
@Configuration
public class LocalDateTimeSerializerConfig {

    @Value("${spring.jackson.date-format:yyyy-MM-dd HH:mm:ss.SSS}")
    private String localDateTimeDeserializerPattern;
    @Value("${spring.jackson.date-format:yyyy-MM-dd}")
    private String localDateDeserializerPattern;
    @Value("${spring.jackson.date-format:yyyy-MM-dd}")
    private String localTimeDeserializerPattern;

    @Bean
    public LocalDateTimeSerializer localDateTimeDeserializer() {
        return new LocalDateTimeSerializer(DateTimeFormatter.ofPattern(localDateTimeDeserializerPattern));
    }
    @Bean
    public LocalDateSerializer localDateSerializer() {
        return new LocalDateSerializer(DateTimeFormatter.ofPattern(localDateDeserializerPattern));
    }
    @Bean
    public LocalTimeSerializer localTimeSerializer() {
        return new LocalTimeSerializer(DateTimeFormatter.ofPattern(localTimeDeserializerPattern));
    }

    @Bean
    public Jackson2ObjectMapperBuilderCustomizer jackson2ObjectMapperBuilderCustomizer() {
        HashMap<Class<?>, JsonSerializer<?>> map = new HashMap<>();
        map.put(LocalDateTime.class, localDateTimeDeserializer());
        map.put(LocalDate.class, localDateSerializer());
        map.put(LocalTime.class, localTimeSerializer());
        return builder -> builder.serializersByType(map);
    }
}
