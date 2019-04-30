package oop;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Arrays;


public class MänguLaud extends Application {
    Group juur = new Group();
    private String iNupp;
    private String aNupp;
    private ArrayList<int[]> vabad = genereeriVabad();
    private Ruut[][] laud;


    public Ruut[][] Mlaud(){
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
    public int kontrolliAsukoht(double x, double y){
        if(x >= 0 & x <= 200){
            if(y <= 200){
                return 1;
            }
            if(y > 200 & y <= 400){
                return 4;
            }
            if(y > 400 & y <= 600){
                return 7;
            }
        }
        if(x > 200 & x <= 400){
            if(y <= 200){
                return 2;
            }
            if(y > 200 & y <= 400){
                return 5;
            }
            if(y > 400 & y <= 600){
                return 8;
            }
        }
        if(x > 400 & x <= 600){
            if(y <= 200){
                return 3;
            }
            if(y > 200 & y <= 400){
                return 6;
            }
            if(y > 400 & y <= 600){
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

    public void uuendaLaud(){
        String[][] seis = lauaSeis(laud);
        int[] koordinaadid = new int[2];
        for(int i = 0; i<3; i++){
            for(int j = 0; j<3; j++){
                if(!seis[i][j].equals("")){
                    koordinaadid[0] = i;
                    koordinaadid[1] = j;
                    eemaldaVabadest(koordinaadid);
                }
            }
        }
    }

    public String[][] lauaSeis(Ruut[][] laud){
        String[][] seis = new String[3][3];
        for(int i = 0; i<3; i++){
            for(int j = 0; j<3; j++){
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

    public void nupud(){
        Button nuppX = new Button("Mängi X-na");
        Button nuppO = new Button("Mängi O-na");
        nuppX.setLayoutX(350);
        nuppX.setLayoutY(650);
        nuppO.setLayoutX(200);
        nuppO.setLayoutY(650);

        nuppX.setOnMouseClicked(event -> {
            iNupp = "X";
            aNupp = "O";
            juur.getChildren().remove(nuppX);
            juur.getChildren().remove(nuppO);

        });

        nuppO.setOnMouseClicked(event -> {
            iNupp = "O";
            aNupp = "X";
            juur.getChildren().remove(nuppX);
            juur.getChildren().remove(nuppO);

        });
        juur.getChildren().addAll(nuppX, nuppO);
    }

    @Override
    public void start(Stage peaLava) throws InterruptedException {


        peaLava.setScene(new Scene(juur, 600, 700));
        peaLava.setTitle("TripsTrapsTroll");
        nupud();

        laud = Mlaud();
        laud[0][0].getNupp();
        vabad = genereeriVabad();

        peaLava.show();
    }

    class Ruut extends StackPane {
        private Text nupp = new Text();


        public Ruut(){


            nupp.setFont(Font.font(120));
            Rectangle mänguRuut = new Rectangle(200, 200);

            mänguRuut.setFill(null);
            mänguRuut.setStroke(Color.BLACK);
            setAlignment(Pos.CENTER);
            getChildren().addAll(mänguRuut, nupp);

            setOnMouseClicked(event -> {

                if(iNupp.equals("X")){
                    nuppX();

                    int asukoht = kontrolliAsukoht(event.getSceneX(), event.getSceneY());
                    eemaldaVabadest(ruutKoordinaatideks(asukoht));

                    aKäik(laud, aNupp);


                } else if(iNupp.equals("O")){
                    nuppO();

                    int asukoht = kontrolliAsukoht(event.getSceneX(), event.getSceneY());
                    eemaldaVabadest(ruutKoordinaatideks(asukoht));

                    aKäik(laud, aNupp);

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
        private String getNupp(){
            return nupp.getText();
        }
        private void setNupp(String nupuTähis){
            nupp.setText(nupuTähis);
        }

    }

    public static void main(String[] args) {
        launch(args);
    }
}
