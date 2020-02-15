package museumvisit;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class MuseumSite {

  protected final String name;
  //A better implementation would be to make occupancy an AtomicInt
  protected int occupancy;
  protected List<Turnstile> exitTurnstiles;
  protected int noOfVisits = 0;

  public MuseumSite(String name) {
    this.name = name;
    this.occupancy = 0;
    this.exitTurnstiles = new ArrayList<>();
  }

  boolean hasAvailability() {
    return true;
  }

  synchronized public void enter() {
    occupancy++;
  }

  synchronized public void exit() {
    assert occupancy > 0 : "occupancy can not be " + occupancy;
    occupancy--;
  }

  public void addExitTurnstile(MuseumSite destination) {
    exitTurnstiles.add(new Turnstile(this, destination));
  }

  public List<Turnstile> getExitTurnstiles() {
    return new ArrayList<>(exitTurnstiles);
  }

  public String getName() {
    return name;
  }

  public int getOccupancy() {
    return occupancy;
  }

  public int getNoOfVisits() {
    return noOfVisits;
  }

  public void incrementNoOfVisits() {
    noOfVisits++;
  }


  @Override
  public String toString() {
    return "Site[" + name + "]";
  }

  @Override
  public boolean equals(Object obj) {
    if (obj instanceof MuseumSite) {
      return name.equals(((MuseumSite) obj).name);
    } else {
      return false;
    }
  }

  @Override
  public int hashCode() {
    return Objects.hash(getClass(), name);
  }
}
