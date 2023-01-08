public class AssertionBoolean {
    private Boolean b;

    public AssertionBoolean(Boolean b) {
        this.b = b;
    }

    public AssertionBoolean isEqualTo(boolean b2) {
        if (b != b2) {
            throw new UnsupportedOperationException();
        }
        return this;
    }

    public AssertionBoolean isTrue() {
        if (b == false) {
            throw new UnsupportedOperationException();
        }
        return this;
    }

    public AssertionBoolean isFalse() {
        if (b == true) {
            throw new UnsupportedOperationException();
        }
        return this;
    }
}
