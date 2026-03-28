package jeu;


import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class JeuDeLaVie implements Observable{
  private Cellule[][] grille;
  protected int xMax;
  protected int yMax;
  private List<Observateur> observateurs;
  private List<Commande> commandes;
  private Visiteur visiteur;

  public JeuDeLaVie(int celluleHauteur, int celluleLargeur){
    xMax = celluleHauteur;
    yMax = celluleLargeur;
    grille = new Cellule[xMax][yMax];
    initialiseGrille();

    observateurs = new ArrayList<Observateur>();
  
    commandes = new ArrayList<Commande>();

    visiteur = new VisiteurClassique(this);
  }

  public void initialiseGrille(){
    for(int i = 0 ; i < xMax ; i++){
      for(int j = 0 ; j < yMax ; j++){
        if(Math.random() < 0.5)
          grille[i][j] = new Cellule(i,j, new CelluleEtatMort());
        else
          grille[i][j] = new Cellule(i,j, new CelluleEtatVivant());
      }
    }
  }

  public void blankGrille(){
    for(int i = 0 ; i < xMax ; i++){
      for(int j = 0 ; j < yMax ; j++){
          grille[i][j] = new Cellule(i,j, new CelluleEtatMort());
      }
    }
  }

  public void setGrilleXY(int x, int y){
    grille[x][y] = new Cellule(x,y, new CelluleEtatVivant());
    return;
  }

  public Cellule getGrilleXY(int x, int y){
    return grille[x][y];
  }

  public int getXMax(){
    return xMax;
  }
  public int getYMax(){
    return yMax;
  }

  public int getNbCellule(){
    int nbCellule = 0;
    for(int i = 0 ; i < xMax ; i++){
      for(int j = 0 ; j < yMax ; j++){
        if (grille[i][j].estVivante()) nbCellule++;
      }
    }
    return nbCellule; 
  }

  public void attacheObservateur(Observateur o){
    observateurs.add(o);
  }
  public void detacheObservateur(Observateur o){
    observateurs.remove(o);
  }
  public void notifieObservateur(){
    observateurs.stream().forEach(o -> o.actualise());
  }

  public void ajouteCommande(Commande c){
    commandes.add(c);
  }
  public void executeCommandes(){
    commandes.stream().forEach(c -> c.executer());
    commandes.clear();
  }

  public void setVisiteur(Visiteur v){
    visiteur = v;
  }

  public void distribueVisiteur(){
    for(int i = 0 ; i < xMax ; i++){
      for(int j = 0 ; j < yMax ; j++){
        grille[i][j].accepte(visiteur);
      }
    }
  }
  public void calculerGenerationSuivante(){
    distribueVisiteur();
    executeCommandes();
    notifieObservateur();
  }

}
