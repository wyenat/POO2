
package io;

//import java.io.Carte;

public class Robot{

    /* Attributs communs à tous les robots et fonctions associées*/
    private String type;
    private Case position;
    private int vitesse_deplacement;
    private int capacite_reservoir;
    private int temps_remplissage;
    private int vitesse_vidage;

    public Case getPosition(){
      /* Renvoie la case sur laquelle est le robot*/
      return this.position;
    }

}
