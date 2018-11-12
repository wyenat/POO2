
package io;

//import java.io.Carte;

public class Incendie{
    /**
     * Contient les donnees d'un incendie:
        -position
        -force
     */
     private int ligne;
     private int colonne;
     private double intensite;

     public int GetLigneIncendie(){
       /* Permet la recuperation de la ligne*/
       return this.ligne;
     }

     public int SetLigneIncendie(int lig){
       /* Permet l'affectation de la ligne
          N'a pour l'instant pas de test */
        this.ligne = lig;
     }


     public int GetColonneIncendie(){
       /* Permet la recuperation de la colonne*/
       return this.colonne;
     }

     public int SetColonneIncendie(int col){
       /* Permet l'affectation de la colonne
          N'a pour l'instant pas de test */
        this.col = col;
     }

     public int GetIntensite(){
       /* Permet la recuperation de l'intensite*/
       return this.intensite;
     }

     public int SetIntensite(double intensite){
       /* Permet l'affectation de l'intensite
          N'a pour l'instant qu'un test simple */
        if (intensite >= 0){
          this.intensite = intensite;
        }
        else{
          throw new IllegalArgumentException("Une intensite ne peut être negative");
        }

     }
}
