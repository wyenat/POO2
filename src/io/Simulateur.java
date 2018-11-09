package io;

import java.io.FileNotFoundException;
import java.util.zip.DataFormatException;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.lang.Math;

import gui.GUISimulator;
import gui.Rectangle;
import gui.Simulable;
import gui.Text;

public class Simulateur implements Simulable {

    public DonneesSimulation donnees;

    public int pas;
    public int time;

    public Evenement[] Evenements = new Evenement[1000000];
    public int nb_evenements;
    public Case[] positions;

    public GUISimulator gui = new GUISimulator(800, 600, Color.BLACK);

    public Simulateur(DonneesSimulation data) {
        gui.setSimulable(this);				// association a la gui!

        /*Lecture des données de simulation */
        this.donnees = data;
        this.time = 0;
        this.pas = 1 + this.donnees.GetCarte().GetTailleCases()/500;
        this.nb_evenements = 0;
        this.positions = new Case[this.donnees.GetRobots().length];
        for (int i =0; i<this.donnees.GetRobots().length; i++){
            int lig = this.donnees.GetRobots()[i].GetLigne();
            int col = this.donnees.GetRobots()[i].GetColonne();
            this.positions[i] = this.donnees.GetCarte().GetTableauDeCases()[lig* this.donnees.GetCarte().GetNbColonnes()+col];

        }
        draw();
    }

    public void executeEvenements(){
        /** Trouve dans la liste des évènements ceux dont la date est passée,
         * et les execute
         */
         for (int i=0; i<nb_evenements; i++){
             if (this.time <= this.Evenements[i].getDate() &  this.Evenements[i].getDate() < this.time + this.pas){
                System.out.println(this.Evenements[i]);
                this.Evenements[i].execute();
             }
         }
    }

    public void addEvenement(Evenement e){
        /**
         * Ajoute un évènement
         */
         this.Evenements[nb_evenements] = e;
         this.nb_evenements++;
    }

    public void AfficherEvenements(){
        /**
         * Affiche les Evenements dans le tableau d'évènements
         */
         for (int i=0; i<nb_evenements; i++){
            System.out.println(i +  " : " + this.Evenements[i].toString());
         }
    }

    public boolean simulationTerminee(){
        /**
         * Retourne true s'il n'y a plus d'évènements en cours
         */
         for (int i=0; i<nb_evenements; i++){
             if (this.time <= this.Evenements[i].getDate()){
                 return false;
             }
         }
         return false;
    }

    @Override
    public void next() {
      /**
       * Avance la simulation d'un pas de temps
       */

       if (simulationTerminee()){
       }
       else{
          this.time += this.pas;
          System.out.println("time = " + this.time);
          executeEvenements();
          draw();
      }

    }

    @Override
    public void restart(){
      /**
       * Replace la simulation dans les conditions initiales
       */
      this.time = 0;
      int sauvegarde_nb_evenements = this.nb_evenements;
    //   Evenement[] sauvegarde_evenements = Evenements;
      try{
        //   System.out.println("##########AVANT RESET########");
        //   AfficherEvenements();
          DonneesSimulation data_init = LecteurDonnees.lire(this.donnees.fichier);
          this.donnees.RemettreInitial(data_init);
        //   System.out.println(this.nb_evenements);
          this.nb_evenements = sauvegarde_nb_evenements;
        //   System.out.println(this.nb_evenements);
        // System.out.println("##########APRES RESET########");
        AfficherEvenements();
        //   this.Evenements = sauvegarde_evenements;
          draw();
      }
      catch (FileNotFoundException e) {
          System.out.println("fichier " + this.donnees.fichier+ " inconnu ou illisible");
      } catch (DataFormatException e) {
          System.out.println("\n\t**format du fichier " +this.donnees.fichier+ " invalide: " + e.getMessage());
      }
    }


    public void draw(){
      // gui.reset();

      Carte cart = this.donnees.GetCarte();
      int nb_lignes = cart.GetNbLignes();
      int nb_colonnes = cart.GetNbColonnes();

      /*La valeur taille_cases dans Cartes sert à quoi ?? J'ai choisi
      d'adapter la taille des cases à ce qu'on peut bien voir à l'écran
      en fonction du nb de lignes et de colonnes*/
      //int taille_theorique cases = cart.GetTailleCases();

      int taille_ecran = 500;
      int taille_cases = taille_ecran/Math.max(nb_colonnes, nb_lignes);

      // System.out.println("LIGNE = " + nb_lignes + "\nColonne = " + nb_colonnes);
      // System.out.println("Taille des cases = " + taille_cases);

      this.gui.reset();

      for (Case c : cart.GetTableauDeCases()){
          c.draw_case(this.gui, taille_cases);
      }

      for (Incendie i : this.donnees.GetIncendies()){
        i.draw_incendie(this.gui, taille_cases);
      }

      for (Robot r : this.donnees.GetRobots()){
        r.draw_robot(this.gui, taille_cases);
      }
    }

    public Case getPosition(Robot robot){
        Case C=null;
        for (int i = 0; i<this.donnees.GetRobots().length; i++){
            if (this.donnees.GetRobots()[i]==robot){

            C = this.positions[i];
            break;
            }
        }
        return C;

    }

    public void setPosition(Robot robot, Case C){

        for (int i = 0; i<this.donnees.GetRobots().length; i++){
            if (this.donnees.GetRobots()[i]==robot){

                this.positions[i] = C;
                break;
            }
        }
    }
}
