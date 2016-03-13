package DBRF;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Insets;

public class GG extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GG frame = new GG();
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
	public GG() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 637, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{284, 53, 0};
		gbl_contentPane.rowHeights = new int[]{23, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JButton SignUpNewUser = new JButton("Sign Up New User");
		SignUpNewUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		
		JButton btnFont = new JButton("Change Font");
		btnFont.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
		fC fontc = new fC();
			
			}
		});
		GridBagConstraints gbc_btnFont = new GridBagConstraints();
		gbc_btnFont.insets = new Insets(0, 0, 5, 0);
		gbc_btnFont.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnFont.anchor = GridBagConstraints.NORTH;
		gbc_btnFont.gridx = 1;
		gbc_btnFont.gridy = 2;
		contentPane.add(btnFont, gbc_btnFont);
		GridBagConstraints gbc_SignUpNewUser = new GridBagConstraints();
		gbc_SignUpNewUser.insets = new Insets(0, 0, 5, 0);
		gbc_SignUpNewUser.gridx = 1;
		gbc_SignUpNewUser.gridy = 3;
		contentPane.add(SignUpNewUser, gbc_SignUpNewUser);
		
		JButton ChangeUserName = new JButton("Change Username");
		GridBagConstraints gbc_ChangeUserName = new GridBagConstraints();
		gbc_ChangeUserName.insets = new Insets(0, 0, 5, 0);
		gbc_ChangeUserName.gridx = 1;
		gbc_ChangeUserName.gridy = 4;
		contentPane.add(ChangeUserName, gbc_ChangeUserName);
		
		JButton ChangePassword = new JButton("Change Password");
		GridBagConstraints gbc_ChangePassword = new GridBagConstraints();
		gbc_ChangePassword.insets = new Insets(0, 0, 5, 0);
		gbc_ChangePassword.gridx = 1;
		gbc_ChangePassword.gridy = 5;
		contentPane.add(ChangePassword, gbc_ChangePassword);
		
		JButton DeleteUser = new JButton("Delete User");
		GridBagConstraints gbc_DeleteUser = new GridBagConstraints();
		gbc_DeleteUser.fill = GridBagConstraints.HORIZONTAL;
		gbc_DeleteUser.insets = new Insets(0, 0, 5, 0);
		gbc_DeleteUser.gridx = 1;
		gbc_DeleteUser.gridy = 6;
		contentPane.add(DeleteUser, gbc_DeleteUser);
	}

}
