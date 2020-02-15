package museumvisit;

import java.util.List;

public class Entrance extends MuseumSite {

  public Entrance() {
    super("Entrance");
  }

  @Override
  boolean hasAvailability() {
    return true;
  }

  @Override
  public List<Turnstile> getExitTurnstiles() {
    return super.getExitTurnstiles();
  }

}
