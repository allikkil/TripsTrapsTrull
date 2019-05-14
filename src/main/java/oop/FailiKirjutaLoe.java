package oop;

import java.io.*;

public class FailiKirjutaLoe {
    private String mängija; // mängija nimi

    public static int loeASkoor() throws Exception {
        int aSkoor = 0;
        try(BufferedReader br = new BufferedReader(new FileReader("skoorid.txt"))) {
            aSkoor = Integer.parseInt(br.readLine());
        }
        return aSkoor;
    }

    public static int loeVSkoor() throws Exception {
        int vSkoor = 0;
        try(BufferedReader br = new BufferedReader(new FileReader("skoorid.txt"))) {
            br.readLine();
            vSkoor = Integer.parseInt(br.readLine());
        }
        return vSkoor;
    }

    public static void kirjutaSkoorid(int a, int v) throws Exception {
        // Sisend: arvuti võite; viike
        try(BufferedWriter bw = new BufferedWriter(new FileWriter("skoorid.txt"))) {
            bw.write(String.valueOf(a));
            bw.write("\r\n");
            bw.write(String.valueOf(v));
        }
    }
}
