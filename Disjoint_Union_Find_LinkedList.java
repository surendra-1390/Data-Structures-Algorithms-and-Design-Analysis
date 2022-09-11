import java.util.*;

public class Disjoint_Union_Find_LinkedList {
    static HashMap<Character, Integer> char_id;
    static Sub_Set[] set;
    static char[] Vertex;

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
            Single_Set node = new Single_Set(List[i], null);
            set[i] = new Sub_Set(i, node, node);
        }
    }

    static int find(char ch) {
        while(char_id.get(ch) != set[char_id.get(ch)].id) {
            ch = Key(set[char_id.get(ch)].id);
        }
        return char_id.get(ch);
    }

    static void union(char x, char y) {
        Single_Set node = set[find(x)].last;
        node.next = set[find(y)].first;
        set[find(x)].last = set[find(y)].last;
        set[find(y)].first = null;
        set[find(y)].last = null;
        set[char_id.get(y)].id = char_id.get(x);
        System.out.println("Union of "+ x +", "+ y +" is done...");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter no. of vertices : ");
        int n = sc.nextInt();
        Vertex = new char[n];
        System.out.println("Enter the List of Vertices : ");
        for (int i = 0; i < n; i++) {
            Vertex[i] = sc.next().charAt(0);
        }
        char_id = new HashMap<Character, Integer>();
        for (int i = 0; i < Vertex.length; i++) {
            char_id.put(Vertex[i], i);
        }
        set = new Sub_Set[Vertex.length];
        makeset(Vertex);
        union('a', 'b');
        union('c', 'e');
        union('b', 'f');
        System.out.println("\nFinding the root id : ");
        for (char ch : Vertex) {
            System.out.println(ch +" : "+ Key(find(ch)));
        }
        sc.close();
    }
}

class Sub_Set {
    int id;
    Single_Set first = null;
    Single_Set last = null;
    Sub_Set(int id, Single_Set first, Single_Set last)
    {
        this.id = id;
        this.first = first;
        this.last = last;
    }
}

class Single_Set {
    char ch;
    Single_Set next = null;
    Single_Set(char ch, Single_Set next)
    {
        this.ch = ch;
        this.next = next;
    }
}