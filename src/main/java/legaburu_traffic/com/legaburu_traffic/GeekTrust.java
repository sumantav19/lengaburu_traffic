package legaburu_traffic.com.legaburu_traffic;

import java.io.File;
import java.io.FileInputStream;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;

import legaburu_traffic.com.legaburu_traffic.domain.City;
import legaburu_traffic.com.legaburu_traffic.domain.TravelHistory;
import legaburu_traffic.com.legaburu_traffic.model.Orbit;
import legaburu_traffic.com.legaburu_traffic.model.Vehicle;
import legaburu_traffic.com.legaburu_traffic.model.Weather;

public class GeekTrust {

  public static void main(String[] args) throws Exception {
    File file = new File(args[0]);
    FileInputStream fileStream = new FileInputStream(file);
    Scanner sc = new Scanner(fileStream,"UTF-8");
    String line = sc.nextLine();
    String[] input = line.split(" ");

    Weather weather = Weather.valueOf(input[0]);
    int orbit1TrafficSpeed = Integer.parseInt(input[1]);
    int orbit2TrafficSpeed = Integer.parseInt(input[2]);
    City legaburu = new City();
    // int trafficSpeed =  10;
    Orbit oribit1= new Orbit(18, 20,orbit1TrafficSpeed, "ORBIT1");
    Orbit oribit2= new Orbit(20, 10,orbit2TrafficSpeed, "ORBIT2");
    
    legaburu.addOrbit("silkdorb", "halthiram", oribit1);
    legaburu.addOrbit("silkdorb", "halthiram", oribit2);

    LinkedList<Vehicle> vehicles =  new LinkedList<Vehicle>();

    Weather[] supportedWeathersBike = {Weather.SUNNY,Weather.WINDY};
    vehicles.add(new Vehicle("BIKE", 10, 2, supportedWeathersBike));

    Weather[] supportedWeathersTukTuk = {Weather.SUNNY,Weather.RAINY};
    vehicles.add(new Vehicle("TUKTUK", 12, 1, supportedWeathersTukTuk));

    Weather[] supportedWeathersCar = {Weather.SUNNY,Weather.RAINY,Weather.WINDY};
    vehicles.add(new Vehicle("CAR", 20, 3, supportedWeathersCar));

    Iterator<Vehicle> vehicleIterator =  vehicles.iterator();
    float minTime = Float.MAX_VALUE;
    String fastestPath = "";
    while(vehicleIterator.hasNext()){
      Vehicle currentVehicle =  vehicleIterator.next();
      TravelHistory travelHistory =  legaburu.travelCity("silkdorb", "halthiram", currentVehicle, weather);
      if(travelHistory.getTravelTime() < minTime ){
        fastestPath=travelHistory.getOrbits();
        minTime = travelHistory.getTravelTime();
      }
    }
    System.out.println(fastestPath);
    sc.close();
  }
}