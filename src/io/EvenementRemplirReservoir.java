package io;


public class EvenementRemplirReservoir extends Evenement {


  public EvenementRemplirReservoir(Simulateur simu, Robot robot){
    super(robot, simu, robot.getDateremplir(), TypeEvenement.remplirReservoir);
    double volume = robot.remplirReservoir(super.getSimu(), super.getSimu().getPosition(robot).GetLigne(), super.getSimu().getPosition(robot).GetColonne());
    super.getSimu().setReservoir(robot, volume);
    simu.addEvenement(this);

  }

  public void execute(){
    super.execute();
    int ligne = super.getRobot().GetLigne();
    int colonne = super.getRobot().GetColonne();
    double volume = super.getRobot().remplirReservoir(super.getSimu(), ligne, colonne);
    if (volume > 0){
        this.robot.setReservoir(volume);
    }
  }

  public String toString(){
      return super.toString() + ": remplirReservoir: fais le plein en (" + this.getRobot().GetLigne() + ", " + this.getRobot().GetColonne() + ")";
  }
}
