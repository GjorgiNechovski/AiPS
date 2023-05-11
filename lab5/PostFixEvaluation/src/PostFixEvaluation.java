import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.NoSuchElementException;

interface Stack<E> {

    // Elementi na stekot se objekti od proizvolen tip.

    // Metodi za pristap:

    public boolean isEmpty ();
    // Vrakja true ako i samo ako stekot e prazen.

    public E peek ();
    // Go vrakja elementot na vrvot od stekot.

    // Metodi za transformacija:

    public void clear ();
    // Go prazni stekot.

    public void push (E x);
    // Go dodava x na vrvot na stekot.

    public E pop ();
    // Go otstranuva i vrakja elementot shto e na vrvot na stekot.
}

class ArrayStack<E> implements Stack<E> {
    private E[] elems;
    private int depth;

    @SuppressWarnings("unchecked")
    public ArrayStack (int maxDepth) {
        // Konstrukcija na nov, prazen stek.
        elems = (E[]) new Object[maxDepth];
        depth = 0;
    }


    public boolean isEmpty () {
        // Vrakja true ako i samo ako stekot e prazen.
        return (depth == 0);
    }


    public E peek () {
        // Go vrakja elementot na vrvot od stekot.
        if (depth == 0)
            throw new NoSuchElementException();
        return elems[depth-1];
    }


    public void clear () {
        // Go prazni stekot.
        for (int i = 0; i < depth; i++)  elems[i] = null;
        depth = 0;
    }


    public void push (E x) {
        // Go dodava x na vrvot na stekot.
        elems[depth++] = x;
    }


    public E pop () {
        // Go otstranuva i vrakja elementot shto e na vrvot na stekot.
        if (depth == 0)
            throw new NoSuchElementException();
        E topmost = elems[--depth];
        elems[depth] = null;
        return topmost;
    }
}

public class PostFixEvaluation {

    static int evaluatePostfix(char [] izraz, int n)
    {
        String input = new String(izraz);

        String[] parts = input.split("\\s+");

        ArrayStack<Integer> all = new ArrayStack<>(parts.length);
        for (int i=0; i<parts.length; i++){
            try{
                int broj = Integer.parseInt(parts[i]);
                all.push(broj);
            }
            catch (Exception e){
                char znak = parts[i].charAt(0);
                switch (znak){
                    case '+':{
                        int vrednost = all.pop() + all.pop();
                        all.push(vrednost);
                        break;
                    }
                    case '-':{
                        int broj1=all.pop();
                        int broj2=all.pop();
                        int vrednost = broj2-broj1;
                        all.push(vrednost);
                        break;
                    }
                    case '/':{
                        int broj1=all.pop();
                        int broj2=all.pop();
                        int vrednost = broj2/broj1;
                        all.push(vrednost);
                        break;
                    }
                    case '*':{
                        int vrednost = all.pop() * all.pop();
                        all.push(vrednost);
                        break;
                    }
                }
            }
        }
        return all.pop();
    }

    public static void main(String[] args) throws Exception{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String expression = br.readLine();
        char exp[] = expression.toCharArray();

        int rez = evaluatePostfix(exp, exp.length);
        System.out.println(rez);

        br.close();

    }

}