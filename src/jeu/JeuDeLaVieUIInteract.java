package jeu;


import javax.swing.*;
import java.awt.Graphics;

public class JeuDeLaVieUIInteract extends JPanel implements Observateur{
  private JeuDeLaVie jeu;
  private int x;
  private int y;

  public JeuDeLaVieUIInteract(JeuDeLaVie unJeu){
    jeu = unJeu;
  }

  public void actualise(){
    repaint();
  }  
  
  public void setXY(int unX, int unY){
    x = unX;
    y = unY;
  }

  @Override
  public void paintComponent(Graphics g){
    super.paintComponent(g);
    
    int cellLargeur = getWidth() / jeu.xMax;
    int cellHauteur = getHeight() / jeu.yMax;

    int px = x * cellLargeur;
    int py = y * cellHauteur;

    if(jeu.getGrilleXY(x,y).estVivante())  
      g.fillRect(px, py, cellLargeur, cellHauteur);
    
  }

}


