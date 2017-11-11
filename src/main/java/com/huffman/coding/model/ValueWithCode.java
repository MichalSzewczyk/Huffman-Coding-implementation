package com.huffman.coding.model;

public class ValueWithCode<V> {
    private final V value;
    private final String code;

    public ValueWithCode(V value, String code) {
        this.value = value;
        this.code = code;

    }

    public V getValue() {
        return value;

    }

    public String getCode() {
        return code;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ValueWithCode<?> that = (ValueWithCode<?>) o;

        if (value != null ? !value.equals(that.value) : that.value != null) return false;
        return code != null ? code.equals(that.code) : that.code == null;
    }

    @Override
    public int hashCode() {
        int result = value != null ? value.hashCode() : 0;
        result = 31 * result + (code != null ? code.hashCode() : 0);
        return result;
    }
}
