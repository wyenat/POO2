package io;

// import java.io.Carte;
// import java.io.NatureTerrain;

public class Case{
    /**
     * Contient les donnees d'une case
     * On rajouter les invariants de classe :
     * 0 < ligne < NbLignes et 0 < colonne < nbColonnes
     */
     private int ligne;
     private int colonne;
    //  private NatureTerrain nature;

     public int GetLigne(){
         /**
          * Retourne la ligne de la case
          */
         return this.ligne;
     }

     public int GetColonne(){
        /*
         * Retourne la colonne de la case
         */
         return this.colonne;
     }


     public void SetLigne(int i){
         /**
          * Affecte la ligne de la case
          */
         this.ligne = i;;
     }

     public void SetColonne(int i){
         /**
          * Affecte la colonne de la case
          */
         this.colonne = i;;
     }



    //  public enum NatureTerrain GetNature{
    //      /**
    //       *  Retourne la nature de la cases
    //       */
    //       return this.nature;
    //  }
}
