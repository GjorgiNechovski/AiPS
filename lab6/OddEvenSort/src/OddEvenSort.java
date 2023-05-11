import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class OddEvenSort {

    static void oddEvenSort(int[] a, int n)
    {
        // Vasiot kod tuka
        ArrayList<Integer> parni = new ArrayList<>();
        ArrayList<Integer> neparni = new ArrayList<>();

        for (int i=0; i<n; i++) {
            if (a[i] % 2 == 0)
                parni.add(a[i]);
            else
                neparni.add(a[i]);
        }

        parni.sort(Comparator.reverseOrder());
        neparni.sort(Comparator.naturalOrder());

        ArrayList<Integer> all = new ArrayList<>();
        all.addAll(neparni);
        all.addAll(parni);

        for (int i=0; i<n; i++)
            a[i] = all.get(i);
    }

    public static void main(String[] args) throws IOException{
        int i;
        BufferedReader stdin = new BufferedReader( new InputStreamReader(System.in));
        String s = stdin.readLine();
        int n = Integer.parseInt(s);

        s = stdin.readLine();
        String [] pom = s.split(" ");
        int [] a = new int[n];
        for(i=0;i<n;i++)
            a[i]=Integer.parseInt(pom[i]);
        oddEvenSort(a,n);
        for(i=0;i<n-1;i++)
            System.out.print(a[i]+" ");
        System.out.print(a[i]);
    }
}