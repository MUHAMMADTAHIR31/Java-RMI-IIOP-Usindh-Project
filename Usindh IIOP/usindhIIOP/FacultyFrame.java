/**
 *
 * @author Muhammad Tahir
 */

import javax.swing.*;
import javax.swing.event.*; 
import java.awt.*;
import java.awt.event.*;
import java.rmi.Naming;
import java.rmi.*;

class FacultyFrame extends JFrame implements ActionListener , ListSelectionListener {
	
	
	JLabel facultyLabel=new JLabel("FACULTY");
	JLabel facIdLabel=new JLabel("Faculty ID");
	JLabel facNameLabel=new JLabel("Faculty Name");
	JLabel remarksLabel=new JLabel("Remarks");
	JLabel FacultyListLabel=new JLabel("Faculty List");
	
	JTextField facIdTextField=new JTextField();
	JTextField facultyNameTextField=new JTextField();
	JTextArea remarksTextArea=new JTextArea();
	JList facultyList=new JList();
	
	JButton addButton=new JButton("ADD");
	JButton updateButton=new JButton("UPDATE");
	JButton clearButton=new JButton("CLEAR");
	JButton backButton=new JButton("BACK");
	JButton deleteButton=new JButton("DELETE");

	Font headerFont=new Font("Times New Roman",Font.BOLD,50);
	Font labelFont=new Font("arial",Font.BOLD,15);
	
	DBManager DatabaseManager;
	
	FacultyFrame(DBManager DatabaseManager)throws Exception{
		this.DatabaseManager= DatabaseManager; 
		
		
		Container con=this.getContentPane();
		con.setLayout(null);
	
		setBounds(200,80,1300,700);	
		facultyLabel.setBounds(460,-120,500,400);
	
		facIdLabel.setBounds(123,-10,200,350);
		facNameLabel.setBounds(100,25,200,350);
		remarksLabel.setBounds(131,60,200,350);
		FacultyListLabel.setBounds(724,153,80,25);
	
		facIdTextField.setBounds(210,153,500,25);
		facultyNameTextField.setBounds(210,188,500,25);
		remarksTextArea.setBounds(210,223,500,250);
		facultyList.setBounds(720,188,450,285);
		
		addButton.setBounds(250,500,80,25);
		updateButton.setBounds(350,500,80,25);
		clearButton.setBounds(450,500,80,25);
		backButton.setBounds(550,500,80,25);
		deleteButton.setBounds(1085,500,80,25);
		
		con.add(facultyLabel);
		con.add(facIdLabel);
		con.add(facNameLabel);
		con.add(remarksLabel);
		con.add(FacultyListLabel);
		con.add(facIdTextField);
		con.add(facultyNameTextField);
		con.add(remarksTextArea);
		con.add(facultyList);
		con.add(addButton);
		con.add(updateButton);
		con.add(clearButton);
		con.add(backButton);
		con.add(deleteButton);
		
		facultyLabel.setFont(headerFont);
		facIdLabel.setFont(labelFont);
		facNameLabel.setFont(labelFont);
		remarksLabel.setFont(labelFont);
		FacultyListLabel.setFont(labelFont);
		
		facultyList.addListSelectionListener(this);
		
		addButton.addActionListener(this);
		deleteButton.addActionListener(this);
		updateButton.addActionListener(this);
		clearButton.addActionListener(this);
		
		getFaculty();	
	}
	
	public void actionPerformed(ActionEvent e){

		if(e.getSource()==addButton){
			addFaculty();
		}
		
		else if(e.getSource()==updateButton){
			updateFaculty();
		}
		
		else if(e.getSource()==deleteButton){
			deletefaculty();
		}
		
		else if(e.getSource()==clearButton){
			clear();
		}
		
	}
	
	public void valueChanged(ListSelectionEvent e){ 
		FacultyBean bean=(FacultyBean)facultyList.getSelectedValue();
        if(bean==null)return;
        
        facIdTextField.setText(""+bean.getFac_id());
        facultyNameTextField.setText(""+bean.getFac_name());
        remarksTextArea.setText(""+bean.getRemarks());
    } 
	
    private void getFaculty(){
     try{
			
			java.util.Vector v = DatabaseManager.getFaculty();
			facultyList.setListData(v);
        }catch(Exception e){
			e.printStackTrace();
			javax.swing.JOptionPane.showMessageDialog(this,"Error: "+e.getMessage());
		}
    }//end Method
	
	private void deletefaculty(){
		
		try{
        
            int[] size = facultyList.getSelectedIndices();
			facultyList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
            
            int rows=0;
          
            for(int i=0; i<size.length; i++){
				FacultyBean bean=(FacultyBean)facultyList.getModel().getElementAt(size[i]);
				if(bean==null)continue;
              
				rows+=DatabaseManager.deleteFaculty(bean.getFac_id());
			}//end for
          
			if(rows>=1){
                JOptionPane.showMessageDialog(this,rows+" record Deleted");
                getFaculty(); clear();
            }
        }catch(Exception ee){
            ee.printStackTrace();
            JOptionPane.showMessageDialog(this,"Error: "+ee.getMessage());                 
        }
      }//end method
    
    private void  clear(){
        facIdTextField.setText("");
        facultyNameTextField.setText("");
        remarksTextArea.setText("");
    }
    
	private void updateFaculty(){
		
        FacultyBean bean=(FacultyBean)facultyList.getSelectedValue();
		if(bean==null)return;
       
        String facname=facultyNameTextField.getText();
        String remarks=remarksTextArea.getText();
       
        try{
				int rows=DatabaseManager.updateFaculty(bean.getFac_id(),facname,remarks);
				if(rows>=1){
                    JOptionPane.showMessageDialog(this,rows+" Record Modified");
                    getFaculty();
                    clear();
				}
            }catch(Exception e){
            JOptionPane.showMessageDialog(this,"Error: "+e.getMessage()); 
        }
    }

	private void addFaculty(){
		
        String facname=facultyNameTextField.getText();
        String remarks=remarksTextArea.getText();
       
        try{
            int rows=DatabaseManager.addFaculty(facname,remarks);
            if(rows>=1){
                    JOptionPane.showMessageDialog(this,rows+" Record Added");
                    getFaculty();
                    clear();
                }
            }catch(Exception e){
            JOptionPane.showMessageDialog(this,"Error: "+e.getMessage()); 
        }   
    }

}
//faculty end







