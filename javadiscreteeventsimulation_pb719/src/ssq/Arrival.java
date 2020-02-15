package ssq;

import simulation.Event;


public class Arrival implements Event<SingleServerQueue> {

  private static final double SERVICE_TIME = 0.25;

  @Override
  public void invoke(SingleServerQueue simulation) {

    double generatedNo = SingleServerQueue.nextNumber();

    simulation.addToTimesList(simulation.getCurrentTime());
    simulation.incPopulation();

    System.out.println("Arrival at " + simulation.getCurrentTime() +
        ", new population = " + simulation.getPopulation());

    if (simulation.qIsEmpty()) {
      simulation.schedule(new Departure(), SERVICE_TIME);
    }
    simulation.schedule(new Arrival(), generatedNo);

  }


}
