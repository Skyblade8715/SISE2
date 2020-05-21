import javax.print.DocFlavor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;

public class Matrix {
    private List<List<Double>> data = new ArrayList<>();

    /*
    data in Matrix is put in similar fashion to how you'd look at Matrix in math
    First layer of lists represents rows, so when data.size() = 2 that means Matrix has 2 rows.
    Then, when data.get(0).size() = 3 it means it has 3 columns. So it'd look like that:
    [ x x x ]
    [ x x x ]
     */

    public Matrix(List<Double> list){
        for(int i = 0; i < list.size(); i++){
            data.add(new ArrayList<>());
            data.get(i).add(list.get(i));
        }
    }

    public Matrix(int n, int m, boolean randValues){
        List<Double> temp = new ArrayList<>();
        for(int i = 0; i < m; i++){
            if(randValues){
                temp.add(Math.random());
            } else {
                temp.add((double) 0);
            }
        }
        for(int i = 0; i < n; i++){
            data.add(temp);
        }
    }

    void activationFunction() {
        for(List<Double> list : this.data){
            for(Double valueToActivate : list){
                valueToActivate = 1.0 / (1.0 + Math.exp(-valueToActivate));
            }
        }
    }

    Matrix transpose() {
        int m = this.data.size();
        int n = this.data.get(0).size();
        Matrix b = new Matrix(n, m, false );
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                b.data.get(j).set(i, this.data.get(i).get(j));
        return b;
    }

    Matrix multiplyMatrices(Matrix B) {

        int a_rows = this.data.size();
        int a_cols = this.data.get(0).size();
        int b_cols = B.data.get(0).size();
        int b_rows = B.data.size();

        if(a_cols != b_rows){
            System.out.println("multiplyMatrices");
        }
        Matrix product = new Matrix(a_rows, b_cols, false);
        double temp = 0;
        for(int i = 0; i < a_rows; i++) {
            for (int j = 0; j < b_cols; j++) {
                temp = product.data.get(i).get(j);
                for (int k = 0; k < a_cols; k++) {
                    temp += this.data.get(i).get(k) * B.data.get(k).get(j);
                }
                product.data.get(i).set(j, temp);
            }
        }

        return product;
    }

    Matrix wiseMultiplication(Matrix b){
        int m = this.data.size();
        int n = this.data.get(0).size();

        Matrix c = new Matrix(m, n, false);
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                c.data.get(i).set(j, this.data.get(i).get(j) * b.data.get(i).get(j));
        return c;
    }

    Matrix subtract(Matrix b) {
        int m = this.data.size();
        int n = this.data.get(0).size();

        Matrix c = new Matrix(m, n, false);
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                c.data.get(i).set(j, this.data.get(i).get(j) - b.data.get(i).get(j));
        return c;
    }

    Matrix addMatrices(Matrix b) {
        int m = this.data.size();
        int n = this.data.get(0).size();
        int x = b.data.size();
        int z = b.data.get(0).size();

        if(x != m || z != n)
            System.out.println("AddMatrices");

        Matrix c = new Matrix(m, n, false);
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                c.data.get(i).set(j, this.data.get(i).get(j) + b.data.get(i).get(j));
        return c;
    }


    Matrix multiplyMatrixByConst(float b) {
        int m = this.data.size();
        int n = this.data.get(0).size();
        Matrix c = new Matrix(m, n, false);
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                c.data.get(i).set(j, this.data.get(i).get(j) * b);
        return c;
    }

    Matrix subConstFromMatrix(float a) {

        int m = this.data.size();
        int n = this.data.get(0).size();
        Matrix c = new Matrix(m, n, false);

        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                c.data.get(i).set(j, a - this.data.get(i).get(j));
            }
        }
        return c;
    }

    Matrix assignWithBias(boolean use){

        if(!use) {
            return this;
        }
        int rows = this.data.size();
        int cols = this.data.get(0).size();

        Matrix nMatrix = new Matrix(rows + 1, cols, false);

        for(int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                nMatrix.data.get(i).set(j, this.data.get(i).get(j));
            }
        }
        for(int j = 0; j < cols; j++) {
            nMatrix.data.get(rows).set(j, 1d);
        }
        return nMatrix;
    }

    Matrix assignWithoutBias(boolean useBias){

        if(!useBias)
            return this;

        int rows = this.data.size();
        int cols = this.data.get(0).size();

        Matrix nMatrix = new Matrix(rows - 1, cols, false);

        for(int i = 0; i < rows - 1; i++){
            for(int j = 0; j < cols; j++) {
                nMatrix.data.get(i).set(j, this.data.get(i).get(j));
            }
        }
        return nMatrix;
    }

    Matrix fillWithOne(){
        int rows = this.data.size();
        int cols = this.data.get(0).size();
        double temp = 1.0;

        Matrix nMatrix = new Matrix(rows, cols, false);
        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < cols; j++) {
                nMatrix.data.get(i).set(j, temp);
            }
        }

        return nMatrix;
    }

}
