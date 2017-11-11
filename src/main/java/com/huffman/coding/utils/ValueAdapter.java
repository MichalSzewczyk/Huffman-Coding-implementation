package com.huffman.coding.utils;

public class ValueAdapter<V> {
    private final V value;
    private final boolean isRight;

    public ValueAdapter(V value, boolean isRight) {
        this.value = value;
        this.isRight = isRight;
    }

    public V getValue() {
        return value;
    }

    public boolean isRight() {
        return isRight;
    }
}
