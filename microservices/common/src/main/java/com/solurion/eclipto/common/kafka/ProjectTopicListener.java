package com.solurion.eclipto.common.kafka;

import com.solurion.eclipto.common.config.KafkaConsumerConfig;
import org.springframework.core.annotation.AliasFor;
import org.springframework.kafka.annotation.KafkaListener;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Documented
@Retention(RetentionPolicy.RUNTIME)
@KafkaListener
public @interface ProjectTopicListener {
    @AliasFor(annotation = KafkaListener.class, attribute = "id")
    String id() default "";

    @AliasFor(annotation = KafkaListener.class, attribute = "topics")
    String[] topics() default {ProjectTopicConfig.TOPIC};

    @AliasFor(annotation = KafkaListener.class, attribute = "containerFactory")
    String containerFactory() default "#{@" + KafkaConsumerConfig.STRING_LONG_CONTAINER_FACTORY + "}";

    @AliasFor(annotation = KafkaListener.class, attribute = "filter")
    String filter();
}
