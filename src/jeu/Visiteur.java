package jeu;

public interface Visiteur{
  public void visiteCelluleVivante(Cellule cellule);
  public void visiteCelluleMorte(Cellule cellule);
}
