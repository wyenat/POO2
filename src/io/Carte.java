package io;

public class Carte{
    /**
     * Contient les données de la carte
     */

     private int taille_cases;
     private int nb_lignes;
     private int nb_colonnes;
     private Case[] tableau_de_cases;

     public Carte(int taille_cases, int nb_lignes, int nb_colonnes, Case[] tableau_de_cases){
       /**
       Crée une carte de taille nb_lignes*nb_colonnes dont les cases sont de
       taille taille_cases
       */
       this.setTailleCases(taille_cases);
       this.setNbLignes(nb_lignes);
       this.setNbColonnes(nb_colonnes);
       this.setTableauDeCases(tableau_de_cases);
     }

     public void setTableauDeCases(Case[] tab){
         /**
          * Définit toutes les case
          * Invariant de classes : len(tableau) == nbLignes * nbColonnes
          */
          if (tab.length != this.getNbLignes() * this.getNbColonnes() ){
              throw new IllegalArgumentException("Nombre de cases non conforme !");
          }
          this.tableau_de_cases = tab;
     }

     public Case[] getTableauDeCases(){
       return this.tableau_de_cases;
     }

     public void setTailleCases(int taille){
         /* Définit la taille des cases.
         Invariant de classe : taille > 0
         */
         if (taille <= 0){
           throw new IllegalArgumentException("Taille des cases nulle ou négative !");
         }
         this.taille_cases = taille;
     }

     public int getTailleCases(){
         /**
          * returns the value of TailleCases
          */
          return this.taille_cases;
      }



     public void setNbLignes(int nbl){
         /* Définit le nombre de lignes.
         Invariant de classe : NbLignes > 0
         */
         if (nbl <= 0){
           throw new IllegalArgumentException("Nombre de lignes nul ou négatif !");
         }
         this.nb_lignes = nbl;
     }

     public int getNbLignes(){
         /**
          * returns the value off NbLignes
          */
          return this.nb_lignes;
      }

     public void setNbColonnes(int nbc){
         /* Définit le nombre de Colonnes.
         Invariant de classe : NbColonnes > 0
         */
         if (nbc <= 0){
           throw new IllegalArgumentException("Nombre de Colonnes nul ou négatif !");
         }
         this.nb_colonnes = nbc;
     }

     public int getNbColonnes(){
         /**
          * returns the value of NbColonnes
          */
          return this.nb_colonnes;
      }

      public boolean voisinExiste(Case C, Direction direction){
         /**
          * Renvoie true s'il a un voisin dans la direction donnée, false sinon.
          */
            switch(direction){
              /*Fais un case sur la direction
              On vérifie qu'on ne depasse pas */
                case NORD:
                  if (C.getLigne() -1 >= 0){
                    return true;
                  }
                  else{
                    return false;
                  }


                case EST:
                  if (C.getColonne() +1 < this.getNbColonnes()){
                    return true;
                  }
                  else{
                    return false;
                  }


                case SUD:
                  if (C.getLigne() +1 < this.getNbLignes()){
                    return true;
                  }
                  else{
                    return false;
                  }

                case OUEST:
                  if (C.getColonne() -1 >= 0){
                    return true;
                    }
                  else{
                    return false;
                  }
                default:
                  return false;

            }

       }

       public Case getVoisin(Case C, Direction direction){
            /**
             * Renvoie le voisin de la case C dans la direction donnée
             */
            if (voisinExiste(C, direction)){

              /*On vérifie quand même que ce voisin existe*/
              int ligne = C.getLigne();
              int colonne = C.getColonne();
              Case[] Tableau = this.getTableauDeCases();
              int taille = this.getNbColonnes();

              switch(direction){

                case NORD:
                  return Tableau[taille*(ligne-1) + colonne];

                case EST:
                  return Tableau[taille*(ligne) + colonne+1];

                case SUD:
                  return Tableau[taille*(ligne+1) + colonne];

                case OUEST:
                  return Tableau[taille*(ligne) + colonne -1];
                default:
                  return C;
            }
          } else {
            return null;
          }
       }









      public String ToString(){
         String returned_string =  "Taille des cases : " + this.getTailleCases() + "\n" + "Dimensions : " + this.getNbLignes() + " * " + this.getNbColonnes();
         for (int lig=0; lig < this.getNbLignes(); lig++){
             for (int col=0; col < this.getNbColonnes(); col++){
                 returned_string += "\n Case(" + lig + "," + col + "): " + this.tableau_de_cases[lig*this.getNbColonnes()+col].getNature();
             }
         }
         return returned_string;
     }

}
