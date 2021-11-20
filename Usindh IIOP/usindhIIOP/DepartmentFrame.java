import java.util.Vector;
import javax.swing.*;
import java.awt.*;
import javax.swing.event.*;
import java.awt.event.*;
import java.rmi.Naming;
import java.rmi.*;

/**
 *
 * @author MOHAMMAD TAHIR
 */
 
public class DepartmentFrame extends javax.swing.JFrame implements ActionListener, ListSelectionListener {

 	JLabel departmentLabel=new JLabel("DEPARTMENT");
	JLabel facultyLabel=new JLabel("FACULTY");		
	JLabel deptIdLabel=new JLabel("DEPARTMENT ID");
	JLabel deptNameLabel=new JLabel("DEPARTMENT NAME");
	JLabel remarksLabel=new JLabel("REMARKS");
	JLabel deptListLabel=new JLabel("DEPARTMENT LIST");
	
	JComboBox facultyComboBox=new JComboBox();
	JTextField deptIdTextField=new JTextField();
	JTextField departmentNameTextFiled=new JTextField();
	JTextArea remarksTextArea=new JTextArea();
	JList departmentList=new JList();
	
	JButton addButton=new JButton("ADD");
	JButton updateButton=new JButton("UPDATE");
	JButton clearButton=new JButton("CLEAR");
	JButton backButton=new JButton("BACK");
	JButton deleteButton=new JButton("DELETE");
	
	Font headerFont=new Font("Times New Roman",Font.BOLD,50);
	Font labelFont=new Font("arial",Font.BOLD,15);
	
	
	DBManager DataBaseManager;
	
    public DepartmentFrame(DBManager DataBaseManager)throws Exception{
		
		this.DataBaseManager=DataBaseManager;
        
		Container con=this.getContentPane();
		con.setLayout(null);
		
		setBounds(200,70,1300,700);
		departmentLabel.setBounds(443,-100,500,400);
		
		facultyLabel.setBounds(113,5,200,350);
		deptIdLabel.setBounds(60,40,200,350);
		deptNameLabel.setBounds(34,75,200,350);
		remarksLabel.setBounds(110,107,200,350);
		
		facultyComboBox.setBounds(204,168,450,25);		
		deptIdTextField.setBounds(204,202,200,25);
		departmentNameTextFiled.setBounds(204,238,500,25);
		remarksTextArea.setBounds(204,272,500,250);
		
		
		deptListLabel.setBounds(727,203,200,25);
		departmentList.setBounds(725,238,452,283);
		
		addButton.setBounds(250,540,80,25);
		updateButton.setBounds(350,540,80,25);
		clearButton.setBounds(450,540,80,25);
		backButton.setBounds(550,540,80,25);
		deleteButton.setBounds(1094,538,80,25);
		
		con.add(departmentLabel);
		con.add(facultyLabel);
		con.add(deptIdLabel);
		con.add(deptNameLabel);
		con.add(remarksLabel);
		con.add(facultyComboBox);
		con.add(deptIdTextField);
		con.add(departmentNameTextFiled);
		con.add(remarksTextArea);
		con.add(deptListLabel);
		con.add(departmentList);
		con.add(addButton);
		con.add(updateButton);
		con.add(clearButton);
		con.add(backButton);
		con.add(deleteButton);
		
		departmentLabel.setFont(headerFont);
		facultyLabel.setFont(labelFont);
		deptIdLabel.setFont(labelFont);
		deptNameLabel.setFont(labelFont);
		remarksLabel.setFont(labelFont);
		deptListLabel.setFont(labelFont);
		
		departmentList.addListSelectionListener(this);
		
		facultyComboBox.addActionListener(this);
		addButton.addActionListener(this);
		deleteButton.addActionListener(this);
		updateButton.addActionListener(this);
		clearButton.addActionListener(this);
		
		getFaculty();	
	}
	
