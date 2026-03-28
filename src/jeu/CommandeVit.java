package jeu;

public class CommandeVit implements Commande{
  private Cellule cellule;
  
  public CommandeVit(Cellule c){
    cellule = c;
  }
  public void executer(){
    cellule.vit();
  }
}
