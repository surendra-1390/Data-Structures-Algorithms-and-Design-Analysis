public class QuickSort
{
    static int Partition(int[] A, int start, int end)
    {
        int pivot = A[start];
        int pidx = start;
        for(int i=start+1; i<=end; i++)
        {
            if(A[i]>=pivot)
            {
                pidx=pidx+1;
                if(i!=pidx)
                {
                    int temp = A[i];
                    A[i] = A[pidx];
                    A[pidx] = temp;
                }
            }
        }
        int temp = A[pidx];
        A[pidx] = A[start];
        A[start] = temp;
        return pidx;
    }

    static void Quick_Sort(int[] A, int start, int end)
    {
        if(start<end)
        {
            int pidx = Partition(A, start, end);
            Quick_Sort(A, start, pidx-1);
            Quick_Sort(A, pidx+1, end);
        }
    }

    static void print(int[] A)
    {
        for(int i=0; i<8; i++)
            System.out.print(A[i] +"  ");
        System.out.println();
    }

    public static void main(String[] args)
    {
        int[] A = {4,5,8,3,10,2,7,5};
        print(A);
        Quick_Sort(A, 0, 7);
        print(A);
    }
}