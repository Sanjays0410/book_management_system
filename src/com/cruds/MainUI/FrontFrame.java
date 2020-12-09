package com.cruds.MainUI;

import javax.swing.JFrame;

import javax.swing.SwingUtilities;
 
public class FrontFrame extends JFrame {

	JFrame parent;

	public FrontFrame() {

		parent=this;
		setTitle("*****BOOK MANAGEMENT SYSTEM*****"); 
		setSize(1000, 1000);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);	
		add(new LoginPanel(parent));
		setVisible(true);

	}
	public static void main(String[] args) {

		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				new FrontFrame();

			}
		});
	}
}
