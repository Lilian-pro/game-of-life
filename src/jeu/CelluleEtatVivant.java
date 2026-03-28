package jeu;

public class CelluleEtatVivant implements CelluleEtat{
  public CelluleEtat vit(){
    return this;
  }
  public CelluleEtat meurt(){
    return new CelluleEtatMort();
  }
  public boolean estVivante(){
    return true;
  }
  public void accepte(Visiteur visiteur, Cellule cellule){
    visiteur.visiteCelluleVivante(cellule);
  }
}
