package io;

import java.util.*;

public class Chemin {
    private Case depart;
    private Case arrivee;
    private Robot robot;
    private Simulateur simu;
    private long temps;
    private LinkedList<Case> liste_cases; //correspond au chemin final
    public boolean possible;

    public Chemin(Case d, Case a, Robot r, Simulateur s){
        this.setDepart(d);
        this.setArrivee(a);
        this.setRobot(r);
        this.setSimu(s);
        this.setTemps(0);
        this.possible = false;
        this.calculer();
    }

    private int getDistanceTemp(Case courante, Simulateur simu){
        /**
         * Calcule le temps que met le robot à aller à une autre case depuis
         * la case départ
         */
         return (simu.donnees.GetCarte().GetTailleCases()/ (int) this.getRobot().GetVitesse());
    }


    private void iterer(Map<Case, Integer> distance_temporelle, Map<Case, LinkedList<Case>> chemin_jusqua_case){
        /**
         * Itère une fois
         */
         // On parcourt toutes les cases présentes dans le distance_temporelle
         Iterator<Case> cases = distance_temporelle.keySet().iterator();
         // Copie de l'itérateur pour éviter java.util.ConcurrentModificationException
         // Si meilleur solution je suis preneur
         int size = distance_temporelle.keySet().size();
         Case[] copie = new Case[size];
         for (int indice = size; indice>0; indice--){
             copie[size-indice] = cases.next();
         }
         // fin copie
         // Indice sans importance : on parcourt juste toutes les cases
         for (int indice = 0; indice<size; indice++){
             Case current = copie[indice];
             // On vérifie que les voisins existent dans la direction dir
             for (Direction dir : Direction.values()) {
                //  System.out.println("Voisin de " + current + " à " + dir + " existe : " + this.getSimu().donnees.GetCarte().voisinExiste(current, dir));
                 if (this.getSimu().donnees.GetCarte().voisinExiste(current, dir)){
                     // On vérifie que le robot peut bien se déplacer sur la case voisine
                     Case voisine = this.simu.donnees.GetCarte().GetVoisin(current, dir);
                     if (!this.getRobot().test_deplacement(voisine)){
                        //  System.out.println("Case interdite !");
                         continue;
                     }
                     // On calcule le temps qu'il faut pour aller à la case voisine
                     Integer dist = new Integer(getDistanceTemp(current, this.getSimu()));
                     // On ajoute ce temps au temps qu'il fallait pour aller à la case courante
                     dist += distance_temporelle.get(current);
                     // On regarde si la case est déjà dans le dico distance_temporelle
                     if (distance_temporelle.containsKey(voisine)){
                         if (dist < distance_temporelle.get(current)){
                             // Le temps est plus petit : un nouveau chemin est trouvé, et plus rapide
                            //  System.out.println("Temps mis à jour : " + voisine + ". Distance = " + dist +"s");
                             distance_temporelle.put(voisine, dist);
                         }
                     }
                     else{
                         // On ajoute dans le dico la case.
                        //  System.out.println("Case ajoutée : " + voisine + ". Distance = " + dist +"s");
                         distance_temporelle.put(voisine, dist);
                         LinkedList<Case> chem = chemin_jusqua_case.get(current);
                         LinkedList<Case> nouv = new LinkedList<Case>();
                         int n = chem.size();
                         for (int indice_copie=0; indice_copie < n; indice_copie++){
                             nouv.add(chem.get(indice_copie));
                         }
                         nouv.add(voisine);
                         chemin_jusqua_case.put(voisine, nouv);
                        //  System.out.println("\n \n ALLER EN CASE : "+ voisine);
                        //  afficherTrajet(chem);
                     }
                 }
             }
         }
     }

    private void calculer(){
        /**
         * Calcule le plus court chemin entre d et a
         *
         * La méthode de calcul est la suivante :
         * - On crée un dictionnaire, qui associe à chaque case sa distance
         *   temporelle à la case de départ.
         * - On la complète en parcourant la carte.
         * - On la retourne lorsque l'itération ne modifie pas le dictionnaire.
         * On stocke en parallèle le chemin pour accéder à la case dans un autre
         * dictionnaire, sous la forme d'une queue.
         */
         Case depart = this.getDepart();
         Case arrivee = this.getArrivee();
         // Création du dictionnaire contenant le temps pour aller dans toutes les autres cases depuis le départ
         Map<Case, Integer> distance_temporelle = new HashMap<Case, Integer> ();
         distance_temporelle.put(depart, 0);
         // Création du dictionnaire contenant la file des cases à parcourir pour aller à la case voulue depuis le départ
         // en temps optimal
         Map<Case, LinkedList<Case>> chemin_jusqua_case = new HashMap<Case, LinkedList<Case>> ();
         int taille_tableau = this.getSimu().donnees.GetCarte().GetNbLignes()* this.getSimu().donnees.GetCarte().GetNbColonnes();
         // Début de l'itération
         int i=0;
         LinkedList<Case> chemin_initial = new LinkedList<Case>();
         chemin_jusqua_case.put(depart, chemin_initial);
         while (this.nonFini(i++)){
             this.iterer(distance_temporelle, chemin_jusqua_case);
         }
         this.possible = chemin_jusqua_case.containsKey(arrivee);
        //  System.out.println(this.possible);
         if (this.possible){
             this.setTemps(distance_temporelle.get(arrivee));
            //  System.out.println(chemin_jusqua_case.get(arrivee));
             this.SetListeCases(chemin_jusqua_case.get(arrivee));
         }
    }

