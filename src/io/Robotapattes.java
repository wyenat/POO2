package io;

public class Robotapattes extends Robot {

  public Robotapattes(int lig, int col, double vitesse_deplacement){
    super(lig, col, vitesse_deplacement);
    super.setReservoir(Double.POSITIVE_INFINITY); //POUDRE DONC INFINI
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

  public double remplirReservoir(Simulateur simu, int ligne, int colonne){
    return 0;
  }

  public long getDateVider(double intensite){

      return ((int)intensite/10)*1;
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

  public double vider(Simulateur simu, int ligne, int colonne, double intensite, Case C){

      if (testVider(simu, ligne, colonne, C)){
        return intensite;
        }
    return 0;
  }

  public double remplirReservoir(){
      return Double.POSITIVE_INFINITY;
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
