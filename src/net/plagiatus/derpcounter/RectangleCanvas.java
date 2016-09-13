package net.plagiatus.derpcounter;
import java.awt.Graphics;

import javax.swing.JComponent;

@SuppressWarnings("serial")
public class RectangleCanvas extends JComponent {

  public void paint(Graphics g) {
	  g.setColor(DerpCounter.textColor);
    g.fillRect(10, 15, 20, 20);  
  }

}
