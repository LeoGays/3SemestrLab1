public class InvertableMatrix implements IInvertableMatrix {

    private Matrix invertableMatrix;

    InvertableMatrix(Matrix matrix) {
        invertableMatrix = new Matrix(matrix);
    }

    @Override
    public double getElemByIndex(int i, int j) {
        return invertableMatrix.getElemByIndex(i,j);
    }

    @Override
    public void changeElemByIndex(int i, int j, double elem) {
        invertableMatrix.changeElemByIndex(i,j,elem);
    }

    @Override
    public double calculateTheDeterminant() {
        return invertableMatrix.calculateTheDeterminant();
    }

    @Override
    public Matrix getInvertableMatrix() {
        int size = invertableMatrix.getN();
        Matrix[] minorMatrix = new Matrix[size*size];
        for (int i = 0; i < size*size; i++) {
            minorMatrix[i] = new Matrix(size-1);
        }
        Matrix invMatrix = new Matrix(size);
        int m = 0;
        for (int i = 0; i < size*size; i++) {
            for (int j = 0; j < size*size; j++) {
                if ((j / size) != (i / size) && (j % size) != (i % size)) {
                    minorMatrix[i].changeElemByIndex(m / (size - 1), m % (size - 1), invertableMatrix.getElemByIndex(j / size, j % size));
                    m++;
                }
            }
            m=0;
        }
        double det =  invertableMatrix.calculateTheDeterminant();

        for ( int i = 0; i < size*size; i++) {
            if (((i % size) + (i / size)) % 2 ==1) {
                invMatrix.changeElemByIndex(i % size, i / size, (-1) * minorMatrix[i].calculateTheDeterminant() / det);
            } else {
                invMatrix.changeElemByIndex(i % size, i / size, minorMatrix[i].calculateTheDeterminant() / det);
            }
            if (invMatrix.getElemByIndex(i % size, i / size) == 0.0) {
                invMatrix.changeElemByIndex(i % size, i / size, Math.abs(invMatrix.getElemByIndex(i % size, i / size)));
            }
        }

        return invMatrix;
    }
}
