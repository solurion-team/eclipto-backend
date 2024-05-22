package com.solurion.eclipto.common.kafka;

import com.solurion.eclipto.common.config.KafkaConsumerConfig;
import org.springframework.core.annotation.AliasFor;
import org.springframework.kafka.annotation.KafkaListener;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@KafkaListener
public @interface WorkspaceTopicListener {

    @AliasFor(annotation = KafkaListener.class, attribute = "topics")
    String[] topics() default {WorkspaceTopicConfig.TOPIC};

    @AliasFor(annotation = KafkaListener.class, attribute = "containerFactory")
    String containerFactory() default "#{@" + KafkaConsumerConfig.STRING_LONG_CONTAINER_FACTORY + "}";

    @AliasFor(annotation = KafkaListener.class, attribute = "filter")
    String filter();

}
