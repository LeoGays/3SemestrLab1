import com.google.gson.Gson;

import java.io.*;

public class FileService {

    public static void  writeMatrixToTextFileOneLine(File file, Matrix matrix) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file),"UTF-8"));
        bw.write(matrix.toString());
        bw.close();
    }

    public static Matrix  readMatrixFromTextFileOneLine(File file) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
        String[] stringArray = br.readLine().split(" ");
        int counter = 0;
        double [] arrayMatrix = new double[stringArray.length];
        for (String elem: stringArray) {
            arrayMatrix[counter++] = Double.parseDouble(elem);
        }
        br.close();
        return new Matrix((int)Math.sqrt(arrayMatrix.length), arrayMatrix);
    }

    public static void  serializeMatrixToBinaryFile(File file, Matrix matrix) throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
        oos.writeObject(matrix);
        oos.close();
    }

    public static Matrix deserializeMatrixFromBinaryFile(File file) throws IOException,ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
        Matrix tr = (Matrix) ois.readObject();
        ois.close();
        return tr;
    }

    public static void serialzeMatrixToJsonFile(File file, Matrix matrix) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(file));
        bw.write(new Gson().toJson(matrix));
        bw.close();
    }

    public static Matrix  deserializeMatrixFromJsonFile(File file) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(file));
        return new Gson().fromJson(br.readLine(),Matrix.class);
    }








}
