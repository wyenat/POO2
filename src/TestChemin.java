import io.*;

import java.io.FileNotFoundException;
import java.util.zip.DataFormatException;

public class TestChemin {
    public static void main(String[] args) {
        try {
          // Mise en place de la simulation.
          Simulateur simu = new Simulateur(LecteurDonnees.lire(args[0]));
          Robot robot = simu.donnees.GetRobots()[1];
          Case depart = simu.donnees.GetCarte().GetTableauDeCases()[robot.GetLigne()*simu.donnees.GetCarte().GetNbLignes()+robot.GetColonne()];
          Case arrivee = simu.donnees.GetCarte().GetTableauDeCases()[57];
          System.out.println("On va de " + depart + " vers " + arrivee);
          Chemin route = new Chemin(depart, arrivee, robot, simu);
          route.deplacement();
        }
          // Partie levée d'exception
          catch (FileNotFoundException e) {
            System.out.println("fichier " + args[0] + " inconnu ou illisible");
          } catch (DataFormatException e) {
            System.out.println("\n\t**format du fichier " + args[0] + " invalide: " + e.getMessage());
          }
    }
}
