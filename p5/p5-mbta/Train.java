import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Train extends Entity {
  public String location = null;
  public String direction = "right";

  private Train(String name) { super(name); }
  private static HashMap<String, Train> map = new HashMap<>();

  public static Train make(String name) {
    // Change this method!
    if (!map.containsKey(name)) {
      map.put(name, new Train(name));
    }

    return map.get(name);
  }
}


