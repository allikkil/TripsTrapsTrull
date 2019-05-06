package oop;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

import static oop.Kontroll.kontroll;


public class MänguLaud extends Application {
    GridPane juur = new GridPane();
    GridPane ruudud = new GridPane();
    Label label = new Label("Edu!");
    boolean arvutiBanter = true; // kas ekraanile kuvatakse mõni taibukas kommentaar
    boolean käikJäetiVahele = false; // viimases hädas appi võetud muutuja, et ruudud õigesti töötaksid
    Stage peaLava2 = new Stage();
    Stage stgMäng = new Stage();


    // Jamamismeetodid
    public void vahetaKõik(Laud laud) {
        // Meetod vahetab kõik nupud vastase omaga.
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (laud.getLaud()[i][j].equals("X")) {
                    asendaNupp(laud, new int[]{i, j}, "O");
                } else if (laud.getLaud()[i][j].equals("O")) {
                    asendaNupp(laud, new int[]{i, j}, "X");
                }
            }
        }
        label.setText("Aitäh, et minu eest kogu \ntöö ära tegid!");
        arvutiBanter = false;
    }

    public void jätaKäikVahele(Laud laud, int[] vahetuseKoord) {
        // Meetod asendab mängija käigu tühja ruuduga ja
        // uuendab ka vabade ruutude massiivi.
        käikJäetiVahele = true;
        asendaNupp(laud, vahetuseKoord, " ");
        laud.lisaVabadesse(vahetuseKoord);
        label.setText("Ma ei arva, et see on hea mõte.\nKäik jääb vahele.");
        arvutiBanter = false;
    }

    public void kustutaJaLooUusRuudustik(Laud laud) {
        // Meetod kustutab mängulaualt kõik käigud.
        laud.setLaud(new String[][]{
                {" ", " ", " "},
                {" ", " ", " "},
                {" ", " ", " "}});
        // Ka vabade ruutude massiiv lähtestatakse.
        laud.setVabad(genereeriVabad());

        // Luuakse uus ruudustik.
        ruudud = new GridPane();
        Stage stgMäng = new Stage();
        Scene stseen = new Scene(ruudud);

        // nuppudest ruutude loomine
        Button ruut1 = new Button();
        Button ruut2 = new Button();
        Button ruut3 = new Button();
        Button ruut4 = new Button();
        Button ruut5 = new Button();
        Button ruut6 = new Button();
        Button ruut7 = new Button();
        Button ruut8 = new Button();
        Button ruut9 = new Button();

        // ruutude grupeerimine
        ruudud.add(label, 0, 0);
        ruudud.add(ruut1, 0, 1);
        ruudud.add(ruut2, 1, 1);
        ruudud.add(ruut3, 2, 1);
        ruudud.add(ruut4, 0, 2);
        ruudud.add(ruut5, 1, 2);
        ruudud.add(ruut6, 2, 2);
        ruudud.add(ruut7, 0, 3);
        ruudud.add(ruut8, 1, 3);
        ruudud.add(ruut9, 2, 3);
        ruudud.setAlignment(Pos.BOTTOM_CENTER);

        // GridPane paigutus
        // kolme tulba paigutus
        for (int x = 0; x <= 2; x++) {
            ColumnConstraints cc = new ColumnConstraints();
            cc.setPercentWidth(100.0 / 3);
            cc.setHgrow(Priority.ALWAYS);
            cc.setFillWidth(true);
            ruudud.getColumnConstraints().add(cc);
        }
        // nelja rea paigutus
        for (int y = 0; y <= 3; y++) {
            RowConstraints rc = new RowConstraints();
            rc.setPercentHeight(100.0 / 4);
            rc.setVgrow(Priority.ALWAYS);
            rc.setFillHeight(true);
            ruudud.getRowConstraints().add(rc);
        }

        ruut1.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        ruut2.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        ruut3.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        ruut4.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        ruut5.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        ruut6.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        ruut7.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        ruut8.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        ruut9.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

        /*RowConstraints rida0 = new RowConstraints();
        rida0.setPercentHeight(40); // esimene rida võtab enda alla 40% aknast
        label.maxWidth(Double.MAX_VALUE);
        rida0.setValignment(VPos.TOP);
        ruudud.getRowConstraints().addAll(rida0);*/

        GridPane.setColumnSpan(label, 3); // GridPane-i labeli rea kolm celli liidetakse üheks celliks

        // ruutude modifitseerimine
        double nuppSize = 100.0;
        /*ruut1.setMinWidth(nuppSize);
        ruut1.setMinHeight(nuppSize);
        ruut2.setMinWidth(nuppSize);
        ruut2.setMinHeight(nuppSize);
        ruut3.setMinWidth(nuppSize);
        ruut3.setMinHeight(nuppSize);
        ruut4.setMinWidth(nuppSize);
        ruut4.setMinHeight(nuppSize);
        ruut5.setMinWidth(nuppSize);
        ruut5.setMinHeight(nuppSize);
        ruut6.setMinWidth(nuppSize);
        ruut6.setMinHeight(nuppSize);
        ruut7.setMinWidth(nuppSize);
        ruut7.setMinHeight(nuppSize);
        ruut8.setMinWidth(nuppSize);
        ruut8.setMinHeight(nuppSize);
        ruut9.setMinWidth(nuppSize);
        ruut9.setMinHeight(nuppSize);*/

        // Ruutudele luuakse funktsioon
        ruut1.setOnAction(event -> {
            mainLoop(laud, 1);
        });
        ruut2.setOnAction(event -> {
            mainLoop(laud, 2);
        });
        ruut3.setOnAction(event -> {
            mainLoop(laud, 3);
        });
        ruut4.setOnAction(event -> {
            mainLoop(laud, 4);
        });
        ruut5.setOnAction(event -> {
            mainLoop(laud, 5);
        });
        ruut6.setOnAction(event -> {
            mainLoop(laud, 6);
        });
        ruut7.setOnAction(event -> {
            mainLoop(laud, 7);
        });
        ruut8.setOnAction(event -> {
            mainLoop(laud, 8);
        });
        ruut9.setOnAction(event -> {
            mainLoop(laud, 9);
        });

        // Kõik GridPane-i elemendid muudetakse skaleeruvaks.
        ruudud.heightProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                double height = (double) newValue / 4;
                for (Node child : ruudud.getChildren()) {
                    child.prefHeight(height);
                }
            }
        });

        ruudud.widthProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                double width = (double) newValue / 3;
                for (Node child : ruudud.getChildren()) {
                    child.prefWidth(width);
                }
            }
        });

        // Näidatakse ka mängijale.
        stgMäng.setScene(stseen);
