package ssq;

import simulation.Event;


public class Departure implements Event<SingleServerQueue> {

  private static final double SERVICE_TIME = 0.25;


  @Override
  public void invoke(SingleServerQueue simulation) {

    simulation.addToTimesList(simulation.getCurrentTime());


    simulation.decPopulation();
    int population = simulation.getPopulation();

    if (population > 0) {
      simulation.schedule(new Departure(), SERVICE_TIME);
    }

    System.out.println("Departure at " + simulation.getCurrentTime() +
        ", new population = " + population);

  }
}
