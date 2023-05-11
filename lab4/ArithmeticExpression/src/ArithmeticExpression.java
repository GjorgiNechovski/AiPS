import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ArithmeticExpression {

    // funkcija za presmetuvanje na izrazot pocnuvajki
    // od indeks l, zavrsuvajki vo indeks r
    static int presmetaj(char c[], int l, int r) {
        // Vasiot kod tuka

        List<Integer> brojki = new ArrayList<Integer>();
        List<Character> znaci = new ArrayList<Character>();

        int sum=0,z=0;
        for (int i=0; i<c.length; i++){
            if(c[i]=='(')
                continue;
            if(Character.isDigit(c[i]))
                brojki.add(Integer.parseInt(String.valueOf(c[i])));

            else if(c[i]!=')')
                znaci.add(c[i]);

            if(c[i]==')'){
                if(znaci.get(znaci.size()-1)=='+')
                    sum = sum+(brojki.get(brojki.size()-2) + brojki.get(brojki.size()-1));

                else
                    sum = sum+(brojki.get(brojki.size()-2) - brojki.get(brojki.size()-1));

                brojki.remove(brojki.size()-1);
                brojki.remove(brojki.size()-1);
                brojki.add(sum);
                znaci.remove(znaci.size()-1);
                z=sum;
                sum=0;
            }
        }
        return z;
    }

    public static void main(String[] args) throws Exception {
        int i,j,k;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String expression = br.readLine();
        char exp[] = expression.toCharArray();

        int rez = presmetaj(exp, 0, exp.length-1);
        System.out.println(rez);

        br.close();

    }

}
