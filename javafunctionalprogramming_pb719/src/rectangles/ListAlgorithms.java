package rectangles;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListAlgorithms {

  /**
   * Returns a new list of rectangles by translating (moving) each rectangle
   * according to the given distance vector.
   *
   * @param rectangles The rectangles to be translated
   * @param vector     The distance vector
   * @return The translated rectangles
   */
  public static List<Rectangle> translate(List<Rectangle> rectangles,
      Point vector) {
    List<Rectangle> translatedRectangles = new ArrayList<>();

    for (Rectangle r : rectangles) {
      translatedRectangles
          .add(new Rectangle(r.getTopLeft().add(vector), r.getWidth(),
              r.getHeight()));
    }
    return translatedRectangles;
  }

  /**
   * Returns a new list of rectangles by scaling each rectangle by a given
   * amount.
   *
   * @param rectangles The rectangles to be scaled
   * @param factor     A non-negative scale factor
   * @return The scaled rectangles
   */
  public static List<Rectangle> scale(List<Rectangle> rectangles, int factor) {
    List<Rectangle> scaledRectangles = new ArrayList<>();

    for (Rectangle r : rectangles) {
      scaledRectangles
          .add(new Rectangle(r.getTopLeft(), r.getWidth() * factor,
              r.getHeight() * factor));
    }
    return scaledRectangles;
  }

  /**
   * Returns a list containing, in order, the bottom-left point of each input
   * rectangle.
   */
  public static List<Point> getBottomLeftPoints(List<Rectangle> rectangles) {
    List<Point> bottomLeftPoints = new ArrayList<>();

    for (Rectangle r : rectangles) {
      bottomLeftPoints
          .add(r.getBottomLeft());
    }
    return bottomLeftPoints;
  }

  /**
   * Returns a list containing all rectangles that intersect with the given
   * rectangle.
   *
   * @param rectangles A list of rectangles to be checked for intersection
   * @param rectangle  The rectangle against which intersection should be
   *                   checked
   * @return All rectangles that do intersect with the given rectangle
   */
  public static List<Rectangle> getAllIntersecting(
      List<Rectangle> rectangles, Rectangle rectangle) {

    List<Rectangle> intersectingRectangles = new ArrayList<>();

    for (Rectangle r : rectangles) {
      if (r.intersects(rectangle)) {
        intersectingRectangles.add(r);
      }
    }
    return intersectingRectangles;
  }

  /**
   * Returns a list containing all rectangles with a bigger area than the given
   * rectangle.
   *
   * @param rectangles A list of rectangles whose area is to be checked
   * @param rectangle  The rectangle against which areas are to be compared
   * @return All rectangles that have a larger area than the given rectangle
   */
  public static List<Rectangle> getAllWithBiggerAreaThan(
      List<Rectangle> rectangles, Rectangle rectangle) {

    List<Rectangle> biggerRectangles = new ArrayList<>();

    for (Rectangle r : rectangles) {
      if (r.area() > rectangle.area()) {
        biggerRectangles.add(r);
      }
    }
    return biggerRectangles;
  }

  /**
   * Returns the largest area among the given rectangles.
   */
  public static int findLargestArea(List<Rectangle> rectangles) {
    int largestArea = 0;

    for (Rectangle r : rectangles) {
      largestArea = Math.max(largestArea, r.area());
    }
    return largestArea;
  }

  /**
   * Returns the largest height among all the given rectangles.
   */
  public static int findMaxHeight(List<Rectangle> rectangles) {
    int maxHeight = 0;

    for (Rectangle r : rectangles) {
      maxHeight = Math.max(maxHeight, r.getHeight());
    }
    return maxHeight;
  }


  /**
   * Computes the sum of areas of all the given rectangles.
   */
  public static int getSumOfAreas(List<Rectangle> rectangles) {
    int sumOfAreas = 0;

    for (Rectangle r : rectangles) {
      sumOfAreas += r.area();
    }
    return sumOfAreas;
  }

  /**
   * Computes the sum of areas of all rectangles that intersect with the given
   * rectangle.
   *
   * @param rectangles The rectangles whose areas to be considered and summed
   * @param rectangle  The rectangle with which intersection is to be checked
   * @return The sum of areas of all rectangles that do intersect with the given
   * rectangle
   */
  public static int getSumOfAreasOfAllIntersecting(
      List<Rectangle> rectangles, Rectangle rectangle) {
    return getSumOfAreas(getAllIntersecting(rectangles, rectangle));
  }

  /**
   * Returns collection that maps each rectangle to its computed area.
   */
  public static Map<Rectangle, Integer> getAreaMap(List<Rectangle> rectangles) {
    Map<Rectangle, Integer> areaMap = new HashMap<>();

    for (Rectangle r : rectangles) {
      areaMap.putIfAbsent(r, r.area());
    }

    return areaMap;
  }
}
