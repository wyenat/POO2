package io;


public class Robotaroues extends Robot {

  public Robotaroues(int lig, int col, double vitesse_deplacement){
    super(lig, col, vitesse_deplacement);
    super.setReservoir(5000);
    //Degueu mais je sais pas faire autrement
    TypeRobot T;
    String mmT = "ROUES";
    T = TypeRobot.valueOf(mmT);
    super.SetTypeRobot(T);
    //

  }


  public double GetVitesse(NatureTerrain Nature){
    double vitesse = super.GetVitesse();
    switch (Nature){
        case TERRAIN_LIBRE:
          break;

        case HABITAT:
          break;

        default:
          vitesse = 0;
          break;
    }
    return vitesse;

  }

  public long getDateremplir(){
    return 10*60;
  }

  public double remplirReservoir(Simulateur simu, int lig, int col){

    if (testRemplir(simu, lig, col)){
      return 5000;
    }
    return 0;
  }

  public boolean testRemplir(Simulateur simu, int lig, int col){
      boolean test1 = (simu.getPosition(this).GetLigne() == lig+1)&&(simu.getPosition(this).GetColonne() == col);
      boolean test2 = (simu.getPosition(this).GetLigne() == lig) && (simu.getPosition(this).GetColonne() == col-1);
      boolean test3 = (simu.getPosition(this).GetLigne() == lig) && (simu.getPosition(this).GetColonne() == col+1);
      boolean test4 = (simu.getPosition(this).GetLigne() == lig-1) && (simu.getPosition(this).GetColonne() == col);
      boolean test5 = (simu.getPosition(this).GetLigne() == lig) && (simu.getPosition(this).GetColonne() == col);

      return (test1 || test2 ||test3 || test4 ||test5);

      }


  public long getDateVider(double intensite){

        if (intensite >= super.getReservoir()){
          return 5*50;
        }
        else {
          return 5*((int)intensite/100);
        }

  }

  public double vider(Simulateur simu, int ligne, int colonne, double intensite, Case C){
    if (testVider(simu, ligne, colonne, C)){
      if (intensite >= super.getReservoir()){
        return (double) 50*100;
      }
      else {
        return intensite;
      }
    }
    return 0;
  }


  public boolean testVider(Simulateur simu, int lig, int col, Case C){
    Incendie[] incendies = simu.donnees.GetIncendies();
    boolean incendie_ici = false;
    Incendie incendie = incendies[0];

    for (int i=0; i<incendies.length; i++){
      if (incendies[i].GetLigne()==lig && incendies[i].GetColonne()==col){
        incendie_ici = true;
        incendie = incendies[i];

      }
    }
    if (incendie_ici){

      boolean test = (C.GetLigne() == incendie.GetLigne()) && (C.GetColonne() == incendie.GetColonne());
      return test;
    }
    return false;
  }

  public boolean test_deplacement(Case C){
    boolean possible = false;
    switch (C.GetNature()){
      case TERRAIN_LIBRE:
          possible = true;
          return possible;
      case HABITAT:
          possible = true;
          return possible;
      default:
          break;
    }
    return possible;
  }


}
