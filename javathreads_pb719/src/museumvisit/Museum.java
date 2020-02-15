package museumvisit;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.IntStream;

public class Museum {

  private final Entrance entrance;
  private final Exit exit;
  private final Set<MuseumSite> sites;

  public Museum(Entrance entrance, Exit exit, Set<MuseumSite> sites) {
    this.entrance = entrance;
    this.exit = exit;
    this.sites = sites;
  }

  public MuseumSite getMostVisitedRoom() {

    MuseumSite mostPopular = null;
    mostPopular = sites.stream()
        .reduce((s1, s2) -> s1.getNoOfVisits() > s2.getNoOfVisits() ? s1 : s2)
        .get();

    return mostPopular;
  }

  public static void main(String[] args) {
    final int numberOfVisitors = 100;
    final Museum museum = buildLoopyMuseum();

    // create the threads for the visitors and get them moving
    List<Thread> visitors = new ArrayList<>();
    IntStream.range(0, numberOfVisitors)
        .sequential()
        .forEach(
            i -> {
              Thread visitorThread = new Thread(
                  new Visitor("Vis" + i, museum.getEntrance()));
              visitors.add(visitorThread);
              visitorThread.start();
            });

    // wait for them to complete their visit
    visitors.forEach(v -> {
      try {
        v.join();
      } catch (InterruptedException e) {
      }
    });

    // Checking no one is left behind
    if (museum.getExit().getOccupancy() == numberOfVisitors) {
      System.out.println("\nAll the visitors reached the exit\n");
    } else {
      System.out.println(
          "\n"
              + (numberOfVisitors - museum.getExit().getOccupancy())
              + " visitors did not reach the exit. Where are they?\n");
    }

    System.out.println(
        "Occupancy status for each room (should all be zero, but the exit site):");
    museum
        .getSites()
        .forEach(
            s -> {
              System.out.println(
                  "Site " + s.getName() + " final occupancy: " + s
                      .getOccupancy());
            });

    MuseumSite mostPopular = museum.getMostVisitedRoom();

    System.out
        .println("The most popular site was " + mostPopular.getName() + ". "
            + "It had " + mostPopular.getNoOfVisits() + " visits!");
  }

  public static Museum buildSimpleMuseum() {

    Entrance entrance = new Entrance();
    Exit exit = new Exit();
    ExhibitionRoom room = new ExhibitionRoom("room", 10);
    Set<MuseumSite> sites = new HashSet<>();

    // Adding Turnstiles
    entrance.addExitTurnstile(room);
    room.addExitTurnstile(exit);

    sites.add(room);
    return new Museum(entrance, exit, sites);
  }

  public static Museum buildLoopyMuseum() {
    Entrance entrance = new Entrance();
    Exit exit = new Exit();
    ExhibitionRoom venomRoom =
        new ExhibitionRoom("Venom KillerAndCure Room", 10);
    ExhibitionRoom whalesRoom =
        new ExhibitionRoom("Whales Exhibition Room", 10);

    Set<MuseumSite> sites = new HashSet<>();

    // Adding Turnstiles
    entrance.addExitTurnstile(venomRoom);
    venomRoom.addExitTurnstile(whalesRoom);
    whalesRoom.addExitTurnstile(venomRoom);
    venomRoom.addExitTurnstile(exit);

    sites.add(venomRoom);
    sites.add(whalesRoom);
    return new Museum(entrance, exit, sites);
  }

  public static Museum buildEmptyMuseum() {
    Entrance entrance = new Entrance();
    Exit exit = new Exit();
    Set<MuseumSite> sites = new HashSet<>();

    entrance.addExitTurnstile(exit);

    return new Museum(entrance, exit, sites);
  }


  public Entrance getEntrance() {
    return entrance;
  }

  public Exit getExit() {
    return exit;
  }

  public Set<MuseumSite> getSites() {
    return sites;
  }
}
