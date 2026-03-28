package structures;

import jeu.*;

import java.io.File;              
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Structures implements Observateur{
  
  private JeuDeLaVie jeu;

  public Structures(JeuDeLaVie unJeu, String unFichier){
    jeu = unJeu;
    jeu.blankGrille();
    monReadFile(unFichier);
    
  }
  
  public void monReadFile(String maStructures){
    File monFichier = new File(maStructures);

    try (Scanner myReader = new Scanner(monFichier)) {
      int x = 50;
      int y = 50;

      while (myReader.hasNextLine()) {
        
        String data = myReader.nextLine();
        for (int i = 0; i < data.length(); i++) {
          char c = data.charAt(i);
          if(c == '1')
            jeu.setGrilleXY(x,y+i);

        }
        x+=1;
      }
    } catch (FileNotFoundException e) {
      System.out.println("Erreur analyse fichier !");
      e.printStackTrace();
    }
  
  }

  public void actualise(){
  }
}
