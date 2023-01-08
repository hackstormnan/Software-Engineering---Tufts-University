import java.util.*;
import com.google.gson.*;

public class C {
    public List<String> l;
    public Map<String, String> m;

    public static void main(String[] args) {
      Gson gson = new Gson();
      C c = new C();
      c.l = List.of("a", "b", "c");
      c.m = Map.of("k1", "v1", "k2", "v2");
      String s = gson.toJson(c);
      System.out.println(s);

      C c2 = gson.fromJson(s, C.class);
      System.out.println(c2.l);
      System.out.println(c2.m);
    }
}
