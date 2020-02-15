package picture;

import java.util.ArrayList;

public class Main {

  public static void main(String[] args) {

    switch (args[0]) {

      case "invert": {
        Process img = new Process(Utils.loadPicture(args[1]));
        img.invert();
        Utils.savePicture(img.getPic(), args[2]);
        break;
      }

      case "grayscale": {
        Process img = new Process(Utils.loadPicture(args[1]));
        img.grayscale();
        Utils.savePicture(img.getPic(), args[2]);
        break;
      }

      case "rotate": {
        Process img = new Process(Utils.loadPicture(args[2]));
        img.rotate(Integer.parseInt(args[1]));
        Utils.savePicture(img.getPic(), args[3]);
        break;
      }

      case "flip": {
        Process img = new Process(Utils.loadPicture(args[2]));
        Utils.savePicture(img.flip(args[1]), args[3]);
        break;
      }

      case "blend": {
        int l = args.length;
        ArrayList<Picture> imgs = new ArrayList<>();

        for (int i = 1; i < l - 1; i++) {
          imgs.add(Utils.loadPicture(args[i]));
        }
        Utils.savePicture(Process.blend(imgs), args[l - 1]);
        break;
      }

      case "blur": {
        Process img = new Process(Utils.loadPicture(args[1]));
        Utils.savePicture(img.blur(), args[2]);
        break;
      }

      case "blurExtension": {
        Process img = new Process(Utils.loadPicture(args[2]));
        int kernelSize = Integer.parseInt(args[1]);
        assert kernelSize % 2 != 0 : "kernel size has to be odd";
        Utils.savePicture(img.blurExtension(kernelSize), args[3]);
        break;

      }
      default:
        throw new IllegalStateException("Unexpected value: " + args[0]);
    }
  }

}
