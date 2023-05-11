import java.util.Scanner;

class RabotnaNedela{

    private int [] casovi;
    private int brNedela;

    public RabotnaNedela(int[] casovi, int brNedela) {
        super();
        this.casovi = casovi;
        this.brNedela = brNedela;
    }

    public int[] getCasovi() {
        return casovi;
    }

    public int getBrNedela() {
        return brNedela;
    }

    public int getCasovi(int i) {
        return casovi[i];
    }

    public int vremeVoEdnaNedela(){
        int sum = 0;
        for (int i=0; i<casovi.length; i++)
            sum+=casovi[i];
        return sum;
    }

    @Override
    public String toString() {
        return "nothing";
    }

}

class Rabotnik{

    private String ime;
    private RabotnaNedela [] nedeli;

    public Rabotnik(String ime, RabotnaNedela[] nedeli) {
        super();
        this.ime = ime;
        this.nedeli = nedeli;
    }

    public String getIme() {
        return ime;
    }

    public RabotnaNedela[] getNedeli() {
        return nedeli;
    }

    public RabotnaNedela getNedela(int i){
        return nedeli[i];
    }

    @Override
    public String toString() {
        return "nothing";
    }

}

public class Main {

    public static int sumNedeli(Rabotnik r) {
        int sum = 0;
        for(int i = 0; i < r.getNedeli().length; i++){
            for(int j = 0; j < r.getNedela(i).getCasovi().length; j++){
                sum += r.getNedela(i).getCasovi(j);
            }
        }
        return sum;
    }

    static Rabotnik najvreden_rabotnik(Rabotnik[] niza){
        Rabotnik best = niza[0];
        System.out.println("");
        for (int i=1; i<niza.length; i++)
            if(sumNedeli(niza[i])>sumNedeli(best))
                best = niza[i];
        return best;
    }

    static void table(Rabotnik[] niza){
        System.out.println("Rab   1   2   3   4   Vkupno");

        for (int i=0; i<niza.length; i++) {
            System.out.println(niza[i].getIme() + "   " + niza[i].getNedela(0).vremeVoEdnaNedela() + "   " + niza[i].getNedela(1).vremeVoEdnaNedela() + "   " + niza[i].getNedela(2).vremeVoEdnaNedela() + "   " + niza[i].getNedela(3).vremeVoEdnaNedela() + "   "+ sumNedeli(niza[i]));
        }
    }

    public static void main(String[] args) {

        int n;
        Scanner input = new Scanner(System.in);
        n = input.nextInt();
        Rabotnik [] niza = new Rabotnik[n];
        for(int i=0;i<n;i++)
        {
            String ime = input.next();
            RabotnaNedela  []nedela = new RabotnaNedela[4];
            for (int j=0; j<4; j++)
            {
                int[] chasovi = new int[5];
                for (int k=0; k<5; k++) {
                    chasovi[k] = input.nextInt();
                }
                nedela[j] = new RabotnaNedela(chasovi,5);
            }
            niza[i] = new Rabotnik(ime,nedela);
        }

        table(niza);
        System.out.println("NAJVREDEN RABOTNIK: " +najvreden_rabotnik(niza).getIme());

    }
}

