import com.google.gson.Gson;

import java.io.File;
import java.util.*;

public class MBTA {

  // Creates an initially empty simulation
  private HashMap<String, List<String>> lines = new HashMap<>();
  private HashMap<String, List<String>> trips = new HashMap<>();
  public HashMap<Train, Station> trainMap = new HashMap<>();
  public HashMap<Passenger, Station> passengerMap = new HashMap<>();
  public HashMap<Passenger, Train> passengerTrainMap = new HashMap<>();

  public HashMap<Train, List<Station>> linesEntity = new HashMap<>();
  public HashMap<Passenger, List<Station>> tripsEntity = new HashMap<>();

  public String direction;

  public MBTA() { }

  // Adds a new transit line with given name and stations
  public void addLine(String name, List<String> stations) {
    lines.put(name, stations);
    Train train = Train.make(name);

    List<Station> station_list = new ArrayList<>();
    for (String station_name : stations) {
      station_list.add(Station.make(station_name));
    }

    linesEntity.put(train, station_list);

    trainMap.put(train, Station.make(stations.get(0)));

    train.location = station_list.get(0).toString();
    train.direction = "right";
  }

  // Adds a new planned journey to the simulation
  public void addJourney(String name, List<String> stations) {
    trips.put(name, stations);
    Passenger passenger = Passenger.make(name);
    List<Station> station_list = new ArrayList<>();
    for (String station_name : stations) {
      station_list.add(Station.make(station_name));
    }

    tripsEntity.put(passenger, station_list);

    passengerMap.put(passenger, Station.make(stations.get(0)));

    passenger.location = station_list.get(0).toString();
    passenger.train = null;
  }

  // Return normally if initial simulation conditions are satisfied, otherwise
  // raises an exception
  public void checkStart() {
    try {
      if (trainMap.isEmpty() || passengerMap.isEmpty()) {
        throw new UnsupportedOperationException();
      }

      for (Train train : trainMap.keySet()) {
        if (trainMap.get(train) != linesEntity.get(train).get(0)) {
          throw new UnsupportedOperationException("train is not at the correct starting position");
        }
        if (train.location != linesEntity.get(train).get(0).toString()) {
          throw new UnsupportedOperationException("train is not at the correct starting position");
        }
        if (train.direction != "right") {
          throw new UnsupportedOperationException("train's start direction is not right");
        }
      }

      for (Passenger passenger : passengerMap.keySet()) {
        if (passengerMap.get(passenger) != tripsEntity.get(passenger).get(0)) {
          throw new UnsupportedOperationException("passenger is not at the correct starting position");
        }
        if (passenger.location != tripsEntity.get(passenger).get(0).toString()) {
          throw new UnsupportedOperationException("passenger is not at the correct starting position");
        }
        if (passenger.train != null) {
          throw new UnsupportedOperationException("passenger's starting train is not null");
        }
      }


    } catch (Exception exception) {
      System.out.println("not start");
      throw new UnsupportedOperationException();
    }
  }

  // Return normally if final simulation conditions are satisfied, otherwise
  // raises an exception
  public void checkEnd() {
    try {
      for (Passenger passenger : passengerMap.keySet()) {
        List<Station> stations = tripsEntity.get(passenger);
        if (stations.size() == 0) {
          throw new UnsupportedOperationException("wrong station data in json file");
        }
        if (passengerMap.get(passenger) != stations.get(stations.size()-1)) {
          throw new UnsupportedOperationException("passenger is not at final destination");
        }
      }
    } catch (Exception exception) {
      throw new UnsupportedOperationException("train should not end");
    }
  }

  // reset to an empty simulation
  public void reset() {
    lines.clear();
    trips.clear();
  }

  public HashMap<String, List<String>> getLines() {
    return lines;
  }

  public HashMap<String, List<String>> getTrips() {
    return trips;
  }

  // adds simulation configuration from a file
  public void loadConfig(String filename) {
    Gson gson = new Gson();
    String contents = get_file_content(filename);

    try {
      MBTA mbta = gson.fromJson(contents, MBTA.class);
      System.out.println(contents);

      for (String line_name : mbta.lines.keySet()) {
        addLine(line_name, mbta.lines.get(line_name));
      }

      for (String trip_name : mbta.trips.keySet()) {
        addJourney(trip_name, mbta.trips.get(trip_name));
      }


    } catch (Exception exception) {
      throw new UnsupportedOperationException("error");
    }
  }

  private String get_file_content(String filename) {
    StringBuilder stringBuilder = new StringBuilder();
    try {
      File file = new File(filename);
      Scanner scanner = new Scanner(file);

      while (scanner.hasNextLine()) {
        stringBuilder.append(scanner.nextLine());
        stringBuilder.append("\n");
      }
      scanner.close();
    } catch (Exception exception) {
      throw new UnsupportedOperationException();
    }

    return stringBuilder.toString();
  }
}
