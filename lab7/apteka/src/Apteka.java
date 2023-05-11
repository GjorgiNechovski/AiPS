import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Objects;

public class Apteka {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(bf.readLine());

        HashMap<String, Lek> lekovi = new HashMap<>(n*2);

        bf.lines().limit(n).forEach(x->{
            Lek temp = new Lek(x);
            lekovi.put(temp.getName().toUpperCase(), temp);
        });

        while(true){
            String idk = bf.readLine();
            if(Objects.equals(idk, "KRAJ"))
                break;

            int broj = Integer.parseInt(bf.readLine());

            Lek temp = lekovi.get(idk.toUpperCase());
            if(temp==null)
                System.out.println("Nema takov lek");

            else{
                System.out.println(temp);
                if(broj>temp.getCount())
                    System.out.println("Nema dovolno lekovi");
                else {
                    System.out.println("Napravena naracka");
                    temp.setCount(temp.getCount()-broj);
                }
            }
        }

    }
}

class Lek{
    String name;
    int available;
    int price;
    int count;

    public Lek(String all) {
        String[] parts = all.split("\\s+");

        name = parts[0];
        available = Integer.parseInt(parts[1]);
        price = Integer.parseInt(parts[2]);
        count = Integer.parseInt(parts[3]);
    }

    public String getName() {
        return name;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lek lek = (Lek) o;
        return available == lek.available && price == lek.price && count == lek.count && Objects.equals(name, lek.name);
    }

    @Override
    public int hashCode() {
        //(29∗(29∗(29∗0+ASCII(c1))+ASCII(c2))+ASCII(c3))%102780
        return (29*(29*(29*0*name.charAt(0))+name.charAt(1))+name.charAt(2))%102780;
    }

    @Override
    public String toString() {
        String temp = "";
        if(available==0)
            temp+="NEG";
        else
            temp+="POZ";

        return String.format("%s%n%s%n%d%n%d", name.toUpperCase(), temp, price,count);
    }
}

