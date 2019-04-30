// Klass sisaldab meetodeid, mis keelavad mängijal võita.

package oop;
import java.util.ArrayList;

public class Jama {
    public static void vahetaKõik(Laud laud) {
        // Meetod vahetab kõik nupud vastase omaga.
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (laud.getLaud()[i][j].equals("X")) {
                    asendaNupp(laud, new int[] {i, j}, "O");
                }
                else if(laud.getLaud()[i][j].equals("O")){
                    asendaNupp(laud, new int[] {i, j}, "X");
                }
            }
        }
    }

    public static void jätaKäikVahele(Laud laud, int[] vahetuseKoord) {
        // Meetod asendab mängija käigu tühja ruuduga ja
        // uuendab ka vabade ruutude massiivi.
        System.out.println("Ma ei arva et see on hea mõte, käik jääb vahele.");
        asendaNupp(laud, vahetuseKoord, " ");
        laud.lisaVabadesse(vahetuseKoord);
    }

    public static void kustutaLaud(Laud laud) {
        // Meetod kustutab mängulaualt kõik käigud.
        laud.setLaud(new String[][] {
                {" "," "," "},
                {" "," "," "},
                {" "," "," "}});
        // Ka vabade ruutude massiiv lähtestatakse.
        laud.setVabad(genereeriVabad());
    }

    public static void asendaNupp(Laud laud, int[] vahetuseKoord, String uusNupp) {
        // Sisend: ruudu koordinaadid, millel nupp vahetatakse; väärtus, mille vastu vahetatakse.
        // Meetod vahetab ühe nupu mingi muu nupu vastu.
        laud.käikLauale(vahetuseKoord[0], vahetuseKoord[1], uusNupp);
    }

    public static void jama(Laud laud, int[] vahetuseKoord) {
        // Sisend: vajalikud muutujad mängulaua manipuleerimiseks.
        // Meetod sisaldab erinevaid meetodeid, mis takistavad mängija võitmist.

        // Valitakse üks random meetod.
        int meetoditeArv = 4; // suurenda käsitsi, kui lisad uue meetodi

        // Valitakse suvaline arv hulgast {1, 2, 3, ..., meetodite arv} ja vastav meetod käivitatakse.
        // NB! Võiks arvata, et järgnevas lõigus saab kasutada floori asemel ka ceil funktsiooni, aga
        // Math.ceil(0.0) = 0 ja see pole hea
        // (kuigi tõenäosus, et random() tagastab täpselt 0.0, on peaaegu olematu).
        double x = Math.random(); // [0.0; 1.0)
        x = x * meetoditeArv + 1.0; // [1.0; meetodite arv + 1.0)
        int meetodiIndex = (int) Math.floor(x); // {1, 2, 3, ..., meetodite arv}

        if (meetodiIndex == 1) {
            // Äsja mängija pandud nupp asendatakse arvuti nupuga.
            asendaNupp(laud, vahetuseKoord, laud.getaNupp());
        }
        else if (meetodiIndex == 2) {
            // Kogu mängulaud kustutatakse.
            System.out.println("See küll hea valik polnud.");
            kustutaLaud(laud);
        }
        else if (meetodiIndex == 3) {
            // Arvuti ei lase sinna ruutu käia.
            jätaKäikVahele(laud, vahetuseKoord);
        }
        else if (meetodiIndex == 4) {
            // vahetame laual kõik nupud omavahel ära.
            vahetaKõik(laud);
        }
    }

    public static ArrayList<int[]> genereeriVabad() {
        // Meetod tagastab tühjale lauale vastava vabade Listi.
        ArrayList<int[]> vabad = new ArrayList<>();
        for (int i = 0; i <= 2; i++) {
            for (int j = 0; j <= 2; j++) {
                int[] ruuduKoord = {i, j};
                vabad.add(ruuduKoord);
            }
        }
        return vabad;
    }
}