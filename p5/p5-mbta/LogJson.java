import java.io.*;
import java.lang.reflect.*;
import java.util.*;
import com.google.gson.*;
import com.google.gson.reflect.*;

public class LogJson {
  private static List<List<String>> entries;
  private static Gson gson = new Gson();
  private static Map<Class<?>, String> eventToKey = new HashMap<>();
  private static Map<String, Class<?>> keyToEvent = new HashMap<>();

  static {
    LogJson.registerEvent(MoveEvent.class, "Move");
    LogJson.registerEvent(DeboardEvent.class, "Deboard");
    LogJson.registerEvent(BoardEvent.class, "Board");
  }

  private LogJson(List<List<String>> entries) { this.entries = entries; }

  public LogJson(Log log) {
    entries = new LinkedList<>();
    for (Event e : log.events()) {
      LinkedList<String> elts = new LinkedList<>(e.toStringList());
      elts.addFirst(eventToKey.get(e.getClass()));
      entries.add(elts);
    }
  }

  public Log toLog() {
    System.out.println(keyToEvent);
    List<Event> events = new LinkedList<>();
    for (List<String> e : entries) {
      System.out.println("key: " + e.get(0) + " result: " + keyToEvent.get(e.get(0)));
      Class<?> cls = keyToEvent.get(e.get(0));
      if (cls == MoveEvent.class) {
        Event evt = new MoveEvent(Train.make(e.get(1)), Station.make(e.get(2)), Station.make(e.get(3)));
        events.add(evt);
      }
      else if (cls == BoardEvent.class) {
        Event evt = new BoardEvent(Passenger.make(e.get(1)), Train.make(e.get(2)), Station.make(e.get(3)));
        events.add(evt);
      }
      else if (cls == DeboardEvent.class) {
        Event evt = new DeboardEvent(Passenger.make(e.get(1)), Train.make(e.get(2)), Station.make(e.get(3)));
        events.add(evt);
      }
      else {
          throw new RuntimeException("Don't know what to do with event kind " + cls);
      }
    }
    return new Log(events);
  }

  public static void registerEvent(Class<?> c, String k) {
    eventToKey.put(c, k); keyToEvent.put(k, c);
  }

  public String toJson() {
    return gson.toJson(entries);
  }

  public static LogJson fromJson(String json) {
    Type t_list_string = TypeToken.getParameterized(List.class, String.class).getType();
    Type t_list_list_string = TypeToken.getParameterized(List.class, t_list_string).getType();
    @SuppressWarnings("unchecked") LogJson lj = new LogJson((List<List<String>>) gson.fromJson(json, t_list_list_string));
    return lj;
  }

  public static LogJson fromJson(Reader r) {
    Type t_list_string = TypeToken.getParameterized(List.class, String.class).getType();
    Type t_list_list_string = TypeToken.getParameterized(List.class, t_list_string).getType();
    @SuppressWarnings("unchecked") LogJson lj = new LogJson((List<List<String>>) gson.fromJson(r, t_list_list_string));
    return lj;
  }

}
