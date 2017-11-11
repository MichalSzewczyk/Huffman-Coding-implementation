package com.huffman.coding.utils;

public class Node {
    private final Integer value;
    private final Node leftChild;
    private final Node rightChild;
    private Node parent;

    public boolean isRight() {
        return isRight;
    }

    public void setRight(boolean right) {
        isRight = right;
    }

    private boolean isRight;


    public Node(Integer value, Node leftChild, Node rightChild) {
        this.value = value;
        this.leftChild = leftChild;
        this.rightChild = rightChild;
    }

    public Node(Integer value) {
        this.value = value;
        this.leftChild = null;
        this.rightChild = null;
    }

    public Integer getValue() {
        return value;
    }

    public Node getLeftChild() {
        return leftChild;
    }

    public Node getRightChild() {
        return rightChild;
    }

    public Node getParent() {
        return parent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Node node = (Node) o;

        if (isRight != node.isRight) return false;
        if (value != null ? !value.equals(node.value) : node.value != null) return false;
        if (leftChild != null ? !leftChild.equals(node.leftChild) : node.leftChild != null) return false;
        if (rightChild != null ? !rightChild.equals(node.rightChild) : node.rightChild != null) return false;
        return parent != null ? parent.equals(node.parent) : node.parent == null;
    }

    @Override
    public int hashCode() {
        int result = value != null ? value.hashCode() : 0;
        result = 31 * result + (leftChild != null ? leftChild.hashCode() : 0);
        result = 31 * result + (rightChild != null ? rightChild.hashCode() : 0);
        result = 31 * result + (parent != null ? parent.hashCode() : 0);
        result = 31 * result + (isRight ? 1 : 0);
        return result;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }
}
