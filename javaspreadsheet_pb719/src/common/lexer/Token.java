package common.lexer;

import static common.lexer.Token.Kind.CELL_LOCATION;
import static common.lexer.Token.Kind.NUMBER;

import common.api.CellLocation;
import java.util.Objects;

/**
 * Representation of a token from the input string.
 *
 * <p>If `kind` if NUMBER or CELL_LOCATION, then the represented value may be
 * found respectively in `numberValue` and `cellLocationValue`.
 */
public class Token {

  public final Kind kind;
  public final CellLocation cellLocationValue;
  public final double numberValue;

  Token(Kind kind) {
    this(kind, null, 0);
    assert (kind != NUMBER && kind != CELL_LOCATION);
  }

  Token(double value) {
    this(NUMBER, null, value);
  }

  Token(CellLocation cellLocation) {
    this(CELL_LOCATION, cellLocation, 0);
  }

  private Token(Kind kind, CellLocation cellLocationValue, double numberValue) {
    this.kind = kind;
    this.cellLocationValue = cellLocationValue;
    this.numberValue = numberValue;
  }


  @Override
  public String toString() {
    switch (kind) {
      case CELL_LOCATION:
        return "CELL(" + cellLocationValue.toString() + ")";
      case NUMBER:
        return "NUMBER(" + numberValue + ")";
      default:
        return kind.name();
    }
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    Token other = (Token) obj;
    if (this.kind != other.kind) {
      return false;
    }
    switch (this.kind) {
      case NUMBER:
        return this.numberValue == other.numberValue;
      case CELL_LOCATION:
        return this.cellLocationValue.equals(other.cellLocationValue);
      default:
        return true;
    }
  }

  @Override
  public int hashCode() {
    switch (kind) {
      case NUMBER:
        return Objects.hash(kind, numberValue);
      case CELL_LOCATION:
        return Objects.hash(kind, cellLocationValue);
      default:
        return Objects.hash(kind);
    }
  }

  /* The reason a map was not used for getting the order is that this
     implementation is O(1) and is more readable, also the map would have to have
     global visibility which is undesirable */
  public enum Kind {

    CARET(Associativity.R, 2) {
      @Override
      public String toString() {
        return "^";
      }

    },
    SLASH(Associativity.L, 3) {
      @Override
      public String toString() {
        return "/";
      }
    },
    STAR(Associativity.L, 3) {
      @Override
      public String toString() {
        return "*";
      }
    },
    PLUS(Associativity.L, 4) {
      @Override
      public String toString() {
        return "+";
      }
    },
    MINUS(Associativity.L, 4) {
      @Override
      public String toString() {
        return "-";
      }
    },
    LPARENTHESIS(Associativity.L, 1) {
      @Override
      public String toString() {
        return "(";
      }
    },
    RPARENTHESIS(Associativity.R, 5) {
      @Override
      public String toString() {
        return ")";

      }
    },
    LANGLE(Associativity.NA, null),
    RANGLE(Associativity.NA, null),
    EQUALS(Associativity.NA, null),
    NUMBER(Associativity.NA, null),
    CELL_LOCATION(Associativity.NA, null);

    Associativity associativity;
    Integer order;

    Kind(Associativity associativity, Integer order) {
      this.associativity = associativity;
      this.order = order;
    }

    public Associativity getAssociativity() {
      return associativity;
    }

    public int getOrder() {
      return order;
    }

    public enum Associativity {
      NA, L, R;
    }
  }

}
