package ticks;

import simulation.Event;
import simulation.Simulation;

public class Tick implements Event<Ticks> {

  @Override
  public void invoke(Ticks simulation) {

//  Uncommment the following lines so that the ticks are displayed
//  like the ticks of an actual clock

//    try {
//      Thread.sleep(1000);
//    } catch (InterruptedException e) {
//      e.printStackTrace();
//    }
    double time = simulation.getCurrentTime();

    System.out.println("Tick at: " + time);
    simulation.schedule(new Tick(), 1);
  }
}
