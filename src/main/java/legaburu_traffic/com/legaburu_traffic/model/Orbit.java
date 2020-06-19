package legaburu_traffic.com.legaburu_traffic.model;

public class Orbit {
  private int length;
  private int numberOfCraters;
  private int trafficSpeed;
  private String name;

  public Orbit(int length, int numberOfCraters,int trafficSpeed,String name) {
    this.length= length;
    this.numberOfCraters=numberOfCraters;
    this.trafficSpeed = trafficSpeed;
    this.name = name;
  }

  public int getNumberOfCraters() {
    return numberOfCraters;
  }

  public int getLength() {
    return length;
  }

  public int getTrafficSpeed(){
    return trafficSpeed;
  }
  public String getName(){
    return this.name;
  }

  public float travelOnOrbit(Vehicle vehicle, Weather weather){
    float crater =  this.numberOfCraters+ weather.craterGradient*this.numberOfCraters/100;
    return vehicle.run(this.length, crater,weather, this.trafficSpeed);
  }
}