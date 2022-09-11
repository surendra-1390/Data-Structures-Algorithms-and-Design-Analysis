import java.util.HashMap;  
import java.util.Map;  


public class String_Matching  
{  
    /**  
    * @param text -- trace the text to see if it contains pattern 
    * @param pattern -- look for this text inside the text parameter 
    * @return -- return index of the first match or -1 if not found 
    */  
    public static int findBruteForce(char[] text, char[] pattern)   
    {  
        System.out.println("Brute force looking for " + String.valueOf(pattern) + " in " + String.valueOf(text));  
        int n = text.length;  
        int m = pattern.length;  
        //checks if the string is empty  
        if (m == 0) return 0;  
        //brute force it -- loop over all characters in text O(n)  
        for (int i=0;i<=n-m;i++)   
        { //index into the text  
            //loop over all characters in pattern while characters match O(m)  
            //index into the pattern  
            int k = 0;   
            while (k<m && text[i+k] == pattern[k])   
            {  
                k++;  
            }  
            //if at end of the pattern, then found match starting at index i in text  
            if (k==m)   
            {  
                System.out.println("\tFound match in the given text at index " + i);  
                return i;  
            }  
        }  
        //if match not found  
        System.out.println("\tNo match found in the given text.");  
        return -1;  
    }  
    /** 
     
    * @param text -- search this text to see if it contains pattern 
    * @param pattern -- look for this text inside the text parameter 
    * @return -- return index of the first match or -1 if not found 
    */  
    public static int findBoyerMoore(char[] text, char[] pattern)   
    {  
        System.out.println("Boyer-Moore looking for " + String.valueOf(pattern) + " in " + String.valueOf(text));  
        int n = text.length;  
        int m = pattern.length;  
        // Test for empty string  
        if (m == 0) return 0;  
        // Initialization, create Map of last position of each character = O(n)  
        Map<Character, Integer> last = new HashMap<>();  
        for (int i = 0; i < n; i++)   
        {  
        // set all chars, by default, to -1      
        last.put(text[i], -1);     
        }          
        for (int i = 0; i < m; i++)   
        {  
        // update last seen positions      
        last.put(pattern[i], i);   
        }  
        //Start with the end of the pattern aligned at index m-1 in the text.   
        //index into the text  
        int i = m - 1;    
        // index into the pattern  
        int k = m - 1;    
        while (i < n)   
        {   
            if (text[i] == pattern[k])   
            {  
                // match! return i if complete match; otherwise, keep checking      
                if (k == 0)   
                {  
                    System.out.println("\tFound match in the given text at index " + i);  
                    return i;   
                }  
                i--; k--;  
            }   
            else   
            { // jump step + restart at end of pattern  
                //iterate over text   
                i += m - Math.min(k, 1 + last.get(text[i]));    
                //move to end of pattern  
                k = m - 1;   
            }  
        }  
        System.out.println("\tNo match found in the given text.");  
        // not found  
        return -1;   
    }  

    public static void KMP(String text, String pattern)
    {
        // base case 1: pattern is null or empty
        if (pattern == null || pattern.length() == 0)
        {
            System.out.println("The pattern occurs with shift 0");
            return;
        }
 
        // base case 2: text is NULL, or text's length is less than that of pattern's
        if (text == null || pattern.length() > text.length())
        {
            System.out.println("Pattern not found");
            return;
        }
 
        char[] chars = pattern.toCharArray();
 
        // next[i] stores the index of the next best partial match
        int[] next = new int[pattern.length() + 1];
        for (int i = 1; i < pattern.length(); i++)
        {
            int j = next[i + 1];
 
            while (j > 0 && chars[j] != chars[i]) {
                j = next[j];
            }
 
            if (j > 0 || chars[j] == chars[i]) {
                next[i + 1] = j + 1;
            }
        }
 
        for (int i = 0, j = 0; i < text.length(); i++)
        {
            if (j < pattern.length() && text.charAt(i) == pattern.charAt(j))
            {
                if (++j == pattern.length()) {
                    System.out.println("The pattern occurs with shift " + (i - j + 1));
                }
            }
            else if (j > 0)
            {
                j = next[j];
                i--;    // since `i` will be incremented in the next iteration
            }
        }
    }



    public static void main(String args[])   
    {  
        char[] text = "abcfefabddef".toCharArray();  
        char[] pattern = "abddef".toCharArray();  
        //function calling  
        findBruteForce(text,pattern);  
        findBoyerMoore(text,pattern);   
        KMP("abcfefabddef", "abddef");       
    }  
}   