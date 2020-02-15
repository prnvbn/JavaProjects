package picture;

import java.util.ArrayList;

public class Process {

  private Picture pic;

  public Process(Picture pic) {
    this.pic = pic;
  }

  public Picture getPic() {
    return pic;
  }

  public void invert() {
    int height = pic.getHeight();
    int width = pic.getWidth();

    for (int x = 0; x < width; x++) {
      for (int y = 0; y < height; y++) {
        Color clr = pic.getPixel(x, y);
        clr.invert();
        pic.setPixel(x, y, clr);
      }
    }
  }

  public void grayscale() {
    int height = pic.getHeight();
    int width = pic.getWidth();

    for (int x = 0; x < width; x++) {
      for (int y = 0; y < height; y++) {
        Color clr = pic.getPixel(x, y);
        clr.grayscale();
        pic.setPixel(x, y, clr);
      }
    }
  }

  public void rotate(int angle) {
    for (int i = 0; i < angle / 90; i++) {
      pic = rotate90();
    }
  }

  private Picture rotate90() {
    int height = pic.getHeight();
    int width = pic.getWidth();

    Picture rotatedPic = Utils.createPicture(height, width);

    for (int x = 0; x < width; x++) {
      for (int y = 0; y < height; y++) {
        Color clr = pic.getPixel(x, y);
        rotatedPic.setPixel(height - y - 1, x, clr);
      }
    }

    return rotatedPic;
  }

  public Picture flip(String hv) {
    int height = pic.getHeight();
    int width = pic.getWidth();

    Picture flippedImg = Utils.createPicture(width, height);
    for (int x = 0; x < width; x++) {
      for (int y = 0; y < height; y++) {
        Color clr = pic.getPixel(x, y);
        switch (hv) {
          case "H": {
            flippedImg.setPixel(width - x - 1, y, clr);
            break;
          }
          case "V": {
            flippedImg.setPixel(x, height - y - 1, clr);
          }
        }
      }
    }
    return flippedImg;
  }

  public static Picture blend(ArrayList<Picture> imgs) {
    int[] minHW = getMinHW(imgs);
    int width = minHW[0];
    int height = minHW[1];

    int listSize = imgs.size();

    Picture blendedPic = Utils.createPicture(width, height);

    for (int x = 0; x < width; x++) {
      for (int y = 0; y < height; y++) {
        Color clr = new Color(0, 0, 0);
        for (int i = 0; i < listSize; i++) {
          Color currentClr = imgs.get(i).getPixel(x, y);
          clr.add(currentClr);
        }
        clr.divide(listSize);
        blendedPic.setPixel(x, y, clr);
      }
    }

    return blendedPic;
  }

  private static int[] getMinHW(ArrayList<Picture> imgs) {
    int[] minHW = {imgs.get(0).getWidth(), imgs.get(0).getHeight()};

    for (int i = 1; i < imgs.size(); i++) {
      if (minHW[0] > imgs.get(i).getWidth()) {
        minHW[0] = imgs.get(i).getWidth();
      }
      if (minHW[1] > imgs.get(i).getHeight()) {
        minHW[1] = imgs.get(i).getHeight();
      }

    }
    return minHW;
  }

  public Picture blur() {
    int height = pic.getHeight();
    int width = pic.getWidth();

    Picture blurredPic = Utils.createPicture(width, height);

    for (int x = 0; x < width; x++) {
      for (int y = 0; y < height; y++) {

        Color clr = new Color(0, 0, 0);
        if (!isBoundary(x, width, 1) && !isBoundary(y, height, 1)) {
          for (int i = x - 1; i <= x + 1; i++) {
            for (int j = y - 1; j <= y + 1; j++) {
              clr.add(pic.getPixel(i, j));
            }
          }
          clr.divide(9);
          blurredPic.setPixel(x, y, clr);
        } else {
          blurredPic.setPixel(x, y, pic.getPixel(x, y));
        }

      }
    }
    return blurredPic;
  }

  // A more general implementation of blur in which one can choose the kernel size of the blur
  public Picture blurExtension(int kernelSize) {
    int height = pic.getHeight();
    int width = pic.getWidth();
    int boundSize = kernelSize / 2;

    Picture blurredPic = Utils.createPicture(width, height);

    for (int x = 0; x < width; x++) {
      for (int y = 0; y < height; y++) {

        Color clr = new Color(0, 0, 0);
        if (!isBoundary(x, width, boundSize) && !isBoundary(y, height, boundSize)) {
          for (int i = x - boundSize; i <= x + boundSize; i++) {
            for (int j = y - boundSize; j <= y + boundSize; j++) {
              clr.add(pic.getPixel(i, j));
            }
          }
          clr.divide(kernelSize * kernelSize);
          blurredPic.setPixel(x, y, clr);
        } else {
          blurredPic.setPixel(x, y, pic.getPixel(x, y));
        }

      }
    }
    return blurredPic;
  }

  private static boolean isBoundary(int i, int bound, int boundSize) {
    return i == 0 || i == bound - boundSize;
  }
}
