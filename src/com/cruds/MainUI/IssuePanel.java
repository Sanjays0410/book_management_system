package com.cruds.MainUI;
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

import com.cruds.dateutil.Dateutil;
import com.cruds.db.BookDAO;
import com.cruds.db.IssueDAO;
import com.cruds.db.StudentDAO;
import com.cruds.demo.Issue;
import com.cruds.exception.SMSException;

public class IssuePanel extends JPanel {

	JFrame parent;
	JPanel Panel;
	JLabel lbl_stdUSN,lbltitle;
	JTextField txt_StudUSN,txttitle;
	JButton  btnok,btnback,btnsearch,btnbookissue;

	JTable table,tabletitle;
	JScrollPane scrollpane,scrollpanetitle;

	String sertitle,serstudUSN;

	Vector<String> colNames=new Vector<>();

	Vector<String> colNamestitle=new Vector<>();





	public IssuePanel(JFrame parent) {
		this.parent=parent;
		Panel=this;

		IssueDAO  dao=new IssueDAO();
		StudentDAO daos=new StudentDAO();
		serstudUSN=null;

		lbl_stdUSN= new JLabel("ENTER USN:");
		txt_StudUSN= new JTextField(10);

		colNames.add("Student_USN");
		colNames.add("Student_Name");



		table=new JTable(new DefaultTableModel(daos.getStudent(serstudUSN),colNames));

		scrollpane=new JScrollPane(table);



		btnok=new JButton("SEARCH");

		btnok.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				serstudUSN =txt_StudUSN.getText().trim();

				Vector<Vector<String>> data=daos.getStudent(serstudUSN);
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

						//JOptionPane.showMessageDialog(Panel, "Student found :)","SUCCESS",JOptionPane.INFORMATION_MESSAGE);

						table.setModel(new DefaultTableModel(data,colNames));


					}

					else
					{ 
						txt_StudUSN.setText("");
						JOptionPane.showMessageDialog(Panel, "Student not found","ERROR",JOptionPane.ERROR_MESSAGE);

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

		btnsearch=new JButton("SEARCH");
		lbltitle=new JLabel("ENTER TITLE:");
		txttitle=new JTextField(10);


		BookDAO  dao1=new BookDAO();	 
		sertitle=null;


		colNamestitle.add("Book_ISBN");
		colNamestitle.add("Book_Title");
		colNamestitle.add("Book_Category");
		colNamestitle.add("No of books");


		tabletitle=new JTable(new DefaultTableModel(dao1.getBooktitle(sertitle),colNamestitle));

		scrollpanetitle=new JScrollPane(tabletitle);


		btnsearch.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {


				sertitle=txttitle.getText().trim();
				Vector<Vector<String>> data=dao1.getBooktitle(sertitle);

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

						//JOptionPane.showMessageDialog(Panel, "title found","SUCCESS",JOptionPane.INFORMATION_MESSAGE);

						tabletitle.setModel(new DefaultTableModel(data,colNamestitle));


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

		btnbookissue=new JButton("ISSUE BOOK");

		btnbookissue.addActionListener(new ActionListener() {


			@Override
			public void actionPerformed(ActionEvent e) {

				int index=table.getSelectedRow();
				int index1=tabletitle.getSelectedRow();


				if( sertitle==null||serstudUSN==null || table.getSelectedRow()<0 || tabletitle.getSelectedRow()<0)

				{
					JOptionPane.showMessageDialog(Panel,"PLease enter and select student and book", "WARNING", JOptionPane.WARNING_MESSAGE);
					return;
				} 




				String selUSN=(String)table.getModel().getValueAt(index, 0);
				String selbookisbn=(String)tabletitle.getModel().getValueAt(index1,0);


				if(dao.updatebook(Integer.parseInt(selbookisbn))>0)
				{
					IssueDAO dao=new IssueDAO();

					Issue issue=new Issue(Dateutil.getCurrentDate(), Dateutil.addToCurrentDate(7), selUSN,Integer.parseInt(selbookisbn));
					int issueid=dao.create(issue);

					if(issueid>0)
					{

						table.setModel(new DefaultTableModel(null,colNamestitle));
						tabletitle.setModel(new DefaultTableModel(null,colNamestitle));
						JOptionPane.showMessageDialog(Panel, " Issue_id= " +issueid+ " Book issued","SUCCESS",JOptionPane.INFORMATION_MESSAGE);

					}
				}
				else
				{
					JOptionPane.showMessageDialog(Panel,"Book unavailable", "ERROR",JOptionPane.ERROR_MESSAGE);

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


		Panel.add(lbl_stdUSN);
		Panel.add(txt_StudUSN);
		Panel.add(btnok);

		Panel.add(lbltitle);
		Panel.add(txttitle);
		Panel.add(btnsearch);
		Panel.add(btnback);
		Panel.add(scrollpane);
		Panel.add(scrollpanetitle);
		Panel.add(btnbookissue);
	}
}
