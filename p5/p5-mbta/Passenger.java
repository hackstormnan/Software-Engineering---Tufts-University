import java.util.HashMap;

public class Passenger extends Entity {
  public String location = null;
  public Train train = null;

  private Passenger(String name) { super(name); }
  private static HashMap<String, Passenger> map = new HashMap<>();


  public static Passenger make(String name) {
    // Change this method!
    if (!map.containsKey(name)) {
      map.put(name, new Passenger(name));
    }

    return map.get(name);
  }
}
