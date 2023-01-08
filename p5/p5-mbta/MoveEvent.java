import java.util.*;

public class MoveEvent implements Event {
  public final Train t; public final Station s1, s2;
  public MoveEvent(Train t, Station s1, Station s2) {
    this.t = t; this.s1 = s1; this.s2 = s2;
  }
  public boolean equals(Object o) {
    if (o instanceof MoveEvent e) {
      return t.equals(e.t) && s1.equals(e.s1) && s2.equals(e.s2);
    }
    return false;
  }
  public int hashCode() {
    return Objects.hash(t, s1, s2);
  }
  public String toString() {
    return "Train " + t + " moves from " + s1 + " to " + s2;
  }
  public List<String> toStringList() {
    return List.of(t.toString(), s1.toString(), s2.toString());
  }
  public void replayAndCheck(MBTA mbta) {

    for (Train train : mbta.linesEntity.keySet()) {
      if (!train.equals(this.t)) {
        continue;
      }

      if (!mbta.trainMap.containsKey(this.t)) {
        throw new UnsupportedOperationException("there is no such train");
      }
      if (!mbta.linesEntity.containsKey(this.t)) {
        throw new UnsupportedOperationException("there is no such train in json file");
      }

      List<Station> stations = mbta.linesEntity.get(this.t);
      if (stations.indexOf(s1) == -1) {
        throw new UnsupportedOperationException("no station s1");
      }
      if (stations.indexOf(s2) == -1) {
        throw new UnsupportedOperationException("no station s2");
      }

      if (mbta.trainMap.get(this.t) != s1) {
        throw new UnsupportedOperationException("train is not at the start station");
      }

      if (train.direction.equals("right")) {
        if (stations.indexOf(s1) + 1 != stations.indexOf(s2)) {
          throw new UnsupportedOperationException("train is moving right, and cannot arrive the next station");
        }
      }
      if (train.direction.equals("left")) {
        if (stations.indexOf(s1) - 1 != stations.indexOf(s2)) {
          throw new UnsupportedOperationException("train is moving left, and cannot arrive the next station");
        }
      }

      for (Train train1 : mbta.trainMap.keySet()) {
        if (mbta.trainMap.get(train1) == this.s2) {
          throw new UnsupportedOperationException(train1 + "station has one train, cannot move there");
        }
      }


      mbta.trainMap.put(train, this.s2);

      if (stations.indexOf(s2) == stations.size()-1) {
        train.direction = "left";
      }
      if (stations.indexOf(s2) == 0) {
        train.direction = "right";
      }

    }
  }
}
