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

import com.cruds.db.BookDAO;
import com.cruds.exception.SMSException;

public class SerAuthorpanel extends JPanel {

	JFrame parent;
	JPanel Panel;

	JButton btnsearch,btnback;
	JLabel lblAuthor;
	JTextField txtAuthor;
	JTable table;
	JScrollPane scrollpane;

	Vector<String> colNames=new Vector<>();

	public SerAuthorpanel(JFrame parent) {
		this.parent=parent;
		Panel=this;

		btnsearch=new JButton("SEARCH");

		lblAuthor=new JLabel("ENTER THE AUTHOR:");
		txtAuthor=new JTextField(20);

		BookDAO  dao=new BookDAO();
		String serauthor=null;
		//String serauthor=null;


		colNames.add("Book_ISBN");
		colNames.add("Book_Title");
		colNames.add("Book_Category");
		colNames.add("No of books");
		colNames.add("Author_Name");
		colNames.add("Author_Mailid");


		table=new JTable(new DefaultTableModel(dao.getBookAuthor(serauthor),colNames));

		scrollpane=new JScrollPane(table);


		btnsearch.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				String serauthor=txtAuthor.getText().trim();
				Vector<Vector<String>> data=dao.getBookAuthor(serauthor);
				try
				{
					if(serauthor.equals(""))

					{
						JOptionPane.showMessageDialog(Panel,"PLease enter the Author", "WARNING", JOptionPane.WARNING_MESSAGE);
						return;
					} 



					if(data.size()>0) 
					{ 

						txtAuthor.setText("");

						JOptionPane.showMessageDialog(Panel, "Author found","SUCCESS",JOptionPane.INFORMATION_MESSAGE);

						table.setModel(new DefaultTableModel(data,colNames));


					}

					else
					{
						txtAuthor.setText("");
						JOptionPane.showMessageDialog(Panel, "Author  not found","ERROR",JOptionPane.ERROR_MESSAGE);

					}
				}catch(NumberFormatException nfe){
					getToolkit().beep();
					JOptionPane.showMessageDialog(Panel,"invaloid data", "error", JOptionPane.ERROR_MESSAGE);
				}catch(SMSException smse){
					getToolkit().beep();
					JOptionPane.showMessageDialog(Panel, smse.getInfo(), "error", JOptionPane.ERROR_MESSAGE);

				}

			}
		});


		btnback=new JButton("BACK");

		btnback.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				parent.remove(Panel);
				parent.add(new ToolbarPanel(parent));
				parent.revalidate();


			}
		});

		Panel.add(lblAuthor);
		Panel.add(txtAuthor);
		Panel.add(btnsearch);
		Panel.add(scrollpane);

		Panel.add(btnback);
	}
}
