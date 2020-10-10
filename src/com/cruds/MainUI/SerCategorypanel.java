package com.cruds.MainUI;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
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

public class SerCategorypanel extends JPanel {

	JFrame parent;
	JPanel Panel;
	JButton btnsearch,btnback;
	JLabel lblcategory;
	JTextField txtCategory;
	JTable table;
	JScrollPane scrollpane;

	Vector<String> colNames=new Vector<>();
	final String[] Categoryid={"1","2","3","4"};
	final String[] CategoryNames = {"Technical","Motivation","Lab Manual","Theory"};
	private JComboBox<String> Categorycombo=new JComboBox<String>(CategoryNames);
	String sercategory;

	public SerCategorypanel(JFrame parent) {

		this.parent=parent;
		Panel=this;


		BookDAO  dao=new BookDAO();
		


		btnsearch=new JButton("SEARCH");

		lblcategory=new JLabel("SELECT CATEGORY:");
		//txtCategory=new JTextField(20);

		//lblcategory = new JLabel("ENTER THE CATEGORY");
		//lblcategory.setPreferredSize(new Dimension(100, 127));
		//lblcategory.setMaximumSize(new Dimension(100, 127));

		Categorycombo.setSelectedIndex(-1);
		Categorycombo.setPreferredSize(new Dimension(140, 22));
		Categorycombo.setMaximumSize(new Dimension(140, 22));


		colNames.add("Book_ISBN");
		colNames.add("Book_Title");
		colNames.add("Book_Category");
		colNames.add("No of books");


		table=new JTable(new DefaultTableModel(dao.getBookCategory(sercategory),colNames));

		scrollpane=new JScrollPane(table);

		btnback=new JButton("BACK");

		//String sercategory=Categorycombo.getSelectedItem().toString();

		


		Categorycombo.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == ItemEvent.SELECTED)
				{
					int index = Categorycombo.getSelectedIndex();
					sercategory = CategoryNames[index];
					//lblcategory.setText(Names);
				}
			}

		});

		btnsearch.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent ar) {

				


				try
				{
					if(sercategory==null)
					{
						JOptionPane.showMessageDialog(Panel,"PLease select the category", "WARNING", JOptionPane.WARNING_MESSAGE);
						return;
					} 

					Vector<Vector<String>> data=dao.getBookCategory(sercategory);
					if(data.size() > 0)
					{ 


						JOptionPane.showMessageDialog(Panel, "Category found","SUCCESS",JOptionPane.INFORMATION_MESSAGE);

						table.setModel(new DefaultTableModel(data,colNames));

					}

					else
					{

						JOptionPane.showMessageDialog(Panel, "Category  not found","ERROR",JOptionPane.ERROR_MESSAGE);

					}

				}catch(NumberFormatException nfe){
					getToolkit().beep();
					JOptionPane.showMessageDialog(Panel,"invalid data", "error", JOptionPane.ERROR_MESSAGE);

				}catch(SMSException smse){
					getToolkit().beep();
					JOptionPane.showMessageDialog(Panel, smse.getInfo(), "error", JOptionPane.ERROR_MESSAGE);

				}

			}
		});

		btnback.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				parent.remove(Panel);
				parent.add(new ToolbarPanel(parent));
				parent.revalidate();


			}
		});


		Panel.add(lblcategory);
		//Panel.add(lblcategory);
		Panel.add(Categorycombo);
		Panel.add(btnsearch);
		Panel.add(scrollpane);
		Panel.add(btnback);
	}
}