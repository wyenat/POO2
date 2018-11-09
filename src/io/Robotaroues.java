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

  public int remplirReservoir(Simulateur simu, int lig, int col){

    if (testRemplir(simu, lig, col)){
      return 5000;
    }
    return 0;
  }

  public boolean testRemplir(Simulateur simu, int lig, int col){
      boolean test1 = (this.GetLigne() == lig+1)&&(this.GetColonne() == col);
      boolean test2 = (this.GetLigne() == lig) && (this.GetColonne() == col-1);
      boolean test3 = (this.GetLigne() == lig) && (this.GetColonne() == col+1);
      boolean test4 = (this.GetLigne() == lig-1) && (this.GetColonne() == col);

      return (test1 || test2 ||test3 || test4);
      }


  public long getDateVider(int intensite){

        if (intensite >= super.getReservoir()){
          return 5*50;
        }
        else {
          return 5*(intensite/100);
        }

  }

  public int vider(Simulateur simu, int ligne, int colonne, int intensite){
    if (testVider(simu, ligne, colonne)){
      if (intensite >= super.getReservoir()){
        return 50*100;
      }
      else {
        return (intensite/100)*100;
      }
    }
    return 0;
  }


  public boolean testVider(Simulateur simu, int lig, int col){
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

      boolean test = (this.GetLigne() == incendie.GetLigne()) && (this.GetColonne() == incendie.GetColonne());
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
