import java.io.InputStream;
import java.util.Arrays;
import java.util.Scanner;


public class main
{
    static void pushZerosToEnd(int arr[], int n)
    {

        int j=0;
        for (int i=0; i<arr.length; i++)
        {
            if(arr[i]!=0)
                arr[j++] = arr[i];
        }
        while(j<n)
            arr[j++] = 0;

        System.out.println("Transformiranata niza e:");
        for (int k : arr) System.out.print(k + " ");
    }

    public static void main (String[] args)
    {
        int arr[];
        int n;

        Scanner k = new Scanner(System.in);
        n = k.nextInt();

        arr = new int[n];
        for (int i=0; i<n; i++)
            arr[i] = k.nextInt();

        pushZerosToEnd(arr, n);

    }
}