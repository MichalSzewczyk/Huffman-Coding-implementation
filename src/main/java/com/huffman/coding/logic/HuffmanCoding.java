package com.huffman.coding.logic;

import com.huffman.coding.utils.CodingUtils;
import com.huffman.coding.utils.Node;
import com.huffman.coding.model.ValueWithCode;
import com.huffman.coding.model.ValueWithFrequency;

import java.util.*;

public class HuffmanCoding<V> implements CodingStrategy<V> {
    private final CodingUtils<V> codingUtils;

    public HuffmanCoding(Queue<ValueWithFrequency<V>> values) {
        this.codingUtils = new CodingUtils<>(values);
    }

    @Override
    public List<ValueWithCode<V>> code() {
        while (!codingUtils.isSingleTree()) {
            if (codingUtils.isTreeNext()) {
                Node treeRoot = codingUtils.getNextTree();
                if (codingUtils.isTreeNext()) {
                    Node nextTreeRoot = codingUtils.getNextTree();
                    codingUtils.mergeTrees(treeRoot, nextTreeRoot);
                } else {
                    ValueWithFrequency<V> nextValue = codingUtils.getNextValue();
                    codingUtils.addValueAsRightSibling(treeRoot, nextValue);
                }
            } else {
                ValueWithFrequency<V> value = codingUtils.getNextValue();
                if (codingUtils.isTreeNext()) {
                    Node nextTreeRoot = codingUtils.getNextTree();
                    codingUtils.addValueAsLeftSibling(value, nextTreeRoot);
                } else {
                    ValueWithFrequency<V> nextValue = codingUtils.getNextValue();
                    codingUtils.createNewTree(value, nextValue);
                }
            }
        }
        return codingUtils.getListOfValuesWithCodes();
    }
}
