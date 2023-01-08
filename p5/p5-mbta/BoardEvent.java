import java.util.*;

public class BoardEvent implements Event {
  public final Passenger p; public final Train t; public final Station s;
  public BoardEvent(Passenger p, Train t, Station s) {
    this.p = p; this.t = t; this.s = s;
  }
  public boolean equals(Object o) {
    if (o instanceof BoardEvent e) {
      return p.equals(e.p) && t.equals(e.t) && s.equals(e.s);
    }
    return false;
  }
  public int hashCode() {
    return Objects.hash(p, t, s);
  }
  public String toString() {
    return "Passenger " + p + " boards " + t + " at " + s;
  }
  public List<String> toStringList() {
    return List.of(p.toString(), t.toString(), s.toString());
  }
  public void replayAndCheck(MBTA mbta) {
    if (!mbta.tripsEntity.containsKey(this.p)) {
      throw new UnsupportedOperationException("no this passenger in json file");
    }

    List<Station> trip_stations = mbta.tripsEntity.get(this.p);

    if (!mbta.passengerMap.containsKey(this.p)) {
      throw new UnsupportedOperationException("no this passenger");
    }

    if (mbta.passengerMap.get(this.p) != this.s) {
      throw new UnsupportedOperationException("passenger is not at this station, cannot board");
    }

    if (trip_stations.indexOf(this.s) == -1) {
      throw new UnsupportedOperationException("no such station");
    }


    mbta.passengerTrainMap.put(this.p, this.t);
    mbta.passengerMap.put(this.p, null);
  }
}
