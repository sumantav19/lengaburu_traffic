package legaburu_traffic.com.legaburu_traffic.domain;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.function.BiFunction;
import java.util.function.Function;

import legaburu_traffic.com.legaburu_traffic.model.Location;
import legaburu_traffic.com.legaburu_traffic.model.Orbit;
import legaburu_traffic.com.legaburu_traffic.model.Vehicle;
import legaburu_traffic.com.legaburu_traffic.model.Weather;

public class City {
  HashMap<String,LinkedList<Location>> cityMap;
  public City(){
     cityMap =  new HashMap<String,LinkedList<Location>>();
  }

  public HashMap<String, LinkedList<Location>> getCityMap() {
    return cityMap;
  }

  public static Function<String, LinkedList<Location>> computeIfAbsent(String destination, Orbit orbit){
    return locationName -> {
      LinkedList<Location> temp = new LinkedList<Location>();
      temp.add(new Location(destination,orbit));
      return temp; 
    };
  }
  public static BiFunction<String,LinkedList<Location>,LinkedList<Location>> computeIfPresent(String destination, Orbit orbit){
    return (locationName,locations)->{
      Location location = locations.stream()
      .filter(x->x.getName().equals(destination))
      .findFirst()
      .orElse(null);
      if(location !=null){
        location.addOrbit(orbit);
      }else{
        Location temp = new Location(destination, orbit);
        locations.add(temp);
      }
      return locations;
    };
  }

  public TravelHistory travelCity(String start,String destination,Vehicle vehicle,Weather weather){
    TravelHistory travelHistory = new TravelHistory(vehicle.getName(), 0.0f);
    return travelCity(start, destination, vehicle, weather,travelHistory);
  }
  public TravelHistory travelCity(String start,String destination,Vehicle vehicle,Weather weather,TravelHistory travelHistory){
    LinkedList<Location> adjacentLocation =  cityMap.get(start);
    Iterator adjLocIt = adjacentLocation.iterator();
    while(adjLocIt.hasNext()){
      Location currentLocation = (Location) adjLocIt.next();
      
      Iterator orbitIterator = currentLocation.getOrbits().iterator();
      float minTime = Float.MAX_VALUE;
      Orbit fastest = null;
      while(orbitIterator.hasNext()){
        Orbit currentOrbit = (Orbit) orbitIterator.next();
        float currentTime =currentOrbit.travelOnOrbit(vehicle, weather);
        if(currentTime <= minTime){
          minTime = currentTime;
          fastest=currentOrbit;
        }
      }
      travelHistory.addOrbit(fastest.getName());
      travelHistory.addTravelTime(minTime);
      if(currentLocation.getName().equals(destination)){
        break;
      }
      travelHistory = travelCity(currentLocation.getName(), destination, vehicle, weather,travelHistory);
    }
    return travelHistory;
  }

  public void addOrbit(String start,String destination, Orbit orbit){
    cityMap.computeIfPresent(start, computeIfPresent(destination,orbit));
    cityMap.computeIfAbsent(start,computeIfAbsent(destination, orbit));
  }

  
   
  
  
}