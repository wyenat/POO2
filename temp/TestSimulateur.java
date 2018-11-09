/**
Fichier à modifier pour faire l'affichage !
On veut crééer une classe qui réalise l'interface Simulable
(Simulateur implements Simulable)


TEST DE LUCILLE, bas les pattes pour le moment svp
*/

import io.LecteurDonnees;

import java.io.FileNotFoundException;
import java.util.zip.DataFormatException;


public class TestSimulateur {

    public static void main(String[] args) {

        if (args.length < 1) {
            System.out.println("Syntaxe: java TestSimulateur <nomDeFichier>");
            System.exit(1);
        }
        try {
            LecteurDonnees.lire(args[0]);
        } catch (FileNotFoundException e) {
            System.out.println("fichier " + args[0] + " inconnu ou illisible");
        } catch (DataFormatException e) {
            System.out.println("\n\t**format du fichier " + args[0] + " invalide: " + e.getMessage());
        }
    }
}
