package com.cruds.MainUI;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.cruds.db.AdminDAO;
import com.cruds.demo.AdminLogin;
import com.cruds.exception.SMSException;

public class LoginPanel  extends JPanel{

	JFrame parent;
	JPanel Panel;

	JButton btnLogin,btnback;
	JLabel lblusername,lblpassword;
	JPasswordField txtusername,txtpassword;
	//JTextField txtusername,txtpassword;

	public LoginPanel(JFrame parent) {

		this.parent=parent;
		Panel=this;



		lblusername=new JLabel("ENTER USERNAME:");
		lblpassword=new JLabel("ENTER PASSWORD:");

		txtusername=new JPasswordField(10);
		txtpassword=new JPasswordField(10);

		btnLogin=new JButton("LOGIN");

		btnLogin.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {


				String username=txtusername.getText().trim();
				String password=txtpassword.getText().trim();

				try
				{

					if(username.equals("") || password.equals(""))
					{
						JOptionPane.showMessageDialog(Panel,"PLease enter username and password", "WARNING", JOptionPane.WARNING_MESSAGE);
						return;
					}

					AdminDAO dao=new AdminDAO();

					AdminLogin u=new AdminLogin(username, password);

					if(dao.User(u))
					{
						txtusername.setText("");
						txtpassword.setText("");

						parent.remove(Panel);
						parent.add(new ToolbarPanel(parent));
						parent.revalidate();
						
						JOptionPane.showMessageDialog(Panel," login succesfully :)", "success :)", JOptionPane.INFORMATION_MESSAGE);

					}
					else
					{
						JOptionPane.showMessageDialog(Panel," login failed :( username or password incorrect", "ERROR", JOptionPane.ERROR_MESSAGE);
					}
					

				}catch(NumberFormatException nfe){
					getToolkit().beep();
					JOptionPane.showMessageDialog(Panel,"invalid data :(", "error", JOptionPane.ERROR_MESSAGE);
				}catch(SMSException smse){
					getToolkit().beep();
					JOptionPane.showMessageDialog(Panel, smse.getInfo(), "error", JOptionPane.ERROR_MESSAGE);

				}

			}

		});


		btnback=new JButton("BACK");

		btnback.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(ABORT);

			}
		});


		Panel.add(lblusername);
		Panel.add(txtusername);
		Panel.add(lblpassword);
		Panel.add(txtpassword);
		Panel.add(btnLogin);
		Panel.add(btnback);

	}

}
