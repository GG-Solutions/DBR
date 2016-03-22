package DBRF;

import java.applet.Applet;
import java.awt.Font;
import java.awt.Graphics;

public class fontObject extends Applet {
	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		
		Font f= new Font("Times new romans", Font.PLAIN, 25);
		
		g.setFont(f);
		g.drawString("testing", 50, 50);
		
	}
}
