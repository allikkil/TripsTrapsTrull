package oop;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.ArrayList;


public class MänguLaud extends Application {
    GridPane ruudud = new GridPane();
    Label label = new Label("Edu!");

    boolean arvutiBanter = true; // kas ekraanile kuvatakse mõni taibukas kommentaar
    boolean käikJäetiVahele = false; // viimases hädas appi võetud muutuja, et ruudud õigesti töötaksid

    Stage stgMäng = new Stage();

    int vSkoor;
    int aSkoor;
    int iSkoor = 0;


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

    public void kustutaJaLooUusRuudustik(Laud laud) throws Exception {
        // Meetod kustutab mängulaualt kõik käigud.
        laud.setLaud(new String[][]{
                {" ", " ", " "},
                {" ", " ", " "},
                {" ", " ", " "}});

        // Ka vabade ruutude massiiv lähtestatakse.
        laud.setVabad(genereeriVabad());

        // Skoorid loetakse failist ja pannakse lauale.
        loeSkoorid();
        label.setText("Edu!");

        Label arvuti = new Label("ARVUTI");
        Label viik = new Label("VIIK");
        Label inimene = new Label("INIMENE");

        GridPane.setValignment(arvuti, VPos.BOTTOM);
        GridPane.setValignment(viik, VPos.BOTTOM);
        GridPane.setValignment(inimene, VPos.BOTTOM);
        GridPane.setHalignment(arvuti, HPos.CENTER);
        GridPane.setHalignment(viik, HPos.CENTER);
        GridPane.setHalignment(inimene, HPos.CENTER);

        double txtSize = 20;

        arvuti.setFont(new Font(txtSize));
        viik.setFont(new Font(txtSize));
        inimene.setFont(new Font(txtSize));

        Label skoorA = new Label(String.valueOf(aSkoor));
        Label skoorV = new Label(String.valueOf(vSkoor));
        Label skoorI = new Label(String.valueOf(iSkoor));
        skoorA.setFont(new Font(txtSize));
        skoorV.setFont(new Font(txtSize));
        skoorI.setFont(new Font(txtSize));

        GridPane.setValignment(skoorA, VPos.TOP);
        GridPane.setValignment(skoorV, VPos.TOP);
        GridPane.setValignment(skoorI, VPos.TOP);
        GridPane.setHalignment(skoorA, HPos.CENTER);
        GridPane.setHalignment(skoorV, HPos.CENTER);
        GridPane.setHalignment(skoorI, HPos.CENTER);

        // Luuakse uus ruudustik.
        ruudud = new GridPane();
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

        // labeli ja skooride paigutus
        ruudud.add(label, 0, 0);
        ruudud.add(arvuti, 3, 0);
        ruudud.add(viik, 4, 0);
        ruudud.add(inimene, 5, 0);
        ruudud.add(skoorA, 3, 1);
        ruudud.add(skoorV, 4, 1);
        ruudud.add(skoorI, 5, 1);

        // ruutude paigutus
        ruudud.add(ruut1, 0, 1);
        ruudud.add(ruut2, 1, 1);
        ruudud.add(ruut3, 2, 1);
        ruudud.add(ruut4, 0, 2);
        ruudud.add(ruut5, 1, 2);
        ruudud.add(ruut6, 2, 2);
        ruudud.add(ruut7, 0, 3);
        ruudud.add(ruut8, 1, 3);
        ruudud.add(ruut9, 2, 3);

        // GridPane paigutus:
        // kuue tulba paigutus
        for (int x = 1; x <= 6; x++) {
            ColumnConstraints cc = new ColumnConstraints();
            cc.setPercentWidth(100.0 / 6);
            cc.setHgrow(Priority.ALWAYS);
            cc.setFillWidth(true);
            ruudud.getColumnConstraints().add(cc);
        }
        // nelja rea paigutus
        for (int y = 1; y <= 4; y++) {
            RowConstraints rc = new RowConstraints();
            rc.setPercentHeight(100.0 / 4);
            rc.setVgrow(Priority.ALWAYS);
            rc.setFillHeight(true);
            ruudud.getRowConstraints().add(rc);
        }

        // ruudud muudetakse skaleeruvateks
        ruut1.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        ruut2.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        ruut3.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        ruut4.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        ruut5.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        ruut6.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        ruut7.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        ruut8.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        ruut9.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

        GridPane.setColumnSpan(label, 3); // GridPane-i labeli rea kolm celli liidetakse üheks celliks

        // Ruutudele luuakse funktsioon
        ruut1.setOnAction(event -> {
            try {
                mainLoop(laud, 1);
            } catch (Exception e) {
                System.out.println("jama lugu");
            }
        });
        ruut2.setOnAction(event -> {
            try {
                mainLoop(laud, 2);
            } catch (Exception e) {
                System.out.println("jama lugu");
            }
        });
        ruut3.setOnAction(event -> {
            try {
                mainLoop(laud, 3);
            } catch (Exception e) {
                System.out.println("jama lugu");
            }
        });
        ruut4.setOnAction(event -> {
            try {
                mainLoop(laud, 4);
            } catch (Exception e) {
                System.out.println("jama lugu");
            }
        });
        ruut5.setOnAction(event -> {
            try {
                mainLoop(laud, 5);
            } catch (Exception e) {
                System.out.println("jama lugu");
            }
        });
        ruut6.setOnAction(event -> {
            try {
                mainLoop(laud, 6);
            } catch (Exception e) {
                System.out.println("jama lugu");
            }
        });
        ruut7.setOnAction(event -> {
            try {
                mainLoop(laud, 7);
            } catch (Exception e) {
                System.out.println("jama lugu");
            }
        });
        ruut8.setOnAction(event -> {
            try {
                mainLoop(laud, 8);
            } catch (Exception e) {
                System.out.println("jama lugu");
            }
        });
        ruut9.setOnAction(event -> {
            try {
                mainLoop(laud, 9);
            } catch (Exception e) {
                System.out.println("jama lugu");
            }
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
        stgMäng.setMinHeight(400.0);
        stgMäng.setMinWidth(700.0);
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

    public void jama(Laud laud, int[] vahetuseKoord) throws Exception {
        // Sisend: vajalikud muutujad mängulaua manipuleerimiseks.
        // Meetod sisaldab erinevaid meetodeid, mis takistavad mängija võitmist.

        // Valitakse üks random meetod.
        int meetoditeArv = 4; // suurenda käsitsi, kui lisad uue meetodi

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


    // Meetodid, mida ei oska kuhugi mujale panna
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

    public void loeSkoorid() throws Exception {
        aSkoor = FailiKirjutaLoe.loeASkoor();
        vSkoor = FailiKirjutaLoe.loeVSkoor();
    }

    public void uuendaASkoor() throws Exception {
        aSkoor += 1;
        FailiKirjutaLoe.kirjutaSkoorid(aSkoor, vSkoor);
    }

    public void uuendaVSkoor() throws Exception {
        vSkoor += 1;
        FailiKirjutaLoe.kirjutaSkoorid(aSkoor, vSkoor);
    }


    // Mängija ja arvuti käikude loomine
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
                uusNupp.setFont(new Font(25));
                uusNupp.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

                // Uus nupp lisatakse vana asukohale.
                ruudud.add(uusNupp, tulp, rida);
                break;
            }
        }
    }


    // Mängu loogika
    public void mainLoop(Laud laud, int ruut) throws Exception {
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
                uuendaASkoor();
                lõpuNupud();
            } else if (laud.getVabad().size() > 0) {
                aKäik(laud);

                if (Kontroll.võitja(laud).equals(laud.getaNupp())) {
                    label.setText("Veel üks võit tehisintellekti jaoks!");
                    uuendaASkoor();
                    lõpuNupud();
                }
            } else {
                label.setText("Sa oled päris hea, aga \nvõita mind endiselt ei suuda!");
                uuendaVSkoor();
                lõpuNupud();
            }
        }
    }

    public void alustaMängu(Laud laud) throws Exception {
        // Mängulaud tühjendatakse (vajalik juhul, kui alustati mitmendat raundi).
        kustutaJaLooUusRuudustik(laud);
    }


    public void lõpuNupud() {
        Button alustaUut = new Button("Alusta uut \nmängu");
        alustaUut.setFont(new Font(15));
        Button sulgeProgramm = new Button("Sulge \nprogramm");
        sulgeProgramm.setFont(new Font(15));

        // lõpunupud üksteisest natuke kaugemale
        GridPane.setMargin(alustaUut, new Insets(0, 0, 0, 10));
        GridPane.setMargin(sulgeProgramm, new Insets(0, 0, 0, 10));

        alustaUut.setOnMouseClicked(event -> {
            stgMäng.close();
            Stage peaLava = new Stage();
            määraNupud(peaLava);
            ruudud.getChildren().remove(alustaUut);
            ruudud.getChildren().remove(sulgeProgramm);
        });

        sulgeProgramm.setOnMouseClicked(event -> {
            Platform.exit();
        });

        alustaUut.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        sulgeProgramm.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        ruudud.add(alustaUut, 3, 2);
        ruudud.add(sulgeProgramm, 3, 3);
    }

    public void määraNupud(Stage peaLava) {
        // Vajalikud muutujad laua loomiseks.
        String[][] tühiLaud = {
                {" ", " ", " "},
                {" ", " ", " "},
                {" ", " ", " "},
        };
        ArrayList<int[]> vabad = new ArrayList<>();

        GridPane nupuValik = new GridPane();
        Scene stsNupuMääramine = new Scene(nupuValik);

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
            Laud laud = new Laud(tühiLaud, vabad, "X", "O");
            peaLava.close();
            try {
                alustaMängu(laud);
            } catch (Exception e) {
                System.out.println("jama lugu");
            }
        });

        // sündmuse lisamine nupule O
        nuppO.setOnAction(event -> {
            Laud laud = new Laud(tühiLaud, vabad, "O", "X");
            peaLava.close();
            try {
                alustaMängu(laud);
            } catch (Exception e) {
                System.out.println("jama lugu");
            }
        });

        // nuppude ja labeli paigutus
        nupuValik.add(label, 1, 0);
        nupuValik.add(nuppX, 0, 1);
        nupuValik.add(nuppO, 2, 1);

        // GridPane paigutus
        RowConstraints rida0 = new RowConstraints();
        rida0.setPercentHeight(50);
        nupuValik.getRowConstraints().addAll(rida0);
        nupuValik.setAlignment(Pos.TOP_CENTER);

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

    public static void main(String[] args) {
        launch(args);
    }
}
