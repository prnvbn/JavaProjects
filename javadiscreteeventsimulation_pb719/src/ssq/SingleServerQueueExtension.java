package ssq;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import simulation.Simulation;


public class SingleServerQueueExtension extends
    Simulation<SingleServerQueue> {

  private int population;
  public static Random generator;

  private int services;

  public SingleServerQueueExtension(long seed) {
    generator = new Random(seed);
    population = 0;
  }

  public void setServices(int services) {
    this.services = services;
  }

  public int getPopulation() {
    return population;
  }

  public boolean qIsEmpty() {
    return getDiary().size() == 0;
  }

  public void incPopulation() {
    population++;
  }

  public void decPopulation() {
    population--;
  }

  public static double nextNumber() {
    return generator.nextDouble();
  }


  @Override
  protected boolean stop() {
    return getCurrentTime() >= runTime;
  }

  @Override
  public SingleServerQueueExtension getState() {
    return this;
  }



  public static void main(String[] args) {

    assert args.length == 2 : "There should be only 2 input arguments";

    long seed = Long.parseLong(args[0]);
    int services = Integer.parseInt(args[1]);

    SingleServerQueueExtension ssq = new SingleServerQueueExtension(seed);
    ssq.setServices(services);

    ssq.schedule(new Arrival(), nextNumber());
    ssq.simulate();
    System.out.println("SIMULATION COMPLETE");

  }
}
