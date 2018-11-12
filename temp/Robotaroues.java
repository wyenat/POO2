package io;

//import java.io.Carte;

public class Robotaroues extends Robot{

    /* Attributs relatifs aux robots a roues et fonctions associees*/

    public Robotaroues()
         {
             this.vitesse = 80;
             this.litres_en_reserve = 5000;
             this.type = "Robotaroues";

         }

    // public void setPosition(Case C){
    //   /* Change la case sur laquelle est le robot si c'est possible*/
    //   if (C.NatureTerrain == "TERRAIN LIBRE" ||  C.NatureTerrain == "HABITAT"){
    //     this.position = C;
    //   }
    //   else{
    //     throw new IllegalArgumentException("Ce Robot ne peut pas aller sur cette case");
    //   }
    // }
    //
    // public double getVitesse(NatureTerrain Nature){
    //   /* On regarde la vitesse du robot sur un type de terrain particulier*/
    //   if (Nature == "TERRAIN LIBRE" ||  Nature == "HABITAT"){
    //     /*On verifie qu'il puisse aller sur ce terrain*/
    //     return this.vitesse;
    //   }
    //   else{
    //     return 0;
    //   }
    // }
    //
    // public void deverserEau(double volume){
    //   if (Volume >= this.capacite_reservoir) {
    //     /*On verifie qu'il puisse verser autant de litres*/
    //     this.capacite_reservoir -= Volume;
    //   }
    //   else{
    //     throw new IllegalArgumentException("On ne peut pas verser autant d'eau");
    //   }
    // }
    //
    //
    // public void remplirReservoir(){
    //   //Fonction qui doit remplir le reservoir de 5000L
    //   // Case C = this.position.GetVoisin();
    //   // ON DOIT PARCOURIR TOUTES LES DIRECTIONS POUR TESTER
    //   //if (this.position.NatureTerrain == this.litres_en_reserve
    }

}
