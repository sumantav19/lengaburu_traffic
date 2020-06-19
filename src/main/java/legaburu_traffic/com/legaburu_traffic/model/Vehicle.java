package legaburu_traffic.com.legaburu_traffic.model;

import java.util.Arrays;
import java.util.List;

public class Vehicle {
  private String name;
  private float speed;
  private int craterTime;
  private List supportedWeathers;

  public Vehicle(String name, int speed, int craterTime, Weather[] supportedWeathers) {
    this.name = name;
    this.speed = speed;
    this.craterTime = craterTime;
    this.supportedWeathers = Arrays.asList(supportedWeathers);
  }

  public float run(float distance, float craters, Weather weather, float trafficSpeed) {
    float speed = this.speed >= trafficSpeed ? trafficSpeed : this.speed;
    if (supportedWeathers.contains(weather)) {
      return (distance / speed * 60) + craters * this.craterTime;
    }
    return Float.MAX_VALUE;
  }

  public String getName() {
    return name;
  }

}