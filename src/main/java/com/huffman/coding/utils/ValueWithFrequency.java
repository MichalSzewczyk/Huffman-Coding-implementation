package com.huffman.coding.utils;

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
