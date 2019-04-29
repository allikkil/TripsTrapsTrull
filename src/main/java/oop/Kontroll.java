// Klass sisaldab meetodeid, mis tuvastavad, kas keegi on mängu võitnud.
package oop;
public class Kontroll {
    public static String kontroll(String[] massiiv) {
        // Sisend: kolme elemendiga massiiv
        // Tagastus: 'X' või 'O', kui kolm elementi on vastavalt võrdsed;
        // muidu tagastab null
        if (massiiv[0].equals("X") && massiiv[1].equals("X") && massiiv[2].equals("X")) {
            return "X";
        }
        if (massiiv[0].equals("O") && massiiv[1].equals("O") && massiiv[2].equals("O")) {
            return "O";
        }
        return null;
    }

    public static String võitja(Laud laud) {
        // Tagastus: 'X' või 'O' või 'N'
        String[] peadiagonaal = new String[3];
        String[] kõrvaldiagonaal = new String[3];
        String[][] transponeeritud = new String[3][3];
        String tulemus; // pärast iga rea/veeru/diagonaali kontrolli on tulemus kas 'X', 'O' või null.

        // Kontrolli ridu ja tulpi.
        // Transponeerime algse laua, et veeruelemente oleks kergem kontrollida.
        for (int i = 0; i <= 2; i++) {
            for (int j = 0; j <= 2; j++) {
                transponeeritud[i][j] = laud.getLaud()[j][i];
            }
        }

        // Samas tsüklis nii ridade kui ka tulpade elementide kontroll.
        for (int i = 0; i <= 2; i++) {
            tulemus = kontroll(laud.getLaud()[i]); // rea kontroll
            if (tulemus != null) {
                return tulemus;
            }

            tulemus = kontroll(transponeeritud[i]); // tulba kontroll
            if (tulemus != null) {
                return tulemus;
            }
        }

        // Kontrolli diagonaale
        // Pane diagonaali elemendid massiivi.
        for (int i = 0; i < 3; i++) {
            peadiagonaal[i] = (laud.getLaud()[i][i]);
        }
        for (int i = 2; i >= 0; i--) {
            kõrvaldiagonaal[Math.abs(i - 2)] = (laud.getLaud()[i][Math.abs(i - 2)]);
        }
        // Peadiagonaali kontroll
        tulemus = kontroll(peadiagonaal);
        if (tulemus != null) {
            return tulemus;
        }
        // Kõrvaldiagonaali kontroll
        tulemus = kontroll(kõrvaldiagonaal);
        if (tulemus != null) {
            return tulemus;
        }
        return "N";
    }
}