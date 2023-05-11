import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Objects;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(bf.readLine());
        HashMap<String, Words> words = new HashMap<>(2*n);

        bf.lines().limit(n).forEach(x->{
            Words temp = new Words(x);
            words.put(temp.macedonianWord, temp);
        });

       while (true){
           String idk = bf.readLine();
           if (idk.equals("KRAJ"))
               break;

           Words temp = words.get(idk);

           if(temp!=null)
               System.out.println(temp.englishWord);
           else
               System.out.println("/");
       }
    }
}

class Words{
    String englishWord;
    String macedonianWord;

    public Words(String x) {
        String[] parts = x.split("\\s+");

        englishWord = parts[0];
        macedonianWord = parts[1];
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Words words = (Words) o;
        return Objects.equals(englishWord, words.englishWord) && Objects.equals(macedonianWord, words.macedonianWord);
    }

    @Override
    public int hashCode() {
        return Objects.hash(englishWord);
    }
}