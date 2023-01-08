import java.util.HashMap;

public class Station extends Entity {
  private String location = null;

  private Station(String name) { super(name); }
  private static HashMap<String, Station> map = new HashMap<>();

  public static Station make(String name) {
    // Change this method!
    if (!map.containsKey(name)) {
      map.put(name, new Station(name));
    }

    return map.get(name);
  }
}
