import java.util.Scanner;

public class Matrix {
    private double[][] m;
    private int numberOfRows;
    private int numberOfColumns;

    public Matrix(int rows, int columns)
    {
        m = new double[rows][columns];

        for(int i = 0; i < rows; i++)
        {
            for(int j = 0; j < columns; j++)
            {
                m[i][j] = 0;
            }
        }
    }

    public Matrix(){
        Scanner scan = new Scanner(System.in);
        System.out.println();
        System.out.print("Please enter the number of rows: "); numberOfRows = scan.nextInt();
        System.out.print("Please enter the number of columns: "); numberOfColumns = scan.nextInt();
        m = new double[numberOfRows][numberOfColumns];
        System.out.println("Please assign the following elements: ");
        for(int i = 0; i < numberOfRows; i++){
            for(int j = 0; j < numberOfColumns; j++){
                System.out.println("Row #" + String.valueOf(i+1) + " Column #" + String.valueOf(j+1));
                //System.out.print("[" + i+1 + "]" + "[" + j+1 + "]: ");
                m[i][j] = scan.nextDouble();
            }
        }
    }
    public int getRows(){
        return numberOfRows;
    }
    private int getColumns(){
        return numberOfColumns;
    }
    private double getElement(int rows, int columns){
        return m[rows][columns];
    }
    public double[][] getMatrix(Matrix matrix){
        double[][] z = new double[matrix.getRows()][matrix.getColumns()];
        for(int i = 0; i < z.length; i++){
            for(int j = 0; j < z[i].length; j++){
                z[i][j] = matrix.getElement(i,j);
            }
        }
        return z;
    }
    private void assignElement(double value, int i, int j){
        m[i][j] = value;
    }

    public Matrix add(Matrix x){
        Matrix z = new Matrix(numberOfRows, numberOfColumns);
        double value;
        System.out.println();
        System.out.println("The sum of the matricices is: ");

        if(numberOfRows == x.getRows() && numberOfColumns == x.getColumns())
        {
            for(int i = 0; i < x.getRows(); i++)
            {
                for(int j = 0; j < x.getColumns(); j++)
                {
                    value = m[i][j] + x.getElement(i,j);
                    z.assignElement(value,i,j);
                    System.out.print("[" + z.getElement(i,j) + "]");
                }
                System.out.print('\n');
            }
            return z;
        }
        else{
            System.out.println("ERROR: The number of rows and columns of the matricies are not equivalent."); return z;
        }
    }
    public Matrix subtract(Matrix x){
        Matrix z = new Matrix(numberOfRows, numberOfColumns);
        double value;
        System.out.println();
        System.out.println("The difference of the two matricices is: ");

        if(numberOfRows == x.getRows() && numberOfColumns == x.getColumns()){
            for(int i = 0; i < x.getRows(); i++){
                for(int j = 0; j < x.getColumns(); j++){
                    value = m[i][j] - x.getElement(i,j);
                    z.assignElement(value,i,j);
                    System.out.print("[" + z.getElement(i,j) + "]");
                }
                System.out.print('\n');
            }
            return z;
        }
        else{System.out.println("ERROR: The number of rows and columns of the matricies are not equivalent."); return z;}
    }

    public Matrix scalerMultiplication(Matrix x, double scaler){
        Matrix z = new Matrix(numberOfRows, numberOfColumns);
        double value;
        System.out.println("Your matrix scaled by " + scaler + " is: ");

        for(int i = 0; i < x.getRows(); i++){
            for(int j = 0; j < x.getColumns(); j++){
                value = m[i][j] * scaler;
                z.assignElement(value,i,j);
                System.out.print("[" + z.getElement(i,j) + "]");
            }
            System.out.print('\n');
        }
        return z;
    }

    public Matrix multiply(Matrix x){
        Matrix z = new Matrix(numberOfRows, x.getColumns());
        double value;
        System.out.println();
        System.out.println("The product of the matricices is: ");

        if(numberOfColumns == x.getRows()){
            for(int i = 0; i < numberOfRows; i++){
                for(int j = 0; j < x.getColumns(); j++){
                    double sum = 0;
                    for(int k = 0; k < numberOfRows; k++){
                        sum += m[i][k] * x.getElement(k,j);
                    }
                    value = sum;
                    z.assignElement(value,i,j);
                    System.out.print("[" + z.getElement(i,j) + "]");
                }
                System.out.print('\n');
            }
            return z;
        }else{
            System.out.println("ERROR: The number of columns of the first matrix and the number of rows of the second matrix are not equivalent.");
            return z;
        }
    }

    private static void getCofactor(double[][] A, double[][] temp, int p, int q, int n){
        int i = 0, j = 0;

        for (int row = 0; row < n; row++)
        {
            for (int col = 0; col < n; col++)
            {

                if (row != p && col != q)
                {
                    temp[i][j++] = A[row][col];

                    if (j == n - 1)
                    {
                        j = 0;
                        i++;
                    }
                }
            }
        }
    }
    void adjoint(double[][] A, int[][] adj)
    {
        if (A.length == 1)
        {
            adj[0][0] = 1;
            return;
        }
        int sign = 1;
        double[][] temp = new double[A[0].length][A.length];

        for (int i = 0; i < A[0].length; i++)
        {
            for (int j = 0; j < A.length; j++)
            {

                getCofactor(A, temp, i, j, A.length);

                sign = ((i + j) % 2 == 0)? 1: -1;

                adj[j][i] = (int) ((sign)*(determinant(temp, A.length-1)));
            }
        }
    }
    public double determinant(double[][] A, int n)
    {
        int D = 0;

        if (n == 1)
            return A[0][0];

        double[][] temp = new double[A.length][A[0].length]; // To store cofactors

        int sign = 1;


        for (int f = 0; f < n; f++)
        {
            getCofactor(A, temp, 0, f, n);
            D += sign * A[0][f] * determinant(temp, n - 1);

            sign = -sign;
        }

        return D;
    }
    public boolean inverse(double A[][], double[][] inverse)
    {
        int det = (int) determinant(A, A.length);
        if (det == 0)
        {
            System.out.print("Singular matrix, can't find its inverse");
            return false;
        }

        int [][]adj = new int[A[0].length][A.length];
        adjoint(A, adj);

        for (int i = 0; i < A[0].length; i++)
            for (int j = 0; j < A.length; j++)
                inverse[i][j] = adj[i][j]/(float)det;

        return true;
    }



}
