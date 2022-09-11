import java.util.Scanner;

public class Matrix_Multiplication
{
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the size of the Array : ");
        int N = sc.nextInt();
        boolean flag = true;
        while(flag)
        {
            if(!Power_of_2(N))
            {
                System.out.println("Invalid Entry...");
                System.out.println("Enter size of Array that is power of 2...");
                System.out.print("Enter the size of the Array : ");
                N = sc.nextInt();
            }
            else
                flag = false;
        }
        int[][] A = new int[N][N];
        int[][] B = new int[N][N];
        System.out.println("Enter the elements of the Matrix A : ");
        for(int i=0; i<N; i++)
        {
            for(int j=0; j<N; j++)
            {
                System.out.print("A["+ i +"]["+ j +"] : ");
                A[i][j] = sc.nextInt();
            }
        }
        System.out.println( "\nEntered Matrix A : ");
        for (int i = 0; i < N; i++)
        {
            for (int j = 0; j < N; j++)
                System.out.print("\t"+ A[i][j]);
            System.out.println();
        }
        System.out.println("Enter the elements of the Matrix B : ");
        for(int i=0; i<N; i++)
        {
            for(int j=0; j<N; j++)
            {
                System.out.print("B["+ i +"]["+ j +"] : ");
                B[i][j] = sc.nextInt();
            }
        }
        System.out.println( "\nEntered Matrix B : ");
        for (int i = 0; i < N; i++)
        {
            for (int j = 0; j < N; j++)
                System.out.print("\t"+ B[i][j]);
            System.out.println();
        }
        int[][] C = multiply(A, B);
        System.out.println( "\nProduct of matrices A and  B : ");
        for (int i = 0; i < N; i++)
        {
            for (int j = 0; j < N; j++)
                System.out.print("\t"+ C[i][j]);
            System.out.println();
        }
        sc.close();
    }

    static boolean Power_of_2(int n)
    {
        while(n != 1)
        {
            if(n%2 == 1)
                return false;
            n = n/2;
        }
        return true;
    }

    static int[][] multiply(int[][] A, int[][] B)
    {
        int n = A.length;
        int[][] R = new int[n][n];
        if (n == 1)
            R[0][0] = A[0][0] * B[0][0];
        else
        {
            int[][] A11 = new int[n/2][n/2];
            int[][] A12 = new int[n/2][n/2];
            int[][] A21 = new int[n/2][n/2];
            int[][] A22 = new int[n/2][n/2];
            int[][] B11 = new int[n/2][n/2];
            int[][] B12 = new int[n/2][n/2];
            int[][] B21 = new int[n/2][n/2];
            int[][] B22 = new int[n/2][n/2];
            split(A, A11, 0, 0);
            split(A, A12, 0, n/2);
            split(A, A21, n/2, 0);
            split(A, A22, n/2, n/2);
            split(B, B11, 0, 0);
            split(B, B12, 0, n/2);
            split(B, B21, n/2, 0);
            split(B, B22, n/2, n/2);
            int[][] M1 = multiply(add(A11, A22), add(B11, B22));
            int[][] M2 = multiply(add(A21, A22), B11);
            int[][] M3 = multiply(A11, sub(B12, B22));
            int[][] M4 = multiply(A22, sub(B21, B11));
            int[][] M5 = multiply(add(A11, A12), B22);
            int[][] M6 = multiply(sub(A21, A11), add(B11, B12));
            int[][] M7 = multiply(sub(A12, A22), add(B21, B22));
            int[][] C11 = add(sub(add(M1, M4), M5), M7);
            int[][] C12 = add(M3, M5);
            int[][] C21 = add(M2, M4);
            int[][] C22 = add(sub(add(M1, M3), M2), M6);
            join(C11, R, 0, 0);
            join(C12, R, 0, n / 2);
            join(C21, R, n / 2, 0);
            join(C22, R, n / 2, n / 2);
        }
        return R;
    }

    static int[][] sub(int[][] A, int[][] B)
    {
        int n = A.length; 
        int[][] C = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                C[i][j] = A[i][j] - B[i][j];
        return C;
    }

    static int[][] add(int[][] A, int[][] B)
    {
        int n = A.length;
        int[][] C = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                C[i][j] = A[i][j] + B[i][j];
        return C;
    }

    static void split(int[][] P, int[][] C, int iB, int jB)
    {
        for (int i1 = 0, i2 = iB; i1 < C.length; i1++, i2++)
            for (int j1 = 0, j2 = jB; j1 < C.length;
                 j1++, j2++)
                C[i1][j1] = P[i2][j2];
    }

    static void join(int[][] C, int[][] P, int iB, int jB)
    {
        for (int i1 = 0, i2 = iB; i1 < C.length; i1++, i2++)
            for (int j1 = 0, j2 = jB; j1 < C.length; j1++, j2++)
                P[i2][j2] = C[i1][j1];
    }
}