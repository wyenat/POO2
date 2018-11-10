package io;
import io.*;
import gui.*;
import java.awt.*;

import io.TypeRobot;

import java.util.*;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class ChefPompier {
    public Simulateur simu;
    public boolean[] incendiesAffectes;

    public ChefPompier(Simulateur simu){
        this.simu = simu;
        this.incendiesAffectes = new boolean[simu.donnees.GetIncendies().length];
        for (int i =0; i<this.incendiesAffectes.length; i++){
            this.incendiesAffectes[i] =false;
        }
    }

    private Chemin trouverPointDeau(Case arrivee, Robot robo){
        /**
         * Trouve le point d'eau le plus proche
         */
         LinkedList<Case> casesDeau = new LinkedList<Case>();
         int nb_cases = this.simu.donnees.GetCarte().GetNbLignes() * this.simu.donnees.GetCarte().GetNbColonnes();
         for (int c=0; c < nb_cases; c++){
             Case current = this.simu.donnees.GetCarte().GetTableauDeCases()[c];
             if (current.GetNature() == NatureTerrain.EAU){
                 if (robo.GetTypeRobot() == TypeRobot.DRONE){
                         casesDeau.add(current);
                        //  System.out.println("current " + current);
                     }
                 else{
                     for (Direction dir:Direction.values()){
                         if (this.simu.donnees.GetCarte().voisinExiste(current, dir)){
                             Case voisine = this.simu.donnees.GetCarte().GetVoisin(current, dir);
                             if (robo.test_deplacement(voisine)){
                                //  System.out.println("voisine " + voisine);
                                 casesDeau.add(voisine);
                             }
                         }
                     }
                 }
            }
        }
        // System.out.println("Les cases " + casesDeau);
         long candidat = -1;
         Chemin chem_candidat = null;
         Iterator iter = casesDeau.descendingIterator();
         while (iter.hasNext()){
             Case eau = (Case) iter.next();
             Chemin chem  = new Chemin(arrivee, eau, robo, this.simu);
             if ((chem.getTemps() < candidat) | (candidat == -1)){
                 candidat = chem.getTemps();
                 chem_candidat = chem;
             }
         }
        //  System.out.println("arrivee" + arrivee);
         return chem_candidat;
    }

    private void traiterIncendie(Chemin chem, Incendie incendie){
      /**
      * Donne les ordres au robot d'aller traiter cet incendie sa mère
      */
      double reservoir = simu.getReservoir(chem.getRobot());
      double intensite = incendie.GetIntensite();

      Chemin pointEau = trouverPointDeau(chem.getArrivee(), chem.getRobot());
      Case case_incendie = chem.getArrivee();
      chem.deplacement();
      if (chem.getRobot().GetTypeRobot()!=TypeRobot.PATTES && reservoir ==0){
          pointEau.deplacement();
          EvenementRemplirReservoir remplir = new EvenementRemplirReservoir(simu, chem.getRobot());
          Chemin retour = new Chemin(pointEau.getArrivee(), case_incendie, chem.getRobot(), this.simu);
          retour.deplacement();
      }


      int nombre_vidages= 0;
      if (intensite>=reservoir && chem.getRobot().GetTypeRobot()!=TypeRobot.PATTES){
          nombre_vidages = (int)intensite/(int)reservoir;
          if (nombre_vidages*(int)reservoir != intensite){
              nombre_vidages+=1;
          }
      }

      else {
          nombre_vidages = 1;
      }
      Chemin retour = new Chemin(pointEau.getArrivee(), case_incendie, chem.getRobot(), this.simu);
      for (int i = 0; i<nombre_vidages; i++) {
        //   System.out.println("alors "+ reservoir + " et inten: " + intensite + "robot " + chem.getRobot());
          EvenementDeverserEau vider = new EvenementDeverserEau(simu, chem.getRobot());
        //   System.out.println(intensite + " ct INTENSE "+ reservoir +" et " + vider);
          if (chem.getRobot().GetTypeRobot() != TypeRobot.PATTES){
              pointEau.deplacement();
              EvenementRemplirReservoir remplir = new EvenementRemplirReservoir(simu, chem.getRobot());
              

              retour.deplacement();
          }



        //   System.out.println("LE RETOUR " + pointEau);
      }



    }

    public void proposer_incendie_naif(){
        Incendie[] incendies =this.simu.donnees.GetIncendies();
        Robot[] robots =this.simu.donnees.GetRobots();
        for (int incendie_indice = 0; incendie_indice < this.incendiesAffectes.length; incendie_indice++){
            for (int robot_indice = 0; robot_indice < this.simu.donnees.GetRobots().length; robot_indice++){
              if (!this.incendiesAffectes[incendie_indice]){
                if (robots[robot_indice].getEtat() == Etat.LIBRE){
                    Carte map =this.simu.donnees.GetCarte();
                    Case depart = map.GetTableauDeCases()[map.GetNbColonnes()*this.simu.getPosition(robots[robot_indice]).GetLigne() + this.simu.getPosition(robots[robot_indice]).GetColonne()];
                    Case arrivee = map.GetTableauDeCases()[map.GetNbColonnes()*incendies[incendie_indice].GetLigne() + incendies[incendie_indice].GetColonne()];
                    Chemin chem = new Chemin(depart, arrivee, robots[robot_indice],this.simu);
                    if (chem.possible){
                    //   System.out.println(" le robot "+ robots[robot_indice] + " va gerer " + incendies[incendie_indice] + " En passant par " + chem);
                      this.incendiesAffectes[incendie_indice] = true;
                      this.traiterIncendie(chem, incendies[incendie_indice]);
                    }
                    else{
                      continue;
                    }
                }
                else{
                  continue;
                }
              }
          else{
            continue;
          }
        }

        }
    }


    public void proposer_incendie_evolue(){
        Incendie[] incendies = this.simu.donnees.GetIncendies();
        Robot[] robots = this.simu.donnees.GetRobots();
        Robot candidat = null;

        for (int incendie_indice = 0; incendie_indice < this.incendiesAffectes.length; incendie_indice++){
          Chemin chemin_candidat = null;
            for (int robot_indice = 0; robot_indice < this.simu.donnees.GetRobots().length; robot_indice++){
              if (!this.incendiesAffectes[incendie_indice]){
              long tempsmin = -1;
                if (robots[robot_indice].getEtat() == Etat.LIBRE){
                    Carte map =this.simu.donnees.GetCarte();
                    Case depart = map.GetTableauDeCases()[map.GetNbColonnes()*this.simu.getPosition(robots[robot_indice]).GetLigne() + this.simu.getPosition(robots[robot_indice]).GetColonne()];
                    Case arrivee = map.GetTableauDeCases()[map.GetNbColonnes()*incendies[incendie_indice].GetLigne() + incendies[incendie_indice].GetColonne()];
                    Chemin chem = new Chemin(depart, arrivee, robots[robot_indice],this.simu);
                    if (chem.possible){
                      if ((tempsmin>chem.getTemps()) | tempsmin == -1 ){
                        chemin_candidat = chem;
                        candidat = robots[robot_indice];
                        tempsmin = chem.getTemps();
                      }
                      else{
                          continue;
                      }
                    }
                    else{
                      continue;
                    }
                }
                else{
                  continue;
                }


            // System.out.println(candidat + " en passant par " + chemin_candidat + "pour " + incendies[incendie_indice]);
            if (chemin_candidat != null){
              this.incendiesAffectes[incendie_indice] = true;
              this.traiterIncendie(chemin_candidat, incendies[incendie_indice]);
            }
           }
          else{
              continue;
            }
        }
       }
      }

      public void extinction(){
          while(true){
              this.proposer_incendie_evolue();

          }

      }

    }
