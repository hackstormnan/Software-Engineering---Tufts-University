public class AssertionString {
    private String s;
    public AssertionString(String s) {
        this.s = s;
    }

    public AssertionString isNotNull() {
        if (s == null) {
            throw new UnsupportedOperationException();
        }
        return this;
    }

    public AssertionString isNull() {
        if (s != null) {
            throw new UnsupportedOperationException();
        }
        return this;
    }

    public AssertionString isEqualTo(Object o2) {
        if (!s.equals(o2)) {
            throw new UnsupportedOperationException();
        }
        return this;
    }

    public AssertionString isNotEqualTo(Object o2) {
        if (s.equals(o2)) {
            throw new UnsupportedOperationException();
        }
        return this;
    }

    public AssertionString startsWith(String s2) {
        if (s.indexOf(s2) != 0) {
            throw new UnsupportedOperationException();
        }
        return this;
    }

    public AssertionString isEmpty() {
        if (!s.isEmpty()) {
            throw new UnsupportedOperationException();
        }
        return this;
    }

    public AssertionString contains(String s2) {
        if (!s.contains(s2)) {
            throw new UnsupportedOperationException();
        }
        return this;
    }

}
