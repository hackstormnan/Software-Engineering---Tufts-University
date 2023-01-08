import java.sql.SQLSyntaxErrorException;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String args[]) {
        Map<String, Throwable> map1 = new HashMap<>();

//        Unit.testClass("TestCase");


        Map<String, Object[]> map2 = new HashMap<>();
        map2 = Unit.quickCheckClass("Quickcheck_TestCase");

//
//        for (String key : map2.keySet()) {
//            System.out.println("the key (method name) is: " + key);
//            System.out.println("the length of object[]: " + map2.get(key).length);
//
//            System.out.println("the elements inside the object[]: ");
//            for (Object o : map2.get(key)) {
//                System.out.println(o);
//            }
//            System.out.println("---------------");
//        }



    }
}
