package com.huffman.coding.logic;

import com.huffman.coding.model.ValueWithCode;
import com.huffman.coding.model.ValueWithFrequency;

import java.util.List;

public interface Coding<V> {
    List<ValueWithCode<V>> getCode();
    void add(ValueWithFrequency<V> valueWithFrequency);
    void clear();
}
