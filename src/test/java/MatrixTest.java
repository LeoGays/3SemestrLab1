import org.junit.Assert;
import org.junit.Rule;
import org.junit.rules.TemporaryFolder;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class MatrixTest {

    private static final double DOUBLE_EPS = 1E-6;

    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Test
    public void testCalculationDet() {
        double[] arrayMatrix = {1,0,0,3};
        Matrix matrix = new Matrix(2,arrayMatrix);
        double det = matrix.calculateTheDeterminant();
        Assert.assertEquals(det, 3.0,DOUBLE_EPS);

        double[] arrayMatrix1 = {1,1,1,1};
        Matrix matrix1 = new Matrix(2,arrayMatrix1);
        double det1 = matrix1.calculateTheDeterminant();
        Assert.assertEquals(det1, 0.0,DOUBLE_EPS);

        double[] arrayMatrix2 = {1,0,0,0,2,0,0,0,3};
        Matrix matrix2 = new Matrix(3,arrayMatrix2);
        double det2 = matrix2.calculateTheDeterminant();
        Assert.assertEquals(det2, 6.0,DOUBLE_EPS);

        double[] arrayMatrix3 = {1,-2,0,3,-4,5,0,0,6};
        Matrix matrix3 = new Matrix(3,arrayMatrix3);
        double det3 = matrix3.calculateTheDeterminant();
        Assert.assertEquals(det3, 12.0,DOUBLE_EPS);
    }


    @Test
    public void testGetInvertableMatrix() {

        double[] arrayMatrix = {1.0,0.0,0.0,2.0};
        InvertableMatrix matrix = new InvertableMatrix(new Matrix(2,arrayMatrix));
        double[] actArrayMatrix = {1.0,0.0,0.0,0.5};
        Matrix actMatrix = new Matrix(2, actArrayMatrix);
        // Assert.assertTrue(Arrays.equals(matrix.getInvertableMatrix().getMatrix(),actMatrix.getMatrix()));
        assertTrue(actMatrix.equals(matrix.getInvertableMatrix()));

        double[] arrayMatrix1 = {1.0,3.0,2.0,2.0};
        InvertableMatrix matrix1 = new InvertableMatrix(new Matrix(2,arrayMatrix1));
        double[] actArrayMatrix1 = {-0.5,0.75,0.5,-0.25};
        Matrix actMatrix1 = new Matrix(2, actArrayMatrix1);
        //Assert.assertTrue(Arrays.equals(matrix1.getInvertableMatrix().getMatrix(),actMatrix1.getMatrix()));
        assertTrue(actMatrix1.equals(matrix1.getInvertableMatrix()));

        double[] arrayMatrix2 = {1.0, 0.0, 0.0,
                0.0, 1.0, 0.0,
                0.0, 0.0, 1.0};
        InvertableMatrix matrix2 = new InvertableMatrix(new Matrix(3,arrayMatrix2));
        double[] actArrayMatrix2 = {1.0, 0.0, 0.0,
                0.0, 1.0, 0.0,
                0.0, 0.0, 1.0};
        Matrix actMatrix2 = new Matrix(3, actArrayMatrix2);
        //Assert.assertTrue(Arrays.equals(matrix2.getInvertableMatrix().getMatrix(),actMatrix2.getMatrix()));
        assertTrue(actMatrix2.equals(matrix2.getInvertableMatrix()));

    }

    @Test
    public void testSumElemMatrix() {
        double[] arrayMatrix = {1.0,2.0,1.0,2.0,6.0,-10.0,9.0,11.0,-2.0};
        Matrix matrix = new Matrix(new Matrix(3,arrayMatrix));
        Assert.assertEquals(DemoMatrix.sumElemMatrix(matrix), 20.0, DOUBLE_EPS);
    }

    @Test
    public void testFileInputMatrix1() {
        double[] arrayToWrite = {1.0,2.0,3.0,4.0};
        Matrix matrixToWrite = new Matrix(2,arrayToWrite);
        try {
            File file = new File("test.txt");
            file.createNewFile();
            FileService.writeMatrixToTextFileOneLine(file, matrixToWrite);
            assertTrue(file.exists());
            Matrix matrixFile = FileService.readMatrixFromTextFileOneLine(file);
            assertEquals(matrixToWrite, matrixFile);
        }catch (Exception ex) {
            fail();
        }
    }

    @Test
    public void testFileInputMatrix2() {
        double[] arrayToWrite = {1.0,0.0,0.0,0.0,1.0,0.0,0.0,0.0,1.0};
        Matrix matrixToWrite = new Matrix(3,arrayToWrite);
        try {
            File file = new File("test.txt");
            file.createNewFile();
            FileService.writeMatrixToTextFileOneLine(file, matrixToWrite);
            assertTrue(file.exists());
            Matrix matrixFile = FileService.readMatrixFromTextFileOneLine(file);
            assertEquals(matrixToWrite, matrixFile);
        }catch (Exception ex) {
            fail();
        }
    }

    @Test
    public void testFileSerializeDesirealizeMatrixJson() {
        double[] arrayToWrite = {1.0,2.0,3.0,4.0};
        Matrix matrixToWrite = new Matrix(2,arrayToWrite);
        try {
            File file = new File("test.txt");
            file.createNewFile();
            FileService.serialzeMatrixToJsonFile(file, matrixToWrite);
            assertTrue(file.exists());
            Matrix matrixRed = FileService.deserializeMatrixFromJsonFile(file);
            assertEquals(matrixToWrite,matrixToWrite);
        } catch (Exception ex) {
            fail();
        }
    }

    @Test
    public void testFileSerializeDesirealizeMatrixJson1() {
        double[] arrayToWrite = {1.0,0.0,0.0,0.0,1.0,0.0,0.0,0.0,1.0};
        Matrix matrixToWrite = new Matrix(3,arrayToWrite);
        try {
            File file = new File("test.txt");
            file.createNewFile();
            FileService.serialzeMatrixToJsonFile(file, matrixToWrite);
            assertTrue(file.exists());
            Matrix matrixRed = FileService.deserializeMatrixFromJsonFile(file);
            assertEquals(matrixToWrite,matrixToWrite);
        } catch (Exception ex) {
            fail();
        }
    }

    @Test
    public void testFlagAndHashDetMatrix() {
        double[] arrayMatrix = {1.0,2.0,3.0,4.0};
        Matrix matrix = new Matrix(2,arrayMatrix);
        double det = matrix.calculateTheDeterminant();
        assertTrue(matrix.isFlag());
        matrix.changeElemByIndex(0,1, -1.0);
        assertFalse(matrix.isFlag());
    }

}