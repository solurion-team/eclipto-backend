package com.solurion.eclipto.common.kafka;

import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.listener.adapter.RecordFilterStrategy;

@RequiredArgsConstructor
public class KeyRecordFilterStrategy<K, V> implements RecordFilterStrategy<K, V> {
    private final K key;

    @Override
    public boolean filter(ConsumerRecord<K, V> consumerRecord) {
        return !key.equals(consumerRecord.key());
    }
}
