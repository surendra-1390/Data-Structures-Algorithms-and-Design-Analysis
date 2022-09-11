import java.io.*;
import java.util.*;
import java.nio.file.*;

class HuffNode implements Serializable {

    private static final long serialVersionUID = 1L;
    Character ch;
    Integer freq;
    HuffNode left = null;
    HuffNode right = null; 

    HuffNode(Character ch, Integer freq) {
        this.ch = ch;
        this.freq = freq;
    }

    HuffNode(Character ch, Integer freq, HuffNode left, HuffNode right) {
        this.ch = ch;
        this.freq = freq;
        this.left = left;
        this.right = right;
    }
}

public class Huffmann_encode_decode {
    static HashMap<Character, String> Code = new HashMap<>();
    
    static void huffmanCode(HuffNode root, String str) {
        if(root == null)
            return;
        if(root.left==null && root.right==null)
            Code.put(root.ch, str);
        huffmanCode(root.left, str+"0");
        huffmanCode(root.right, str+"1");
    }

    static void encode(HuffNode root, String str) {
        
        huffmanCode(root, "");
        System.out.println(Code);
        try {
            FileWriter f = new FileWriter("C:/Users/Bellamkonda Pavan/DAA/problems/encode.txt");
            char[] ch = str.toCharArray();
            for(char c: ch)
                f.write(Code.get(c));
            f.close(); 
        }
        catch (Exception e) {
            System.err.println("" + e);
        }
        System.out.println("\nEncoding Successful...\n");
    }

    static int decode(HuffNode root, int index, String str, FileWriter f) {
        if (root == null)
            return index;
        if (root.left==null && root.right==null) {
            try {
                f.write(root.ch);
            } catch (Exception e) {
                System.err.println("" + e);
            }
            return index;
        }
        index++;
        root = (str.charAt(index) == '0') ? root.left : root.right;
        index = decode(root, index, str, f);
        return index;
    }

    public static void main(String[] args) {
        try {
            String data = new String(Files.readAllBytes(Paths.get("C:/Users/Bellamkonda Pavan/DAA/problems/input.txt")));
            char[] ch = data.toCharArray();
            HashMap<Character, Integer> charCount = new HashMap<Character, Integer>();
            for (char c : ch)
                if (charCount.containsKey(c))
                    charCount.put(c, charCount.get(c) + 1);
                else
                    charCount.put(c, 1);
            System.out.println(charCount);
            PriorityQueue<HuffNode> PQ = new PriorityQueue<HuffNode>(Comparator.comparingInt(node -> node.freq));
            for (var entry : charCount.entrySet())
                PQ.add(new HuffNode(entry.getKey(), entry.getValue()));
            while (PQ.size() != 1) {
                HuffNode left = PQ.poll();
                HuffNode right = PQ.poll();
                int sum = left.freq + right.freq;
                PQ.add(new HuffNode(null, sum, left, right));
            }
            HuffNode root = PQ.peek();
            FileOutputStream fos = new FileOutputStream("C:/Users/Bellamkonda Pavan/DAA/problems/obj.ser");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(root);
            oos.close();
            System.out.println("\nObject Serialized...\n");
            encode(root, data);String str = new String(Files.readAllBytes(Paths.get("C:/Users/Bellamkonda Pavan/DAA/problems/encode.txt")));
            int index = -1;
            FileWriter f = new FileWriter("C:/Users/Bellamkonda Pavan/DAA/problems/decode.txt");
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("C:/Users/Bellamkonda Pavan/DAA/problems/obj.ser"));
            HuffNode r = (HuffNode) ois.readObject();
            while (index < str.length() - 1)
                index = decode(r, index, str, f);
            System.out.println("Decoding Successful...");
            ois.close();
            f.close();
        } catch (Exception e) {
            System.err.println("" + e);
        }
   }
}