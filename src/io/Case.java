package io;
import gui.*;
import java.awt.*;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import gui.GUISimulator;
import gui.Rectangle;
import gui.Simulable;
import gui.Text;


public class Case {
  private int ligne;
  private int colonne;
  private NatureTerrain nature;

  public Case(int l, int c, NatureTerrain n){
    /**
    Constructeur de la case
    */
    this.ligne = l;
    this.colonne = c;
    this.nature = n;
  }

  public int GetLigne(){
    return this.ligne;
  }

  public int GetColonne(){
    return this.colonne;
  }

  public NatureTerrain GetNature(){
      /**
       * Retourne la nature de la case
       */
       return this.nature;
  }

  @Override
  public String toString(){
    int lig = this.GetLigne();
    int col = this.GetColonne();

    return "(" + lig + "," + col + ")";
  }

  public void draw_case(GUISimulator gui, int taille_case){
      // Color bords = Color.decode("#000000");
      //
      // String color_fond = "#ffffff";
      // switch (this.GetNature()){
      //     case EAU:
      //         color_fond = "#a0edff"; break;
      //     case FORET:
      //         color_fond = "#87ff70"; break;
      //     case ROCHE:
      //         color_fond = "#808080"; break;
      //     case HABITAT:
      //         color_fond = 	"#ff9ec6"; break;
      // }
      // Color fond = Color.decode(color_fond);
      //
      // gui.addGraphicalElement(
      //   new Rectangle(
      //       taille_case/2 + this.colonne*taille_case, //abcisse du milieu de la case
      //       taille_case/2 + this.ligne*taille_case, //ordonnee du milieu de la case
      //       bords, //couleur des contours
      //       fond, //Couleur du fond
      //       taille_case, //largeur
      //       taille_case //longueur
      //   )
      // );
      int x = taille_case/10 + (this.GetLigne())* taille_case;
      int y =  taille_case/10 + (this.GetColonne())* taille_case;
      int taille = taille_case;
      switch (this.GetNature()){
          case EAU:
            gui.addGraphicalElement(new ImageElement(y, x, "img/EAU.jpg", taille, taille, new Canvas()));
            break;

          case FORET:
            gui.addGraphicalElement(new ImageElement(y, x, "img/FORET.png", taille, taille, new Canvas()));
            break;

          case ROCHE:
            gui.addGraphicalElement(new ImageElement(y, x, "img/ROCHE.jpg", taille, taille, new Canvas()));
            break;

          case HABITAT:
            gui.addGraphicalElement(new ImageElement(y, x, "img/HABITAT.jpg", taille, taille, new Canvas()));
            break;

          case TERRAIN_LIBRE:
            gui.addGraphicalElement(new ImageElement(y, x, "img/TERRAIN_LIBRE.jpg", taille, taille, new Canvas()));
            break;
      }

  }
}
