package oop;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Arrays;

import static oop.Kontroll.kontroll;


public class MänguLaud extends Application {

    /*
    private String iNupp; // mängija ja arvuti nupud; 'X' või 'O'
    private String aNupp;

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

    public void alustaMängu() {
        Stage lava = new Stage();
        Label label = new Label("Edu!");

        Button nupp1 = new Button();
        Button nupp2 = new Button();
        Button nupp3 = new Button();
        Button nupp4 = new Button();
        Button nupp5 = new Button();
        Button nupp6 = new Button();
        Button nupp7 = new Button();
        Button nupp8 = new Button();
        Button nupp9 = new Button();

        double nuppSize = 75;

        nupp1.setMinWidth(nuppSize);
        nupp1.setMinHeight(nuppSize);
        nupp2.setMinWidth(nuppSize);
        nupp2.setMinHeight(nuppSize);
        nupp3.setMinWidth(nuppSize);
        nupp3.setMinHeight(nuppSize);
        nupp4.setMinWidth(nuppSize);
        nupp4.setMinHeight(nuppSize);
        nupp5.setMinWidth(nuppSize);
        nupp5.setMinHeight(nuppSize);
        nupp6.setMinWidth(nuppSize);
        nupp6.setMinHeight(nuppSize);
        nupp7.setMinWidth(nuppSize);
        nupp7.setMinHeight(nuppSize);
        nupp8.setMinWidth(nuppSize);
        nupp8.setMinHeight(nuppSize);
        nupp9.setMinWidth(nuppSize);
        nupp9.setMinHeight(nuppSize);

        GridPane ruudud = new GridPane();
        ruudud.add(nupp1, 0, 0);
        ruudud.add(nupp2, 1, 0);
        ruudud.add(nupp3, 2, 0);
        ruudud.add(nupp4, 0, 1);
        ruudud.add(nupp5, 1, 1);
        ruudud.add(nupp6, 2, 1);
        ruudud.add(nupp7, 0, 2);
        ruudud.add(nupp8, 1, 2);
        ruudud.add(nupp9, 2, 2);

        ruudud.setAlignment(Pos.BOTTOM_CENTER);
        Scene stseen = new Scene(ruudud);
        lava.setScene(stseen);
        lava.setMinHeight(500);
        lava.setMinWidth(300);
        lava.setResizable(true);
        lava.show();
    }


    @Override
    public void start(Stage peaLava) throws Exception {
        Stage väljak = new Stage();

        Label label = new Label("Vali oma nupp");
        Button nuppX = new Button("X");
        Button nuppO = new Button("O");

        double nuppSize = 50;
        double txtSize = 25;

        label.setFont(new Font(txtSize));
        nuppX.setMinHeight(nuppSize);
        nuppX.setMinWidth(nuppSize);
        nuppX.setFont(new Font(txtSize));
        nuppO.setMinHeight(nuppSize);
        nuppO.setMinWidth(nuppSize);
        nuppO.setFont(new Font(txtSize));

        // sündmuse lisamine nupule X
        nuppX.setOnAction(event -> {
            setiNupp("X");
            setaNupp("O");
            alustaMängu();
            väljak.close();
             });

        // sündmuse lisamine nupule O
        nuppO.setOnAction(event -> {
            setiNupp("O");
            setaNupp("X");
            alustaMängu();
            väljak.close();
             });

        // nuppude grupeerimine
        FlowPane pane = new FlowPane(50, 10);
        pane.setAlignment(Pos.TOP_CENTER);
        pane.getChildren().addAll(nuppX, nuppO);

        // küsimuse ja nuppude gruppi paigutamine
        VBox vBox = new VBox(25);
        vBox.setAlignment(Pos.TOP_CENTER);
        vBox.getChildren().addAll(label, pane);

        Scene stseen = new Scene(vBox);
        väljak.setScene(stseen);
        väljak.setMinHeight(170);
        väljak.setMinWidth(500);
        väljak.setResizable(false);
        väljak.show();
    }
*/


    Group juur = new Group();
    private String iNupp;
    private String aNupp;
    private ArrayList<int[]> vabad = genereeriVabad();
    private Ruut[][] laud;
    private Text info;


