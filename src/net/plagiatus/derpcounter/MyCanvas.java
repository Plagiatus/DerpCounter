package net.plagiatus.derpcounter;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JComponent;

@SuppressWarnings("serial")
public class MyCanvas extends JComponent {

  public void paint(Graphics g) {
    Image img1 = Toolkit.getDefaultToolkit().getImage(DerpCounter.path);

    float width = img1.getHeight(null) / 50f;
    //System.out.println(width);
    
    if(width>0){
    	width = img1.getWidth(null) / width;
    	//System.out.println("2:"+  width);
    	g.drawImage(img1, 10, 0, (int) width, 50, this);
    } else {
    	g.drawImage(img1, 10, 0, 50, 50, this);
    }
    
    
    
  }
}