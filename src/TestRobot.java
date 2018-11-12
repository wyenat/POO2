

import io.*;

public class TestRobot {

    public static void main(String[] args) {
        Robotdrone Vivien = new Robotdrone(0, 0 ,10);
        Robot Lucille = new Robotapattes(0, 0 ,10);
        Robot Enguerran = new Robotachenilles(0, 0 ,10);
        Robot Roux = new Robotaroues(0, 0 ,10);
        //Degueu mais je sais pas faire autrement
        NatureTerrain T;
        String mmT = "ROCHE";
        T = NatureTerrain.valueOf(mmT);
        //

        //Robot construit avec Robot a roues mais de type Robot,
        //on en cree un du bon type
        Robot BOB = new Robotaroues(0, 0 ,10);
        switch (BOB.GetTypeRobot()) {
            case ROUES:
              Robotaroues BOB_new = new Robotaroues(BOB.GetLigne(), BOB.GetColonne(), BOB.GetVitesse());
              break;

            default:
              System.out.println("AIE");
              break;

        }

        System.out.println(Vivien.GetVitesse(T));
        System.out.println(Lucille.GetLigne());
        System.out.println(Enguerran.GetLigne());
    }

}
