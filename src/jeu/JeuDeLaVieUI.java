package jeu;

import javax.swing.*;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Dimension;

public class JeuDeLaVieUI extends JPanel implements Observateur{
  private JeuDeLaVie jeu;
  private double scale = 1.0;
  private int densite = 1; 

  public JeuDeLaVieUI(JeuDeLaVie unJeu, int uneDensite){
    jeu = unJeu;
    densite = uneDensite;
  }

  public double getScale(){
    return scale;
  }

  public void setScale(double uneScale){
    scale = uneScale;

    int newWidth = (int)(jeu.xMax * scale);
    int newHeight = (int)(jeu.yMax * scale);
    setPreferredSize(new Dimension(newWidth, newHeight));
    revalidate();
    repaint();

  }

  public void actualise(){
    repaint();
  }

  public int getLargeur(){
    return (int)((getWidth()/scale) / jeu.xMax);
  }

  public int getHauteur(){
    return (int)((getHeight()/scale) / jeu.yMax);
  }

  @Override
  public void paintComponent(Graphics g){
    super.paintComponent(g);
   
    Graphics2D g2d = (Graphics2D) g;
    g2d.scale(scale, scale);

    int cellLargeur = 1;
    int cellHauteur = 1;

    for(int x = 0 ; x < jeu.xMax ; x++){
      for(int y = 0 ; y < jeu.yMax ; y++){
        int px = x * cellLargeur;
        int py = y * cellHauteur;

        if(jeu.getGrilleXY(x,y).estVivante())  
          g2d.fillRect(px, py, cellLargeur, cellHauteur);
      }
    }
  }

  

}
