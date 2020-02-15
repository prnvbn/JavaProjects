package spreadsheet;

import common.api.CellLocation;
import common.api.EvaluationContext;
import common.api.Expression;
import java.util.Set;

public class Number implements Expression {

  private Double no = 0.0;

  public Number(Double no) {
    this.no = no;
  }

  @Override
  public String toString() {
    return no.toString();
  }

  @Override
  public double evaluate(EvaluationContext context) {
    return no;
  }

  @Override
  public void findCellReferences(Set<CellLocation> dependencies) {

  }
}
