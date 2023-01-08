public class Assertion {
    /* You'll need to change the return type of the assertThat methods */
    static AssertionObject assertThat(Object o) {
        AssertionObject ao = new AssertionObject(o);

        return ao;
    }
    static AssertionString assertThat(String s) {
	    AssertionString as = new AssertionString(s);

        return as;
    }
    static AssertionBoolean assertThat(boolean b) {
	    AssertionBoolean ab = new AssertionBoolean(b);

        return ab;
    }
    static AssertionInteger assertThat(int i) {
	    AssertionInteger ai = new AssertionInteger(i);

        return ai;
    }
}
