package rectangles;

public class Point {

  private final int x;
  private final int y;


  public Point(int x, int y) {
    this.x = x;
    this.y = y;
  }

  public Point(int x) {
    this(x, 0);
  }

  public Point() {
    this(0, 0);
  }

  public int getY() {
    return y;
  }

  public int getX() {
    return x;
  }

  public Point setX(int newX) {
    return new Point(newX, getY());
  }

  public Point setY(int newY) {
    return new Point(getX(), newY);
  }

  public boolean isLeftOf(Point other) {
    return getX() < other.getX();
  }

  public boolean isRightOf(Point other) {
    return getX() > other.getX();
  }

  public boolean isBelow(Point other) {
    return getY() > other.getY();
  }

  public boolean isAbove(Point other) {
    return getY() < other.getY();
  }

  public Point add(Point vector) {
    return new Point(getX() + vector.getX(), getY() + vector.getY());
  }
}
