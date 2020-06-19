package legaburu_traffic.com.legaburu_traffic.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.LinkedList;

import org.junit.Test;
import org.junit.Assert.*;

import legaburu_traffic.com.legaburu_traffic.model.Location;
import legaburu_traffic.com.legaburu_traffic.model.Orbit;
import legaburu_traffic.com.legaburu_traffic.model.Vehicle;
import legaburu_traffic.com.legaburu_traffic.model.Weather;

public class CityTest {

  @Test public void testCityAddOrbit(){
    City city  = new City();
    Orbit orbit1 = new Orbit(20,10,40,"ORBIT1");
    Orbit orbit2 = new Orbit(18,20,25,"ORBIT2");
    Orbit orbit3 = new Orbit(20,10,40,"ORBIT3");
    Orbit orbit4 = new Orbit(20,10,25,"ORBIT4");
    city.addOrbit("Silkdorb", "Halthiram", orbit1);
    city.addOrbit("Silkdorb", "Halthiram", orbit2);
    city.addOrbit("Silkdorb", "Mormangal", orbit3);
    city.addOrbit("Silkdorb", "Mormangal", orbit4);


    HashMap <String,LinkedList<Location>> actualMap =  city.getCityMap(); 
    LinkedList<Location> actualList=  actualMap.get("Silkdorb");
    Location halthiram = actualList.stream()
    .filter(x->x.getName().equals("Halthiram"))
    .findFirst()
    .orElse(null);
    assertTrue("can reach halthiram from silkdorb","Halthiram".equals(halthiram.getName()));
    LinkedList<Orbit> orbitsHalthiram = halthiram.getOrbits();
    // intentionally checking the reference as orbits are created in same scope
    assertTrue("orbit1 is present between silkdorb and halthiram",orbitsHalthiram.contains(orbit1));
    assertTrue("orbit2 is present between silkdorb and halthiram",orbitsHalthiram.contains(orbit2));

    Location mormangal = actualList.stream()
    .filter(x->x.getName().equals("Mormangal"))
    .findFirst()
    .orElse(null);
    assertTrue("can reach mormangal from silkdorb","Mormangal".equals(mormangal.getName()));
    LinkedList<Orbit> orbitsMormangal = mormangal.getOrbits();
    // intentionally checking the reference as orbits are created in same scope
    assertTrue("orbit3 is present between silkdorb and mormangal",orbitsMormangal.contains(orbit3));
    assertTrue("orbit4 is present between silkdorb and mormangal",orbitsMormangal.contains(orbit4));
    
  }
  

  @Test public void testCityGetCityTravelTime(){
    City city  = new City();
    Orbit orbit1 = new Orbit(20,10,10,"ORBIT1");
    Orbit orbit2 = new Orbit(18,20,10,"ORBIT2");
    // Orbit orbit3 = new Orbit(20,10,40);
    // Orbit orbit4 = new Orbit(20,10,25);
    city.addOrbit("Silkdorb", "Halthiram", orbit1);
    city.addOrbit("Silkdorb", "Halthiram", orbit2);
    // city.addOrbit("Halthiram", "Mormangal", orbit3);
    // city.addOrbit("Halthiram", "Mormangal", orbit4);
    Weather[] supportedWeathers = {Weather.SUNNY,Weather.RAINY};
    Vehicle vehicle = new Vehicle("TUK TUK",12,1,supportedWeathers);
    // Vehicle superCar = new Vehicle("Super Car",20,3,supportedWeathers);
    TravelHistory  travelHistory1  = city.travelCity("Silkdorb", "Halthiram", vehicle, Weather.RAINY);
    assertEquals("should return total time taken to travel is 128",132.0,travelHistory1.getTravelTime(),0.0);
    assertEquals("should return vehicle name and orbits taken","TUK TUK ORBIT2",travelHistory1.getOrbits());

    TravelHistory  travelHistory2  = city.travelCity("Silkdorb", "Halthiram", vehicle, Weather.WINDY);
    assertEquals("should return max time taken if unsupported weather",Float.MAX_VALUE,travelHistory2.getTravelTime(),0.0);
    // assertEquals("",150,city.getCityTravelTime("Silkdorb", "Halthiram", superCar, Weather.RAINY,10),0.0);
    // assertEquals("",300,city.getCityTravelTime("Silkdorb", "Mormangal", superCar, Weather.RAINY,10),0.0);
  }
}