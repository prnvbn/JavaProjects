package simulation;

import java.util.PriorityQueue;
import java.util.Queue;

public class Simulation<S> {

  double currentTime;
  Queue<ScheduledEvent<S>> diary = new PriorityQueue<>();

  public Queue<ScheduledEvent<S>> getDiary() {
    return diary;
  }

  public double getCurrentTime() {
    return currentTime;
  }

  protected boolean stop() {
    return false;
  }

  public void schedule(Event<S> e, double offset) {
    diary.add(new ScheduledEvent<S>(offset + currentTime, e));
  }

  public void simulate() {
    while (!diary.isEmpty()) {
      ScheduledEvent<S> e = diary.poll();
      currentTime = e.getTime();
      if (stop()) {
        break;
      }
      e.invoke(this);
    }
  }

  
  public S getState(){
    return null;
  }

}
