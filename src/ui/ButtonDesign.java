package ui;
 
import java.awt.Color;
import java.awt.Insets;
  
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.BorderFactory;
import javax.swing.border.LineBorder;
import javax.swing.border.EmptyBorder;
  
  public class ButtonDesign extends JButton {
      
    public ButtonDesign(String unNom){
      super(unNom);
      setBackground(new Color(230,230,230));
      setForeground(Color.BLACK);
      
      setOpaque(true);
      setBorderPainted(true);
        
      setBorder(BorderFactory.createCompoundBorder(
            new LineBorder(Color.BLACK, 1, true),
            BorderFactory.createEmptyBorder(20,20,20,20)));
      
      setHorizontalTextPosition(SwingConstants.CENTER);
      
    } 
 } 
