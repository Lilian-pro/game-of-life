package jeu;

public class Cellule{
  private int x, y;
  private CelluleEtat etat;

  public Cellule(int unX, int unY, CelluleEtat unEtat){
    etat = unEtat;
    x = unX;
    y = unY;
  }

  public void vit(){
    etat = etat.vit();
  }

  public void meurt(){
    etat = etat.meurt();
  }

  public boolean estVivante(){
    return etat.estVivante();
  }

  public int nombreVoisinesVivantes(JeuDeLaVie unjeu){
    int voisines = 0;

    if( ((x < unjeu.xMax) && (x >= 0)) && ((y+1 < unjeu.yMax) && (y+1 >= 0)) )
      if(unjeu.getGrilleXY(x,y+1).estVivante()) voisines+=1;
   
    if( ((x+1 < unjeu.xMax) && (x+1 >= 0)) && ((y+1 < unjeu.yMax) && (y+1 >= 0)) )
      if(unjeu.getGrilleXY(x+1,y+1).estVivante()) voisines+=1;
    
    if( ((x+1 < unjeu.xMax) && (x+1 >= 0)) && ((y < unjeu.yMax) && (y >= 0)) )
      if(unjeu.getGrilleXY(x+1,y).estVivante()) voisines+=1;
    
    if( ((x+1 < unjeu.xMax) && (x+1 >= 0)) && ((y-1 < unjeu.yMax) && (y-1 >= 0)) )
      if(unjeu.getGrilleXY(x+1,y-1).estVivante()) voisines+=1;
    
    if( ((x < unjeu.xMax) && (x >= 0)) && ((y-1 < unjeu.yMax) && (y-1 >= 0)) )
      if(unjeu.getGrilleXY(x,y-1).estVivante()) voisines+=1;
    
    if( ((x-1 < unjeu.xMax) && (x-1 >= 0)) && ((y-1 < unjeu.yMax) && (y-1 >= 0)) )
      if(unjeu.getGrilleXY(x-1,y-1).estVivante()) voisines+=1;
    
    if( ((x-1 < unjeu.xMax) && (x-1 >= 0)) && ((y < unjeu.yMax) && (y >= 0)) )
      if(unjeu.getGrilleXY(x-1,y).estVivante()) voisines+=1;
    
    if( ((x-1 < unjeu.xMax) && (x-1 >= 0)) && ((y+1 < unjeu.yMax) && (y+1 >= 0)) )
      if(unjeu.getGrilleXY(x-1,y+1).estVivante()) voisines+=1;

    return voisines;
  }

  public void accepte(Visiteur visiteur){
    etat.accepte(visiteur, this);
  }

}
