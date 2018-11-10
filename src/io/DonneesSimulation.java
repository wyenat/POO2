package io;

import java.io.FileNotFoundException;
import java.util.zip.DataFormatException;

public class DonneesSimulation{

    private Carte carte;
    private Incendie[] incendies;
    private Robot[] robots;
    public String fichier;

    public DonneesSimulation(Carte c, Incendie i[], Robot r[], String file)
    throws FileNotFoundException, DataFormatException
    {
        /**
         * Constucteur de DonneesSimulation
         */
         this.setCarte(c);
         this.setIncendies(i);
         this.setRobot(r);
         this.fichier = file;
     }


     public void setCarte(Carte c){
         this.carte =c ;
     }

     public void setIncendies(Incendie i[]){
         this.incendies = i;
     }

     public void setRobot(Robot r[]){
         this.robots = r;
     }

     public Carte getCarte(){
         return this.carte;
     }

     public Incendie[] getIncendies(){
         return this.incendies;
     }

     public Robot[] getRobots(){
         return this.robots;
     }

    private static String AfficherIncendies(Incendie[] incendies){
        /**
         * Affiche textuellement les incendies
         */
         String returned_string = "\n\n\t#Incendies";
         for (int i=0; i<incendies.length; i++){
             returned_string += "\nIncendie " + i + ": Position : (" + incendies[i].getLigne()
                    + "," + incendies[i].getColonne() + ")\tIntensité : "
                    + incendies[i].getIntensite();
         }
         return returned_string;
    }

    private static String AfficherRobots(Robot[] robots){
        /**
         * Affiche textuellement les robots
         */
         String stringReturned = "\n\n\t#Robots";
         for (int i=0; i<robots.length; i++){
             stringReturned += "\nRobot " + i + ": Position : (" + robots[i].getLigne()
                    + "," + robots[i].getColonne() + ")\t type : " + "\t Vitesse : " + robots[i].getVitesse();
         }
         return stringReturned;
    }

    public String afficher(){
        /**
         * Affiche textuellement les DonneesSimulation
         */
        String stringReturned = "\t #Carte" + this.carte.ToString();
        stringReturned += AfficherIncendies(this.incendies) + AfficherRobots(this.robots);
        return stringReturned;
    }

    public void RemettreInitial(DonneesSimulation nouvelle){
        /**
         * Mets les paramètres Robots et Incendie de this aux états de nouvelle
         * SANS MODIFIER l'adresse des robots ou des incendies
         */
         for (int len=0; len < this.getRobots().length; len++){
             this.getRobots()[len].setLigne(nouvelle.getRobots()[len].getLigne());
             this.getRobots()[len].setColonne(nouvelle.getRobots()[len].getColonne());
             this.getRobots()[len].setReservoir(nouvelle.getRobots()[len].getReservoir());
         }
         for (int len=0; len < this.getIncendies().length; len++){
             this.getIncendies()[len].setIntensite(nouvelle.getIncendies()[len].getIntensite());
         }
     }
}
