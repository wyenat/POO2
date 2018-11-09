
import io.*;

import java.io.FileNotFoundException;
import java.util.zip.DataFormatException;

public class TestEvenement {

    public static void main(String[] args) {

        try {
          Simulateur simu = new Simulateur(LecteurDonnees.lire(args[0]));

          Direction sud;
          String mdir = "SUD";
          sud = Direction.valueOf(mdir);


          Direction ouest;
          String mdir2 = "OUEST";
          ouest = Direction.valueOf(mdir2);

          Direction est;
          String mdir3 = "EST";
          est = Direction.valueOf(mdir3);

          Robot pattes = simu.donnees.GetRobots()[2];
          Robot roues = simu.donnees.GetRobots()[1];
          Robot Vivien = simu.donnees.GetRobots()[0];


          Evenementdeplacement deplacer = new Evenementdeplacement(simu, Vivien, sud);

          //EvenementRemplirReservoir remplirpattes = new EvenementRemplirReservoir(simu, pattes);


          Evenementdeplacement deplacerencore = new Evenementdeplacement(simu, Vivien, sud);

          Evenementdeplacement deplacerencoreencore = new Evenementdeplacement(simu, Vivien, sud);

          Evenementdeplacement deplace = new Evenementdeplacement(simu, Vivien, sud);

          Evenementdeplacement deplac = new Evenementdeplacement(simu, Vivien, ouest);

          Evenementdeplacement depla = new Evenementdeplacement(simu, Vivien, ouest);

          EvenementDeverserEau vide = new EvenementDeverserEau(simu, Vivien);

          Evenementdeplacement depl = new Evenementdeplacement(simu, Vivien, est);

          Evenementdeplacement dep = new Evenementdeplacement(simu, Vivien, est);

          EvenementRemplirReservoir remplir = new EvenementRemplirReservoir(simu, Vivien);

          Evenementdeplacement deplacerencoreencorencore = new Evenementdeplacement(simu, Vivien, ouest);

           Evenementdeplacement deplacerencoreencorencore2 = new Evenementdeplacement(simu, Vivien, ouest);

           EvenementDeverserEau vide2 = new EvenementDeverserEau(simu, Vivien);

           Evenementdeplacement de = new Evenementdeplacement(simu, roues, ouest);

           Evenementdeplacement d = new Evenementdeplacement(simu, roues, ouest);
        }

        // Partie levée d'exception
        catch (FileNotFoundException e) {
          System.out.println("fichier " + args[0] + " inconnu ou illisible");
        } catch (DataFormatException e) {
          System.out.println("\n\t**format du fichier " + args[0] + " invalide: " + e.getMessage());
        }





    }

}
