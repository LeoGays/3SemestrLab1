public interface IMatrix {

        double eps = 0.000000001;
        double getElemByIndex(int i, int j);
        void changeElemByIndex(int i, int j, double elem);
        double calculateTheDeterminant();

}
