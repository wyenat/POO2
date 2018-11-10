package io;


public class Evenementdeplacement extends Evenement {
  private Direction direction;
  private Case Case;


  public Evenementdeplacement(Simulateur simu, Robot robot, Direction direction){
    super(robot, simu, ((long) simu.donnees.getCarte().getTailleCases()/ (long) robot.getVitesse()), TypeEvenement.deplacement);
    this.Case = simu.donnees.getCarte().getTableauDeCases()[0];
    this.direction = direction;
    Case but = simu.donnees.getCarte().getVoisin(simu.getPosition(robot), direction);
    simu.setPosition(robot, but);
    simu.addEvenement(this);
  }

  public Case getCase(){
      return this.Case;
  }

  public Direction getDirection(){
      return this.direction;
  }

  public void execute(){
    super.execute();
    Simulateur simu = super.getSimu();
    Carte map = simu.donnees.getCarte();
    Robot robot  = super.getRobot();
    int lig = robot.getLigne();
    Case C = map.getTableauDeCases()[(robot.getLigne())*(map.getNbLignes()) + robot.getColonne()];
    this.Case = C;
    if (map.voisinExiste(C, this.direction)){
        this.Case = map.getVoisin(C, this.direction);
        super.robot = robot;
        boolean possible = robot.test_deplacement(this.Case);

        if (possible){
          super.getRobot().setPosition(this.Case);
        }
    }
    else{
      throw new IllegalArgumentException("Le robot ne peut pas aller la");
    }
  }


    @Override
    public String toString(){
        return super.toString() + "Déplacement : le Robot" + (super.getRobot()).toString() + " va vers " + this.getDirection();
    }
  }
