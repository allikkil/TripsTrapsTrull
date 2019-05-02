
package oop;
import java.util.ArrayList;
import java.util.Scanner;

public class TripsTrapsTrull {
    public static void aKäik(Laud laud) {
        // Meetod teeb arvuti käigu.

        // Valitakse suvaline vaba ruut...
        int ruuduIndex = (int) (Math.random() * laud.getVabad().size() - 1);
        int[] käiguKoord = laud.getVabad().get(ruuduIndex);

        // ...ja tehakse sinna käik.
        laud.käikLauale(käiguKoord[0], käiguKoord[1], laud.getaNupp());

        // Eemaldab äsja kasutatud ruudu vabade listist.
        laud.eemaldaVabadest(käiguKoord);
    }

    public static int[] ruutKoordinaatideks(int ruut) {
        // Sisend: mängija valitud ruut
        // Tagastus: vastava ruudu koordinaadid
        int start = 1;
        int[] ruuduKoord = new int[2];
        for (int i = 0; i <= 2; i++) {
            for (int j = 0; j <= 2; j++) {
                if (start == ruut) {
                    ruuduKoord[0] = i;
                    ruuduKoord[1] = j;
                    return ruuduKoord;
                }
                start++;
            }
        }
        return null; // Selle reani ei jõuta praktikas kunagi, aga ilma selle käsuta viskab veateate.
    }

    public static void iKäik(Laud laud, int ruut) {
        // Mängija sisestatud ruut teisendatakse ümber koordinaatideks.
        int[] ruuduKoord = ruutKoordinaatideks(ruut);
        int rida = ruuduKoord[0];
        int tulp = ruuduKoord[1];

        // Käik lisatakse lauale.
        laud.käikLauale(rida, tulp, laud.getiNupp());

        // Ruudu koordinaadid eemaldatakse vabade ruutude massiivist,
        // et arvuti ei saaks sinna ise enam käia.
        laud.eemaldaVabadest(ruuduKoord);
    }

    public static void printLaud(Laud laud) {
        // Sisend: mängulaud
        // Meetod väljastab mängulaua ilusal kujul.
        for (int i = 0; i <= 2; i++) {
            StringBuilder rida = new StringBuilder();

            for (int j = 0; j <= 2; j++) {
                rida.append(" ");
                rida.append(laud.getLaud()[i][j]);
                rida.append(" ");
                rida.append("|");
            }

            rida.setLength(rida.length() - 1); // eemaldatakse viimane element '|'
            System.out.println(rida);

            if (i != 2) { // Kui just ei väljastatud kõige alumist rida, siis väljasta horisontaaljoon
                System.out.println("-----------");
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        // Defineeritakse vaja minevad muutujad.
        String iEsimene; // 'J', 'E'
        String iNupp; // 'X', 'O'
        String aNupp;
        int aTulemus = 0; // arvuti võidud
        int vTulemus = 0; // viigid


        System.out.println();
        System.out.println("Let's play a game!");


        // Määratakse mängija ja arvuti "nupud"
        Scanner reader = new Scanner(System.in);
        System.out.print("Kas tahad olla 'X' või 'O'? ");
        iNupp = reader.next();
        if (iNupp.equals("X")) {
            aNupp = "O";
        } else {
            aNupp = "X";
        }


        // Kinnitatakse esimesena käija.
        System.out.print("Kas julged esimesena käia? ('J'/'E') ");
        iEsimene = reader.next();


        // Luuakse laud.
        Laud laud = new Laud(new String[][] {
                {" "," "," "},
                {" "," "," "},
                {" "," "," "}}, new ArrayList<>(), iNupp, aNupp, "");


        // Vabade ruutude massiiv täidetakse.
        Jama.kustutaLaud(laud);


        // Alustatakse mängu.
        System.out.println();
        System.out.println("Hea küll, hakkame pihta!");


        // Kui mängija on esimene, siis alustatakse mängu normaalselt.
        if (iEsimene.equals("J")) {

            System.out.println("Tee esimene käik.");
            System.out.println();
            printLaud(laud);

            // Mäng kestab nii kaua kui arvuti pole võitnud või vabade ruutude massiiv tühjaks saab.
            // Tingimus "vabad.size() > 0" on vajalik juhuks, kui toimub järgmine sündmuste kulg:
            // 1. Mängija tegi viimase käigu, mis oli võitev.
            // 2. Jamamiseks valiti meetod 3, mis asendab mängija käigu vaba ruuduga.
            // 3. Arvuti pani enda käigu sinna (ainukesse järele jäänud) ruutu.
            // 4. See käik polnud võitev.
            // Ilma selle tingimuseta küsitaks mängijalt käike edasi, mis sest et laud sai täis.
            while (!Kontroll.võitja(laud).equals(aNupp) && laud.getVabad().size() > 0) {
                // Küsitakse mängija käik.
                System.out.print("Vali ruut: ");
                int ruut = reader.nextInt();
                System.out.println();

                // Realiseeritakse mängija käik.
                iKäik(laud, ruut);

                // Kui mängija tegi võitva käigu, siis hakatakse jamama.
                if (Kontroll.võitja(laud).equals(iNupp)) {
                    int[] vahetuseKoord = ruutKoordinaatideks(ruut);
                    Thread.sleep(500);
                    Jama.jama(laud, vahetuseKoord);
                }

                printLaud(laud);
                Thread.sleep(500);

                // Kui pärast jamamist arvuti võidab, siis on mäng läbi.
                if (Kontroll.võitja(laud).equals(aNupp)) {
                    System.out.println();
                    System.out.println("Veel üks võit tehisintellekti jaoks!");
                    break;
                }

                // Kui arvuti pole veel võitnud ja laual on ruumi, siis
                // arvuti teeb suvalise käigu ja tulemus väljastatakse.
                if (laud.getVabad().size() > 0) {
                    System.out.println();
                    Thread.sleep(500);
                    System.out.println("Katsu selle vastu saada!");
                    aKäik(laud);
                    printLaud(laud);
                }

                // Kui ruumi enam pole, siis järelikult mängija tegi just viimase käigu ja kui
                // programm on jõudnud siia punkti, siis ei olnud viimane käik ka võitev.
                else {
                    System.out.println();
                    System.out.println("Sa oled päris hea, aga võita mind endiselt ei suuda!");
                }
            }
            System.out.println("GG!");
        }

        // Kui mängija pole esimene, siis arvuti võidab.
        else {
            System.out.println("Teen esimese käigu.");

            // Arvuti paneb suvaliselt käike kuni võidab.
            while (!Kontroll.võitja(laud).equals(aNupp)) {
                aKäik(laud);

                // Väljastab käigu tulemuse.
                printLaud(laud);
                System.out.println();

                if (!Kontroll.võitja(laud).equals(aNupp)) {
                    Thread.sleep(500);
                    System.out.println("Oled liiga aeglane!");
                }
            }

            System.out.println("gg ez");
        }

        if (Kontroll.võitja(laud).equals(aNupp)) {
            aTulemus += 1;
        } else {
            vTulemus += 1;
        }

        // TODO: Meetod, mis salvestab uue mängude tulemuse faili.

        System.out.println("Skoor: Arvuti - " + aTulemus + " Viik - " + vTulemus);
        System.out.print("Kas soovid uuesti mängida? ('J'/'E') ");
        if (reader.next().equals("J")) {
            main(args);
        }

        reader.close();
    }
}