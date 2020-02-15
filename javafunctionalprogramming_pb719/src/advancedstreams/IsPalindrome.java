package advancedstreams;

public class IsPalindrome {

  public static boolean isPalindrome(String string) {
    int l = string.length();
    for (int i = 0; i < l / 2; i++) {
      if (string.charAt(i) != string.charAt(l - i - 1)) {
        return false;
      }
    }
    return true;
  }
}
