package DBRF;

import java.applet.Applet;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JLabel;

public class FontObject extends Applet {

	//public FontClass fc = new FontClass();
	public void paint(Graphics g) {
		
		Font x = new Font("Times new romans", Font.PLAIN, 19);
		
		g.setFont(x);
	
		g.drawString("testing", 19 ,19);
	}
}
