import java.util.Scanner;

public class Calc {
    static void display(double A[][])
    {
        for (int i = 0; i < A[0].length; i++)
        {
            for (int j = 0; j < A.length; j++)
                System.out.print(A[i][j] + " ");
            System.out.println();
        }
    }
    public static void main(String[] args) {
        boolean keepGoing = true;
        while (keepGoing){
            Scanner scan = new Scanner(System.in);
            System.out.println("Welcome to the Linear Algebra Calculator. Please Enter the number for which operation you would like.");
            System.out.println("1: Addition");
            System.out.println("2: Subtraction");
            System.out.println("3: Scaler Multiplication");
            System.out.println("4: Multiply Matrices");
            //System.out.println("5: Row Reduced Echelon Form");
            System.out.println("5: Find the Inverse");
            System.out.println("6: Find The Determinant");
            int number = scan.nextInt();

            switch(number){
                case 1:
                    System.out.println("Enter the first Matrix");
                    Matrix m1 = new Matrix();
                    System.out.println(("Enter the second matrix"));
                    Matrix m2 = new Matrix();
                    m1.add(m2);
                    break;
                case 2:
                    System.out.println("Enter the first Matrix");
                    m1 = new Matrix();
                    System.out.println(("Enter the second matrix"));
                    m2 = new Matrix();
                    m1.subtract(m2);
                    break;
                case 3:
                    System.out.println("Enter the Matrix you want to scale");
                    m1 = new Matrix();
                    System.out.println("How much would you like to scale it by?");
                    int scaler = scan.nextInt();
                    m1.scalerMultiplication(m1,scaler);
                    break;
                case 4:
                    System.out.println("Enter the first Matrix");
                    m1 = new Matrix();
                    System.out.println(("Enter the second matrix"));
                    m2 = new Matrix();
                    m1.multiply(m2);
                    break;
                case 5:
                    System.out.println("Enter the Matrix you want to find the inverse of");
                    m1 = new Matrix();
                    double[][] A = m1.getMatrix(m1);
                    double [][]adj = new double[A[0].length][A.length];
                    double [][]inv = new double[A[0].length][A.length];
                    System.out.print("\nThe Inverse is :\n");
                    if (m1.inverse(A, inv))
                        display(inv);
                    break;
                case 6:
                    System.out.println("Enter the Matrix you want to find the determinant of");
                    m1 = new Matrix();
                    System.out.println("The determinant of the matrix is" + m1.determinant(m1.getMatrix(m1), m1.getRows()));
                    break;

            }
            System.out.println("\nWould you like to do another problem?");
            System.out.println("Type yes or no");
            String check = scan.next();
            if(check.equalsIgnoreCase("no")){
                System.out.println("\nGoodbye");
                keepGoing = false;
            }


        }
    }
}
