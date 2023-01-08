import javax.management.remote.JMXConnectorServerMBean;
import java.util.*;

public class DeboardEvent implements Event {
  public final Passenger p; public final Train t; public final Station s;
  public DeboardEvent(Passenger p, Train t, Station s) {
    this.p = p; this.t = t; this.s = s;
  }
  public boolean equals(Object o) {
    if (o instanceof DeboardEvent e) {
      return p.equals(e.p) && t.equals(e.t) && s.equals(e.s);
    }
    return false;
  }
  public int hashCode() {
    return Objects.hash(p, t, s);
  }
  public String toString() {
    return "Passenger " + p + " deboards " + t + " at " + s;
  }
  public List<String> toStringList() {
    return List.of(p.toString(), t.toString(), s.toString());
  }
  public void replayAndCheck(MBTA mbta) {
    if (!mbta.passengerMap.containsKey(this.p)) {
      throw new UnsupportedOperationException("no such passenger at the json file");
    }
    if (!mbta.passengerTrainMap.containsKey(this.p)) {
      throw new UnsupportedOperationException("this passenger is not in any trains");
    }

    if (mbta.passengerTrainMap.get(this.p) != this.t) {
      throw new UnsupportedOperationException("passenger is not in this train");
    }
    if (mbta.trainMap.get(this.t) != s) {
      throw new UnsupportedOperationException("train is not at this station");
    }

    List<Station> trip_stations = mbta.tripsEntity.get(this.p);
    if (!trip_stations.contains(this.s)) {
      throw new UnsupportedOperationException("this is not the station the passenger want to go");
    }

    mbta.passengerTrainMap.remove(this.p);
    mbta.passengerMap.put(this.p, this.s);

  }
}
