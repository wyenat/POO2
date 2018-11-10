package io;

public enum TypeRobot {
  DRONE ("DRONE"),
  ROUES ("ROUES"),
  CHENILLES ("CHENILLES"),
  PATTES ("PATTES");

  private String name = "";

  TypeRobot(String name){
    /**
    Constructeur
    */
    this.name = name;
  }

  public String toString(){
    return name;
  }
}
