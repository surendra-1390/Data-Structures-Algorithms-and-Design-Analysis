import java.util.*;

public class Fractional_Knapsack
{
    static void interchange(int[] A, int a, int b)
    {
        int temp = A[a];
        A[a] = A[b];
        A[b] = temp;
    }

    static void interchange(double[] A, int a, int b)
    {
        double temp = A[a];
        A[a] = A[b];
        A[b] = temp;
    }

    static int Partition(double[] A, int[] B, int[] C, int start, int end)
    {
        double pivot = A[start];
        int pidx = start;
        for(int i=start+1; i<=end; i++)
        {
            if(A[i]>=pivot)
            {
                pidx=pidx+1;
                if(i!=pidx)
                {
                    interchange(A, i, pidx);
                    interchange(B, i, pidx);
                    interchange(C, i, pidx);
                    
                }
            }
        }
        interchange(A, start, pidx);
        interchange(B, start, pidx);
        interchange(C, start, pidx);
        return pidx;
    }

    static void Quick_Sort(double[] A, int[] B, int[] C, int start, int end)
    {
        if(start<end)
        {
            int pidx = Partition(A, B, C, start, end);
            Quick_Sort(A, B, C, start, pidx-1);
            Quick_Sort(A, B, C, pidx+1, end);
        }
    }

    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the No. of the Items : ");
        int n = sc.nextInt();
        int[] weight = new int[n];
        int[] cost = new int[n];
        double[] ratio = new double[n];
        for(int i=0; i<n; i++)
        {
            System.out.print("Enter the Weight of the Item "+ (i+1) +" : ");
            weight[i] = sc.nextInt();
            System.out.print("Enter the Cost of the Item "+ (i+1) +" : ");
            cost[i] = sc.nextInt();
            ratio[i] = (double)cost[i]/weight[i];
        }
        System.out.print("Enter the total Knapsack capacity in weights : ");
        int C = sc.nextInt();
        Quick_Sort(ratio, weight, cost, 0, n-1);
        System.out.println("\t\t  ");
        for (int i = 0; i < n; i++) {
            System.out.print("\tItem"+ (i+1));
        }
        System.out.print("\nWeight: ");
        for (int i = 0; i < n; i++) {
            System.out.print("  \t"+ weight[i]);
        }
        System.out.print("\nCost:  ");
        for (int i = 0; i < n; i++) {
            System.out.print("  \t"+ cost[i]);
        }
        System.out.print("\nRatio:  ");
        for (int i = 0; i < n; i++) {
            System.out.print("  \t"+ String.format("%.2f", ratio[i]));
        }
        double total_value=0;
        for(int i=0; i<n; i++)
        {
            double x=0;
            if(weight[i]<C)
            {
                System.out.print("\nFraction of Item"+ (i+1) +" taken : 1");
                x=1;
                C=C-weight[i];
            }
            else
            {
                x = (double)C/weight[i];
                System.out.print("\nFraction of Item"+ (i+1) +" taken : "+ String.format("%.2f", x));
                C=0;
            }
            total_value+=x*cost[i];
        }
        System.out.println("\nTotal Value : "+ total_value);
        sc.close();
    }
}