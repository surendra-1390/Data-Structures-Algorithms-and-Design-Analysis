import java.util.*;

public class Disjoint_Union_Find_RootedTree {
    static HashMap<Character, Integer> char_id;
    static char[] p;
    static int[] rank;

    static char Key(int value)
    {
        for(var entry : char_id.entrySet()) {
            if(entry.getValue() == value)
                return entry.getKey();
        }
        return '\0';
    }

    static void makeset(char[] List) {
        for (int i = 0; i < List.length; i++) {
            p[i] = List[i];
            rank[i] = 0;
        }
    }

    static char find(char ch) {
        if(ch != p[char_id.get(ch)])
            p[char_id.get(ch)] = find(p[char_id.get(ch)]);
        return p[char_id.get(ch)];
    }

    static void union(char x, char y) {
        if(rank[char_id.get(x)] > rank[char_id.get(y)])
            p[char_id.get(y)] = x;
        else {
            p[char_id.get(x)] = y;
            if(rank[char_id.get(x)] == rank[char_id.get(y)])
                rank[char_id.get(y)] = rank[char_id.get(y)] + 1;
        }
        System.out.println("Union of "+ x +", "+ y +" is done...");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter no. of vertices : ");
        int n = sc.nextInt();
        char[] Vertex = new char[n];
        System.out.println("Enter the List of Vertices : ");
        for (int i = 0; i < n; i++) {
            Vertex[i] = sc.next().charAt(0);
        }
        char_id = new HashMap<Character, Integer>();
        for (int i = 0; i < Vertex.length; i++) {
            char_id.put(Vertex[i], i);
        }
        p = new char[n];
        rank = new int[n];
        makeset(Vertex);
        union('a', 'b');
        union('c', 'e');
        union('b', 'f');
        System.out.println("\nFinding the root : ");
        for (char ch : Vertex) {
            System.out.println(ch +" : "+ find(ch));
        }
        sc.close();
    }
}