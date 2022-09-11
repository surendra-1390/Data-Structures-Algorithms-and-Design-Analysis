import java.util.Scanner;

public class DC_Merge_Sort {
    static void Merge(int[] B, int[] C, int[] A, int n)
    {
        int i=0, j=0, k=0;
        while(i<n/2 && j<n-(n/2))
        {
            if(B[i] <= C[j])
            {
                A[k++] = B[i++];
            }
            else
            {
                A[k++] = C[j++];
            }
        }
        while(i<n/2)
        {
            A[k++] = B[i++];
        }
        while(j<n-(n/2))
        {
            A[k++] = C[j++];
        }
    }

    static void print_Arr(int[] Arr, int n)
    {
        int i;
        for(i=0; i<n; i++)
        {
            System.out.print(" "+ Arr[i] +", ");
        }
        System.out.println("");
    }

    static void Copy(int[] X, int l, int r, int[] Y)
    {
        int i, j=0;
        for(i=l; i<r; i++)
        {
            Y[j++] = X[i];
        }
    }

    static void Merge_Sort(int[] A, int n)
    {
        int[] B = new int[n/2];
        int[] C = new int[n-(n/2)];
        if(n>1)
        {
            Copy(A, 0, (n/2), B);
            Copy(A, n/2, n, C);
            Merge_Sort(B, n/2);
            Merge_Sort(C, n-(n/2));
            Merge(B, C, A, n);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] A;
        System.out.print("Enter No. of Elements : ");
        int n = sc.nextInt();
        A = new int[n];
        System.out.println("Enter Array Elements : ");
        for (int i = 0; i < n; i++) {
            A[i] = sc.nextInt();
        }
        System.out.println("Array Before Sorting...");
        print_Arr(A, n);
        Merge_Sort(A, n);
        System.out.println("\nArray After Sorting...");
        print_Arr(A, n);
        sc.close();
    }
}