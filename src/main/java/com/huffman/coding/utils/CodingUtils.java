package com.huffman.coding.utils;

import com.huffman.coding.model.ValueWithCode;
import com.huffman.coding.model.ValueWithFrequency;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

public class CodingUtils<V> {
    private final Map<ValueAdapter<V>, Node> valueToParentNodeMapping;
    private final Queue<ValueWithFrequency<V>> valuesWithFrequencies;
    private final Queue<Node> roots;

    public CodingUtils(Queue<ValueWithFrequency<V>> valuesWithFrequencies) {
        this.valuesWithFrequencies = valuesWithFrequencies;
        this.valueToParentNodeMapping = new HashMap<>();
        this.roots = new PriorityQueue<>(Comparator.comparingInt(Node::getValue));

    }

    public List<ValueWithCode<V>> getListOfValuesWithCodes() {
        return valueToParentNodeMapping
                .entrySet()
                .stream()
                .map(this::getValueWithCode)
                .collect(Collectors.toList());
    }


    public ValueWithCode<V> getValueWithCode(Map.Entry<ValueAdapter<V>, Node> valueAdapterNodeEntry) {
        StringBuilder codeBuilder = new StringBuilder();
        codeBuilder.append(valueAdapterNodeEntry.getKey().isRight() ? 1 : 0);
        return new ValueWithCode<>(
                valueAdapterNodeEntry.getKey().getValue(),
                buildCode(valueAdapterNodeEntry.getValue(), codeBuilder));
    }


    public String buildCode(Node value, StringBuilder codeBuilder) {
        if (isNull(value) || isNull(value.getParent())) {
            return codeBuilder.reverse().toString();
        }
        codeBuilder.append(value.isRight() ? 1 : 0);
        return buildCode(value.getParent(), codeBuilder);
    }

    public void createNewTree(ValueWithFrequency<V> value, ValueWithFrequency<V> nextValue) {
        Node newRoot = new Node(value.getFrequency() + nextValue.getFrequency());
        valueToParentNodeMapping.put(new ValueAdapter<>(value.getValue(), false), newRoot);
        valueToParentNodeMapping.put(new ValueAdapter<>(nextValue.getValue(), true), newRoot);
        roots.add(newRoot);
    }

    public void addValueAsLeftSibling(ValueWithFrequency<V> value, Node nextTreeRoot) {
        Node newRoot = new Node(value.getFrequency() + nextTreeRoot.getValue());
        nextTreeRoot.setRight(true);
        nextTreeRoot.setParent(newRoot);
        valueToParentNodeMapping.put(new ValueAdapter<>(value.getValue(), false), newRoot);
        roots.add(newRoot);
    }

    public void addValueAsRightSibling(Node treeRoot, ValueWithFrequency<V> nextValue) {
        Node newRoot = new Node(nextValue.getFrequency() + treeRoot.getValue(), null, treeRoot);
        treeRoot.setRight(false);
        treeRoot.setParent(newRoot);
        valueToParentNodeMapping.put(new ValueAdapter<>(nextValue.getValue(), true), newRoot);
        roots.add(newRoot);
    }

    public void mergeTrees(Node treeRoot, Node nextTreeRoot) {
        Node newRoot =
                new Node(treeRoot.getValue() + nextTreeRoot.getValue(), treeRoot, nextTreeRoot);
        treeRoot.setParent(newRoot);
        treeRoot.setRight(false);
        nextTreeRoot.setParent(newRoot);
        nextTreeRoot.setRight(true);
        roots.add(newRoot);
    }

    public boolean isTreeNext() {
        return valuesWithFrequencies.isEmpty() || !roots.isEmpty() && valuesWithFrequencies.peek().getFrequency() >= roots.peek().getValue();
    }

    public boolean isSingleTree() {
        return valuesWithFrequencies.isEmpty() && roots.size() <= 1;
    }

    public Node getNextTree() {
        return roots.poll();
    }

    public ValueWithFrequency<V> getNextValue() {
        return valuesWithFrequencies.poll();
    }
}
