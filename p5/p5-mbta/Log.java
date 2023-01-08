import java.lang.reflect.*;
import java.io.*;
import java.util.*;
import com.google.gson.*;
import com.google.gson.reflect.*;

public class Log {
  private List<Event> events;

  public Log() { events = new LinkedList<>(); }

  public Log(List<Event> events) { this.events = events; }

  public synchronized List<Event> events() { return events; }

  public synchronized void train_moves(Train t, Station s1, Station s2) {
    Event e = new MoveEvent(t, s1, s2);
    events.add(e);
    System.out.println(e);
    System.out.flush();
  }

  public synchronized void passenger_boards(Passenger p, Train t, Station s) {
    Event e = new BoardEvent(p, t, s);
    events.add(e);
    System.out.println(e);
    System.out.flush();
  }

  public synchronized void passenger_deboards(Passenger p, Train t, Station s) {
    Event e = new DeboardEvent(p, t, s);
    events.add(e);
    System.out.println(e);
    System.out.flush();
  }
}
