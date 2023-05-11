import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.NoSuchElementException;
import java.util.Stack;

public class CheckXML {

    public static void main(String[] args) throws Exception{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        int n = Integer.parseInt(s);
        String [] redovi = new String[n];

        for(int i=0;i<n;i++)
            redovi[i] = br.readLine();

        int valid=1;

        Stack<String> all = new Stack<String>();
        for (int i=0; i<n; i++){
            if(redovi[i].charAt(1)!='/' && redovi[i].charAt(0)=='[') {
                all.push(redovi[i]);
            }
            else if(redovi[i].charAt(1)=='/'){
                String segashno = redovi[i];
                String pregledaj = all.pop();
                if(segashno.substring(2,segashno.length()-1).equals(pregledaj.substring(1,pregledaj.length()-1)))
                    continue;
                else{
                    valid=0;
                    break;
                }
            }
        }

        // Vasiot kod tuka
        // Moze da koristite dopolnitelni funkcii ako vi se potrebni

        System.out.println(valid);

        br.close();
    }
}