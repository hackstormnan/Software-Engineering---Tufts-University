import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Quickcheck_TestCase {
//    @Property
//    public boolean int_test(@IntRange(min = 7, max = 9) Integer i, @StringSet(strings={"s1", "s2"}) String s, @IntRange(min = 1, max = 2) Integer i1, @StringSet(strings={"s1", "s2"}) String s1) {
//        return false;
//    }

//    @Property
//    public boolean int_test(@IntRange(min = 7, max = 9) Integer i, @StringSet(strings={"s1", "s2"}) String s) {
//        return false;
//    }
//
//        @Property
//        public boolean int_test(@IntRange(min = -1, max = 9) Integer i) {
//            return i >= -1;
//        }
//
    @Property
    public boolean string_test(@StringSet(strings={"d", "e", "f"}) String s) {
        return true;
    }

//    @Property public boolean testFoo(@ForAll(name="genIntSet", times=10) Object o) {
//        Set s = (Set) o;
//        s.add("foo");
//        return s.contains("foo");
//    }
//
//    int count = 0;
//    public Object genIntSet() {
//        Set s = new HashSet();
//        for (int i=0; i<count; i++) { s.add(i); }
//        count++;
//        return s;
//    }

//    @Property
//    public boolean testList1(@ListLength(min=0, max=2) List<@IntRange(min=5, max=7) Integer> i){
//        return true;
//    }

//    @Property
//    public boolean testList2(@ListLength(min=0, max=2) List<@StringSet(strings={"s1", "s2"}) String> s){
//        return true;
//    }
}
