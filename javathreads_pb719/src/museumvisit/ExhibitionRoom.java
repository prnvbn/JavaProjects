package museumvisit;

public class ExhibitionRoom extends MuseumSite {

  private int capacity;

  public ExhibitionRoom(String name, int capacity) {
    super(name);
    assert capacity > 0 : "The capacity has to be a positive integer";
    this.capacity = capacity;
  }

  public int getCapacity() {
    return capacity;
  }

  @Override
  synchronized boolean hasAvailability() {
    return occupancy < capacity;
  }
}
