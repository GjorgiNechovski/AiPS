import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ZigZagSequence {

    static int najdiNajdolgaCikCak(int a[]) {
        // Vasiot kod tuka

        boolean znak;
        int i=0;
        if(a[i]==0)
            znak = vidiZnak(a[++i]);
        else
            znak = vidiZnak(a[i]);

        int max=0, sequence = 1;
        for (; i<a.length; i++){

            if(a[i]==0){
                if(sequence>max){
                    max=sequence;
                }
                sequence=1;
                if(i+1<a.length)
                    znak = vidiZnak(a[++i]);
                else
                    break;
            }

            if(znak && a[i]<0){
                sequence++;
                znak=vidiZnak(a[i]);
            }
            else if(!znak && a[i]>0){
                sequence++;
                znak = vidiZnak(a[i]);
            }

            else {
                if(sequence>max){
                    max=sequence;
                }
                sequence=1;
                znak = vidiZnak(a[i]);
            }

        }
        if(sequence>max){
            max=sequence;
        }

        return max;
    }

    static boolean vidiZnak(int a){
        return a>0;
    }


    public static void main(String[] args) throws Exception {
        int i,j,k;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int a[] = new int[N];
        for (i=0;i<N;i++)
            a[i] = Integer.parseInt(br.readLine());

        int rez = najdiNajdolgaCikCak(a);
        System.out.println(rez);

        br.close();

    }

}
