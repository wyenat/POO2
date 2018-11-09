package io;
import io.*;


public class Robotdrone extends Robot{

  public Robotdrone(int lig, int col, double vitesse_deplacement){
    super(lig, col, vitesse_deplacement);
    super.setReservoir(10000);
    //Degueu mais je sais pas faire autrement
    TypeRobot T;
    String mmT = "DRONE";
    T = TypeRobot.valueOf(mmT);
    super.SetTypeRobot(T);
    //


  }

  public long getDateremplir(){
    return 30*60;
  }

  public boolean testRemplir(Simulateur simu, int lig, int col){
    if (super.GetLigne() == lig && super.GetColonne() == col){
        return true;
      }

    return false;
  }

  public double remplirReservoir(Simulateur simu, int lig, int col){
    if (testRemplir(simu, lig, col)){
      return 10000;
    }
    return 0;
  }

  public long getDateVider(double intensite){
    return ((int)(intensite))/333;
  }


  public double vider(Simulateur simu, int ligne, int colonne, double intensite){
      if (testVider(simu, ligne, colonne)){
        if (intensite >= super.getReservoir()){
          return 10000;
        }
        else {
          return intensite;
        }
      }
      return 0;
  }

  public double GetVitesse(NatureTerrain Nature){
    double vitesse = super.GetVitesse();
    return vitesse;
  }


  public boolean testVider(Simulateur simu, int lig, int col){
    Incendie[] incendies = simu.donnees.GetIncendies();
    boolean incendie_ici = false;
    Incendie incendie = incendies[0];

    for (int i=0; i<incendies.length; i++){
      if (incendies[i].GetLigne()==lig && incendies[i].GetColonne()==col){
        incendie_ici = true;
        incendie = incendies[i];
      }
    }
    if (incendie_ici){


      if (this.GetLigne() == incendie.GetLigne() && this.GetColonne() == incendie.GetColonne()){
        return true;
      }
    }
    return false;
  }



  public boolean test_deplacement(Case C){
    return true;
  }


  public void calcul_deplacement(Robot robot, Case C, DonneesSimulation donnees){
      // NatureTerrain nature;
      // String mmnature = "EAU";
      // nature = NatureTerrain.valueOf(mmnature);
      // Carte carte = donnees.GetCarte();
      // int nb_lignes =100;// carte.GetNbLignes();
      // int nb_colonnes = 100;//carte.GetNbColonnes();
      // int ligne_arrivee = C.GetLigne();
      // int colonne_arrivee = C.GetColonne();
      // int ligne_depart = robot.GetLigne();
      // int colonne_depart = robot.GetColonne();
      // Case[] Tableau = new Case[1000];//(0, 0, nature);//[ecart_col*ecart_lig];
      // int nb_cases = 0;
      // int sum_lig = 1;
      // int sum_col = 1;
      //
      //
      // int ecart_lig = ligne_arrivee - ligne_depart;
      // if (ligne_arrivee < ligne_depart){
      //   sum_lig = -1;
      //   ecart_lig = -ecart_lig;
      // }
      // int ecart_col = colonne_arrivee - colonne_depart;
      // if (colonne_arrivee < colonne_depart){
      //   sum_col = -1;
      //   ecart_col = -ecart_col;
      // }
      //
      //
      // if (ligne_arrivee < nb_lignes && ligne_depart < nb_colonnes){
      //     while (ligne_depart != ligne_arrivee){
      //       Case c = new Case(ligne_depart, colonne_depart, nature);
      //       Tableau[nb_cases] = c;
      //       ligne_depart = ligne_depart + sum_lig;
      //       nb_cases++;
      //     }
      //     while (colonne_depart != colonne_arrivee){
      //       Case c = new Case(ligne_depart, colonne_depart, nature);
      //       Tableau[nb_cases] = c;
      //       colonne_depart = colonne_depart + sum_col;
      //       nb_cases++;
      //     }
      //     Case c = new Case(ligne_depart, colonne_depart, nature);
      //     Tableau[nb_cases] = c;
      //     colonne_depart = colonne_depart + sum_col;
      //     nb_cases++;
      // }
      // else {
      //   System.out.println("AAAH");
      // }
      // return Tableau;
    }

}
