package oop;
import java.util.ArrayList;
import java.util.Arrays;


public class Laud {
    private String info; // Juhised mängijale
    private String[][] laud; // 3x3 maatriks; salvestab mängu hetkeseisu
    private ArrayList<int[]> vabad; // vabade ruutude koordinaatide List
    private String iNupp; // mängija ja arvuti nupud; 'X' või 'O'
    private String aNupp;

    public Laud(String[][] laud, ArrayList<int[]> vabad, String iNupp, String aNupp, String info) {
        setLaud(laud);
        setVabad(vabad);
        setiNupp(iNupp);
        setaNupp(aNupp);
        setInfo(info);
    }

    public String getiNupp() {
        return iNupp;
    }

    public void setiNupp(String iNupp) {
        this.iNupp = iNupp;
    }

    public String getaNupp() {
        return aNupp;
    }

    public void setaNupp(String aNupp) {
        this.aNupp = aNupp;
    }

    public String[][] getLaud() {
        return laud;
    }

    public void setLaud(String[][] laud) {
        this.laud = laud;
    }

    public ArrayList<int[]> getVabad() {
        return vabad;
    }

    public void setVabad(ArrayList<int[]> vabad) {
        this.vabad = vabad;
    }

    public String getInfo() { return info; }

    public void setInfo(String info) { this.info = info; }

    public void käikLauale(int rida, int tulp, String nupp) {
        // Sisend: käigu koordinaadid ja asetatav nupp
        // Meetod paneb nupu lauale.
        laud[rida][tulp] = nupp;
    }

    public int koordRuuduks(int[] ruuduKoord) {
        int ruut = 8;
        for (int i = 0; i <= 7; i++) {
            if (Arrays.toString(vabad.get(i)).equals(Arrays.toString(ruuduKoord))) {
                ruut = i;
                break;
            }
        }
        return ruut;
    }

    public void eemaldaVabadest(int[] ruuduKoord) {
        // Sisend: kustutamisele mineva ruudu koordinaadid.
        // Meetod kustutab vabade ruutude massiivist äsja kasutatud ruudu koordinaadid.

        // Eemaldamine toimub indexi kaudu, sest teisiti ei saanud tööle...
        vabad.remove(koordRuuduks(ruuduKoord));
    }

    public void lisaVabadesse(int[] ruuduKoord) {
        vabad.add(ruuduKoord);
    }
}
