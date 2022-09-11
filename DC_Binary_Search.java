import java.util.*;

public class DC_Binary_Search {
    static int Binary_Search(int[] Arr, int left, int right, int target)
    {
        int mid;
        if(left <= right)
        {
            mid = (left+right)/2;
            if(target == Arr[mid])
                return mid;
            else if(target < Arr[mid])
                return Binary_Search(Arr, left, mid-1, target);
            else
                return Binary_Search(Arr, mid+1, right, target);
        }
        else
            return -1;
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
        System.out.print("Enter Target Element : ");
        int target = sc.nextInt();
        int target_index = Binary_Search(A, 0, n-1, target);
        if(target_index != -1)
            System.out.println(target +" is Found at Index "+ target_index);
        else
            System.out.println(target +" is Not Found...");
        sc.close();
    }
}