
package io;



public class EvenementDeverserEau extends Evenement {


  public EvenementDeverserEau(Simulateur simu, Robot robot){
    super(robot, simu, robot.getDatevider(simu), TypeEvenement.DeverserEau);
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
      int intensite = incendie.GetIntensite();
      int reservoir = super.getSimu().getReservoir(robot);
      if (intensite == 0){
        throw new IllegalArgumentException("On ne peut pas faire d'intervention ici");
      }

      int volume = robot.Vider(super.getSimu(), ligne, colonne, intensite);
      if (volume == 0){
        return;
      }
      System.out.println(" ON VA DEVERSER " + volume + " AVEC UNE INTENSITE DE " + (intensite-volume) + " avec " + super.getRobot());
      incendie.setIntensite(intensite - volume);
      super.robot.setReservoir(reservoir - volume);

      }

      public String toString(){
          return super.toString() + ": DeverserEau: " + this.robot + "déverse en (" + this.getRobot().GetLigne() + ", " + this.getRobot().GetColonne() + ")";
      }


  }