//        stgMäng.setMinHeight(536.0);
//        stgMäng.setMinWidth(600.0);
        stgMäng.setResizable(true);
        stgMäng.show();
    }

    public void asendaNupp(Laud laud, int[] vahetuseKoord, String uusNupp) {
        // Sisend: ruudu koordinaadid, millel nupp vahetatakse; väärtus, mille vastu vahetatakse.
        // Meetod vahetab ühe nupu mingi muu nupu vastu.
        laud.käikLauale(vahetuseKoord[0], vahetuseKoord[1], uusNupp);

        if (!käikJäetiVahele) {
            kuvaKäik(vahetuseKoord, uusNupp);
        }
        käikJäetiVahele = false;
        arvutiBanter = false;
    }

    public void jama(Laud laud, int[] vahetuseKoord) {
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
        } else if (meetodiIndex == 2) {
            // Kogu mängulaud kustutatakse.
            label.setText("See küll hea valik polnud.");
            arvutiBanter = false;
            kustutaJaLooUusRuudustik(laud);
        } else if (meetodiIndex == 3) {
            // Arvuti ei lase sinna ruutu käia.
            jätaKäikVahele(laud, vahetuseKoord);
        } else if (meetodiIndex == 4) {
            // vahetame laual kõik nupud omavahel ära.
            vahetaKõik(laud);
        }
    }

    public ArrayList<int[]> genereeriVabad() {
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

    public int[] ruutKoordinaatideks(int ruut) {
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

    public void lõpuNupud(){
        Button alustaUut = new Button("Alusta uut mängu");
        Button sulgeProgramm = new Button("Sulge programm");

        alustaUut.setOnMouseClicked(event -> {

            määraNupud(peaLava2);
            ruudud.getChildren().remove(alustaUut);
            ruudud.getChildren().remove(sulgeProgramm);
        });
        sulgeProgramm.setOnMouseClicked(event -> {
            Platform.exit();
        });
        alustaUut.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        sulgeProgramm.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        ruudud.add(alustaUut, 3, 3);
        ruudud.add(sulgeProgramm, 3, 2);
    }

    public void iKäik(Laud laud, int[] käiguKoord) {
        int rida = käiguKoord[0];
        int tulp = käiguKoord[1];

        // Käik lisatakse lauale.
        laud.käikLauale(rida, tulp, laud.getiNupp());

        // Ruudu koordinaadid eemaldatakse vabade ruutude massiivist, et
        // arvuti ei saaks sinna ise enam käia.
        laud.eemaldaVabadest(käiguKoord);
    }

    public void aKäik(Laud laud) {
        // Meetod teeb arvuti käigu.

        // Valitakse suvaline vaba ruut...
        int ruuduIndex = (int) (Math.random() * laud.getVabad().size() - 1);
        int[] käiguKoord = laud.getVabad().get(ruuduIndex);

        // ...ja tehakse sinna käik.
        laud.käikLauale(käiguKoord[0], käiguKoord[1], laud.getaNupp());

        // Eemaldab äsja kasutatud ruudu vabade listist.
        laud.eemaldaVabadest(käiguKoord);

        // Kuvatakse käik.
        kuvaKäik(käiguKoord, laud.getaNupp());

        // Kui arvutil on kohane kuvada kommentaar.
        if (arvutiBanter) {
            label.setText("Katsu selle vastu saada!");
        }
        arvutiBanter = true;
    }

    public void kuvaKäik(int[] käiguKoord, String nupp) {
        // ruudu koordinaadid GridPane-s
        int rida = käiguKoord[0] + 1;
        int tulp = käiguKoord[1];

        // Otsitakse nendele koordinaatidele vastav ruut
        for (Node child : ruudud.getChildren()) {
            if (GridPane.getColumnIndex(child) == tulp && GridPane.getRowIndex(child) == rida) {
                // Vana nupp eemaldatakse.
                ruudud.getChildren().remove(child);

                // Luuakse uus nupp.
                Button uusNupp = new Button(nupp);
                uusNupp.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

                // Uus nupp lisatakse vana asukohale.
                ruudud.add(uusNupp, tulp, rida);
                break;
            }
        }
    }

    public void mainLoop(Laud laud, int ruut) {
        if (!Kontroll.võitja(laud).equals(laud.getaNupp()) && laud.getVabad().size() > 0) {
            int[] käiguKoord = ruutKoordinaatideks(ruut);
            iKäik(laud, käiguKoord);

            if (Kontroll.võitja(laud).equals(laud.getiNupp())) {
                jama(laud, käiguKoord);
            } else {
                kuvaKäik(käiguKoord, laud.getiNupp());
            }

            if (Kontroll.võitja(laud).equals(laud.getaNupp())) {
                label.setText("Veel üks võit tehisintellekti jaoks!");
                lõpuNupud();
            } else if (laud.getVabad().size() > 0) {
                aKäik(laud);

                if (Kontroll.võitja(laud).equals(laud.getaNupp())) {
                    label.setText("Veel üks võit tehisintellekti jaoks!");
                    lõpuNupud();
                }
            } else {
                label.setText("Sa oled päris hea, aga \nvõita mind endiselt ei suuda!");
                lõpuNupud();
            }
        }
    }

    public void alustaMängu(Laud laud) {
        // Mängulaud tühjendatakse (vajalik juhul, kui alustati mitmendat raundi).
        kustutaJaLooUusRuudustik(laud);
    }

    public void määraNupud(Stage peaLava) {
        peaLava2 = peaLava;
        // Vajalikud muutujad laua loomiseks.
        String[][] tühiLaud = {
                {" ", " ", " "},
                {" ", " ", " "},
                {" ", " ", " "},
        };
        ArrayList<int[]> vabad = new ArrayList<>();
        String info = "Edu!";

        juur = new GridPane();
        Scene stsNupuMääramine = new Scene(juur);

        // graafilised elemendid
        Label label = new Label("Vali oma nupp");
        Button nuppX = new Button("X");
        Button nuppO = new Button("O");

        // nuppude ja labeli suuruse, fondi muutmine
        double nuppSize = 50.0;
        double txtSize = 25.0;
        label.setFont(new Font(txtSize));
        nuppX.setMinHeight(nuppSize);
        nuppX.setMinWidth(nuppSize);
        nuppX.setFont(new Font(txtSize));
        nuppO.setMinHeight(nuppSize);
        nuppO.setMinWidth(nuppSize);
        nuppO.setFont(new Font(txtSize));

        // sündmuse lisamine nupule X
        nuppX.setOnAction(event -> {
            Laud laud = new Laud(tühiLaud, vabad, "X", "O", info);
            alustaMängu(laud);
            peaLava.close();
        });

        // sündmuse lisamine nupule O
        nuppO.setOnAction(event -> {
            Laud laud = new Laud(tühiLaud, vabad, "O", "X", info);
            alustaMängu(laud);
            peaLava.close();
        });

        // nuppude ja labeli paigutus
        juur.add(label, 1, 0);
        juur.add(nuppX, 0, 1);
        juur.add(nuppO, 2, 1);

        // GridPane paigutus
        RowConstraints rida0 = new RowConstraints();
        rida0.setPercentHeight(50);
        juur.getRowConstraints().addAll(rida0);
        juur.setAlignment(Pos.TOP_CENTER);

        // Nupu küsimise akna loomine
        peaLava.setScene(stsNupuMääramine);
        peaLava.setHeight(175.0);
        peaLava.setWidth(450.0);
        peaLava.setResizable(false);
        peaLava.show();
    }


    @Override
    public void start(Stage peaLava) throws Exception {
        label.setFont(new Font(20));
        määraNupud(peaLava);
    }


    /*Group juur = new Group();
    Pane pane = new Pane();

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
                ruut.setTranslateX(j * 167);
                ruut.setTranslateY(i * 167);
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
        if (x >= 0 & x <= 167) {
            if (y <= 167) {
                return 1;
            }
            if (y > 167 & y <= 367) {
                return 4;
            }
            if (y > 367 & y <= 567) {
                return 7;
            }
        }
        if (x > 167 & x <= 367) {
            if (y <= 167) {
                return 2;
            }
            if (y > 167 & y <= 367) {
                return 5;
            }
            if (y > 367 & y <= 567) {
                return 8;
            }
        }
        if (x > 400 & x <= 567) {
            if (y <= 167) {
                return 3;
            }
            if (y > 200 & y <= 367) {
                return 6;
            }
            if (y > 367 & y <= 567) {
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

    public void uuendaLaud() {
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
    }

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
        // Valitakse suvaline vaba ruut ...
        int ruuduIndex = (int) (Math.random() * vabad.size() - 1);
        int[] käiguKoord = vabad.get(ruuduIndex);

        // ... ja tehakse sinna käik.
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
        nuppX.setLayoutY(550);
        nuppO.setLayoutX(200);
        nuppO.setLayoutY(550);

        nuppX.setOnMouseClicked(event -> {
            iNupp = "X";
            aNupp = "O";
            juur.getChildren().remove(nuppO);
            juur.getChildren().remove(nuppX);
            info.setText("");
        });

        nuppO.setOnMouseClicked(event -> {
            iNupp = "O";
            aNupp = "X";
            juur.getChildren().remove(nuppO);
            juur.getChildren().remove(nuppX);
            info.setText("");
        });
        juur.getChildren().addAll(nuppX, nuppO);
    }

    public void lõpuNupud() {
        Button mängiUuesti = new Button("Mängi uuesti");
        Button sulgeProgramm = new Button("Sulge programm");
        mängiUuesti.setLayoutX(200);
        mängiUuesti.setLayoutY(550);
        sulgeProgramm.setLayoutX(350);
        sulgeProgramm.setLayoutY(550);

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
        info.setText("Vali nupp!");
        info.setX(200);
        info.setY(525);
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
            info.setText("Võitjaks osutus " + aNupp);
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
        info.setText("Võitjaks osutus " + aNupp);
        lõpuNupud();
    }


    @Override
    public void start(Stage peaLava) throws InterruptedException {
        peaLava.setScene(new Scene(juur, 500, 600));
        peaLava.setMinWidth(517);
        peaLava.setMinHeight(640);
        peaLava.setTitle("TripsTrapsTroll");

        // kõikide objektide suurused muutuvad koos ekraaniga
        pane.getChildren().add(juur);
        pane.heightProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                for (Node child : pane.getChildren()) {
                    child.prefHeight(10);
                }
            }
        });

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
            Rectangle mänguRuut = new Rectangle(167, 167);

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
*/
    public static void main(String[] args) {
        launch(args);
    }
}
