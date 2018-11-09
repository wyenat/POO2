package io;

import java.io.*;
import java.util.*;
import java.util.zip.DataFormatException;
import io.Carte;


public class LectureDonneesSimulateur {

    /**
     * Lit et simule le contenu d'un fichier de donnees (cases,
     * robots et incendies).
     * Ceci est méthode de classe; utilisation:
     * LecteurDonnees.lire(fichierDonnees)
     * @param fichierDonnees nom du fichier à lire
     */

    public static DonneesSimulateur lire(String fichierDonnees)
        throws FileNotFoundException, DataFormatException {
        LectureDonneesSimulateur lecteur = new LectureDonneesSimulateur(fichierDonnees);
        Carte carte = lecteur.lire_et_simuler_Carte();
        Incendie incendies[];
        incendies = lecteur.lire_et_simuler_Incendies();
        Robot robots[];
        robots = lecteur.lire_et_simuler_Robots();
        scanner.close();
        DonneesSimulateur donneesSimulateur = new DonneesSimulateur(carte, incendies, robots);
        donneesSimulateur.afficher();
        return donneesSimulateur;
    }




    // Tout le reste de la classe est prive!

    private static Scanner scanner;

    /**
     * Constructeur prive; impossible d'instancier la classe depuis l'exterieur
     * @param fichierDonnees nom du fichier a lire
     */
    public LectureDonneesSimulateur(String fichierDonnees)
        throws FileNotFoundException {
        scanner = new Scanner(new File(fichierDonnees));
        scanner.useLocale(Locale.US);
    }

    /**
     * Lit et affiche les donnees de la carte.
     * @throws ExceptionFormatDonnees
     */
    public Carte lire_et_simuler_Carte() throws DataFormatException {
        ignorerCommentaires();
        try {
            int nbLignes = scanner.nextInt();
            int nbColonnes = scanner.nextInt();
            int tailleCases = scanner.nextInt();	// en m
            Case cases[];
            cases = new Case[nbLignes*nbColonnes];
            // System.out.println("Carte " + nbLignes + "x" + nbColonnes
                    // + "; taille des cases = " + tailleCases);

                    for (int lig = 0; lig < nbLignes; lig++) {
                        for (int col = 0; col < nbColonnes; col++) {
                        cases[lig*nbColonnes + col] = this.lire_et_simuler_Case(lig, col);
                        }
            }
            Carte carte = new Carte(tailleCases, nbLignes, nbColonnes, cases);
            return carte;

        } catch (NoSuchElementException e) {
            throw new DataFormatException("Format invalide. "
                    + "Attendu: nbLignes nbColonnes tailleCases");
        }
        // une ExceptionFormat levee depuis lireCase est remontee telle quelle
    }


    /**
     * Lit et affiche les donnees d'une case.
     */
    public Case lire_et_simuler_Case(int lig, int col) throws DataFormatException {
        ignorerCommentaires();
        String chaineNature = new String();
        NatureTerrain nature;

        try {
            chaineNature = scanner.next();
            // si NatureTerrain est un Enum, vous pouvez recuperer la valeur
            // de l'enum a partir d'une String avec:
            nature = NatureTerrain.valueOf(chaineNature);

            verifieLigneTerminee();
        } catch (NoSuchElementException e) {
            throw new DataFormatException("format de case invalide. "
                    + "Attendu: nature altitude [valeur_specifique]");
        }
        Case caseActive = new Case(lig, col, nature);
        return caseActive;
    }


    /**
     * Lit et affiche les donnees des incendies.
     */
    public Incendie[] lire_et_simuler_Incendies() throws DataFormatException {
        ignorerCommentaires();
        try {
            int nbIncendies = scanner.nextInt();
            if (nbIncendies <= 0){
                throw new IllegalArgumentException("Il n'y a pas le feu en fait...");
            }
            Incendie incendies[];
            incendies = new Incendie[nbIncendies];

            // System.out.println("Nb d'incendies = " + nbIncendies);
            for (int i = 0; i < nbIncendies; i++) {
                incendies[i] = lire_et_simuler_Incendie(i);
            }
            return incendies;

        } catch (NoSuchElementException e) {
            throw new DataFormatException("Format invalide. "
                    + "Attendu: nbIncendies");
        }
    }


