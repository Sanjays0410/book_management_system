package com.cruds.MainUI;

import java.awt.BorderLayout;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.cruds.db.BookDAO;
import com.cruds.demo.Author;
import com.cruds.demo.Book;
import com.cruds.exception.SMSException;

public class Addpanel extends JPanel {

	JFrame parent;
	JPanel Panel;


	JTextField txtbook_isbn, txt_title,  txtno_of_book, txtauthor_name, txtauthormail_id;
	JLabel lblbook_isbn, lbl_title, lblcategory, lblno_of_book, lblauthor_name, lblauthor_mailid;

	JButton btnadd,btnok,btnback;

	final String[] CategoryId = {"1","2","3","4"};
	final String[] CategoryNames = {"Technical","Motivation","Lab Manual","Theory"};

	JComboBox<String> Categorycombo=new JComboBox<>(CategoryNames);

	//private JLabel lblDept = null;

	public Addpanel( JFrame parent) {
		this.parent=parent;
		Panel=this;




		//Panel.B);
		lblbook_isbn=new JLabel("BOOK_ISBN:");
		lbl_title=new JLabel("BOOK_TITLE:");
		//lblcategory=new JLabel("BOOK_CATEGORY:");
		lblno_of_book=new JLabel("NO_OF_BOOKS:");
		lblauthor_name=new JLabel("AUTHOR_NAME:");
		lblauthor_mailid=new JLabel("AUTHOR_MAIL_ID:");


		txtbook_isbn=new JTextField(6);
		txt_title=new JTextField(6);
		//txtCategory=new JTextField(10);
		txtno_of_book=new JTextField(6);
		txtauthor_name=new JTextField(6);
		txtauthormail_id=new JTextField(6);



		lblcategory = new JLabel("BOOK_CATEGORY:");
		//lblcategory.setPreferredSize(new Dimension(100, 127));
		//lblcategory.setMaximumSize(new Dimension(100, 127));


		Categorycombo.setSelectedIndex(-1);
		Categorycombo.setPreferredSize(new Dimension(140, 22));
		Categorycombo.setMaximumSize(new Dimension(140, 22));

		/*	Categorycombo.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == ItemEvent.SELECTED)
				{
				int index = Categorycombo.getSelectedIndex();
				String CategoryName = CategoryNames[index];
				//lblcategory.setText(CategoryName);
				}
			}
		});

		 */

		btnok=new JButton("SUBMIT");
		btnback=new JButton("BACK");

		BookDAO dao=new BookDAO();

		btnok.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {



				Categorycombo.addItemListener(new ItemListener() {

					@Override
					public void itemStateChanged(ItemEvent e) {
						if(e.getStateChange() == ItemEvent.SELECTED)
						{
							int index = Categorycombo.getSelectedIndex();
							String CategoryName = CategoryNames[index];
							//lblcategory.setText(CategoryName);
						}
					}
				});

				String book_isbn=txtbook_isbn.getText().trim();
				String title=txt_title.getText().trim();
				//String CategoryNames=Categorycombo.getName().trim();
				String no_of_books=txtno_of_book.getText().trim();
				String author_name=txtauthor_name.getText().trim();
				String author_mailid=txtauthormail_id.getText().trim();



				try
				{
					if(book_isbn.equals("") ||title.equals("") ||  no_of_books.equals("") || author_name.equals("")|| author_mailid.equals(""))

					{
						JOptionPane.showMessageDialog(Panel,"details cannot be empty", "WARNING", JOptionPane.WARNING_MESSAGE);
						return;
					} 

					Author aut=new Author(author_name, author_mailid);

					int index = Categorycombo.getSelectedIndex();
					String Categorycombo = CategoryNames[index];;
					Book b=new Book(Integer.parseInt(book_isbn), title, Categorycombo, Integer.parseInt(no_of_books), aut);

					if(dao.Create(b))
					{
						//help to enter nxt data////
						txtbook_isbn.setText("");
						txt_title.setText("");
						//txtCategory.setText("");
						txtno_of_book.setText("");
						txtauthor_name.setText("");
						txtauthormail_id.setText("");


						JOptionPane.showMessageDialog(Panel,"Book data added succesfully :)", "success", JOptionPane.INFORMATION_MESSAGE);

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

		btnback.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent ae) {

				parent.remove(Panel);
				parent.add(new ToolbarPanel(parent));
				parent.revalidate();


			}
		});

		Panel.add(lblbook_isbn);
		Panel.add(txtbook_isbn);
		Panel.add(lbl_title);
		Panel.add(txt_title);
		Panel.add(lblcategory);
		Panel.add(Categorycombo);
		Panel.add(lblno_of_book);
		Panel.add(txtno_of_book);
		Panel.add(lblauthor_name);
		Panel.add(txtauthor_name);
		Panel.add(lblauthor_mailid);
		Panel.add(txtauthormail_id);  

		Panel.add(btnok);
		Panel.add(btnback);


		//setVisible(true);
	}
}
