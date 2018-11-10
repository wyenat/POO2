
package io;



public class EvenementDeverserEau extends Evenement {


  public EvenementDeverserEau(Simulateur simu, Robot robot){
    super(robot, simu, robot.getDatevider(simu), TypeEvenement.DeverserEau);
    int ligne = super.getSimu().getPosition(robot).GetLigne();
    int colonne = super.getSimu().getPosition(robot).GetColonne();
    Incendie[] incendies = super.getSimu().donnees.GetIncendies();
    Incendie incendie = incendies[0];
    for (int i=0; i<incendies.length; i++){
      if (incendies[i].GetLigne()==ligne && incendies[i].GetColonne()==colonne){
        incendie = incendies[i];
      }
    }
    double intensite = incendie.GetIntensite();
    double reservoir = super.getSimu().getReservoir(robot);
    if (intensite == 0){
      throw new IllegalArgumentException("On ne peut pas faire d'intervention ici");
    }

    double volume = robot.Vider(super.getSimu(), ligne, colonne, intensite);
    if (volume == 0){
      return;
    }
    // System.out.println(" on est la " + (reservoir) + " vol " + volume);
    super.getSimu().setReservoir(robot, reservoir - volume);
    simu.addEvenement(this);

  }

  public void execute(){
      super.execute();
      Robot robot = super.getRobot();
      int ligne = robot.GetLigne();
      int colonne = robot.GetColonne();
      Incendie[] incendies = super.getSimu().donnees.GetIncendies();
      Incendie incendie = incendies[0];
      for (int i=0; i<incendies.length; i++){
        if (incendies[i].GetLigne()==ligne && incendies[i].GetColonne()==colonne){
          incendie = incendies[i];
        }
      }
      double intensite = incendie.GetIntensite();
      double reservoir = super.getSimu().getReservoir(robot);
    //   System.out.println(" ON VA  " + reservoir + " AVEC UNE INTENSITE DE " + intensite);
      if (intensite == 0){
        throw new IllegalArgumentException("On ne peut pas faire d'intervention ici");
      }

      double volume = robot.Vider(super.getSimu(), ligne, colonne, intensite);
      if (volume == 0){
        return;
      }
      incendie.setIntensite(intensite - volume);
      super.robot.setReservoir(reservoir - volume);

      }

      public String toString(){
          return super.toString() + "DeverserEau: " + this.robot + "déverse en (" + this.getRobot().GetLigne() + ", " + this.getRobot().GetColonne() + ")";
      }


  }
