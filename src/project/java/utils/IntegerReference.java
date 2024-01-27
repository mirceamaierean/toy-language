package utils;

public class IntegerReference {
    int value;

    public IntegerReference() {
        this.value = 0;
    }

    public IntegerReference(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void increase(int delta) {
        this.value += delta;
    }
}
