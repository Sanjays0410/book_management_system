package com.cruds.MainUI;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.cruds.db.BookDAO;


public class ListAll extends JPanel {

	JFrame parent;
	JPanel PanelList;

	JTable table;
	JScrollPane scrollpane;

	JButton btnback;

	Vector<String> colNames=new Vector<>();

	public ListAll(JFrame parent) {

		this.parent=parent;
		PanelList=this;



		colNames.add("Book_isbn");
		colNames.add("Book_title");
		colNames.add("book_category"); 
		colNames.add("book_no_of_books");
		colNames.add("Author_name");
		colNames.add("Author_email_id");

		BookDAO dao=new BookDAO();

		table=new JTable(new DefaultTableModel(dao.getAllbook(), colNames));

		//table.setModel(new DefaultTableModel(dao.getAllbook(),colNames));

		scrollpane=new JScrollPane(table);

		btnback=new JButton("BACK");

		btnback.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				parent.remove(PanelList);
				parent.add(new ToolbarPanel(parent));
				parent.revalidate();
			}

		});
		PanelList.add(scrollpane);
		PanelList.add(btnback);


	}

}
