package io;
import java.io.*;
import java.util.*;

public abstract class Evenement{
    /**
     * Cette classe gère les évènements qui se déroulent au cours de la simu
     */
  protected Robot robot;
  private long dateFin;
  private Simulateur simu;
  private TypeEvenement typeEvenement;


public Evenement(Robot robot, Simulateur simulation, long dateFin, TypeEvenement type){
    /**
     * Constructeur de la classe Evenement 
     */
    this.simu = simulation;
    this.robot = robot;
    this.setDate(dateFin);
    this.robot.setEtat(Etat.OCCUPE);
    this.typeEvenement = type;
  }
  

  public void execute(){
      /**
       * Execute l'évènement.
       */
       long dateMax = 0;
       Iterator<Evenement> iter = simu.evenements.iterator();
       while (iter.hasNext()){
           Evenement eve = iter.next();
           if ( this.getRobot() == eve.getRobot()){
               if ( dateMax < eve.getDate()){
                 dateMax = eve.getDate();
               }
           }
       }
       if (dateMax <= this.simu.time + this.simu.pas){
          this.getRobot().setEtat(Etat.LIBRE);
       }
  }
  
  
  @Override
  public String toString(){
      return "Evenement : date de fin = " + this.getDate() + ", de type : ";
  }
  
  // sets & gets 
  
  public void setDate(long Date){
      /**
      * Calcule la date d'execution de l'évènement 
      */
      long dateMax = 0;
      Iterator<Evenement> iter = simu.evenements.iterator();
      while (iter.hasNext()){
          Evenement eve = iter.next();
          if ( this.getRobot() == eve.getRobot()){
              if ( dateMax < eve.getDate()){
                  dateMax = eve.getDate();
              }
          }
      }
      this.dateFin = Date + dateMax;
  }

  public Simulateur getSimu(){
      return this.simu;
  }
  
  public void setSimu(Simulateur simulateur){
      this.simu = simulateur;
  }
  
  
  public Robot getRobot(){
      return this.robot;
  }

  public long getDate(){
    return this.dateFin;
  }

  public TypeEvenement getTypeEvenement(){
      return this.typeEvenement;
  }

  public void setTypeEvenement(TypeEvenement type){
      this.typeEvenement = type;
  }

}
