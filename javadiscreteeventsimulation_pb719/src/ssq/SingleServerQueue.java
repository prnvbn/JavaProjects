package ssq;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import simulation.Simulation;


public class SingleServerQueue extends Simulation<SingleServerQueue> {

  private int population;
  private static List<Integer> populationList;
  private static List<Double> timeList;
  public static Random generator;
  private double runTime;

  public SingleServerQueue(long seed) {
    generator = new Random(seed);
    population = 0;
    populationList = new ArrayList<>();
    timeList = new ArrayList<>();
  }

  public void setRunTime(double runTime) {
    this.runTime = runTime;
  }

  public int getPopulation() {
    return population;
  }

  public boolean qIsEmpty() {
    return getDiary().size() == 0;
  }

  public void incPopulation() {
    populationList.add(population);
    population++;
  }

  public void decPopulation() {
    populationList.add(population);
    population--;
  }

  public static double nextNumber() {
    return generator.nextDouble();
  }

  public void addToTimesList(double time) {
    timeList.add(time);
  }

  @Override
  protected boolean stop() {
    return getCurrentTime() >= runTime;
  }

  @Override
  public SingleServerQueue getState() {
    return this;
  }

  public Double getMeanQueueLength() {

    double diff = 0.0;

    ArrayList<Double> durationTimes = new ArrayList<>();
    durationTimes.add(timeList.get(0));

    int size = timeList.size();
    for (int i = 1; i < size; i++) {
      diff = timeList.get(i) - timeList.get(i - 1);
      durationTimes.add(diff);
    }

    double mean = 0.0;
    for (int i = 0; i < size; i++) {
      mean += (durationTimes.get(i) * populationList.get(i));
    }
    mean +=
        (runTime - timeList.get(size - 1)) * (getPopulation());
    return mean / runTime;
  }


  public static void main(String[] args) {

    assert args.length == 2 : "There should be only 2 input arguments";

    long seed = Long.parseLong(args[0]);
    double runtime = Double.parseDouble(args[1]);

    SingleServerQueue ssq = new SingleServerQueue(seed);
    ssq.setRunTime(runtime);

    ssq.schedule(new Arrival(), nextNumber());
    ssq.simulate();
    System.out.println("SIMULATION COMPLETE - the mean queue length was "
        + ssq.getMeanQueueLength());

  }
}