    /**
     * Lit et affiche les donnees du i-eme incendie.
     * @param i
     */
    public Incendie lire_et_simuler_Incendie(int i) throws DataFormatException {
        ignorerCommentaires();
        try {
            int lig = scanner.nextInt();
            int col = scanner.nextInt();
            int intensite = scanner.nextInt();
            if (intensite <= 0) {
                throw new DataFormatException("incendie " + i
                        + "nb litres pour eteindre doit etre > 0");
            }
            verifieLigneTerminee();

            Incendie incendie = new Incendie(lig, col, intensite);
            return incendie;

        } catch (NoSuchElementException e) {
            throw new DataFormatException("format d'incendie invalide. "
                    + "Attendu: ligne colonne intensite");
        }
    }


    /**
     * Lit et affiche les donnees des robots.
     */
    public Robot[] lire_et_simuler_Robots() throws DataFormatException {
        ignorerCommentaires();
        try {
            int nbRobots = scanner.nextInt();
            if (nbRobots <= 0){
                throw new IllegalArgumentException("Il n'y a pas de robots en fait...");
            }
            Robot robots[];
            robots = new Robot[nbRobots];
            for (int i = 0; i < nbRobots; i++) {
                robots[i] = lire_et_simuler_Robot(i);
            }
            return robots;

        } catch (NoSuchElementException e) {
            throw new DataFormatException("Format invalide. "
                    + "Attendu: nbRobots");
        }
    }


    /**
     * Lit et affiche les donnees du i-eme robot.
     * @param i
     */
    public Robot lire_et_simuler_Robot(int i) throws DataFormatException {
        ignorerCommentaires();

        try {
            int lig = scanner.nextInt();
            int col = scanner.nextInt();
            String type = scanner.next();
            String s = scanner.findInLine("(\\d+)");	// 1 or more digit(s) ?
            // pour lire un flottant:    ("(\\d+(\\.\\d+)?)");
            int vitesse;
            TypeRobot typeRobot = TypeRobot.valueOf(type);
            if (s == null) {
                vitesse = VitesseDefaut(typeRobot);
            } else {
                vitesse = Integer.parseInt(s);
            }
            verifieLigneTerminee();
            Robot robot = new Robot(typeRobot, lig, col, vitesse);
            return robot;
            // System.out.println();
        } catch (NoSuchElementException e) {
            throw new DataFormatException("format de robot invalide. "
                    + "Attendu: ligne colonne type [valeur_specifique]");
        }
    }

private static int VitesseDefaut(TypeRobot type){
    /**
     * Renvoie la vitesse par défaut du robot si elle n'est pas
     * précisée dans le .map
     */
     int vitesseDefaut;
     switch(type){
        case CHENILLES:
            vitesseDefaut = 60;
            break;
        case ROUES:
            vitesseDefaut = 80;
            break;
        case PATTES:
            vitesseDefaut = 30;
            break;
        case DRONE:
            vitesseDefaut = 100;
            break;
        default:
            vitesseDefaut = 0;
    }
    return vitesseDefaut;
}


    /** Ignore toute (fin de) ligne commencant par '#' */
    private void ignorerCommentaires() {
        while(scanner.hasNext("#.*")) {
            scanner.nextLine();
        }
    }

    /**
     * Verifie qu'il n'y a plus rien a lire sur cette ligne (int ou float).
     * @throws ExceptionFormatDonnees
     */
    private void verifieLigneTerminee() throws DataFormatException {
        if (scanner.findInLine("(\\d+)") != null) {
            throw new DataFormatException("format invalide, donnees en trop.");
        }
    }
}