	public void actionPerformed(ActionEvent e){

		if(e.getSource()==addButton){
			addDepartment();
		}
		
		else if(e.getSource()==updateButton){
			updateDepartment();
		}
		
		else if(e.getSource()==deleteButton){
			deleteDepartment();
		}
		
		else if(e.getSource()==clearButton){
			clear();
		}
		
		else if(e.getSource()==facultyComboBox){
			getDepartment();
		}
		
	}
	
    private void getFaculty(){
        try{
			
            Vector v=DataBaseManager.getFaculty();
            facultyComboBox.removeAllItems();
               
            for(int i=0; i<v.size(); i++)
                
            facultyComboBox.addItem(v.elementAt(i));
            
        }catch(Exception e)
        {
            e.printStackTrace();
            
           JOptionPane.showMessageDialog(this,"Error"+e);
        }
    }
    
	private void getDepartment(){
       
        FacultyBean bean=(FacultyBean)facultyComboBox.getSelectedItem();
        if(bean==null)return;
        
        try{
			Vector v=DataBaseManager.getDepartment(bean.getFac_id());
			departmentList.setListData(v);
         
		}catch(Exception e){
			e.printStackTrace();
			JOptionPane.showMessageDialog(this,"Error in Department"+e);
		}
    }
	
    private void deleteDepartment(){
        
      try{
            int[] size = departmentList.getSelectedIndices();
			departmentList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
            
			int rows=0;
          
            for(int i=0; i<size.length; i++){
				DepartmentBean bean=(DepartmentBean)departmentList.getModel().getElementAt(size[i]);
				if(bean==null)continue;
              
				rows+=DataBaseManager.deleteDepartment(bean.getDept_id());
			}//end for
          
			if(rows>=1){
				JOptionPane.showMessageDialog(this,rows+" record Deleted");
                getDepartment(); clear();
            }
        }catch(Exception ee){
            ee.printStackTrace();
            JOptionPane.showMessageDialog(this,"Error: "+ee.getMessage());                 
        }
    }
    
    private void clear(){
        facultyComboBox.setSelectedIndex(0);
        deptIdTextField.setText("");
        departmentNameTextFiled.setText("");
        remarksTextArea.setText("");
    }
	
	public void valueChanged(ListSelectionEvent e){                                              
      
        DepartmentBean bean=(DepartmentBean)departmentList.getSelectedValue();
        if(bean==null)return;
        
        deptIdTextField.setText(""+bean.getDept_id());
		departmentNameTextFiled.setText(""+bean.getDept_name());
        remarksTextArea.setText(""+bean.getRemarks());
    }
    
    private void updateDepartment(){
        
		DepartmentBean bean=(DepartmentBean)departmentList.getSelectedValue();
        if(bean==null)return;
       
        String deptName=departmentNameTextFiled.getText();
        String remarks=remarksTextArea.getText();
          
        try{
                int rows=DataBaseManager.updateDepartment(bean.getDept_id(),bean.getFac_id(),deptName,remarks);
                if(rows>=1)
                {
                    JOptionPane.showMessageDialog(this,rows+" Record Modified");
                    getDepartment();
                    clear();
                }
            }catch(Exception e)
            {
                e.printStackTrace();
                 JOptionPane.showMessageDialog(this,"Error: "+e.getMessage());
            }
            
    }
  
	private void addDepartment(){
		FacultyBean bean=(FacultyBean)facultyComboBox.getSelectedItem();
        if(bean==null)return;
       
        String deptName=departmentNameTextFiled.getText();
        String remarks=remarksTextArea.getText();
        
        try{
                int rows=DataBaseManager.addDepartment(bean.getFac_id(),deptName,remarks);
                if(rows>=1)
                {
                    JOptionPane.showMessageDialog(this,rows+" Record Modified");
                    getDepartment();
                    clear();
                }
            }catch(Exception e){
			JOptionPane.showMessageDialog(this,"Error: "+e.getMessage());
            e.printStackTrace();
        }
    }

}
