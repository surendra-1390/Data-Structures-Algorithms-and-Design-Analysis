import java.util.Scanner;

class Large_Num_Multiplication
{
    public static void main(String[] Args)
    {
        Scanner sc = new Scanner(System.in);
        
        boolean flag = true;
        System.out.print("Enter First integer value : ");
        int Num1 = sc.nextInt();
        String num1 = to_binary(Num1);
        while(flag)
        {
            if(!Power_of_2(num1.length()))
            {
                System.out.println("Invalid Entry...");
                System.out.println("Enter integer with no. of bits power of 2...");
                Num1 = sc.nextInt();
                num1 = to_binary(Num1);
            }
            else
                flag = false;
        }
        System.out.print("Enter Second integer value : ");
        int Num2 = sc.nextInt();
        String num2 = to_binary(Num2);
        flag = true;
        while(flag)
        {
            if(!Power_of_2(num2.length()))
            {
                System.out.println("Invalid Entry...");
                System.out.println("Enter integer with no. of bits power of 2...");
                Num2 = sc.nextInt();
                num2 = to_binary(Num2);
            }
            else
                flag = false;
        }
        System.out.println("Multipication of "+ Num1 +" and "+ Num2 +" is "+ multiply(num1, num2));
        sc.close();
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

    static String to_binary(int n)
    {
        String result = "";
        while(n != 0)
        {
            if(n%2 == 1)
                result = '1' + result;
            else
                result = '0' + result;
            n = n/2;
        }
        return result;
    }
    
    static String[] Equal_length(String U, String V)
    {
        String[] s = new String[2];
        int len1 = U.length();
        int len2 = V.length();
        if(len1 < len2)
        {
            for (int i = 0; i<(len2-len1); i++)
                U = '0' + U;
        }
        else if(len1 > len2)
        {
            for (int i = 0; i<(len1-len2); i++)
                V = '0' + V;
        }
        s[0] = U;
        s[1] = V;
        return s;
    }

    static int multiplysinglebit(String U, String V)
    {
        int res = (U.charAt(0) - '0') * (V.charAt(0) - '0');
        return res;
    }

    static String addbits(String first, String second)
    {
        String[] res = new String[2];
        res = Equal_length(first, second);
        first = res[0];
        second = res[1];
        int length = first.length();
        String result = "";
        int carry = 0;
        for (int i = length - 1; i >= 0; i--)
        {
            int firstBit = first.charAt(i) - '0';
            int secondBit = second.charAt(i) - '0';
            int sum = (firstBit ^ secondBit ^ carry) + '0';
            result = (char) sum + result;
            carry = (firstBit & secondBit) | (secondBit & carry) | (firstBit & carry);
        }
        if (carry == 1)
            result = '1' + result;
        return result;
    }

    static long multiply(String U, String V)
    {
        String[] res = new String[2];
        res = Equal_length(U, V);
        U = res[0];
        V = res[1];
        int n = U.length();
        if(n == 0)
            return 0;
        if(n == 1)
            return multiplysinglebit(U, V);
        int mid = n/2;
        String W = U.substring(0, mid);
        String X = U.substring(mid, n);
        String Y = V.substring(0, mid);
        String Z = V.substring(mid, n);
        long M1 = multiply(W, Y);
        long M2 = multiply(X, Z);
        long M3 = multiply(addbits(W, X), addbits(Y, Z));
        long val = M1*(1<<(2*(n-mid))) + (M3 - M1 - M2)*(1<<(n-mid)) + M2;
        return val;
    }
}

