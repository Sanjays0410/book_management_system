package com.cruds.MainUI;

import java.awt.Component;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.cruds.db.StudentDAO;

public class ViewstudentsPanel extends JPanel {

	JFrame parent;
	JPanel Panel;
	
	JTable table;
	JScrollPane scrollpane;
	JButton btnback;
	
	Vector<String> colNames=new Vector<>();
	
	public ViewstudentsPanel(JFrame parent) {
		
		
		this.parent=parent;
		Panel=this;
		
	
		colNames.add("Student_USN");
		colNames.add("Student_Name");
		
		StudentDAO dao=new StudentDAO();
		table=new JTable(new DefaultTableModel(dao.getAllstudent(), colNames));
		scrollpane=new JScrollPane(table);
		
		
		btnback=new JButton("BACK");
		
		btnback.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				parent.remove(Panel);
				parent.add(new  AddStudentPanel(parent));
				parent.revalidate();
				
			}
		});
		
		
		Panel.add(scrollpane);
		Panel.add(btnback);
	}
	
}
