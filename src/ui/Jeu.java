package ui;
import structures.*;
import jeu.*;
import java.lang.Thread;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.lang.Math;

public class Jeu extends JPanel implements ActionListener{

  private static int pausePlay = 1;
  private static final Object lock = new Object();
  private static int sleepValue = 100;
  private static int oldGeneration = 0;
  private static String fichier;

  public void actionPerformed(ActionEvent e){
    String composant = e.getActionCommand();
  }

  public Jeu(int x, int y, int densite, String unFichier){
    
    fichier = unFichier;

    JeuDeLaVie jeu = new JeuDeLaVie(x, y);
    JeuDeLaVieUI ui = new JeuDeLaVieUI(jeu, densite); 
    JeuDeLaVieTerm term = new JeuDeLaVieTerm(jeu);
    JeuDeLaVieUIInteract interact = new JeuDeLaVieUIInteract(jeu);
    
    jeu.attacheObservateur(ui); 
    jeu.attacheObservateur(term);
    jeu.attacheObservateur(interact);
    
    if(fichier != ""){
      Structures s = new Structures(jeu, unFichier);
      jeu.attacheObservateur(s);
    }

    //fenêtre
    this.setLayout(new BorderLayout());

    //Panel parent
    JPanel uiPanel = new JPanel(new BorderLayout());
    uiPanel.setPreferredSize(new Dimension(800,600));

    //Label génération et nb cellule
    JLabel viewGeneration = new JLabel("Jeu de la Vie");
    viewGeneration.setFont(new Font("Arial", Font.PLAIN, 16));    
    //Label bouton et contrôle du jeu
    JPanel controlPanel = new JPanel();
    controlPanel.setLayout(new BoxLayout(controlPanel, BoxLayout.X_AXIS));

    ButtonDesign playBouton = new ButtonDesign("Play");
    playBouton.addActionListener(e -> {
      synchronized(lock){
        pausePlay = 0;
        lock.notifyAll();
      }
    });
    ButtonDesign pauseBouton = new ButtonDesign("Pause");
    pauseBouton.addActionListener(e -> pausePlay = 1);
    ButtonDesign uneGeneBouton = new ButtonDesign("Avancer");
    uneGeneBouton.addActionListener(e -> {
      jeu.calculerGenerationSuivante();
    });
    
    JScrollPane scrollPane = new JScrollPane(ui, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    ui.addMouseListener(new MouseAdapter(){
      public void mousePressed(MouseEvent me){
        System.out.println(me);
        Point p = me.getPoint();
      
        double scale = ui.getScale();

        int x = (int)(p.x / scale);
        int y = (int)(p.y / scale);

        if(x<0 || y<0 || x >= jeu.getXMax() || y >= jeu.getYMax()){
          return;
        }

        Cellule posGrille = jeu.getGrilleXY(x, y);
        
        if(!posGrille.estVivante())
          posGrille.vit();
        else
          posGrille.meurt();

        ui.repaint();
          
      }  
    });
    JSlider slideVitesse = new JSlider(0, 200);
    
    JSlider slideZoom = new JSlider(400, 800, 400);
    
    slideZoom.addChangeListener(e -> {
      double scale = slideZoom.getValue() / 100.0;
      ui.setScale(scale);
    });

    ButtonDesign modeNormal = new ButtonDesign("Normal");
    modeNormal.addActionListener(e -> jeu.setVisiteur(new VisiteurClassique(jeu)));
    ButtonDesign modeDayNight = new ButtonDesign("DayNight");
    modeDayNight.addActionListener(e -> jeu.setVisiteur(new VisiteurDayNight(jeu)));
    ButtonDesign modeHighLife = new ButtonDesign("HighLife");
    modeHighLife.addActionListener(e -> jeu.setVisiteur(new VisiteurHighLife(jeu)));


    controlPanel.add(playBouton);
    controlPanel.add(pauseBouton);
    controlPanel.add(uneGeneBouton);
    controlPanel.add(slideVitesse);
    controlPanel.add(slideZoom);
    controlPanel.add(modeNormal);
    controlPanel.add(modeDayNight);
    controlPanel.add(modeHighLife);

    uiPanel.add(viewGeneration, BorderLayout.NORTH);
    uiPanel.add(scrollPane, BorderLayout.CENTER);
    uiPanel.add(controlPanel, BorderLayout.SOUTH);
    this.add(uiPanel, BorderLayout.CENTER);
  
    Timer timer = new Timer(100, e -> {
      if(pausePlay == 0){
          jeu.calculerGenerationSuivante();

          
          if(term.getGeneration() != oldGeneration){
              viewGeneration.setText(
                  "Numéro de la génération : " 
                  + term.getGeneration() 
                  + ", nombre de cellules : " 
                  + jeu.getNbCellule()
              );
              oldGeneration = term.getGeneration();
          }
      }
    });
    slideVitesse.addChangeListener(e -> {
      timer.setDelay(slideVitesse.getValue());
    });

    timer.start(); 
  }
}
