import java.util.*;
import java.lang.Math;

public class Min_Max_DC {
    static int min(int[] A, int low, int high)
    {
        if((high-low) == 1)
        {
            if(A[low]<A[high])
                return A[low];
            else
                return A[high];
        }
        else
        {
            int mid = (low+high)/2;
            int m1 = min(A, low, mid);
            int m2 = min(A, mid+1, high);
            int m = Math.min(m1, m2);
            return m;
        }
    }

    static int max(int[] A, int low, int high)
    {
        if(high-low == 1)
        {
            if(A[low]>A[high])
                return A[low];
            else
                return A[high];
        }
        else
        {
            int mid = (low+high)/2;
            int m1 = max(A, low, mid);
            int m2 = max(A, mid+1, high);
            int m = Math.max(m1, m2);
            return m;
        }
    }

    static boolean Power_of_2(int n)
    {
        while(n != 1)
        {
            if(n%2 == 1)
                return false;
            n= n/2;
        }
        return true;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] A;
        boolean flag = true;
        System.out.print("Enter No. of Elements : ");
        int n = sc.nextInt();
        while(flag)
        {
            if(!Power_of_2(n))
            {
                System.out.println("Invalid Entry...");
                System.out.println("Enter integer which is power of 2...");
                n = sc.nextInt();
            }
            else
                flag = false;
        }
        A = new int[n];
        System.out.println("Enter Array Elements : ");
        for (int i = 0; i < n; i++) {
            A[i] = sc.nextInt();
        }
        int min = min(A, 0, n-1);
        int max = max(A, 0, n-1);
        System.out.println("Minimum value : "+ min);
        System.out.println("Maximum value : "+ max);
        sc.close();
    }
}