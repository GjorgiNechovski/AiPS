import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Objects;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(bf.readLine());

        HashMap<String, Korisnik> korisnici = new HashMap<>(n*2);

        bf.lines().limit(n).forEach(x->{
            Korisnik temp = new Korisnik(x);
            korisnici.put(temp.name, temp);
        });

        while(true){
            String[] idk = bf.readLine().split("\\s+");

            if(Objects.equals(idk[0], "KRAJ"))
                break;

            Korisnik temp = korisnici.get(idk[0]);

            if(temp!=null && idk[1].equals(temp.password)) {
                System.out.println("Najaven");
                break;
            }
            else
                System.out.println("Nenajaven");
        }
    }
}

class Korisnik{
    String name;
    String password;

    public Korisnik(String all) {
        String[]parts = all.split("\\s+");

        name = parts[0];
        password = parts[1];
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Korisnik korisnik = (Korisnik) o;
        return Objects.equals(name, korisnik.name) && Objects.equals(password, korisnik.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, password);
    }
}