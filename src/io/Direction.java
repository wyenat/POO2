package io;

public enum Direction {
    NORD ("NORD"),
    EST ("EST"),
    SUD ("SUD"),
    OUEST ("OUEST");

    private String name = "";

    Direction(String nom){
        this.name = nom;
    }

    public String ToString(){
        return name;
    }
}
