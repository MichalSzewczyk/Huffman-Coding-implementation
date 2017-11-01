package com.huffman.coding.logic;

import com.huffman.coding.utils.ValueWithFrequency;

import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

public class BasicCoding<V> implements Coding<V> {
    private final Queue<ValueWithFrequency<V>> values;

    public BasicCoding() {
        values = new PriorityQueue<>((firstValue, secondValue) -> secondValue.getFrequency() - firstValue.getFrequency());
    }

    @Override
    public Map<Character, Integer> getCode() {
        return null;
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
