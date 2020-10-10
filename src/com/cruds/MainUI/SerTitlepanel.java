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

public class SerTitlepanel extends JPanel {

	JFrame parent;
	JPanel Panel;

	JTable table;
	JScrollPane scrollpane;
	JButton btnserach,btnback;
	JLabel lbltitle;
	JTextField txttitle;

	Vector<String> colNames=new Vector<>();



	public SerTitlepanel(JFrame parent) {

		this.parent=parent;
		Panel=this;

		btnserach=new JButton("SEARCH");
		lbltitle=new JLabel("ENTER THE TITLE:");
		txttitle=new JTextField(20);


		BookDAO  dao=new BookDAO();	 
		String sertitle=null;


		colNames.add("Book_ISBN");
		colNames.add("Book_Title");
		colNames.add("Book_Category");
		colNames.add("No of books");


		table=new JTable(new DefaultTableModel(dao.getBooktitle(sertitle),colNames));

		scrollpane=new JScrollPane(table);


		btnserach.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {


				String sertitle=txttitle.getText().trim();
				Vector<Vector<String>> data=dao.getBooktitle(sertitle);
				try
				{
					if(sertitle.equals(""))

					{
						JOptionPane.showMessageDialog(Panel,"PLease enter the Title", "WARNING", JOptionPane.WARNING_MESSAGE);
						return;
					} 



					if(data.size()>0)
					{ 

						txttitle.setText("");

						JOptionPane.showMessageDialog(Panel, "title found","SUCCESS",JOptionPane.INFORMATION_MESSAGE);

						table.setModel(new DefaultTableModel(data,colNames));


						/////////add new panel for table/////////////////////





					}

					else
					{
						txttitle.setText("");
						JOptionPane.showMessageDialog(Panel, "title  not found","ERROR",JOptionPane.ERROR_MESSAGE);

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


		Panel.add(lbltitle);
		Panel.add(txttitle);
		Panel.add(btnserach);
		Panel.add(scrollpane);

		Panel.add(btnback);



	}
}
