package legaburu_traffic.com.legaburu_traffic.domain;

public class TravelHistory {
  private StringBuilder orbits;
  private float travelTime;

  public TravelHistory(String orbits,float travelTime){
    this.orbits=new StringBuilder(orbits);
    this.travelTime=travelTime;
  }
  public void addOrbit(String orbitName){
    orbits.append(" "+orbitName);
  }
  public float getTravelTime(){
    return travelTime;
  }

  public void addTravelTime(float travelTime){
    this.travelTime+=travelTime;
  }
  public String getOrbits(){
    return orbits.toString();
  }

  
}