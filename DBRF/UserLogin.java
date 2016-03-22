package DBRF;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import javax.swing.JTextField;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import javax.swing.JPasswordField;
import java.awt.Font;

public class UserLogin extends JFrame {

	private JPanel contentPane;
	private JTextField txtName;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserLogin frame = new UserLogin();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public UserLogin() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 465, 305);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtName = new JTextField();
		txtName.setBounds(110, 136, 105, 20);
		txtName.setToolTipText("Name");
		contentPane.add(txtName);
		txtName.setColumns(10);
		
		JButton btnNewButton = new JButton("Login");
		btnNewButton.setBounds(188, 187, 86, 23);
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				try {
					@SuppressWarnings("deprecation")
					boolean loginFlag = LoginInfo.uLogin(txtName.getText(),passwordField.getText());
					if(loginFlag){
					newSuccess();
					} 
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		contentPane.add(btnNewButton);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(306, 136, 86, 20);
		passwordField.setToolTipText("Password");
		contentPane.add(passwordField);
		
		JLabel lblNewLabel = new JLabel("User Name:");
		lblNewLabel.setBounds(38, 139, 115, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(238, 139, 105, 14);
		contentPane.add(lblPassword);
		
		JLabel lblDragonBoatFestival = new JLabel("Dragon Boat Festival");
		lblDragonBoatFestival.setFont(new Font("Times New Roman", Font.BOLD, 25));
		lblDragonBoatFestival.setBounds(108, 22, 284, 63);
		contentPane.add(lblDragonBoatFestival);
		
	
	}
	
	/**
	 * Login after the password Success
	 * Inputs - Yes or No
	 * Outputs 	- Will take the user to the new festival or load a old one.
	 * 
	 **/
	
	public void newSuccess (){
		this.dispose();
		int dialogButton = JOptionPane.YES_NO_OPTION;
		int dialogResult = JOptionPane.showConfirmDialog(this, "Load a Festival?", "Festival Load", dialogButton);
		if(dialogResult == 0) {
		 this.dispose();
		 new MainMenu().setVisible(true);
		} else {
		 this.dispose();
		 new EventPageSetup().setVisible(true);
		 
		} 
		
	}
}
