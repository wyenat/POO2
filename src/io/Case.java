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

  public int getLigne(){
    return this.ligne;
  }

  public int getColonne(){
    return this.colonne;
  }

  public NatureTerrain getNature(){
      /**
       * Retourne la nature de la case
       */
       return this.nature;
  }

  @Override
  public String toString(){
    int lig = this.getLigne();
    int col = this.getColonne();
    return "(" + lig + "," + col + ")";
  }

  public void draw_case(GUISimulator gui, int taille_case){
      /**
       * Dessine la case en question
       */
      int x = taille_case/10 + (this.getLigne())* taille_case;
      int y =  taille_case/10 + (this.getColonne())* taille_case;
      int taille = taille_case;
      switch (this.getNature()){
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
