package com.cruds.MainUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;



public class ToolbarPanel extends JPanel {

	JButton btnAdd,btnsertitle,btnserCategory,btnserauthor,btnlist, btnstu,btnissue,btnissueview,btnlogout;
	JFrame parent;
	JPanel currentPanel;
	JTextField txtname;
	JToolBar toolbar;

	public ToolbarPanel(JFrame parent) {


		this.parent=parent;
		currentPanel=this;
		toolbar=new JToolBar();
		toolbar.setOrientation(SwingConstants.VERTICAL);

		btnAdd =new JButton("ADD BOOK");



		btnAdd.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {


				parent.remove(currentPanel);
				parent.add(new Addpanel(parent));
				parent.revalidate();
				parent.repaint();  




			}
		});

		btnsertitle=new JButton("SEARCH ON TITLE");

		btnsertitle.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent ae) {

				parent.remove(currentPanel);
				parent.add(new SerTitlepanel(parent));
				parent.revalidate();
				parent.repaint(); 
			}
		});

		btnserCategory=new JButton("SEARCH ON CATEGORY");

		btnserCategory.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				parent.remove(currentPanel);
				parent.add(new SerCategorypanel(parent));
				parent.revalidate();
				parent.repaint(); 

			}
		});

		btnserauthor=new JButton("SEARCH ON AUTHOR");

		btnserauthor.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				parent.remove(currentPanel);
				parent.add(new SerAuthorpanel(parent));
				parent.revalidate();
				parent.repaint(); 

			}
		});


		btnlist=new JButton("LIST ALL BOOK");

		btnlist.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent ae) {
				parent.remove(currentPanel);
				parent.add(new ListAll(parent));
				parent.revalidate();
				parent.repaint();

			}
		});

		btnstu=new JButton(" ADD STUDENT");

		btnstu.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				parent.remove(currentPanel);
				parent.add(new AddStudentPanel(parent));
				parent.revalidate();
				parent.repaint();

			}
		});


		btnissue=new JButton(" ISSUE BOOK ");

		btnissue.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				parent.remove(currentPanel);
				parent.add(new IssuePanel(parent));
				parent.revalidate();
				parent.repaint();


			}
		});

		btnissueview=new JButton("BOOK ISSUE VIEW");

		btnissueview.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				parent.remove(currentPanel);
				parent.add(new IssueViewPanel(parent));
				parent.revalidate();
				parent.repaint();
			}
		});
		
		
		btnlogout=new JButton("LOGOUT");
		
		
		btnlogout.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				 int response = JOptionPane.showConfirmDialog(currentPanel, "Do you want to LOGOUT?", "Confirm",
					        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				 
				 if (response == JOptionPane.YES_OPTION)
				 {
					 
					 
					// table.setModel(new DefaultTableModel(dao.getAllStudentForJTable(),colNames));
						//JOptionPane.showMessageDialog(currentPanel,"LOGOUT SUCESS", "success", JOptionPane.INFORMATION_MESSAGE);
				
						
				 }
				else
				{
					
					//JOptionPane.showMessageDialog(currentPanel,"", "unsuccess", JOptionPane.PLAIN_MESSAGE);
				return;
				}
				 
				 
				 parent.remove(currentPanel);
				 parent.add(new LoginPanel(parent));
				 parent.revalidate();
				
				
			}
		});


		//////////////////TOOLBAR/////////////////		 

		/*	 toolbar.add(btnAdd);
		 toolbar.add(btnsertitle);
		 toolbar.add(btnserCategory);
		 toolbar.add(btnserauthor);
		 toolbar.add(btnlist);
		 toolbar.add(btnissue);
		 toolbar.add(btnissueview);
		 currentPanel.add(toolbar,BorderLayout.WEST);

		 */ 



		/////////////////////////BUTTON/////////////////////// 

		currentPanel.add(btnAdd);
		currentPanel.add(btnsertitle);
		currentPanel.add(btnserCategory);
		currentPanel.add(btnserauthor);
		currentPanel.add(btnlist);
		currentPanel.add(btnstu);
		currentPanel.add(btnissue);
		currentPanel.add(btnissueview);   
		currentPanel.add(btnlogout);

	}


}
