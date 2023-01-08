import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Sim {

  public static void run_sim(MBTA mbta, Log log) {
    ReentrantLock total_lock = new ReentrantLock();                                      //establish a huge lock
    AtomicInteger doneTrainCount = new AtomicInteger();
    AtomicInteger doneTripCount = new AtomicInteger();
    Condition condition = total_lock.newCondition();

    HashMap<Station, ReentrantLock> stationLockMap = new HashMap<>();                    //station map to their unique lock

    for (Train train : mbta.linesEntity.keySet()) {                                      //initialize the stationLockMap
      for (Station station : mbta.linesEntity.get(train)) {
        if (stationLockMap.containsKey(station)) {
          continue;
        }
        stationLockMap.put(station, new ReentrantLock());
      }
    }

    for (Train train : mbta.linesEntity.keySet()) {
      new Thread(() -> {                                                                 //set thread for every train
        List<Station> stations = mbta.linesEntity.get(train);                            //this train's all stations

        Station current_station = Station.make(train.location);                          //train's current station
        stationLockMap.get(current_station).lock();                                      //lock the current station, because train is still here

        while (true) {
          if (doneTripCount.get() == mbta.tripsEntity.size()) {                          //all passengers arrive at their destinations
            total_lock.lock();
            doneTrainCount.getAndIncrement();                                            //one more train done

            condition.signalAll();
            total_lock.unlock();
            System.out.println("train " + train + " simulation is over");
            break;
          }
          Station next_station;                                                          //train's next moving position

          int station_position = stations.indexOf(Station.make(train.location));         //the index of train's current position

          if (train.direction.equals("left")) {                                          //when the train goes left
            if (train.location.equals(stations.get(0).toString())) {                     //train reaches the left border
              train.direction = "right";
              next_station = stations.get(1);
            } else {
              next_station = stations.get(station_position-1);
            }
          } else {                                                                       //when the train goes right
            if (train.location.equals(stations.get(stations.size()-1).toString())) {     //train reaches the right border
              train.direction = "left";
              next_station = stations.get(stations.size()-2);
            } else {
              next_station = stations.get(station_position+1);
            }
          }

          stationLockMap.get(next_station).lock();                                        //the next station is locking, because train is going there

          synchronized (train) {
            log.train_moves(train, current_station, next_station);

            train.location = next_station.toString();                                     //move train to the next station

            for (Passenger passenger : mbta.tripsEntity.keySet()) {

              if (passenger.train != null && passenger.train.equals(train)) {
                passenger.location = next_station.toString();
              }
            }

            train.notifyAll();
            stationLockMap.get(current_station).unlock();                                //unlock the current (previous) station
            current_station = next_station;                                               //update current station
          }


          try {
            Thread.sleep(500);
          } catch (InterruptedException e) {
            e.printStackTrace();
            throw new UnsupportedOperationException("something wrong with train thread's sleep");
          }
        }
      }).start();
    }                                                                                    //train's threads end bracket

    for (Passenger passenger : mbta.tripsEntity.keySet()) {
      new Thread(() -> {
        List<Station> stations = mbta.tripsEntity.get(passenger);                        //Passenger's all stations


        while (true) {
          Station current_station = Station.make(passenger.location);                    //passenger's current station

          if (passenger.train == null
                  && current_station.equals(stations.get(stations.size()-1))) {          //passenger arrive at the final destination
            total_lock.lock();
            doneTripCount.getAndIncrement();                                             //one more trip done

            condition.signalAll();
            total_lock.unlock();
            System.out.println(passenger + " arrives his/her destination");
            break;
          }

          int current_index = stations.indexOf(current_station);
          Station next_station = Station.make(stations.get(current_index+1).toString()); //passenger's next station

          Train train = null;                                                            //find a train

          for (Train temp_train : mbta.linesEntity.keySet()) {
            if (mbta.linesEntity.get(temp_train).contains(current_station)
                    && mbta.linesEntity.get(temp_train).contains(next_station)) {
              train = temp_train;
            }
          }


          synchronized (train) {
            if (passenger.train == null || !passenger.train.equals(train)) {
              while (!train.location.equals(current_station.toString())) {
                try {
                  train.wait();
                } catch (InterruptedException e) {
                  e.printStackTrace();
                  throw new UnsupportedOperationException("wait error");
                }
              }

              log.passenger_boards(passenger, train, current_station);
              passenger.train = train;
              train.notifyAll();
            } else {
              while (!train.location.equals(next_station.toString())) {
                try {
                  train.wait();
                } catch (InterruptedException e) {
                  e.printStackTrace();
                  throw new UnsupportedOperationException("wait error");
                }
              }

              log.passenger_deboards(passenger, train, next_station);
              passenger.train = null;
              train.notifyAll();
            }
          }
        }
      }).start();
    }                                                                                    //passenger's threads end bracket

    while (true) {
      total_lock.lock();
      try {
        if (doneTrainCount.get() == mbta.linesEntity.size()
                && doneTripCount.get() == mbta.tripsEntity.size()) {
          break;
        } else {
          condition.await();
        }
      } catch (Exception exception) {
        throw new UnsupportedOperationException("lock error");
      } finally {
        total_lock.unlock();
      }
    }

  }                                                                                      //simulation end bracket

  public static void main(String[] args) throws Exception {
//    if (args.length != 1) {
//      System.out.println("usage: ./sim <config file>");
//      System.exit(1);
//    }

    MBTA mbta = new MBTA();
//    mbta.loadConfig(args[0]);

    mbta.loadConfig("D:\\Tufts University\\First Semester\\CS121 Software Engineering\\p5\\p5-mbta\\sample.json");
    Log log = new Log();

    run_sim(mbta, log);

    String s = new LogJson(log).toJson();
    PrintWriter out = new PrintWriter("log.json");
    out.print(s);
    out.close();

    mbta.reset();
//    mbta.loadConfig(args[0]);
    mbta.loadConfig("D:\\Tufts University\\First Semester\\CS121 Software Engineering\\p5\\p5-mbta\\sample.json");
    Verify.verify(mbta, log);
  }
}
