package spreadsheet;

import static common.lexer.Token.Kind.CARET;
import static common.lexer.Token.Kind.LPARENTHESIS;
import static common.lexer.Token.Kind.MINUS;
import static common.lexer.Token.Kind.PLUS;
import static common.lexer.Token.Kind.RPARENTHESIS;
import static common.lexer.Token.Kind.SLASH;
import static common.lexer.Token.Kind.STAR;

import common.api.Expression;
import common.lexer.InvalidTokenException;
import common.lexer.Lexer;
import common.lexer.Token;
import common.lexer.Token.Kind;
import common.lexer.Token.Kind.Associativity;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class Parser {

  private static final List<Kind> POSSIBLE_OPS =
      Arrays
          .asList(PLUS, MINUS, STAR, SLASH, CARET, LPARENTHESIS, RPARENTHESIS);

  /**
   * Parse a string into an Expression.
   *
   * <p>DO NOT CHANGE THE SIGNATURE. The test suite depends on this.
   */
  static Expression parse(String input) throws InvalidSyntaxException {

    Stack<Expression> operand = new Stack<>();
    Stack<Kind> operators = new Stack<>();

    Lexer lexer = new Lexer(input);
    Token token;

    /*tokenize() was not used as it is easier to get rid of the unwanted tokens
    inside the while loop */
    while (true) {
      try {
        token = lexer.nextToken();
      } catch (InvalidTokenException e) {
        throw new InvalidSyntaxException(e.getMessage());
      }
      if (token == null) {
        break;
      }

      Kind tokenKind = token.kind;

      if (tokenKind == Kind.NUMBER) {
        operand.push(new Number(token.numberValue));
      } else if (tokenKind == Kind.CELL_LOCATION) {
        operand.push(new CellReference(token.cellLocationValue));
      } else if (POSSIBLE_OPS.contains(tokenKind)) {

        if (!operators.isEmpty()) {
          Kind topOp = operators.peek();

          if ((tokenKind.getOrder() == topOp.getOrder()
              && topOp.getAssociativity() == Associativity.L) ||
              tokenKind.getOrder() > topOp.getOrder()) {
            Expression exp2 = operand.pop();
            Expression exp1 = operand.pop();

            //Instead of topOp, operator.pop() is used because topOp
            // was only peeking and not removing something from the stack
            BinOp binOp = new BinOp(exp1, exp2, operators.pop());
            operand.push(binOp);
          }
        }
        operators.push(tokenKind);
      }

    }

    while (!operators.isEmpty()) {
      Expression exp2 = operand.pop();
      Expression exp1 = operand.pop();

      BinOp binOp = new BinOp(exp1, exp2, operators.pop());
      operand.push(binOp);
    }

    return operand.pop();
  }


}
