package DBRF;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Insets;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import java.awt.Dimension;

public class Settings extends JFrame {

	public JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Settings frame = new Settings();
//					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Settings() {
		
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(FestivalObject.getXPos(), FestivalObject.getYPos(), FestivalObject.getWindowWidth(), FestivalObject.getWindowHeight());
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
//		GridBagLayout gbl_contentPane = new GridBagLayout();
//		gbl_contentPane.columnWidths = new int[]{286, 0, 0, 0, 0, 0};
//		gbl_contentPane.rowHeights = new int[]{23, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
//		gbl_contentPane.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
//		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
//		frame.setLayout(gbl_contentPane);
		
		JButton deleteUser = new JButton("Delete User");
		deleteUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		deleteUser.setFont(FestivalObject.getFont());
		deleteUser.setEnabled(true);
		deleteUser.setSelected(true);
		deleteUser.setBounds(439, 252, 134, 23);
		deleteUser.setFocusable(false);
		contentPane.add(deleteUser);
		
		JButton signUpNewUser = new JButton("Sign Up New User");
		signUpNewUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		signUpNewUser.setFont(FestivalObject.getFont());
		signUpNewUser.setEnabled(true);
		signUpNewUser.setSelected(true);
		signUpNewUser.setBounds(439, 218, 134, 23);
		signUpNewUser.setFocusable(false);
		contentPane.add(signUpNewUser);
		
		JButton fontButton = new JButton("Change Font");
		fontButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				
				FontClass fontc = new FontClass();
				//FontObject fontO = new FontObject();
				
			}
		});
		fontButton.setFont(FestivalObject.getFont());
		fontButton.setEnabled(true);
		fontButton.setSelected(true);
		fontButton.setBounds(439, 82, 134, 23);
		fontButton.setFocusable(false);
		contentPane.add(fontButton);
		
		
