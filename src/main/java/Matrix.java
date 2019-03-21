import java.util.Arrays;
import java.util.Objects;

public class Matrix implements IMatrix {

    private int N;
    private double matrix[];
    private double det;
    private boolean flag;

    Matrix (int N, double [] matrix) {
        setN(N);
        setMatrix(matrix);
    }

    Matrix (int N){
        this(N, new double[N*N]);
    }

    Matrix( Matrix matrix) {
        this(matrix.getN(),matrix.getMatrix());
    }

    @Override
    public double getElemByIndex(int i, int j) {
        return matrix[i*N + j];
    }

    @Override
    public void changeElemByIndex(int i, int j, double elem) {
        matrix[i*N + j] = elem;
        setFlag(false);
    }

    @Override
    public double calculateTheDeterminant() {
        Matrix newMatrix = new Matrix(N,matrix);
        double max;
        double det = 1;
        double v;
        int l = 0;
        int i = 0;
        int j = 0;

        while (i < N-1 && j < N-1) {

            max = 0;
            for (int k = i; k < N ; k++) {
                if(Math.abs(newMatrix.getElemByIndex(k,j)) > 0){
                    max = newMatrix.getElemByIndex(k,j);
                    l = k;
                }
            }

            if (Math.abs(max) <= eps) {
                return 0;
            }

            if (l != i) {
                for(int k = j; k < N; k++) {
                    v = newMatrix.getElemByIndex(i,k);
                    newMatrix.changeElemByIndex(i,k,newMatrix.getElemByIndex(l,k));
                    newMatrix.changeElemByIndex(l,k, (-1)*v);
                }
            }

            for (int k = i + 1 ; k < N; k++) {

                v = (-1) * newMatrix.getElemByIndex(k,j) / newMatrix.getElemByIndex(i,j);

                newMatrix.changeElemByIndex(k,j,0);

                for (l = j + 1; l < N ; l++) {
                    newMatrix.changeElemByIndex(k,l, newMatrix.getElemByIndex(k,l) + v*newMatrix.getElemByIndex(i,l));
                }

            }

            i++;
            j++;
        }

        for (int k = 0; k < N; k++) {
            det *= newMatrix.getElemByIndex(k,k);
        }
        setDet(det);
        setFlag(true);

        return det;
    }

    public int getN() {
        return N;
    }

    public void setN(int N) {
        this.N = N;
    }

    public double[] getMatrix() {
        return matrix;
    }

    public void setMatrix(double[] matrix) {
        this.matrix = matrix;
        setFlag(false);
    }

    public double getDet() {
        return det;
    }

    public void setDet(double det) {
        this.det = det;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    @Override
    public String toString() {
        String strnigArray = "";
        for (double elem: this.getMatrix()) {
            strnigArray =  strnigArray + Double.toString(elem) + " " ;
        }
        return strnigArray;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Matrix matrix1 = (Matrix) o;
        return N == matrix1.N &&
                Arrays.equals(matrix, matrix1.matrix);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(N);
        result = 31 * result + Arrays.hashCode(matrix);
        return result;
    }
}