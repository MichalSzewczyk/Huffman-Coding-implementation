package com.huffman.coding.logic;

import com.huffman.coding.model.ValueWithCode;

import java.util.List;

public interface CodingStrategy<V> {
    List<ValueWithCode<V>> code();
}
