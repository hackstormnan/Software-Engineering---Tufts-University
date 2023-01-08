public class AssertionInteger {
    private int i;

    public AssertionInteger(int i) {
        this.i = i;
    }

    public AssertionInteger isEqualTo(int i2) {
        if (i != i2) {
            throw new UnsupportedOperationException();
        }
        return this;
    }

    public AssertionInteger isLessThan(int i2) {
        if (i >= i2) {
            throw new UnsupportedOperationException();
        }
        return this;
    }

    public AssertionInteger isGreaterThan(int i2) {
        if (i <= i2) {
            throw new UnsupportedOperationException();
        }
        return this;
    }
}
