package com.huffman.coding.logic;

import com.huffman.coding.model.ValueWithCode;
import com.huffman.coding.model.ValueWithFrequency;

import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class CodingFacade<V> implements Coding<V> {
    private final Queue<ValueWithFrequency<V>> values;
    private final CodingStrategy<V> codingStrategy;

    public CodingFacade() {
        values = new PriorityQueue<>(Comparator.comparingInt(ValueWithFrequency::getFrequency));
        this.codingStrategy = new HuffmanCoding<>(values);
    }

    @Override
    public List<ValueWithCode<V>> getCode() {
        return codingStrategy.code();
    }

    @Override
    public void add(ValueWithFrequency<V> valueWithFrequency) {
        values.add(valueWithFrequency);
    }

    @Override
    public void clear() {
        values.clear();
    }
}
