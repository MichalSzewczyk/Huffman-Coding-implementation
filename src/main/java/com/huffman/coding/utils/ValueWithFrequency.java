package com.huffman.coding.utils;

public final class ValueWithFrequency<V> {
    private final V value;
    private final Integer frequency;

    public ValueWithFrequency(V value, Integer frequency) {
        this.value = value;
        this.frequency = frequency;
    }

    public V getValue() {
        return value;
    }

    public Integer getFrequency() {
        return frequency;
    }
}
