import java.util.*;

public class Matrix_Chain_Multiplication {

    static int[][] m;
    static int[][] s;
    static int max = Integer.MAX_VALUE;

    static int[][] multiply(int[][] X, int[][] Y) {
        int[][] res = new int[X.length][Y[0].length];
        for(int i = 0; i < X.length; i++) {
            for(int j = 0; j < Y[0].length; j++) {
                res[i][j]=0;
                for(int k = 0; k < Y.length; k++) {
                    res[i][j] += X[i][k] * Y[k][j];
                }
            }
        }
        return res;
    }

    static int[][] mult(int[][][] A, int i, int j) {
        if(i<j) {
            int[][] X = mult(A, i, s[i][j]);
            int[][] Y = mult(A, s[i][j]+1, j);
            return multiply(X, Y);
        }
        else
            return A[i];
    }

    static void chain(int[] p, int n) {
        for (int i = 1; i < n; i++) {
            m[i][i] = 0;
        }
        for (int l = 2; l < n; l++) {
            for(int i = 1; i < (n-l+1); i++) {
                int j = (i+l-1);
                if(j == n)
                    continue;
                m[i][j] = max;
                for(int k = i; k < j; k++) {
                    int q = m[i][k] + m[k+1][j] + p[i-1]*p[k]*p[j];
                    if(q < m[i][j]) {
                        m[i][j] = q;
                        s[i][j] = k;
                    }
                }
            }
        }
    }

    static void print_mult_seq(int i, int j) {
        if(i == j)
            System.out.print("A"+ i);
        else {
            System.out.print("(");
            print_mult_seq(i, s[i][j]);
            print_mult_seq(s[i][j]+1, j);
        }
        System.out.print(")");
    }

    static void print(int[][] A, int m, int n) {
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                 System.out.print("\t"+ A[i][j]);
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] p;
        System.out.print("Enter No. of Matrices : ");
        int n = sc.nextInt();
        p = new int[n+1];
        System.out.println("\nEnter the prefix List : ");
        for (int i = 0; i <= n; i++) {
            System.out.print("p"+ i +" : ");
            p[i] = sc.nextInt();
        }
        int[][][] A = new int[n+1][][];
        for (int i = 1; i <= n; i++) {
            A[i] = new int[p[i-1]][p[i]];
            System.out.println("\nEnter Elements of Matrix A"+ i +" of size ("+ p[i-1] +"x"+ p[i] +"): ");
            for (int j = 0; j < p[i-1]; j++) {
                for (int k = 0; k < p[i]; k++) {
                    System.out.print("A"+ i +"["+ j +"]["+ k +"] : ");
                    A[i][j][k] = sc.nextInt();
                }
            }
        }
        for (int i = 1; i <= n; i++) {
            System.out.print("\nA"+ i +":");
            print(A[i], A[i].length, A[i][0].length);
        }
        m = new int[n+1][n+1];
        s = new int[n+1][n+1];
        chain(p, n+1);
        System.out.println("\n\nMinimum no. of multiplications : "+ m[1][n]);
        System.out.print("\nSequence of Multiplication : ");
        print_mult_seq(1, n);
        int[][] res = mult(A, 1, n);
        System.out.print("\n\nRes:");
        print(res, res.length, res[0].length);
        System.out.println("\n\n");
        sc.close();
    }
}