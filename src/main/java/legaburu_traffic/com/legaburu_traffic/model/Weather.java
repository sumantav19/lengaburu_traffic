package legaburu_traffic.com.legaburu_traffic.model;

public enum Weather {
  SUNNY(-10),RAINY(+20),WINDY(0);
  public int craterGradient;
  private Weather(int craterGradient){
    this.craterGradient = craterGradient;
  }
}