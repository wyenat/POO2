package io;

public enum TypeRobot {
  DRONE ("DRONE"),
  ROUES ("ROUES"),
  CHENILLES ("CHENILLES"),
  PATTES ("PATTES");

  private String name = "";

  /**
  Constructeur
  */
  TypeRobot(String name){
    this.name = name;
  }

  public String toString(){
    return name;
  }
}
