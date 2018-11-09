// package io;

import java.io.Carte;

import java.io.FileNotFoundException;
import java.util.zip.DataFormatException;

public class TestDonneesSimulation {

    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Syntaxe: java TestLecteurDonnees <nomDeFichier>");
            System.exit(1);
        }
        try {
            DonneesSimulation donnees = new DonneesSimulation();
            donnes.cree(args[0]);
        } catch (FileNotFoundException e) {
            System.out.println("fichier " + args[0] + " inconnu ou illisible");
        } catch (DataFormatException e) {
            System.out.println("\n\t**format du fichier " + args[0] + " invalide: " + e.getMessage());
        }
    }

}
