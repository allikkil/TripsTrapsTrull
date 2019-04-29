package oop;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class MänguLaud extends Application {

    @Override
    public void start(Stage peaLava) throws Exception {
        boolean playerTurn = true;
        Group juur = new Group();
        peaLava.setScene(new Scene(juur, 600, 600));
        peaLava.setTitle("TripsTrapsTroll");

        //paneme ruudud paika
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Ruut ruut = new Ruut();
                ruut.setTranslateX(j * 200);
                ruut.setTranslateY(i * 200);

                juur.getChildren().add(ruut);
            }
        }

        peaLava.show();
    }

    class Ruut extends StackPane {
        private Text nupp = new Text();


        public Ruut() {
            //katsetamise mõttes
            String player = "X";
            nupp.setFont(Font.font(120));
            Rectangle mänguRuut = new Rectangle(200, 200);

            mänguRuut.setFill(null);
            mänguRuut.setStroke(Color.BLACK);
            setAlignment(Pos.CENTER);
            getChildren().addAll(mänguRuut, nupp);

            setOnMouseClicked(event -> {
                if(player.equals("X")){
                    nuppX();
                } else if(player.equals("O")){
                    nuppO();
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

    }

    public static void main(String[] args) {
        launch(args);
    }
}
