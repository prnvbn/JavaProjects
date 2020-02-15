package museumvisit;

import java.util.Collections;
import java.util.List;

public class Exit extends MuseumSite {

  public Exit() {
    super("Exit");
  }

  @Override
  boolean hasAvailability() {
    return true;
  }

  @Override
  public List<Turnstile> getExitTurnstiles() {
    return Collections.emptyList();
  }
}
