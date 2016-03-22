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
import java.io.IOException;
import javax.swing.JLabel;
import java.awt.Font;

public class RemoveUser extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtUser;
	private JPasswordField txtPass;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			RemoveUser dialog = new RemoveUser();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public RemoveUser() {
		setResizable(false);
		setBounds(100, 100, 383, 230);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			txtUser = new JTextField();
			txtUser.setToolTipText("User");
			txtUser.setBounds(62, 103, 86, 20);
			contentPanel.add(txtUser);
			txtUser.setColumns(10);
		}
		
		txtPass = new JPasswordField();
		txtPass.setToolTipText("Password");
		txtPass.setBounds(236, 103, 94, 20);
		contentPanel.add(txtPass);
		{
			JLabel lblRemoveUser = new JLabel("Remove User");
			lblRemoveUser.setFont(new Font("Times New Roman", Font.BOLD, 15));
			lblRemoveUser.setBounds(25, 25, 110, 29);
			contentPanel.add(lblRemoveUser);
		}
		{
			JLabel lblName = new JLabel("Name:");
			lblName.setBounds(25, 106, 46, 14);
			contentPanel.add(lblName);
		}
		{
			JLabel lblPassword = new JLabel("Password:");
			lblPassword.setBounds(168, 106, 76, 14);
			contentPanel.add(lblPassword);
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
							LoginInfo.removeUser(txtUser.getText(), txtPass.getText());
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						closeThis();
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
	
	private void closeThis(){
		this.dispose();
	}
	
}
