package museumvisit;

import java.util.Optional;

public class Turnstile {

  private final MuseumSite originRoom;
  private final MuseumSite destinationRoom;

  public Turnstile(MuseumSite originRoom, MuseumSite destinationRoom) {
    assert !originRoom.equals(destinationRoom)
        : "The destination and origin rooms must be different";
    this.originRoom = originRoom;
    this.destinationRoom = destinationRoom;
  }

  public Optional<MuseumSite> passToNextRoom() {

    MuseumSite firstRoomToLock;
    MuseumSite secondRoomToLock;

    //Adding a universal ordering to prevent race conditions
    if (originRoom.hashCode() < destinationRoom.hashCode()) {
      firstRoomToLock = originRoom;
      secondRoomToLock = destinationRoom;
    } else {
      firstRoomToLock = destinationRoom;
      secondRoomToLock = originRoom;
    }

    synchronized (firstRoomToLock) {
      synchronized (secondRoomToLock) {
        if (destinationRoom.hasAvailability()) {
          originRoom.exit();
          destinationRoom.enter();
          return Optional.of(destinationRoom);
        }
        return Optional.empty();
      }
    }
  }

  public MuseumSite getOriginRoom() {
    return originRoom;
  }

  public MuseumSite getDestinationRoom() {
    return destinationRoom;
  }
}
