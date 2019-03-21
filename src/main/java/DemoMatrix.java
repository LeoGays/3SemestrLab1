import java.io.File;
import java.io.FileReader;
import java.util.Scanner;

public class DemoMatrix {


    public static void outputToConsole (Matrix matrix) {
        int k = 0;
        for(double elem: matrix.getMatrix()) {
            k++;
            System.out.print(elem + " ");
            if (k % 3 == 0) {
                System.out.println(" ");
                k = 0;
            }
        }
    }


    public static double sumElemMatrix(Matrix matrix) {
        double sum = 0;
        for (double elem: matrix.getMatrix()) {
            sum += elem;
        }
        return sum;
    }



}