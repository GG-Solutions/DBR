package DBRF;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class FontClass extends JFrame {
	public static void main(String[] args) {
		new FontClass();
	}
	
	public JLabel sampleText = new JLabel("Sample Text");
	
	private JComboBox fontComboBox;
	
	private JComboBox sizeComboBox;
	
	private JCheckBox boldCheck;
	
	private String[] fonts;
	
	public FontClass() {
		
		this.setSize(500, 150);
		//sampleText.setFont();
		
		FontListener fl = new FontListener();
		this.add(sampleText, BorderLayout.NORTH);
		GraphicsEnvironment g = GraphicsEnvironment.getLocalGraphicsEnvironment();
		fonts = g.getAvailableFontFamilyNames();
		/*creates dropdown list to select font option*/
		JPanel controlPanel = new JPanel();
	    fontComboBox = new JComboBox(fonts);
	    fontComboBox.addActionListener(fl);
	    controlPanel.add(new JLabel("Family: "));
	    controlPanel.add(fontComboBox);
	    /*gives all font sizes options to choose from*/
	    Integer[] sizes = { 7, 8, 9, 10, 11, 12, 14, 18, 20, 22, 24};
	    /*creates combobox for sizes option*/
	    sizeComboBox = new JComboBox(sizes);
	    sizeComboBox.setSelectedIndex(5);
	    sizeComboBox.addActionListener(fl);
	    controlPanel.add(new JLabel("Size: "));
	    controlPanel.add(sizeComboBox);
	    /*creates check box for bold option*/
	    boldCheck = new JCheckBox("Bold");
	    boldCheck.addActionListener(fl);
	    controlPanel.add(boldCheck);
	    
	    this.add(controlPanel, BorderLayout.SOUTH);
	    fl.updateText();
	    
	    this.setVisible(true);
	}

  	public class FontListener implements ActionListener {
	    public void actionPerformed(ActionEvent e) {
	    	updateText();
	    }
	    
	    /*updates the change*/
	    public void updateText() {
	      String name = (String) fontComboBox.getSelectedItem();
	
	      Integer size = (Integer) sizeComboBox.getSelectedItem();
	
	      int style;    //checks if bold is check or not if not then it makes font to plain
	      	if (boldCheck.isSelected())
	        style = Font.BOLD;
	 
	      else
	        style = Font.PLAIN;
	
	      Font f = new Font(name, style, size.intValue());
	      sampleText.setFont(f);
	    }
  	}
}