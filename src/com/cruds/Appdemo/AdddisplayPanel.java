package com.cruds.Appdemo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;



public class AdddisplayPanel extends JPanel {

	JButton btnback;
	JFrame parent;
	 JPanel currentpanel;
	public AdddisplayPanel(JFrame parent) {
		
		this.parent=parent;
		 currentpanel=this;
		 btnback=new JButton("BACK");
		 
		 
		
		 btnback.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				parent.remove(currentpanel);
				parent.add(new Addpanel(parent));
				parent.revalidate();
				
			}
		});
		 add(btnback);
	}

}
