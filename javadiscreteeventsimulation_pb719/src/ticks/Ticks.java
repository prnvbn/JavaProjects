package ticks;

import simulation.Simulation;

public class Ticks extends Simulation<Ticks> {

  //Added a runTime field to have an appropriate stop condition
  //The only reason this field is present is if we want to change the stop condition
  private double runTime;

  public Ticks(double runTime) {
    this.runTime = runTime;
  }

  @Override
  protected boolean stop() {
    return getCurrentTime() >= runTime;
  }

  @Override
  public Ticks getState() {
    return this;
  }

  public static void main(String[] args) {

    assert args.length == 1 : "There should only be one input argument";

    double runtime = Double.parseDouble(args[0]);

    Ticks t = new Ticks(runtime);

    t.schedule(new Tick(), 1);

    t.simulate();
  }
}

