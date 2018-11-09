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
    public boolean continuer_a_iterer;

    public Chemin(Case d, Case a, Robot r, Simulateur s){
        this.setDepart(d);
        this.setArrivee(a);
        this.setRobot(r);
        this.setSimu(s);
        this.setTemps(0);
        this.possible = false;
        this.continuer_a_iterer = true;
        this.calculer();
        afficherTrajet(this.liste_cases);
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
         this.continuer_a_iterer = false;
         Iterator<Case> cases = distance_temporelle.keySet().iterator();
         int size = distance_temporelle.keySet().size();
         Case[] copie = new Case[size];
         for (int indice = size; indice>0; indice--){
             copie[size-indice] = cases.next();
         }
         for (int indice = 0; indice<size; indice++){
             Case current = copie[indice];
             for (Direction dir : Direction.values()) {
                 if (this.getSimu().donnees.GetCarte().voisinExiste(current, dir)){
                     Case voisine = this.simu.donnees.GetCarte().GetVoisin(current, dir);
                     if (!this.getRobot().test_deplacement(voisine)){
                         continue;
                     }
                     Integer dist = new Integer(getDistanceTemp(current, this.getSimu()));
                     dist += distance_temporelle.get(current);
                     if (distance_temporelle.containsKey(this.getArrivee())){
                         if (distance_temporelle.get(this.getArrivee()) < dist){
                             //Le chemin pour aller jusqu'à cette case est déjà plus long que
                             // celui pour aller à l'arrivée, on ne poursuit pas.
                             continue;
                         }
                     }
                     if (distance_temporelle.containsKey(voisine)){
                         if (dist < distance_temporelle.get(current)){
                             // Le temps est plus petit : un nouveau chemin est trouvé, et plus rapide
                             distance_temporelle.put(voisine, dist);
                             LinkedList<Case> chem = chemin_jusqua_case.get(current);
                             LinkedList<Case> nouv = new LinkedList<Case>();
                             int n = chem.size();
                             for (int indice_copie=0; indice_copie < n; indice_copie++){
                                 nouv.add(chem.get(indice_copie));
                             }
                             nouv.add(voisine);
                             chemin_jusqua_case.put(voisine, nouv);
                             this.continuer_a_iterer = true;
                         }
                     }
                     else{
                         // On ajoute dans le dico la case.
                         distance_temporelle.put(voisine, dist);
                         LinkedList<Case> chem = chemin_jusqua_case.get(current);
                         LinkedList<Case> nouv = new LinkedList<Case>();
                         int n = chem.size();
                         for (int indice_copie=0; indice_copie < n; indice_copie++){
                             nouv.add(chem.get(indice_copie));
                         }
                         nouv.add(voisine);
                         chemin_jusqua_case.put(voisine, nouv);
                         this.continuer_a_iterer = true;
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
         this.continuer_a_iterer = true;
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
         while (this.continuer_a_iterer){
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
        // System.out.println("deplacement de " + this.getRobot() + "avec " + this.GetListeCases() + "depuis " + prece);

        LinkedList<Case> parcourt = this.GetListeCases();
        int n = parcourt.size();
        for (int indice=0; indice < n; indice++){

            Direction dir = trouverDirection(this.getSimu(), prece, parcourt.get(indice));
            // System.out.println("on va vers " + parcourt.get(indice) + "donc" + dir);
            prece = parcourt.get(indice);
            Evenementdeplacement deplacement = new Evenementdeplacement(this.getSimu(), this.getRobot(), dir);
            // System.out.println(dir);
        }
    }

    public void afficherTrajet(LinkedList<Case> trajet){
        int n = trajet.size();
        for (int indice=0; indice < n; indice++){
            // System.out.println(trajet.get(indice));
        }
    }

    private Direction trouverDirection(Simulateur simu, Case prece, Case endroit){
        int ligne_robot = prece.GetLigne();
        int col_robot = prece.GetColonne();
        Direction dir = Direction.SUD;
        int diff_ligne  = ligne_robot - endroit.GetLigne();
        int diff_col = col_robot - endroit.GetColonne();
        if (diff_ligne == 1){dir = Direction.NORD;}
        if (diff_col == 1){dir = Direction.OUEST;}
        if (diff_col == -1){dir = Direction.EST;}
        return dir;
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
