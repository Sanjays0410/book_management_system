package com.cruds.MainUI;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.cruds.db.IssueDAO;
import com.cruds.db.StudentDAO;
import com.cruds.demo.Student;
import com.cruds.exception.SMSException;



public class AddStudentPanel extends JPanel {

	JFrame parent;
	JPanel Panel;

	JTextField txtstud_usn, txt_name;
	JLabel lblstud_usn,lbl_name;

	JButton btnadd,btnok,btnback,btnviewstudent;

	StudentDAO dao=new StudentDAO();
	JTable table;
	JScrollPane scrollpane;
	
	Vector<String> colNames=new Vector<>();

	public AddStudentPanel(JFrame parent) {

		this.parent=parent;
		Panel=this;


		lblstud_usn= new JLabel("ENTER THE USN:");
		lbl_name=new JLabel("ENTER THE NAME:");

		txtstud_usn=new JTextField(15);
		txt_name=new JTextField(15);
		
		colNames.add("Student_USN");
		colNames.add("Student_Name");


		btnok=new JButton("SUBMIT");
		btnback=new JButton("BACK");

		StudentDAO dao=new StudentDAO();
		
		
		

		

		btnok.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {


				String stud_usn=txtstud_usn.getText().trim();
				String stud_name=txt_name.getText().trim();
				
				

				try{
					if(stud_usn.equals("")|| stud_name.equals(""))
					{
						JOptionPane.showMessageDialog(Panel,"Details cannot be empty", "WARNING", JOptionPane.WARNING_MESSAGE);
						return;
					}
					Student s=new Student(stud_usn, stud_name);
					if(dao.CreateStudent(s))
					{
						txtstud_usn.setText("");
						txt_name.setText("");
						
						
						
						JOptionPane.showMessageDialog(Panel,"Student details added succesfully :)", "success", JOptionPane.INFORMATION_MESSAGE);
						

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
		
		btnviewstudent =new JButton("VIEW ALL STUDENT") ;
		
		btnviewstudent.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				parent.remove(Panel);
				parent.add(new ViewstudentsPanel(parent));
				parent.revalidate();
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
		Panel.add(btnviewstudent);
		Panel.add(btnback);
		

	}
}
