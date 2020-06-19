package legaburu_traffic.com.legaburu_traffic.model;

import java.util.LinkedList;

public class Location {
  private String name;
  private LinkedList<Orbit> orbits;

  public Location(String name,Orbit orbit){
    this.name = name;
    this.orbits = new LinkedList<Orbit>();
    this.orbits.add(orbit);
  }

  public void addOrbit(Orbit orbit){
    this.orbits.add(orbit);
  } 

  public String getName(){
    return this.name;
  }

  public LinkedList<Orbit>  getOrbits(){
    return orbits;
  }
}