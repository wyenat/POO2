import io.*;

import java.io.FileNotFoundException;
import java.util.zip.DataFormatException;

public class TestChefPompier{
    public static void main(String[] args) {
        try {
          // Mise en place de la simulation.
          Simulateur simu = new Simulateur(LecteurDonnees.lire(args[0]));
          ChefPompier Enguerran = new ChefPompier(simu);
        //   Enguerran.proposer_incendie_naif();

        Enguerran.extinction();
    }
          // Partie levée d'exception
          catch (FileNotFoundException e) {
            System.out.println("fichier " + args[0] + " inconnu ou illisible");
          } catch (DataFormatException e) {
            System.out.println("\n\t**format du fichier " + args[0] + " invalide: " + e.getMessage());
          }

    }
}
