import java.util.Scanner;
import java.util.ArrayList;

public class LCS {
    static String A, B;
    static int m, n;
    static int[][] L;
    static ArrayList<String> S = new ArrayList<String>();
    static ArrayList<String> S_new = new ArrayList<String>();
    static int ind = -1;

   static int Long_len() {
        L = new int[m+1][n+1];
        for (int i = 0; i <= m; i++) {
            L[i][0] = 0;
        }
        for (int i = 0; i <= n; i++) {
            L[0][i] = 0;
        }
        for(int i = 1; i <= m; i++) {
            for(int j = 1; j <= n; j++) {
                if(A.charAt(i-1) == B.charAt(j-1))
                    L[i][j] = L[i-1][j-1] + 1;
                else
                    L[i][j] = Math.max(L[i][j-1], L[i-1][j]);
            }
        }
        System.out.print("      ");
        for (int i = 0; i < n; i++) {
            System.out.print(B.charAt(i) +"  ");
        }
        System.out.println("");
        for (int i = 0; i <= m; i++) {
            if(i != 0)
                System.out.print(A.charAt(i-1) +"  ");
            else
                System.out.print("   ");
            for (int j = 0; j <= n; j++) {
                System.out.print(L[i][j] +"  ");
            }
            System.out.println("");
        }
        return L[m][n];
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter String A : ");
        A = sc.nextLine();
        System.out.print("Enter String B : ");
        B = sc.nextLine();
        m = A.length();
        n = B.length();
        int Long_Len = Long_len();
        System.out.println("Lenght of LCS : "+ Long_Len);
        S.add(seq(m, n, ""));
        for (String s : S) {
            if(s.length() == Long_Len && !(S_new.contains(s)))
                S_new.add(s);
        }
        System.out.println("All possible LCS's : "+ S);
        System.out.println("All possible LCS's : "+ S_new);
        sc.close();
    }

    static String seq(int i, int j, String s) {
        while(i>0 && j>0 && L[i][j] != 0) {
            if(A.charAt(i-1) == B.charAt(j-1)) {
                s = A.charAt(i-1)+s;
                i--;
                j--;
            }
            else {
                if(L[i-1][j] > L[i][j-1])
                    i--;
                else if(L[i-1][j] < L[i][j-1])
                    j--;
                else if (L[i][j] == L[i-1][j] && L[i][j] == L[i][j-1] && L[i][j] != 0) {
                    S.add(s);
                    ind = ind + 1;
                    S.set(ind, seq(i-1, j, S.get(ind)));
                    S.add(s);
                    ind = ind + 1;
                    S.set(ind, seq(i, j-1, S.get(ind)));
                    break;
                }
            }
        }
        return s;
    }
}