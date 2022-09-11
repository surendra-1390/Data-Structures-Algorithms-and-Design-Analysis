import java.util.*;

class Set {
    int a, b, w;
    Set(int a, int b, int w) {
        this.a = a;
        this.b = b;
        this.w = w;
    }
}

public class Prims_Algo {

    static int max = Integer.MAX_VALUE;

    static void print(int[][] A, int n) {
        for (int i = 0; i < n; i++) {
            for (int j = i+1; j < n; j++) {
                if(A[i][j] != max)
                    System.out.println("E("+ i +", "+ j +") -> W("+ A[i][j] +")");
            }
        }
    }

    static void assign_Max(int[][] A, int n) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                A[i][j] = max;
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter No. of Vertices and Edges : ");
        int V = sc.nextInt();
        int E = sc.nextInt();
        int[][] Graph = new int[V][V];
        assign_Max(Graph, V);
        System.out.println("Enter two Vertices that has Edge and weight : ");
        for(int i=0; i<E; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            int w = sc.nextInt();
            Graph[a][b] = w;
            Graph[b][a] = w;
        }
        System.out.println("\nGraph :");
        print(Graph, V);
        int[][] Tree = new int[V][V];
        assign_Max(Tree, V);
        int[] selected = new int[V];
        for (int i = 0; i < V; i++)
            selected[i] = 0;
        int Edge = 0;
        selected[0] = 1;
        while(Edge < V-1) {
            PriorityQueue<Set> PQ = new PriorityQueue<Set>(Comparator.comparingInt(s -> s.w));
            for(int j=0; j < V; j++) {
                for(int k=0; k < V; k++) {
                    if(selected[j] == 0 && selected[k] == 1)
                        PQ.add(new Set(j, k, Graph[j][k]));
                }
            }
            Set s = PQ.peek();
            selected[s.a] = 1;
            Tree[s.a][s.b] = s.w;
            Tree[s.b][s.a] = s.w;
            ++Edge;
        }
        System.out.println("\nTree :");
        print(Tree, V);
        sc.close();
    }
}