package simulation;

import simulation.Simulation;

public interface Event<S> {

  void invoke(S simulation);
}
