package jeu;

public class JeuDeLaVieTerm implements Observateur{

  private int generation;
  private JeuDeLaVie jeu;

  public JeuDeLaVieTerm(JeuDeLaVie unJeu){
    jeu = unJeu;
    generation = 0;
  }

  public int getGeneration(){
    return generation;
  }
  public void actualise(){
    generation++;
    System.out.println("Numéro de la génération : "+ generation +", nombre de cellules : "+ jeu.getNbCellule() );
  }
}
