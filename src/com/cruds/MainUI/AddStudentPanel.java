package com.cruds.MainUI;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.cruds.db.IssueDAO;
import com.cruds.demo.Student;
import com.cruds.exception.SMSException;



public class AddStudentPanel extends JPanel {

	JFrame parent;
	JPanel Panel;

	JTextField txtstud_usn, txt_name;
	JLabel lblstud_usn,lbl_name;

	JButton btnadd,btnok,btnback;

	IssueDAO dao=new IssueDAO();

	public AddStudentPanel(JFrame parent) {

		this.parent=parent;
		Panel=this;


		lblstud_usn= new JLabel("ENTER THE USN:");
		lbl_name=new JLabel("ENTER THE NAME:");

		txtstud_usn=new JTextField(15);
		txt_name=new JTextField(15);


		btnok=new JButton("SUBMIT");
		btnback=new JButton("BACK");



		btnok.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {


				String stud_usn=txtstud_usn.getText().trim();
				String stud_name=txt_name.getText().trim();

				try{
					if(stud_usn.equals("")|| stud_name.equals(""))
					{
						JOptionPane.showMessageDialog(Panel,"details cannot be empty", "WARNING", JOptionPane.WARNING_MESSAGE);
						return;
					}
					Student s=new Student(stud_usn, stud_name);
					if(dao.CreateStudent(s))
					{
						txtstud_usn.setText("");
						txt_name.setText("");

						JOptionPane.showMessageDialog(Panel,"student data added succesfully :)", "success", JOptionPane.INFORMATION_MESSAGE);

					}	

				}catch(NumberFormatException nfe){
					getToolkit().beep();
					JOptionPane.showMessageDialog(Panel,"invalid data", "error", JOptionPane.ERROR_MESSAGE);
				}catch(SMSException smse){
					getToolkit().beep();
					JOptionPane.showMessageDialog(Panel, smse.getInfo(), "error", JOptionPane.ERROR_MESSAGE);

				}


			}
		});

		btnback.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent ae) {

				parent.remove(Panel);
				parent.add(new ToolbarPanel(parent));
				parent.revalidate();


			}
		});
		Panel.add(lblstud_usn);
		Panel.add(txtstud_usn);
		Panel.add(lbl_name);
		Panel.add(txt_name);
		Panel.add(btnok);
		Panel.add(btnback);

	}
}
