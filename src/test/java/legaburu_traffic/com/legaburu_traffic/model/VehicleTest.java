package legaburu_traffic.com.legaburu_traffic.model;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.Assert.*;

public class VehicleTest {
  
  @Test public void testVehicleRun(){
    Weather[] supportedWeathers = {Weather.SUNNY,Weather.RAINY};
    Vehicle vehicle = new Vehicle("TUK TUK",12,1,supportedWeathers);    
    assertEquals("given vehicle should travel in 130",130, vehicle.run(20, 10, Weather.RAINY, 10),0.0);
    assertEquals("given vehicle should travel in 110",109, vehicle.run(20, 9, Weather.SUNNY, 12),0.0);
    
    assertEquals("given unsupported weather vehicle should return max value",Float.MAX_VALUE, vehicle.run(10, 10, Weather.WINDY, 10),0.0);
  }
}