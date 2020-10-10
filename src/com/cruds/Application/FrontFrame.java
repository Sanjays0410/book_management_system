package com.cruds.Application;

	import java.awt.BorderLayout;



import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Vector;

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
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

import com.cruds.db.BookDAO;
import com.cruds.demo.Author;
import com.cruds.demo.Book;

import com.cruds.exception.SMSException;
	public class FrontFrame extends JFrame{

	
		
		JToolBar toolbar;
		JTable table;
		JScrollPane scrollpane;
		JPanel panel,panel1;
		JButton btnadd, btnok, btnback;
		 JButton btnsertitle, btnsearch;
		JTextField txtbook_isbn, txt_title, txtCategory,  txtno_of_book, txtauthor_name, txtauthormail_id;
		JLabel lblbook_isbn, lbl_title, lblCategory, lblno_of_book, lblauthor_name, lblauthor_mailid;
		
		JLabel lblsearchtitle;
		JTextField txtsearchtitle;
		
		
		String sertitle=null;
		
		
		
		 Vector<String> colNames=new Vector<>();
		//JFrame parent;
		
		
		FrontFrame()
		{
			setTitle("BOOK MANAGEMENT SYSTEM");
			setSize(500, 400);
			setLocationRelativeTo(null);
			setDefaultCloseOperation(EXIT_ON_CLOSE);
			
			//parent=this;
			
			
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
			
			 BookDAO dao=new BookDAO();
			
			
			
			 colNames.add("Book_ISBN");
			 colNames.add("Book_Title");
			 colNames.add("Book_Category");
			 colNames.add("No of books");
			// colNames.add("Author_Name");
			 //colNames.add("Author_email_id");
			 
			 table=new JTable(new DefaultTableModel(dao.getBooktitle(sertitle),colNames));
				
				scrollpane=new JScrollPane(table);
				 
			 
			 
			
		
	///////// BUTTON FOR ADD//////////////////////////		
			 btnadd= new JButton("ADD BOOK");
			btnok=new JButton("SUBMIT");
		btnback=new JButton("EXIT");
		
		
	//////////BUTTON FOR SEARCH ON TITLE//////////////////	
		 btnsertitle =new JButton("SEARCH ON TITLE");
	     btnsearch=new JButton("SEARCH");
			
	     
	    
			
		       toolbar= new JToolBar();
		      
		       
		  //     panel=new JPanel();
		       
		       
		       toolbar.setOrientation(SwingConstants.VERTICAL);
		     
		       
		       
		      
		       
		       
		       
		      // add(new Backpanel(parent));
		       
///////////////////////////////////////BUTTON ADD////////////////////////////////////////////////////////////////////////
		  
		      
		       
		       btnadd.addActionListener((new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
	       
		      
					 panel=new JPanel();
					 
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
							JOptionPane.showMessageDialog(panel,"details cannot be empty", "WARNING", JOptionPane.WARNING_MESSAGE);
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
					
						
						JOptionPane.showMessageDialog(panel,"student data added succesfully", "success", JOptionPane.INFORMATION_MESSAGE);

					}	
				
					}catch(NumberFormatException nfe){
						getToolkit().beep();
						JOptionPane.showMessageDialog(panel,"invaloid data", "error", JOptionPane.ERROR_MESSAGE);
					}catch(SMSException smse){
						getToolkit().beep();
						JOptionPane.showMessageDialog(panel, smse.getInfo(), "error", JOptionPane.ERROR_MESSAGE);
					
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
		      
		     
		        panel.add(lblbook_isbn);
			    panel.add(txtbook_isbn);
			    panel.add(lbl_title);
			    panel.add(txt_title);
			    panel.add(lblCategory);
			    panel.add(txtCategory);
			    panel.add(lblno_of_book);
			    panel.add(txtno_of_book);
			    panel.add(lblauthor_name);
			    panel.add(txtauthor_name);
			    panel.add(lblauthor_mailid);
			    panel.add(txtauthormail_id);
			    panel.add(btnok);
			    panel.add(btnback);
			    add(panel);
			   
		      setVisible(true);
		
		     
				}
				}));
		       
		       
		      
	////////////////////////////////////////BUTTON SEARCH ON TITLE////////////////////////////////////////////////
		       
		       
		       btnsertitle=new JButton("SEARCH ON TITLE");
		       
		       btnsearch=new JButton("SEARCH");
		      
		       panel1=new JPanel();
		       
		      
		
		       lblsearchtitle=new JLabel("ENTER THE TITLE");      
		 txtsearchtitle=new JTextField(20);    
		 
		
		 
		 
		
		 
		 
		 
		 btnsertitle.addActionListener(new ActionListener() {
	
	    	
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if(panel!=null)
				{
					remove(panel);
					
					revalidate();
				 	repaint();
				}
				
				else{
					add(panel1);
					revalidate();
				 	repaint();
				}
			 	
			 	
		    	   
		
		 
		 btnsearch.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				
				
		//////////////////////////////SEARCH BUTTON///////////////////////////////
				
				
				
			
				String sertitle=txtsearchtitle.getText().trim();
				try
				{
					if(sertitle.equals(""))
						
					{
						JOptionPane.showMessageDialog(panel1,"PLease enter the Title", "WARNING", JOptionPane.WARNING_MESSAGE);
						return;
					} 
				/*	String author_name=null;
					String author_mailid=null;
					Author aut=new Author(author_name, author_mailid);
					
					String book_isbn=null;
					//String title;
					String Category=null;
					String no_of_books=null;
					Book b=new Book(Integer.parseInt(book_isbn), sertitle, Category, Integer.parseInt(no_of_books), aut);
					
					*/
					 Vector<Vector<String>> list=dao.getBooktitle(sertitle);
					
				if(dao.getBooktitle(sertitle) != null)
				{ 
					  
						txt_title.setText("");
						table.setModel(new DefaultTableModel(dao.getBooktitle(sertitle),colNames));
						
						JOptionPane.showMessageDialog(panel1, "title found","success",JOptionPane.INFORMATION_MESSAGE);
					}
				else
				{
					txt_title.setText("");
					JOptionPane.showMessageDialog(panel1, "title not found","error",JOptionPane.INFORMATION_MESSAGE);
				}
				
				
				}catch(NumberFormatException nfe){
					getToolkit().beep();
					JOptionPane.showMessageDialog(panel1,"invaloid data", "error", JOptionPane.ERROR_MESSAGE);
				}catch(SMSException smse){
					getToolkit().beep();
					JOptionPane.showMessageDialog(panel1, smse.getInfo(), "error", JOptionPane.ERROR_MESSAGE);
				
				
				
				}
				
				
			}
		});
		 btnback.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					System.exit(ABORT); ;
				}
			});
		       
		 	panel1.add(lblsearchtitle);
			panel1.add(txtsearchtitle);
			panel1.add(scrollpane);
			panel1.add(btnsearch);
			panel1.add(btnback);
			
			add(panel1);
		    setVisible(true);   
			}
	
			
			});     
		       

		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
			         
		    toolbar.add(btnadd);
		    toolbar.add(btnsertitle);
		    
		    
		  
		     add(toolbar,BorderLayout.WEST);
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