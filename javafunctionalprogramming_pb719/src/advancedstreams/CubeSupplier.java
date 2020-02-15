package advancedstreams;

import java.util.NoSuchElementException;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class CubeSupplier implements Supplier<Integer> {

  private Integer count = 0;

  @Override
  public Integer get() {
    count++;
    Integer cube = cube(count);

    if (cube < 0) {
      throw new NoSuchElementException();
    }
    return cube;
  }

  public static Stream<Integer> cubeStream() {
    return Stream.generate(new CubeSupplier());
  }

  public static Stream<Integer> boundedCubeStream(int lBound, int uBound) {
    return cubeStream()
        .skip(lBound)
        .limit(uBound);
  }

  private static int cube(int num) {
    return num * num * num;
  }

  public static Stream<Integer> palindromicCubes(int lBound, int uBound) {
    return boundedCubeStream(lBound, uBound)
        .filter(c -> IsPalindrome.isPalindrome(c.toString()));
  }
}
