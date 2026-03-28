package ui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class Menu {

  public Menu() {
    // Création de la fenêtre
    JFrame frame = new JFrame("Menu avec TextArea");
    frame.setSize(800, 600);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    // Layout
    frame.setLayout(new BorderLayout());

    // Layout spécial pour switcher
    CardLayout cardLayout = new CardLayout();
    JPanel container = new JPanel(cardLayout);

    // ===== Panel 1 =====
    
    // Panel pour contenir les TextArea
    JPanel panel1 = new JPanel();
    panel1.setLayout(new BorderLayout());

    // Conteneur central avec espacement
    JPanel centerPanel = new JPanel();
    centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
    centerPanel.setBorder(new EmptyBorder(40, 80, 40, 80)); // marges globales

    // Création des TextArea
    JTextArea textArea1 = createTextArea("Largeur");
    JTextArea textArea2 = createTextArea("Hauteur");
    JTextArea textAreaDensite = createTextArea("Densité (Pas fini)");


    centerPanel.add(textArea1);
    centerPanel.add(Box.createRigidArea(new Dimension(0, 15)));

    centerPanel.add(textArea2);
    centerPanel.add(Box.createRigidArea(new Dimension(0, 15)));

    centerPanel.add(textAreaDensite);
    centerPanel.add(Box.createRigidArea(new Dimension(0, 30))); 


    // Boutons
    JPanel buttonPanel = new JPanel();
    buttonPanel.setLayout(new GridLayout(1, 3, 15, 0));
    
    ButtonDesign btn1 = new ButtonDesign("Aller au Jeu");
    
    btn1.addActionListener(e -> {
      String valeur1 = textArea1.getText();
      String valeur2 = textArea2.getText();
      String valeurdensite = textAreaDensite.getText();

      try {
        int v1 = Integer.parseInt(valeur1.trim());
        int v2 = Integer.parseInt(valeur2.trim());
        //int vdensite = Integer.parseInt(valeurdensite.trim());

        // Création du panel Jeu avec les valeurs
        Jeu panel2 = new Jeu(v1, v2, 1, ""); // ton constructeur doit accepter 2 int

        // Ajout à container et affichage
        container.add(panel2, "panel2");
        cardLayout.show(container, "panel2");
      
      } catch (NumberFormatException ex) {
        System.out.println("error");
      }
    });

    ButtonDesign gliderbtn = new ButtonDesign("Glider");
    gliderbtn.addActionListener(e -> {
      try{
        Jeu panel2 = new Jeu(100, 100, 1, "./structures/glider.txt");
        // Ajout à container et affichage
        container.add(panel2, "panel2");
        cardLayout.show(container, "panel2");

      } catch (NumberFormatException ex) {
        System.out.println("error");
      }

    });

    ButtonDesign gunbtn = new ButtonDesign("Gun");
    gunbtn.addActionListener(e -> {
      try{
        Jeu panel2 = new Jeu(100, 100, 1, "./structures/gun.txt");
        // Ajout à container et affichage
        container.add(panel2, "panel2");
        cardLayout.show(container, "panel2");

      } catch (NumberFormatException ex) {
        System.out.println("error");
      }

    });

    buttonPanel.add(btn1);
    buttonPanel.add(gliderbtn);
    buttonPanel.add(gunbtn);

    centerPanel.add(buttonPanel);

    JLabel title = new JLabel("Menu", SwingConstants.CENTER);
    title.setFont(new Font("Arial", Font.BOLD, 24));
    title.setBorder(new EmptyBorder(20, 0, 10, 0));

    panel1.add(title, BorderLayout.NORTH);
    panel1.add(centerPanel, BorderLayout.CENTER);

    // ===== Panel 2 =====

    // Ajout des panels avec un nom
    container.add(panel1, "panel1");


    // Ajout au frame
    frame.add(container);

    frame.setVisible(true);

  }

  // Méthode utilitaire pour uniformiser les champs
  private JTextArea createTextArea(String title) {
    JTextArea ta = new JTextArea(2, 20);
    ta.setBorder(BorderFactory.createTitledBorder(title));
    ta.setMaximumSize(new Dimension(Integer.MAX_VALUE, 60));
    return ta;
  }

  public static void main(String[] args){
    new Menu();
  }
}