    public void deplacement(){
        /*Création des événements pour que le robot se déplace*/
        Case prece = this.getSimu().getPosition(this.getRobot());//this.getSimu().donnees.GetCarte().GetTableauDeCases()[this.getRobot().GetLigne()* this.getSimu().donnees.GetCarte().GetNbColonnes()+this.getRobot().GetColonne()];
        for (int i = 0; i<this.getSimu().donnees.GetCarte().GetNbLignes(); i++){
            for (int j=0; j<this.getSimu().donnees.GetCarte().GetNbColonnes(); j++){
                if (prece.GetLigne() == i && prece.GetColonne() == j){
                    prece = this.getSimu().donnees.GetCarte().GetTableauDeCases()[i* this.getSimu().donnees.GetCarte().GetNbColonnes()+j];
                }
            }
        }
        System.out.println("deplacement de " + this.getRobot() + "avec " + this.GetListeCases() + "depuis " + prece);

        LinkedList<Case> parcourt = this.GetListeCases();
        int n = parcourt.size();
        for (int indice=0; indice < n; indice++){

            Direction dir = trouverDirection(this.getSimu(), prece, parcourt.get(indice));
            // System.out.println("on va vers " + parcourt.get(indice) + "donc" + dir);
            prece = parcourt.get(indice);
            Evenementdeplacement deplacement = new Evenementdeplacement(this.getSimu(), this.getRobot(), dir);
            // System.out.println(dir);
        }
        // Direction dir = trouverDirection(this.getSimu(), prece, this.arrivee);
        // System.out.println("on va vers " + this.arrivee + "donc" + dir);
        //
        // Evenementdeplacement deplacement = new Evenementdeplacement(this.getSimu(), this.getRobot(), dir);
    }

    public void afficherTrajet(LinkedList<Case> trajet){
        int n = trajet.size();
        for (int indice=0; indice < n; indice++){
            System.out.println(trajet.get(indice));
        }
    }

    private Direction trouverDirection(Simulateur simu, Case prece, Case endroit){
        int ligne_robot = prece.GetLigne();
        int col_robot = prece.GetColonne();
        Direction dir = Direction.SUD;
        int diff_ligne  = ligne_robot - endroit.GetLigne();
        int diff_col = col_robot - endroit.GetColonne();
        // System.out.println("ligne " + diff_ligne + " col " + diff_col);
        if (diff_ligne == 1){dir = Direction.NORD;}
        if (diff_col == 1){dir = Direction.OUEST;}
        if (diff_col == -1){dir = Direction.EST;}
        // System.out.println("diff_col = " + diff_col + " diff_ligne = " + diff_ligne);
        return dir;
        // if ((ligne_robot - endroit.GetLigne() != 0 & col_robot - endroit.GetColonne() == 0 ) | (ligne_robot - endroit.GetLigne() ==0 & col_robot - endroit.GetColonne() !=0)){
        //     switch(ligne_robot - endroit.GetLigne()){
        //         case 1:
        //              dir = Direction.NORD;
        //              break;
        //         case -1:
        //              dir = Direction.SUD;
        //              break;
        //         case 0:
        //              switch(col_robot - endroit.GetColonne()){
        //                  case 1:
        //                      dir = Direction.OUEST;
        //                      break;
        //                  case -1:
        //                      dir = Direction.EST;
        //                      break;
        //                  case 0:
        //                     throw new IllegalArgumentException("Déplacement sur même case");
        //                  default:
        //                     throw new IllegalArgumentException("Colonne : Il faut donner une case voisine !" + col_robot);
        //              }
        //              break;
        //         default:
        //           throw new IllegalArgumentException("Ligne : Il faut donner une case voisine !" + ligne_robot);
        //         }
        // // }
        // // else{
        // //     // throw new IllegalArgumentException("Diagonale !");
        // //     System.out.println("diago :)");
        // // }
        // return dir;
    }

    private boolean nonFini(int i){
        /**
         * Vérifie si on a encore besoin d'itérer
         * TODO, là c'est un truc merdique pour boucler trkl
         * N'itère plus quand le tableau chemin_jusqua_case est stable par itération
         */
         return (i!=64);
    }

    // Set et get...
    public void setDepart(Case d){
    this.depart = d;
    }

    public Case getDepart(){
    return this.depart;
    }

    public void setArrivee(Case a){
    this.arrivee = a;
    }

    public Case getArrivee(){
    return this.arrivee;
    }

    public void setRobot(Robot r){
    this.robot = r;
    }

    public Robot getRobot(){
    return this.robot;
    }

    public void setSimu(Simulateur simu){
    this.simu = simu;
    }

    public Simulateur getSimu(){
    return this.simu;
    }

    public LinkedList<Case> GetListeCases(){
        return this.liste_cases;
    }

    public void SetListeCases(LinkedList<Case> liste){
        this.liste_cases = liste;
    }

    public long getTemps(){
        return this.temps;
    }

    public void setTemps(long time){
        this.temps = time;
    }
    // Fin set et get...

    public String toString(){
        return "[" + this.liste_cases + "]";
    }
}
