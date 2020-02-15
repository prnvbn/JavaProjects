package simulation;

public class ScheduledEvent<S> implements Comparable<ScheduledEvent> {

  private double time;
  private Event event;


  public Event getEvent() {
    return event;
  }


  public ScheduledEvent(double time, Event event) {
    this.time = time;
    this.event = event;
  }

  @Override
  public int compareTo(ScheduledEvent scheduledEvent) {

    return Double.compare(time, scheduledEvent.getTime());
  }

  public double getTime() {
    return time;
  }

  public void invoke(Simulation simulation) {
    event.invoke(simulation);
  }
}
