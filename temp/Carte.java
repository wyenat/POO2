package io;

//import java.io.Case;

public class Carte{
    /**
     * Contient les donnees de la carte
     */

     private int tailleCases;
     private int nb_lignes;
     private int nb_colonnes;
     private Case[] TableauDeCases;

     public Carte(int tailleC, int nbL, int nbC){
         /**
          * Cree une carte de taille nbL*nbC dont les cases sont de taille tailleC
          */
         this.SetTailleCases(tailleC);
         this.SetNbLignes(nbL);
         this.SetNbColonnes(nbC);

     }

     public void SetTailleCases(int taille){
         /* Definit la taille des cases.
          *Invariant de classe : taille > 0
          */
          if (taille <= 0){
              throw new IllegalArgumentException("Taille des cases nulle ou negative !");
          }
          this.tailleCases = taille;
     }

     public int GetTailleCases(){
         /**
          * Retourne la taille des cases
          */
          return this.tailleCases;
     }

     public void SetNbLignes(int taille){
         /* Definit le nombre de lignes.
          *Invariant de classe : NbLignes > 0
          */
          if (taille <= 0){
              throw new IllegalArgumentException("Nombre de lignes nul ou negatif !");
          }
          this.NbLignes = taille;
     }

     public int GetNbLignes(){
         /**
          * Retourne le nombre de lignes
          */
          return this.NbLignes;
     }

     public void SetNbColonnes(int taille){
         /* Definit le nombre de Colonnes.
          *Invariant de classe : NbColonnes > 0
          */
          if (taille <= 0){
              throw new IllegalArgumentException("Nombre de Colonnes nul ou negatif !");
          }
          this.nb_colonnes = taille;
     }

     public int GetNbColonnes(){
         /**
          * Retourne le nombre de Colonnes
          */
          return this.nb_Colonnes;
     }

     public int GetTableauDeCases(){
         /**
          * Retourne le tableau de cases
          */
          return this.TableauDeCases;
     }

    //  public int voisinExiste(Case C, dir direction){
    //    /*Renvoie 1 s'il a un voisin dans la direction donnee*/
    //       switch(direction){
    //         /*Fais un case sur la direction
    //         On verifie qu'on ne depasse pas */
    //           case "NORD":
    //             if (C.ligne -1 >= 0){
    //               return 1;
    //             }
    //             else{
    //               return 0;
    //             }
    //             break;
     //
    //           case "EST":
    //             if (C.colonne +1 <= this.GetNbColonnes()){
    //               return 1;
    //             }
    //             else{
    //               return 0;
    //             }
    //             break;
     //
    //           case "SUD":
    //             if (C.ligne -1 <= this.GetColonne()){
    //               return 1;
    //             }
    //             else{
    //               return 0;
    //             }
    //             break;
    //           case "OUEST":
    //             if (C.colonne -1 >= 0){
    //               return 1;
    //               }
    //             else{
    //               return 0;
    //             }
    //             break;
     //
    //       }

     //}

    //  public int GetVoisin(Case C, dir direction){
    //       /*Renvoie le voisin de la case C dans la direction donnee*/
    //       if (voisinExiste(C, direction)){
     //
    //         /*On verifie quand même que ce voisin existe*/
    //         int ligne = C.GetLigne();
    //         int colonne = C.GetColonne();
    //         Case[] Tableau = this.GetTableauDeCases();
    //         int taille = this.GetNbColonnes();
     //
    //         switch(direction){
     //
    //           case "NORD":
    //             return Tableau[taille*(ligne-1) + colonne];
     //
    //           case "EST":
    //             return Tableau[taille*(ligne) + colonne+1];
     //
    //           case "SUD":
    //             return Tableau[taille*(ligne+1) + colonne];
     //
    //           case "OUEST":
    //             return Tableau[taille*(ligne) + colonne -1];
    //       }
    //     } else {
    //       /*Triche pour renvoyer quelquechose même s'il y a pas de voisin*/
    //       return C;
    //     }
    //  }


 }
