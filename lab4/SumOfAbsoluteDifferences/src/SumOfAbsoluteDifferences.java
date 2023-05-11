import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

import static java.lang.Math.abs;

public class SumOfAbsoluteDifferences {

    static int solve(int numbers[], int N, int K) {
        // vasiot kod ovde
        // mozete da napisete i drugi funkcii dokolku vi se potrebni

        List<Integer> numbersList = new ArrayList<>();

        List<Integer> maxElements = new ArrayList<>();
        List<Integer> minElements = new ArrayList<>();


        for (int i = 0; i < numbers.length; i++) {
            numbersList.add(numbers[i]);
        }

        int brojach=0;
        while(brojach<K) {
            int min = 1000, max = 0, tempMAX = 0, tempMIN = 0;

            for (int i = 0; i < numbersList.size(); i++) {
                if (numbersList.get(i) > max) {
                    max = numbersList.get(i);
                    tempMAX = i;
                }
            }
            for (int i = 0; i < numbersList.size(); i++) {
                if (numbersList.get(i) < min) {
                    min = numbersList.get(i);
                    tempMIN = i;
                }
            }

            maxElements.add(max);
            minElements.add(min);


            if(tempMIN<tempMAX) {
                numbersList.remove(tempMIN);
                numbersList.remove(tempMAX-1);
            }
            else{
                numbersList.remove(tempMIN);
                numbersList.remove(tempMAX);
            }
            brojach+=2;
        }

        numbersList.clear();

        for (int i=0; i<numbers.length; i++){
            for (int j=0; j<maxElements.size(); j++){
                if(numbers[i]==maxElements.get(j)) {
                    numbersList.add(maxElements.get(j));
                    maxElements.remove(j);
                    break;
                }
            }

            for (int j=0; j<minElements.size(); j++){
                if(numbers[i]==minElements.get(j)) {
                    numbersList.add(minElements.get(j));
                    minElements.remove(j);
                    break;
                }
            }
        }

        int sum=0;

        for (int i=0; i<numbersList.size()-1; i++)
            sum = sum+ Math.abs(numbersList.get(i+1)-numbersList.get(i));

        return sum;
    }

    private static int getSum(List<Integer> maxElements, List<Integer> minElements, int brojach, int sum) {
        while(maxElements.size()!=0 && minElements.size()!=0) {
            sum = sum + Math.abs(maxElements.get(maxElements.size()-1) - minElements.get(minElements.size()-1));

            maxElements.remove(maxElements.get(maxElements.size()-1));
            minElements.remove(minElements.get(minElements.size()-1));
        }
        return sum;
    }

    public static void main(String[] args) throws Exception {
        int i,j,k;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int numbers[] = new int[N];

        st = new StringTokenizer(br.readLine());
        for (i=0;i<N;i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }

        int res = solve(numbers, N, K);
        System.out.println(res);

        br.close();

    }

}