package io;

public enum NatureTerrain {
  EAU ("EAU"),
  FORET ("FORET"),
  ROCHE ("ROCHE"),
  TERRAIN_LIBRE ("TERRAIN_LIBRE"),
  HABITAT ("HABITAT");

  private String name = "";

  /**
  Constructeur
  */
  NatureTerrain(String name){
    this.name = name;
  }

  public String toString(){
    return name;
  }
}
