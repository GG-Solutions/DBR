package DBRF;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.swing.JLabel;
import java.awt.Font;

public class AddUser extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtName;
	private JPasswordField txtPass;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			AddUser dialog = new AddUser();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public AddUser() {
		setResizable(false);
		setBounds(100, 100, 355, 171);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		txtName = new JTextField();
		txtName.setToolTipText("Name");
		txtName.setBounds(50, 52, 86, 20);
		contentPanel.add(txtName);
		txtName.setColumns(10);
		
		txtPass = new JPasswordField();
		txtPass.setBounds(215, 52, 97, 20);
		contentPanel.add(txtPass);
		{
			JLabel lblAddUser = new JLabel("Add User");
			lblAddUser.setFont(new Font("Times New Roman", Font.BOLD, 15));
			lblAddUser.setBounds(29, 11, 97, 20);
			contentPanel.add(lblAddUser);
		}
		{
			JLabel lblNewLabel = new JLabel("Name:");
			lblNewLabel.setBounds(10, 55, 46, 14);
			contentPanel.add(lblNewLabel);
		}
		{
			JLabel lblNewLabel_1 = new JLabel("Password:");
			lblNewLabel_1.setBounds(146, 55, 86, 14);
			contentPanel.add(lblNewLabel_1);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addMouseListener(new MouseAdapter() {
					@SuppressWarnings("deprecation")
					@Override
					public void mouseClicked(MouseEvent arg0) {
						
							try {
								LoginInfo.addUser(txtName.getText(), txtPass.getText());
							} catch (FileNotFoundException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							closeThis ();
						
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	private void closeThis (){
		this.dispose();
	}
}
