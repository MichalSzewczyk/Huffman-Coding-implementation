package com.huffman.coding.logic;

import com.huffman.coding.utils.Node;
import com.huffman.coding.utils.ValueAdapter;
import com.huffman.coding.utils.ValueWithFrequency;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

public class HuffmanCoding<V> implements CodingStrategy<V> {
    private final Map<ValueAdapter<V>, Node> valueToParentNodeMapping;
    private Queue<Node> roots;

    public HuffmanCoding() {
        valueToParentNodeMapping = new HashMap<>();
        roots = new PriorityQueue<>(Comparator.comparingInt(Node::getValue));
    }



    @Override
    public List<ValueWithCode<V>> code(Queue<ValueWithFrequency<V>> valuesWithFrequencies) {
        while (!valuesWithFrequencies.isEmpty()) {
            ValueWithFrequency<V> actual = valuesWithFrequencies.poll();
            Optional<Node> optionalRightSiblingOfActual = getNodeWithSmallestValueLessThan(actual.getFrequency());
            if (!optionalRightSiblingOfActual.isPresent()) {
                createNewTree(valuesWithFrequencies, actual);
            } else {
                attachToExistingTree(actual, optionalRightSiblingOfActual.get());
            }
        }



        Optional<Node> optionalRoot = mergeRootsToSingleRoot();
        if (optionalRoot.isPresent()) {
            return getListOfValuesWithCodes();
        }

        return Collections.emptyList();
    }

    private List<ValueWithCode<V>> getListOfValuesWithCodes() {
        return valueToParentNodeMapping
                .entrySet()
                .stream()
                .map(this::getValueWithCode)
                .collect(Collectors.toList());
    }

    private ValueWithCode<V> getValueWithCode(Map.Entry<ValueAdapter<V>, Node> valueAdapterNodeEntry) {
        StringBuilder codeBuilder = new StringBuilder(valueAdapterNodeEntry.getKey().isRight() ? 1 : 0);
        return new ValueWithCode<>(
                valueAdapterNodeEntry.getKey().getValue(),
                buildCode(valueAdapterNodeEntry.getValue(), codeBuilder));
    }

    private String buildCode(Node value, StringBuilder codeBuilder) {
        if (isNull(value)) {
            return codeBuilder.toString();
        }
        codeBuilder.append(value.isRight() ? 1 : 0);
        return buildCode(value.getParent(), codeBuilder);
    }

    private Optional<Node> mergeRootsToSingleRoot() {
        return roots.stream().reduce((left, right) -> new Node(left.getValue() + right.getValue(), left, right));
    }

    private void createNewTree(Queue<ValueWithFrequency<V>> valuesWithFrequencies, ValueWithFrequency<V> actual) {
        ValueWithFrequency<V> next = valuesWithFrequencies.poll();
        int valueOfNewRoot = actual.getFrequency() + next.getFrequency();
        Node newRoot = new Node(valueOfNewRoot);
        roots.add(newRoot);
        valueToParentNodeMapping
                .put(new ValueAdapter<>(actual.getValue(), false), newRoot);
        valueToParentNodeMapping
                .put(new ValueAdapter<>(next.getValue(), true), newRoot);
    }

    private void attachToExistingTree(ValueWithFrequency<V> actual, Node siblingOfActual) {
        int valueOfNewRoot = siblingOfActual.getValue() + actual.getFrequency();
        roots.remove(siblingOfActual);
        Node newRoot = new Node(valueOfNewRoot, null, siblingOfActual);
        roots.add(newRoot);
        valueToParentNodeMapping
                .put(new ValueAdapter<>(actual.getValue(), false), newRoot);
    }

    private Optional<Node> getNodeWithSmallestValueLessThan(Integer actualValue) {
        return roots.stream()
                .filter(node -> node.getValue() < actualValue)
                .reduce((l, r) -> l.getValue() < r.getValue() ? l : r);

    }

}
