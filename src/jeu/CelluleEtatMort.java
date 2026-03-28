package jeu;

public class CelluleEtatMort implements CelluleEtat{
  public CelluleEtat vit(){
    return new CelluleEtatVivant();
  }
  public CelluleEtat meurt(){
    return this;
  }
  public boolean estVivante(){
    return false;
  }
  public void accepte(Visiteur visiteur, Cellule cellule){
    visiteur.visiteCelluleMorte(cellule);
  }
}
