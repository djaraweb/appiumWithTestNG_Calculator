package utilities;

public class Helpers {

    public static int getFactorial(int n) {
        int factorial = 1;
        for (int i = 2; i <= n; ++i) {
            factorial *= i;
        }
        return factorial;
    }

    public static int getSuma(int n1, int n2) {
        return n1 + n2;
    }

    public static int getMultiply(int n1, int n2) {
        return n1 * n2;
    }
}
