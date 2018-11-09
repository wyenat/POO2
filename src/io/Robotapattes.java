package io;

public class Robotapattes extends Robot {

  public Robotapattes(int lig, int col, double vitesse_deplacement){
    super(lig, col, vitesse_deplacement);
    super.setReservoir(1000000); //POUDRE DONC INFINI
    //Degueu mais je sais pas faire autrement
    TypeRobot T;
    String mmT = "PATTES";
    T = TypeRobot.valueOf(mmT);
    super.SetTypeRobot(T);
    //


  }

  public double GetVitesse(NatureTerrain Nature){
    double vitesse = super.GetVitesse();
    switch (Nature){
      case ROCHE:
      vitesse-=10;
      break;

      case EAU:
      vitesse = 0;
      break;

      default:
      break;
    }
    return vitesse;
  }


  public long getDateremplir(){
    return 0;
  }

  public int remplirReservoir(Simulateur simu, int ligne, int colonne){
    return 0;
  }

  public long getDateVider(int intensite){

      return (intensite/10)*1;
  }



  public boolean testVider(Simulateur simu, int lig, int col){
    return true;

    }

  public int vider(Simulateur simu, int ligne, int colonne, int intensite){

      if (testVider(simu, ligne, colonne)){
        return intensite;
        }
    return 0;
  }

  public int remplirReservoir(){
      return -1;
  }


  public boolean test_deplacement(Case C){
    boolean possible = true;
    switch (C.GetNature()){
      case EAU:
        possible = false;
        break;
      default:
          break;
    }
    return possible;
  }

}
