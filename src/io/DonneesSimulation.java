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
         this.SetCarte(c);
         this.SetIncendies(i);
         this.SetRobot(r);
         this.fichier = file;
     }


     public void SetCarte(Carte c){
         this.carte =c ;
     }

     public void SetIncendies(Incendie i[]){
         this.incendies = i;
     }

     public void SetRobot(Robot r[]){
         this.robots = r;
     }

     public Carte GetCarte(){
         return this.carte;
     }

     public Incendie[] GetIncendies(){
         return this.incendies;
     }

     public Robot[] GetRobots(){
         return this.robots;
     }

    private static String AfficherIncendies(Incendie[] incendies){
        /**
         * Affiche textuellement les incendies
         */
         String returned_string = "\n\n\t#Incendies";
         for (int i=0; i<incendies.length; i++){
             returned_string += "\nIncendie " + i + ": Position : (" + incendies[i].GetLigne()
                    + "," + incendies[i].GetColonne() + ")\tIntensité : "
                    + incendies[i].GetIntensite();
         }
         return returned_string;
    }

    private static String AfficherRobots(Robot[] robots){
        /**
         * Affiche textuellement les robots
         */
         String stringReturned = "\n\n\t#Robots";
         for (int i=0; i<robots.length; i++){
             stringReturned += "\nRobot " + i + ": Position : (" + robots[i].GetLigne()
                    + "," + robots[i].GetColonne() + ")\t type : " + "\t Vitesse : " + robots[i].GetVitesse();
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
         for (int len=0; len < this.GetRobots().length; len++){
             this.GetRobots()[len].setLigne(nouvelle.GetRobots()[len].GetLigne());
             this.GetRobots()[len].setColonne(nouvelle.GetRobots()[len].GetColonne());
             this.GetRobots()[len].setReservoir(nouvelle.GetRobots()[len].getReservoir());
         }
         for (int len=0; len < this.GetIncendies().length; len++){
             this.GetIncendies()[len].setIntensite(nouvelle.GetIncendies()[len].GetIntensite());
         }
     }
}
