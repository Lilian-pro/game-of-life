package jeu;

public class VisiteurClassique implements Visiteur{
  private JeuDeLaVie jeu;
  
  public VisiteurClassique(JeuDeLaVie unJeu){
    jeu = unJeu;
  }
  
  public void visiteCelluleVivante(Cellule cellule){
    if(cellule.nombreVoisinesVivantes(jeu) < 2 || cellule.nombreVoisinesVivantes(jeu) > 3)
      jeu.ajouteCommande(new CommandeMeurt(cellule)); 
    else
      jeu.ajouteCommande(new CommandeVit(cellule));
  }
  public void visiteCelluleMorte(Cellule cellule){
    if(cellule.nombreVoisinesVivantes(jeu) == 3)
      jeu.ajouteCommande(new CommandeVit(cellule));
    else
      jeu.ajouteCommande(new CommandeMeurt(cellule)); 
  }

}
