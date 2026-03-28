package jeu;

public class CommandeMeurt implements Commande{
  private Cellule cellule;

  public CommandeMeurt(Cellule c){
    cellule = c;
  }
  public void executer(){
    cellule.meurt();
  }
}
