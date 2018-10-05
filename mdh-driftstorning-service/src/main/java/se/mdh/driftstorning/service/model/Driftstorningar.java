package se.mdh.driftstorning.service.model;

import java.util.List;

public class Driftstorningar {
  private List<Driftstorning> driftstorningar;

  public Driftstorningar() {
  }

  public List<Driftstorning> getDriftstorningar() {
    return driftstorningar;
  }

  public void setDriftstorningar(List<Driftstorning> driftstorningar) {
    this.driftstorningar = driftstorningar;
  }

  @Override
  public String toString() {
    return "Driftstorningar{" +
        "driftstorningar=" + driftstorningar +
        '}';
  }
}
