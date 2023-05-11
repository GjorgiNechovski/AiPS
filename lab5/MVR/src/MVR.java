import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class Gragjanin{
    String name;
    int lKarta;
    int pasos;
    int vozachka;

    public Gragjanin(String name, int lKarta, int pasos, int vozachka) {
        this.name = name;
        this.lKarta = lKarta;
        this.pasos = pasos;
        this.vozachka = vozachka;
    }

    public String getName() {
        return name;
    }

    public int getlKarta() {
        return lKarta;
    }

    public int getPasos() {
        return pasos;
    }

    public int getVozachka() {
        return vozachka;
    }
}

public class MVR {

    public static void main(String[] args) {

        Scanner br = new Scanner(System.in);

        Queue<Gragjanin> LicniKarti = new LinkedList<Gragjanin>();
        Queue<Gragjanin> Pasosi = new LinkedList<Gragjanin>();
        Queue<Gragjanin> Vozacki = new LinkedList<Gragjanin>();

        int N = Integer.parseInt(br.nextLine());
        for (int i = 1; i <= N; i++) {
            String imePrezime = br.nextLine();
            int lKarta = Integer.parseInt(br.nextLine());
            int pasos = Integer.parseInt(br.nextLine());
            int vozacka = Integer.parseInt(br.nextLine());
            Gragjanin covek = new Gragjanin(imePrezime, lKarta, pasos, vozacka);

            if (lKarta == 1)
                LicniKarti.add(covek);
            else if (pasos == 1)
                Pasosi.add(covek);
            else if (vozacka == 1)
                Vozacki.add(covek);
        }

        while (LicniKarti.size() > 0) {
            Gragjanin temp = LicniKarti.poll();
            if (temp.getPasos() == 1)
                Pasosi.add(temp);
            else if (temp.getVozachka() == 1)
                Vozacki.add(temp);
            else
                System.out.println(temp.getName());
        }
        while (Pasosi.size() > 0) {
            Gragjanin temp = Pasosi.poll();
            if (temp.getVozachka() == 1)
                Vozacki.add(temp);
            else
                System.out.println(temp.getName());
        }
        while (Vozacki.size() > 0) {
            Gragjanin temp = Vozacki.poll();
            System.out.println(temp.getName());
        }

    }
}
