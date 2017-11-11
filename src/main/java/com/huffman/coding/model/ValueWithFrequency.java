package com.huffman.coding.model;

import com.huffman.coding.utils.Tuple;

public final class ValueWithFrequency<V> {
    private final Tuple<V, Integer> valueWithFrequency;

    public ValueWithFrequency(V value, Integer frequency) {
        valueWithFrequency = new Tuple<>(value, frequency);
    }

    public V getValue() {
        return valueWithFrequency.getKey();
    }

    public Integer getFrequency() {
        return valueWithFrequency.getValue();
    }
}
