package spreadsheet;


import common.api.CellLocation;
import common.api.EvaluationContext;
import common.api.Expression;
import common.lexer.Token.Kind;
import java.util.Set;

public class BinOp implements Expression {


  private Expression exp1;
  private Expression exp2;
  private Kind op;

  public BinOp(Expression exp1, Expression exp2, Kind op) {

    this.exp1 = exp1;
    this.exp2 = exp2;
    this.op = op;
  }


  @Override
  public String toString() {
    return "(" + exp1 + " " + op + " " + exp2 + ")";
  }

  @Override
  public double evaluate(EvaluationContext context) {
    switch (op) {
      case SLASH:
        return exp1.evaluate(context) / exp2.evaluate(context);
      case STAR:
        return exp1.evaluate(context) * exp2.evaluate(context);
      case CARET:
        return Math.pow(exp1.evaluate(context), exp2.evaluate(context));
      case MINUS:
        return exp1.evaluate(context) - exp2.evaluate(context);
      case PLUS:
        return exp1.evaluate(context) + exp2.evaluate(context);
    }
    return 0;
  }

  @Override
  public void findCellReferences(Set<CellLocation> dependencies) {
    exp1.findCellReferences(dependencies);
    exp2.findCellReferences(dependencies);
  }

}





