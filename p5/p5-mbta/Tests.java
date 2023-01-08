import static org.junit.Assert.*;
import org.junit.*;

import java.util.HashMap;

public class Tests {
  @Test public void testPass() {
    if (Passenger.make("red") == Passenger.make("red")) {
      System.out.println("Passenger success");
    }
    if (Passenger.make("red") != Passenger.make("blue")) {
      System.out.println("Passenger success");
    }

    if (Train.make("red") == Train.make("red")) {
      System.out.println("Train success");
    }
    if (Train.make("red") != Train.make("blue")) {
      System.out.println("Train success");
    }

    if (Station.make("red") == Station.make("red")) {
      System.out.println("Station success");
    }
    if (Station.make("red") != Station.make("blue")) {
      System.out.println("Station success");
    }

    MBTA mbta = new MBTA();
    mbta.loadConfig("C:\\Users\\12866\\Downloads\\p5\\p5-mbta\\sample.json");
//    System.out.println(mbta.getLines());
//    System.out.println(mbta.getTrips());

    Station davis = Station.make("Davis");
//    for (Train train : mbta.linesEntity.keySet()) {
//      for (Station station : mbta.linesEntity.get(train)) {
//        System.out.println(station.toString());
//        System.out.println(Station.make(station.toString()));
//        if (station.equals(davis)) {
//          System.out.println("aa");
//        }
//      }
//    }

    HashMap<Station, String> map = new HashMap<>();
    Station davis1 = Station.make("Davis");
    map.put(davis, "success");
    System.out.println(map.get(Station.make("Davis")));

    Passenger passenger1 = Passenger.make("aa");
    passenger1.train = Train.make("bb");
    HashMap<Passenger, String> map1 = new HashMap<>();
    map1.put(passenger1, "cc");

    passenger1.train = Train.make("dd");
    System.out.println(map1.get(passenger1));
    for (Passenger passenger : map1.keySet()) {
      System.out.println(passenger);
    }

  }
}
