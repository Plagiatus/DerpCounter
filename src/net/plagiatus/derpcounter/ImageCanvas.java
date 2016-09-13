package net.plagiatus.derpcounter;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JComponent;

@SuppressWarnings("serial")
public class ImageCanvas extends JComponent {

  public void paint(Graphics g) {
    Image img1 = Toolkit.getDefaultToolkit().getImage(DerpCounter.path);

    Font font = new Font("SansSerif", Font.PLAIN, DerpCounter.textSize);
    g.setFont(font);
    g.setColor(DerpCounter.textColor);
    g.drawImage(img1, 0, 0, this);
    g.drawString(DerpCounter.displayText + " " + Integer.toString(DerpCounter.counter), DerpCounter.xPosition, DerpCounter.yPosition);
    

  }
}