//		GridBagConstraints gbc_btnFont = new GridBagConstraints();
//		gbc_btnFont.insets = new Insets(0, 0, 5, 0);
//		gbc_btnFont.fill = GridBagConstraints.HORIZONTAL;
//		gbc_btnFont.anchor = GridBagConstraints.NORTH;
//		gbc_btnFont.gridx = 4;
//		gbc_btnFont.gridy = 4;
//		frame.getContentPane().add(fontButton, gbc_btnFont);
//		
//		GridBagConstraints gbc_SignUpNewUser = new GridBagConstraints();
//		gbc_SignUpNewUser.insets = new Insets(0, 0, 5, 0);
//		gbc_SignUpNewUser.gridx = 4;
//		gbc_SignUpNewUser.gridy = 5;
//		frame.getContentPane().add(signUpNewUser, gbc_SignUpNewUser);
//		
//		JButton ChangeUserName = new JButton("Change Username");
//		GridBagConstraints gbc_ChangeUserName = new GridBagConstraints();
//		gbc_ChangeUserName.insets = new Insets(0, 0, 5, 0);
//		gbc_ChangeUserName.gridx = 4;
//		gbc_ChangeUserName.gridy = 6;
//		frame.getContentPane().add(ChangeUserName, gbc_ChangeUserName);
//		
//		JButton ChangePassword = new JButton("Change Password");
//		GridBagConstraints gbc_ChangePassword = new GridBagConstraints();
//		gbc_ChangePassword.insets = new Insets(0, 0, 5, 0);
//		gbc_ChangePassword.gridx = 4;
//		gbc_ChangePassword.gridy = 7;
//		frame.getContentPane().add(ChangePassword, gbc_ChangePassword);
//		
//		GridBagConstraints gbc_DeleteUser = new GridBagConstraints();
//		gbc_DeleteUser.fill = GridBagConstraints.HORIZONTAL;
//		gbc_DeleteUser.insets = new Insets(0, 0, 5, 0);
//		gbc_DeleteUser.gridx = 4;
//		gbc_DeleteUser.gridy = 8;
//		frame.getContentPane().add(deleteUser, gbc_DeleteUser);
		
		//main menu stuff is first set here since it is alwayse where you start
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		//the menu items
		JMenuItem mntmHome = new JMenuItem("Home");
		mntmHome.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				FestivalObject.setWindowPosition(getX(), getY());	//store the window position in FestivalObject
				new MainMenu().setVisible(true);
				dispose();
			}
		});
		mntmHome.setFont(FestivalObject.getFont());
		mntmHome.setHorizontalTextPosition(SwingConstants.CENTER);
		mntmHome.setHorizontalAlignment(SwingConstants.CENTER);
		mntmHome.setPreferredSize(new Dimension(100, 25));
		mntmHome.setBorder(new BevelBorder(BevelBorder.RAISED, null, new Color(128, 128, 128), null, null));
		mntmHome.setBackground(Color.WHITE);
		menuBar.add(mntmHome);
		
		JMenuItem mntmSave = new JMenuItem("Save");
		mntmSave.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				SaveAndLoad.saveXML();
			}
		});
		mntmSave.setFont(FestivalObject.getFont());
		mntmSave.setHorizontalTextPosition(SwingConstants.CENTER);
		mntmSave.setHorizontalAlignment(SwingConstants.CENTER);
		mntmSave.setBorder(new BevelBorder(BevelBorder.RAISED, null, new Color(128, 128, 128), null, null));
		mntmSave.setPreferredSize(new Dimension(100, 25));
		mntmSave.setBackground(Color.WHITE);
		menuBar.add(mntmSave);
		
		JMenuItem mntmSettings = new JMenuItem("Settings");
		mntmSettings.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				//don't do anything here
			}
		});
		mntmSettings.setFont(FestivalObject.getFont());
		mntmSettings.setHorizontalTextPosition(SwingConstants.CENTER);
		mntmSettings.setHorizontalAlignment(SwingConstants.CENTER);
		mntmSettings.setBorder(new BevelBorder(BevelBorder.RAISED, null, new Color(128, 128, 128), null, null));
		mntmSettings.setPreferredSize(new Dimension(100, 25));
		mntmSettings.setBackground(Color.WHITE);
		menuBar.add(mntmSettings);
		
		JMenuItem mntmHelp = new JMenuItem("Help");
		mntmHelp.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				FestivalObject.setWindowPosition(getX(), getY());	//store the window position in FestivalObject
				//TODO - add help stuff
			}
		});
		mntmHelp.setFont(FestivalObject.getFont());
		mntmHelp.setHorizontalTextPosition(SwingConstants.CENTER);
		mntmHelp.setHorizontalAlignment(SwingConstants.CENTER);
		mntmHelp.setBorder(new BevelBorder(BevelBorder.RAISED, null, new Color(128, 128, 128), null, null));
		mntmHelp.setPreferredSize(new Dimension(100, 25));
		mntmHelp.setBackground(Color.WHITE);
		menuBar.add(mntmHelp);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("");
		mntmNewMenuItem.setBorderPainted(true);
		mntmNewMenuItem.setBorder(null);
		mntmNewMenuItem.setOpaque(true);
		mntmNewMenuItem.setPreferredSize(new Dimension(600, 25));
		menuBar.add(mntmNewMenuItem);
		
		JMenuItem mntmLogout = new JMenuItem("Logout");
		mntmLogout.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				FestivalObject.setWindowPosition(getX(), getY());	//store the window position in FestivalObject
				new UserLogin().setVisible(true);
				dispose();
			}
		});
		mntmLogout.setFont(FestivalObject.getFont());
		mntmLogout.setHorizontalTextPosition(SwingConstants.CENTER);
		mntmLogout.setHorizontalAlignment(SwingConstants.CENTER);
		mntmLogout.setBorder(new BevelBorder(BevelBorder.RAISED, null, new Color(128, 128, 128), null, null));
		mntmLogout.setPreferredSize(new Dimension(100, 25));
		mntmLogout.setBackground(Color.WHITE);
		menuBar.add(mntmLogout);
	}
}