    public Ruut[][] Mlaud() {
        Ruut[][] laud = new Ruut[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Ruut ruut = new Ruut();
                ruut.setTranslateX(j * 200);
                ruut.setTranslateY(i * 200);
                laud[i][j] = ruut;
                juur.getChildren().add(ruut);
            }
        }
        return laud;
    }

    public boolean laudTäis(String[][] seis){
        for(int i = 0; i<3; i++){
            for(int j = 0; j<3; j++){
                if(seis[i][j].equals("")){
                    return false;
                }
            }
        }
        return true;
    }

    public int kontrolliAsukoht(double x, double y) {
        if (x >= 0 & x <= 200) {
            if (y <= 200) {
                return 1;
            }
            if (y > 200 & y <= 400) {
                return 4;
            }
            if (y > 400 & y <= 600) {
                return 7;
            }
        }
        if (x > 200 & x <= 400) {
            if (y <= 200) {
                return 2;
            }
            if (y > 200 & y <= 400) {
                return 5;
            }
            if (y > 400 & y <= 600) {
                return 8;
            }
        }
        if (x > 400 & x <= 600) {
            if (y <= 200) {
                return 3;
            }
            if (y > 200 & y <= 400) {
                return 6;
            }
            if (y > 400 & y <= 600) {
                return 9;
            }
        }
        return 0;
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

  /*  public void uuendaLaud() {
        String[][] seis = lauaSeis(laud);
        int[] koordinaadid = new int[2];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (!seis[i][j].equals("")) {
                    koordinaadid[0] = i;
                    koordinaadid[1] = j;
                    eemaldaVabadest(koordinaadid);
                }
            }
        }
    }*/

    public String[][] lauaSeis(Ruut[][] laud) {
        String[][] seis = new String[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                seis[i][j] = laud[i][j].getNupp();
            }
        }
        return seis;
    }

    public void aKäik(Ruut[][] laud, String tähis) {
        int ruuduIndex = (int) (Math.random() * vabad.size() - 1);
        int[] käiguKoord = vabad.get(ruuduIndex);

        // ...ja tehakse sinna käik.

        laud[käiguKoord[0]][käiguKoord[1]].setNupp(aNupp);

        // Eemaldab äsja kasutatud ruudu vabade listist.
        eemaldaVabadest(käiguKoord);

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

    public void nupud() {
        Button nuppX = new Button("Mängi X-na");
        Button nuppO = new Button("Mängi O-na");
        nuppX.setLayoutX(350);
        nuppX.setLayoutY(650);
        nuppO.setLayoutX(200);
        nuppO.setLayoutY(650);

        nuppX.setOnMouseClicked(event -> {
            iNupp = "X";
            aNupp = "O";
            juur.getChildren().remove(nuppO);
            juur.getChildren().remove(nuppX);
        });

        nuppO.setOnMouseClicked(event -> {
            iNupp = "O";
            aNupp = "X";
            juur.getChildren().remove(nuppO);
            juur.getChildren().remove(nuppX);
        });
        juur.getChildren().addAll(nuppX, nuppO);
    }

    public void lõpuNupud() {
        Button mängiUuesti = new Button("Mängi uuesti");
        Button sulgeProgramm = new Button("Sulge programm");
        mängiUuesti.setLayoutX(200);
        mängiUuesti.setLayoutY(650);
        sulgeProgramm.setLayoutX(350);
        sulgeProgramm.setLayoutY(650);

        sulgeProgramm.setOnMouseClicked(event -> {
            Platform.exit();
        });
        mängiUuesti.setOnMouseClicked(event -> {
            juur.getChildren().remove(mängiUuesti);
            juur.getChildren().remove(sulgeProgramm);
            uusMäng();
        });
        juur.getChildren().addAll(mängiUuesti, sulgeProgramm);
    }

    public Text infoTekst() {
        Text info = new Text();
        info.setText("Siia tuleb vajalik info");
        info.setX(200);
        info.setY(625);
        juur.getChildren().add(info);
        return info;
    }

    public static String võitja(String[][] laud) {
        // Tagastus: 'X' või 'O' või 'N'
        String[] peadiagonaal = new String[3];
        String[] kõrvaldiagonaal = new String[3];
        String[][] transponeeritud = new String[3][3];
        String tulemus; // pärast iga rea/veeru/diagonaali kontrolli on tulemus kas 'X', 'O' või null.

        // Kontrolli ridu ja tulpi.
        // Transponeerime algse laua, et veeruelemente oleks kergem kontrollida.
        for (int i = 0; i <= 2; i++) {
            for (int j = 0; j <= 2; j++) {
                transponeeritud[i][j] = laud[j][i];
            }
        }

        // Samas tsüklis nii ridade kui ka tulpade elementide kontroll.
        for (int i = 0; i <= 2; i++) {
            tulemus = kontroll(laud[i]); // rea kontroll
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
            peadiagonaal[i] = (laud[i][i]);
        }
        for (int i = 2; i >= 0; i--) {
            kõrvaldiagonaal[Math.abs(i - 2)] = (laud[i][Math.abs(i - 2)]);
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

    public void uusMäng() {
        vabad = genereeriVabad();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                laud[i][j].setNupp("");
            }
        }
        info.setText("Alustan uut mängu");
        nupud();
    }




    //Siit algavad jamamise asjad


    public void jama(int[] vahetuseKoord) {
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
            asendaNupp(vahetuseKoord, aNupp);
        }
        else if (meetodiIndex == 2) {
            // Kogu mängulaud kustutatakse.
            info.setText("See küll hea valik polnud.");
            uusMäng();
        }
        else if (meetodiIndex == 3) {
            // Arvuti ei lase sinna ruutu käia.
            jätaKäikVahele(vahetuseKoord);
        }
        else if (meetodiIndex == 4) {
            // vahetame laual kõik nupud omavahel ära.
            vahetaKõik();
        }
    }


    public void asendaNupp(int[] vahetuseKoord, String uusNupp) {
        // Sisend: ruudu koordinaadid, millel nupp vahetatakse; väärtus, mille vastu vahetatakse.
        // Meetod vahetab ühe nupu mingi muu nupu vastu.
        laud[vahetuseKoord[0]][vahetuseKoord[1]].setNupp(uusNupp);
        if (võitja(lauaSeis(laud)).equals(aNupp)) {
            info.setText("Võitjaks osutus: " + aNupp);
            lõpuNupud();
        }
    }


    public void jätaKäikVahele(int[] vahetuseKoord) {
        // Meetod asendab mängija käigu tühja ruuduga ja
        // uuendab ka vabade ruutude massiivi.
        info.setText("Ma ei arva et see on hea mõte, käik jääb vahele.");
        asendaNupp(vahetuseKoord, " ");
        vabad.add(vahetuseKoord);
        aKäik(laud, aNupp);
    }


    public void vahetaKõik() {
        // Meetod vahetab kõik nupud vastase omaga.
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (laud[i][j].getNupp().equals("X")) {
                    asendaNupp(new int[] {i, j}, "O");
                }
                else if(laud[i][j].getNupp().equals("O")){
                    asendaNupp(new int[] {i, j}, "X");
                }
            }
        }
        info.setText("Võitjaks osutus: " + aNupp);
        lõpuNupud();
    }


    @Override
    public void start(Stage peaLava) throws InterruptedException {
        peaLava.setScene(new Scene(juur, 600, 700));
        peaLava.setTitle("TripsTrapsTroll");
        nupud();

        laud = Mlaud();
        laud[0][0].getNupp();
        vabad = genereeriVabad();
        info = infoTekst();

        peaLava.show();
    }

    class Ruut extends StackPane {
        private Text nupp = new Text();

        public Ruut() {
            nupp.setFont(Font.font(120));
            Rectangle mänguRuut = new Rectangle(200, 200);

            mänguRuut.setFill(null);
            mänguRuut.setStroke(Color.BLACK);
            setAlignment(Pos.CENTER);
            getChildren().addAll(mänguRuut, nupp);

            setOnMouseClicked(event -> {
                if (iNupp.equals("X")) {
                    nuppX();

                    int asukoht = kontrolliAsukoht(event.getSceneX(), event.getSceneY());
                    eemaldaVabadest(ruutKoordinaatideks(asukoht));
                    if (võitja(lauaSeis(laud)).equals(iNupp)) {
                        jama(ruutKoordinaatideks(asukoht));
                    } else {
                        if(laudTäis(lauaSeis(laud))){
                            info.setText("Tekkis viik");
                            uusMäng();
                        }


                        aKäik(laud, aNupp);
                        if (võitja(lauaSeis(laud)).equals(aNupp)) {
                            info.setText("Võitjaks osutus: " + aNupp);
                            lõpuNupud();
                        }
                    }

                } else if (iNupp.equals("O")) {
                    nuppO();

                    int asukoht = kontrolliAsukoht(event.getSceneX(), event.getSceneY());
                    eemaldaVabadest(ruutKoordinaatideks(asukoht));
                    if (võitja(lauaSeis(laud)).equals(iNupp)) {
                        jama(ruutKoordinaatideks(asukoht));

                    } else {
                        if(laudTäis(lauaSeis(laud))){
                            info.setText("Tekkis viik");
                            uusMäng();
                        }
                        aKäik(laud, aNupp);

                        if (võitja(lauaSeis(laud)).equals(aNupp)) {
                            info.setText("Võitjaks osutus: " + aNupp);
                            lõpuNupud();
                        }
                    }

                }

            });
        }

        //nupu tähistamiseks
        private void nuppX() {
            nupp.setText("X");
        }

        private void nuppO() {
            nupp.setText("O");
        }

        private String getNupp() {
            return nupp.getText();
        }

        private void setNupp(String nupuTähis) {
            nupp.setText(nupuTähis);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
