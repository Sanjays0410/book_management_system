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

import com.cruds.db.IssueDAO;
import com.cruds.exception.SMSException;

public class IssueViewPanel extends JPanel {

	JFrame parent;
	JPanel Panel;
	JButton btnback,btnok;
	JLabel lbl_stdUSN;
	JTextField txt_StudUSN;
	JTable table;
	JScrollPane scrollpane;

	Vector<String> colNames=new Vector<>();

	String serstudUSN;

	public IssueViewPanel(JFrame parent) {
		this.parent=parent;
		Panel=this;

		IssueDAO  dao=new IssueDAO();
		serstudUSN=null;

		lbl_stdUSN= new JLabel("ENTER THE USN");
		txt_StudUSN= new JTextField(10);

		colNames.add("Book_ISBN");
		colNames.add("Book_title");
		colNames.add("Student_USN");
		colNames.add("Issued_Date");
		colNames.add("Return_Date");


		table=new JTable(new DefaultTableModel(null,colNames));

		scrollpane=new JScrollPane(table);



		btnok=new JButton("SUBMIT");

		btnok.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				serstudUSN =txt_StudUSN.getText().trim();

				Vector<Vector<String>> data=dao.Issuebook(serstudUSN);
				try
				{
					if(serstudUSN.equals(""))

					{
						JOptionPane.showMessageDialog(Panel,"PLease enter the USN", "WARNING", JOptionPane.WARNING_MESSAGE);
						return;
					} 


					if(data.size()>0) 
					{ 

						txt_StudUSN.setText("");

						JOptionPane.showMessageDialog(Panel, "Details found :)","SUCCESS",JOptionPane.INFORMATION_MESSAGE);

						table.setModel(new DefaultTableModel(data,colNames));
						return;
					}

					else
					{ 
						txt_StudUSN.setText("");
						JOptionPane.showMessageDialog(Panel, "Details not found","ERROR",JOptionPane.ERROR_MESSAGE);

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
			public void actionPerformed(ActionEvent e) {

				parent.remove(Panel);
				parent.add(new ToolbarPanel(parent));
				parent.revalidate();


			}
		});


		Panel.add(lbl_stdUSN);
		Panel.add(txt_StudUSN);
		Panel.add(btnok);
		Panel.add(scrollpane);
		Panel.add(btnback);	

	}
}
