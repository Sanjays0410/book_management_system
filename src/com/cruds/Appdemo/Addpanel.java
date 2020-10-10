package com.cruds.Appdemo;


import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;

import com.cruds.db.BookDAO;
import com.cruds.demo.Author;
import com.cruds.demo.Book;
import com.cruds.exception.SMSException;
public class Addpanel extends JPanel {

	JFrame parent;
	JPanel currentpanel;
	JTextField txtname;
	JToolBar toolbar;
	
	JButton btnadd;
	
	
	//JToolBar toolbar;
	JTable table;
	JScrollPane scrollpane;
	//JPanel currentpanel;
	JButton  btnok, btnback;
	 JButton btnsertitle, btnsearch;
	JTextField txtbook_isbn, txt_title, txtCategory,  txtno_of_book, txtauthor_name, txtauthormail_id;
	JLabel lblbook_isbn, lbl_title, lblCategory, lblno_of_book, lblauthor_name, lblauthor_mailid;
	
	JLabel lblsearchtitle;
	JTextField txtsearchtitle;
	
	public Addpanel(JFrame parent) {
		
		this.parent=parent;
		currentpanel=this;
		btnadd=new JButton("ADD");
		txtname=new JTextField(20);
		
		toolbar=new JToolBar();
		toolbar.setOrientation(SwingConstants.VERTICAL);
		
		
		lblbook_isbn=new JLabel("BOOK_ISBN");
		lbl_title=new JLabel("BOOK_TITLE");
		lblCategory=new JLabel("BOOK_CATEGORY");
		lblno_of_book=new JLabel("NO_OF_BOOKS");
		lblauthor_name=new JLabel("AUTHOR_NAME");
		lblauthor_mailid=new JLabel("AUTHOR_MAIL_ID");
		
		
		txtbook_isbn=new JTextField(10);
		txt_title=new JTextField(10);
		txtCategory=new JTextField(10);
		txtno_of_book=new JTextField(10);
		txtauthor_name=new JTextField(10);
		txtauthormail_id=new JTextField(10);
		
		btnok=new JButton("SUBMIT");
		btnback=new JButton("BACK");
		
		
		BookDAO dao=new BookDAO();
		
		btnadd.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				
				
				currentpanel=new JPanel();
				/////////////////BUTTON SUBMIT///////////////////////////	    
					       
					       
					       
					      btnok.addActionListener(new ActionListener() {
							
							@Override
							public void actionPerformed(ActionEvent e) {
								
								String book_isbn=txtbook_isbn.getText().trim();
								String title=txt_title.getText().trim();
								String Category=txtCategory.getText().trim();
								String no_of_books=txtno_of_book.getText().trim();
								String author_name=txtauthor_name.getText().trim();
								String author_mailid=txtauthormail_id.getText().trim();
								
								
								
								try
								{
									if(book_isbn.equals("") ||title.equals("") || Category.equals("") || no_of_books.equals("") || author_name.equals("")|| author_mailid.equals(""))
										
									{
										JOptionPane.showMessageDialog(currentpanel,"details cannot be empty", "WARNING", JOptionPane.WARNING_MESSAGE);
										return;
									} 
								
								Author aut=new Author(author_name, author_mailid);
								
								Book b=new Book(Integer.parseInt(book_isbn), title, Category, Integer.parseInt(no_of_books), aut);
								
								if(dao.Create(b))
								{
									//help to enter nxt data////
									txtbook_isbn.setText("");
									txt_title.setText("");
									txtCategory.setText("");
									txtno_of_book.setText("");
									txtauthor_name.setText("");
									txtauthormail_id.setText("");
								
									
									JOptionPane.showMessageDialog(currentpanel,"student data added succesfully", "success", JOptionPane.INFORMATION_MESSAGE);

								}	
							
								}catch(NumberFormatException nfe){
									getToolkit().beep();
									JOptionPane.showMessageDialog(currentpanel,"invaloid data", "error", JOptionPane.ERROR_MESSAGE);
								}catch(SMSException smse){
									getToolkit().beep();
									JOptionPane.showMessageDialog(currentpanel, smse.getInfo(), "error", JOptionPane.ERROR_MESSAGE);
								
								}
								
							}
							});
			////////////////////////////////////BUTTON BACK//////////////////////////////////////////		      
					     btnback.addActionListener(new ActionListener() {
							
							@Override
							public void actionPerformed(ActionEvent e) {
								System.exit(ABORT); ;
							}
						});
					      
					     
					       
							
				
				
				
				
				//parent.remove(currentpanel);
				//parent.add(new AdddisplayPanel(parent));
				parent.revalidate();
				 currentpanel.add(lblbook_isbn);
				    currentpanel.add(txtbook_isbn);
				    currentpanel.add(lbl_title);
				    currentpanel.add(txt_title);
				    currentpanel.add(lblCategory);
				    currentpanel.add(txtCategory);
				    currentpanel.add(lblno_of_book);
				    currentpanel.add(txtno_of_book);
				    currentpanel.add(lblauthor_name);
				    currentpanel.add(txtauthor_name);
				    currentpanel.add(lblauthor_mailid);
				    currentpanel.add(txtauthormail_id);
				    currentpanel.add(btnok);
				    currentpanel.add(btnback);
				    add(currentpanel);
					setVisible(true);
			       
								
				
			}
		});
		toolbar.add(btnadd);
		add(toolbar,BorderLayout.WEST);
		setVisible(true);
	}

		
}



