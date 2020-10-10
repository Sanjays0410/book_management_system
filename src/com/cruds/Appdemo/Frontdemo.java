package com.cruds.Appdemo;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;



public class Frontdemo extends JFrame {
	

	JFrame parent;
	

	
	 public Frontdemo() {
		// TODO Auto-generated constructor stub
	
		parent=this;
		setTitle("BOOK MANAGEMENT SYSTEM"); 
		setSize(500, 500);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		

	
		add(new Addpanel(parent));
		
		
		
		
		setVisible(true);

	}
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
			new Frontdemo();  
				 
			}
		});
	}
	
}
 
