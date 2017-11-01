package com.huffman.coding.logic;

import com.huffman.coding.utils.ValueWithFrequency;

import java.util.Map;

public interface Coding<V> {
    Map<Character, Integer> getCode();
    void add(ValueWithFrequency<V> valueWithFrequency);
    void clear();
}
