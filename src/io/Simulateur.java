package io;

import java.io.FileNotFoundException;
import java.util.zip.DataFormatException;
import java.util.*;
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

    public LinkedList<Evenement> evenements = new LinkedList<Evenement>();
    public Case[] positions;
    public double[] reservoirs;

    public GUISimulator gui = new GUISimulator(800, 600, Color.BLACK);

    public Simulateur(DonneesSimulation data) {
        /**
         *  Les les données dans data, et crée la simulation associée.
         */
        gui.setSimulable(this);	
        this.donnees = data;
        this.time = 0;
        this.pas = 1 + this.donnees.getCarte().getTailleCases()/500;
        this.positions = new Case[this.donnees.getRobots().length];
        this.reservoirs = new double[this.donnees.getRobots().length];
        for (int i =0; i<this.donnees.getRobots().length; i++){
            int lig = this.donnees.getRobots()[i].getLigne();
            int col = this.donnees.getRobots()[i].getColonne();
            this.positions[i] = this.donnees.getCarte().getTableauDeCases()[lig* this.donnees.getCarte().getNbColonnes()+col];
            this.reservoirs[i] = this.donnees.getRobots()[i].getReservoir();

        }
        draw();
    }

    public void executeEvenements(){
        /** 
         * Trouve dans la liste des évènements ceux dont la date est passée,
         * et les execute
         */
         Iterator<Evenement> iter = evenements.iterator();
         while(iter.hasNext()){
             Evenement eve = iter.next();
             if (this.time <= eve.getDate() &  eve.getDate() < this.time + this.pas){
                // System.out.println(eve);
                eve.execute();
             }
         }
    }

    public void addEvenement(Evenement e){
        /**
         * Ajoute un évènement
         */
         this.evenements.add(e);
    }

    public void AfficherEvenements(){
        /**
         * Affiche les Evenements dans le tableau d'évènements
         */
         Iterator<Evenement> iter = evenements.iterator();
         int i=0;
         while (iter.hasNext()){
            Evenement eve = iter.next();
            System.out.println(i +  " : " + eve);
            i++;
         }
    }

    private boolean simulationTerminee(){
        /**
         * Retourne true s'il n'y a plus d'évènements en cours
         */
         Iterator<Evenement> iter = evenements.iterator();
         while (iter.hasNext()){
             Evenement eve = iter.next();
             if (this.time <= eve.getDate()){
                 return false;
             }
         }
         System.out.println("Simulation terminée");
         return true;
    }

    @Override
    public void next() {
      /**
       * Avance la simulation d'un pas de temps, si elle n'est pas finie.
       */

       if (simulationTerminee()){
       }
       else{
          this.time += this.pas;
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
      try{
          DonneesSimulation data_init = LecteurDonnees.lire(this.donnees.fichier);
          this.donnees.RemettreInitial(data_init);
          draw();
      }
      catch (FileNotFoundException e) {
          System.out.println("fichier " + this.donnees.fichier+ " inconnu ou illisible");
      } catch (DataFormatException e) {
          System.out.println("\n\t**format du fichier " +this.donnees.fichier+ " invalide: " + e.getMessage());
      }
    }


    private void draw(){
        /**
         * Dessine la simulation 
         */
      Carte cart = this.donnees.getCarte();
      int nb_lignes = cart.getNbLignes();
      int nb_colonnes = cart.getNbColonnes();
      int taille_ecran = 500;
      int taille_cases = taille_ecran/Math.max(nb_colonnes, nb_lignes);
      this.gui.reset();

      for (Case c : cart.getTableauDeCases()){
          c.draw_case(this.gui, taille_cases);
      }

      for (Incendie i : this.donnees.getIncendies()){
        i.draw_incendie(this.gui, taille_cases);
      }

      for (Robot r : this.donnees.getRobots()){
        r.draw_robot(this.gui, taille_cases);
      }
    }

    public Case getPosition(Robot robot){
        /**
         * Retourne la position du robot entré, en case.
         */
        Case C=null;
        for (int i = 0; i<this.donnees.getRobots().length; i++){
            if (this.donnees.getRobots()[i]==robot){

                C = this.positions[i];
                break;
            }
        }
        return C;

    }

    public void setPosition(Robot robot, Case C){
        /**
         * Met le robot à la case rentrée
         */
        for (int i = 0; i<this.donnees.getRobots().length; i++){
            if (this.donnees.getRobots()[i]==robot){

                this.positions[i] = C;
                break;
            }
        }
    }

    public double getReservoir(Robot robot){
        /** 
         * Retourne le reservoir du robot 
         */
        double volume=0;
        for (int i = 0; i<this.donnees.getRobots().length; i++){
            if (this.donnees.getRobots()[i]==robot){

            volume = this.reservoirs[i];
            break;
            }
        }
        return volume;

    }

    public void setReservoir(Robot robot, double volume){
        /**
         * Met le volume entré dans le reservoir du robot
         */
        for (int i = 0; i<this.donnees.getRobots().length; i++){
            if (this.donnees.getRobots()[i]==robot){

                this.reservoirs[i] = volume;
                break;
            }
        }
    }
}
