package spreadsheet;

import common.api.CellLocation;
import common.api.EvaluationContext;
import java.util.HashMap;
import java.util.Map;

public class Spreadsheet implements EvaluationContext {


  private Map<CellLocation, Double> sheet = new HashMap<>();

  /**
   * Construct an empty spreadsheet.
   *
   * <p>DO NOT CHANGE THE SIGNATURE. The test suite depends on this.
   */
  Spreadsheet() {
  }

  /**
   * Parse and evaluate an expression, using the spreadsheet as a context.
   *
   * <p>DO NOT CHANGE THE SIGNATURE. The test suite depends on this.
   */
  public double evaluateExpression(String expression)
      throws InvalidSyntaxException {

    double value = 0.0;

    try {
      value = Parser.parse(expression).evaluate(this);
    } catch (InvalidSyntaxException e) {
      System.out.println(e.getMessage());
    }

    return value;
  }

  /**
   * Assign an expression to a cell.
   *
   * <p>DO NOT CHANGE THE SIGNATURE. The test suite depends on this.
   */
  public void setCellExpression(CellLocation location, String input)
      throws InvalidSyntaxException {
    sheet.put(location, evaluateExpression(input));
  }

  @Override
  public double getCellValue(CellLocation location) {
    return sheet.getOrDefault(location, 0.0);
  }
}
