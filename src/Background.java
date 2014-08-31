import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
// class that receives the image for the background
public class Background extends JPanel{

	Image bg = new ImageIcon("images/BackG.jpeg").getImage();


	public void paintComponent(Graphics g){
		g.drawImage(bg, 0, 0, getWidth(), getHeight(), this);
	}

}