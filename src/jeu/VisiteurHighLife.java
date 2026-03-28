package jeu;

public class VisiteurHighLife implements Visiteur{
  private JeuDeLaVie jeu;
  
  public VisiteurHighLife(JeuDeLaVie unJeu){
    jeu = unJeu;
  }
  
  public void visiteCelluleVivante(Cellule cellule){
    int c = cellule.nombreVoisinesVivantes(jeu);
    if(c == 2 || c == 3)
      jeu.ajouteCommande(new CommandeVit(cellule)); 
    else
      jeu.ajouteCommande(new CommandeMeurt(cellule));
  }
  public void visiteCelluleMorte(Cellule cellule){
    int c = cellule.nombreVoisinesVivantes(jeu);
    if(c == 3 || c == 6)
      jeu.ajouteCommande(new CommandeVit(cellule));
    else
      jeu.ajouteCommande(new CommandeMeurt(cellule)); 
  }

}
