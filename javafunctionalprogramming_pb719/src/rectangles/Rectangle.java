package rectangles;

import java.util.Optional;
import java.util.stream.Stream;

// Another way to represent rectangle could have been to use topLeft and bottomLeft as fields

public class Rectangle {

  private final Point topLeft;
  private final int width;
  private final int height;

  public Rectangle(Point topLeft, int width, int height) {
    this.topLeft = topLeft;
    this.width = width;
    this.height = height;
  }

  public Rectangle(Point p1, Point p2) {
    this.topLeft = new Point(Math.min(p1.getX(), p2.getX()),
        Math.min(p1.getY(), p2.getY()));
    this.width = Math.abs(p1.getX() - p2.getX());
    this.height = Math.abs(p1.getY() - p2.getY());
  }

  public Rectangle(int w, int h) {
    this(new Point(), w, h);
  }

  public int getWidth() {
    return width;
  }

  public int getHeight() {
    return height;
  }

  public Rectangle setWidth(int newWidth) {
    assert newWidth >= 0 : "the width has to be non negative ";
    return new Rectangle(getTopLeft(), newWidth, getHeight());
  }

  public Rectangle setHeight(int newHeight) {
    assert newHeight >= 0 : "the height has to be non negative ";
    return new Rectangle(getTopLeft(), getWidth(), newHeight);
  }

  public Point getTopLeft() {
    return topLeft;
  }

  public Point getTopRight() {
    return topLeft.add(new Point(width, 0));
  }

  public Point getBottomLeft() {
    return topLeft.add(new Point(0, height));
  }

  public Point getBottomRight() {
    return topLeft.add(new Point(width, height));
  }

  public int area() {
    return height * width;
  }

  public boolean intersects(Rectangle other) {
    return
        !(topLeft.getX() > other.getBottomRight().getX()   // R1 is right to R2
        || getBottomRight().getX() < other.topLeft.getX()  // R1 is left to R2
        || topLeft.getY() > other.getBottomRight().getY()  // R1 is above R2
        || getBottomRight().getY() < other.topLeft.getY());// R1 is below R2
  }

  public Optional<Rectangle> intersection(Rectangle other) {

    Optional<Rectangle> intersection = Optional.empty();

    if (intersects(other)) {
      Point otherTopLeft = other.getTopLeft();
      Point newTopLeft =
          getTopLeft().isRightOf(otherTopLeft)
              ? getTopLeft()
              : otherTopLeft;

      Point otherBottomRight = other.getBottomRight();
      Point newBottomRight =
          getBottomRight().isAbove(otherBottomRight)
              ? getBottomRight()
              : otherBottomRight;

      intersection = Optional.of(new Rectangle(newTopLeft, newBottomRight));
    }

    return intersection;
  }


}
