import java.util.Vector;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.rmi.Naming;
import java.rmi.*;

/**
 *
 * @author MOHAMMAD TAHIR
 */
public class ProgramFrame extends javax.swing.JFrame implements ActionListener,ListSelectionListener{

	JLabel programLabel=new JLabel("PROGRAM");
	JLabel facultyLabel=new JLabel("FACULTY");
	JLabel deptLabel=new JLabel("DEPARTMENT");
	JLabel progIdLabel=new JLabel("PROGRAM ID");
	JLabel progNameLabel=new JLabel("PROGRAM NAME");
	JLabel durationLabel=new JLabel("DURATION");
	JLabel inSemesterLabel=new JLabel("IN SEMESTER");
	JLabel remarksLabel=new JLabel("REMARKS");
	JLabel programListLabel=new JLabel("PROGRAM LIST");
	
	JComboBox facultyComboBox=new JComboBox();
	JComboBox departmentComboBox=new JComboBox();
	JTextField progIdTextField=new JTextField();
	JTextField programNameTextField=new JTextField();
	JTextField durationInSemsterTextField=new JTextField();
	JTextArea remarksTextArea=new JTextArea();
	JList programList=new JList();
	
	JButton addButton=new JButton("ADD");
	JButton updateButton=new JButton("UPDATE");
	JButton clearButton=new JButton("CLEAR");
	JButton backButton=new JButton("BACK");
	JButton deleteButton=new JButton("DELETE");
		
	Font headerFont=new Font("Times New Roman",Font.BOLD,50);
	Font labelFont=new Font("arial",Font.BOLD,15);
	
	DBManager DataBaseManager;
		
	ProgramFrame(DBManager DataBaseManager)throws Exception{
		
		this.DataBaseManager=DataBaseManager;
		
		Container con=this.getContentPane();
		con.setLayout(null);
		
		setBounds(100,70,1300,800);
		programLabel.setBounds(505,-100,500,400);
		
		facultyLabel.setBounds(127,10,200,350);
		deptLabel.setBounds(94,40,200,350);
		progIdLabel.setBounds(101,70,200,350);
		progNameLabel.setBounds(75,100,200,350);
		durationLabel.setBounds(117,130,200,350);
		inSemesterLabel.setBounds(330,130,200,350);
		remarksLabel.setBounds(120,160,200,350);		
		
		facultyComboBox.setBounds(220,173,430,25);
		departmentComboBox.setBounds(220,203,430,25);
		progIdTextField.setBounds(220,233,100,25);
		programNameTextField.setBounds(220,263,500,25);
		durationInSemsterTextField.setBounds(220,293,100,25);
		remarksTextArea.setBounds(220,324,500,250);		
		
		programListLabel.setBounds(745,70,200,350);
		programList.setBounds(740,263,450,310);
		
		addButton.setBounds(300,600,80,25);
		updateButton.setBounds(400,600,80,25);
		clearButton.setBounds(500,600,80,25);
		backButton.setBounds(600,600,80,25);
		deleteButton.setBounds(1105,595,80,25);
		
		con.add(programLabel);
		con.add(facultyLabel);
		con.add(deptLabel);
		con.add(progIdLabel);
		con.add(progNameLabel);
		con.add(durationLabel);
		con.add(remarksLabel);
		con.add(facultyComboBox);
		con.add(departmentComboBox);
		con.add(progIdTextField);
		con.add(programNameTextField);
		con.add(durationInSemsterTextField);
		con.add(inSemesterLabel);
		con.add(remarksTextArea);
		con.add(programListLabel);
		con.add(programList);
		con.add(addButton);
		con.add(updateButton);
		con.add(clearButton);
		con.add(backButton);
		con.add(deleteButton);

		programLabel.setFont(headerFont);
		facultyLabel.setFont(labelFont);
		deptLabel.setFont(labelFont);
		progIdLabel.setFont(labelFont);
		progNameLabel.setFont(labelFont);
		durationLabel.setFont(labelFont);
		inSemesterLabel.setFont(labelFont);
		remarksLabel.setFont(labelFont);
		programListLabel.setFont(labelFont);
		
		programList.addListSelectionListener(this);
		
		facultyComboBox.addActionListener(this);
		departmentComboBox.addActionListener(this);
		
		addButton.addActionListener(this);
		deleteButton.addActionListener(this);
		updateButton.addActionListener(this);
		clearButton.addActionListener(this);
		
		getFaculty();	
	}
	
	public void actionPerformed(ActionEvent e){

		if(e.getSource()==addButton){
			addProgram();
		}
		
		else if(e.getSource()==updateButton){
			updateProgram();
		}
		
		else if(e.getSource()==deleteButton){
			deleteProgram();
		}
		
		else if(e.getSource()==clearButton){
			clear();
		}
		
		else if(e.getSource()==facultyComboBox){
			getDepartment();
		}
		
		else if(e.getSource()==departmentComboBox){
			getProgram();
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
           
            JOptionPane.showMessageDialog(this,"Error");
        }
    }
    
	private void getDepartment(){
		
        FacultyBean bean=(FacultyBean)facultyComboBox.getSelectedItem();
        if(bean==null)return;
        
        try{
            Vector v=DataBaseManager.getDepartment(bean.getFac_id());
			departmentComboBox.removeAllItems();
            
            for(int i=0; i<v.size(); i++)
			departmentComboBox.addItem(v.elementAt(i));
        }
        catch(Exception e){
            e.printStackTrace();
			JOptionPane.showMessageDialog(this,"Error"+e);
        }
    }
   
	private void getProgram(){
        
		DepartmentBean bean=(DepartmentBean)departmentComboBox.getSelectedItem();
        if(bean==null)return;
       
        try{
           
            Vector v=DataBaseManager.getProgram(bean.getDept_id());
			programList.setListData(v);
        }catch(Exception e){
			e.printStackTrace();
           JOptionPane.showMessageDialog(this,"Error"+e);
        }
	}
    
    private void deleteProgram(){     
        try{     
         int[] size =programList.getSelectedIndices();
         programList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
            
          int rows=0;
          
            for(int i=0; i<size.length; i++)
          {
              ProgramBean bean=(ProgramBean)programList.getModel().getElementAt(size[i]);
              
              if(bean==null)continue;
              
              rows+=DataBaseManager.deleteProgram(bean.getProg_id());
          
          }//end for
          
         if(rows>=1)
            {
                 JOptionPane.showMessageDialog(this,rows+" record Deleted");
                  getProgram(); clear();
            }
        }catch(Exception ee)
        {
            ee.printStackTrace();
            JOptionPane.showMessageDialog(this,"Error: "+ee.getMessage());                 
        }
    }
	
    private void clear(){
        facultyComboBox.setSelectedIndex(0);
        departmentComboBox.setSelectedIndex(0);
        progIdTextField.setText("");
        programNameTextField.setText("");
        durationInSemsterTextField.setText("");
        remarksTextArea.setText(""); 
    } 
	
	public void valueChanged(ListSelectionEvent e){                                         
        
		ProgramBean bean=(ProgramBean)programList.getSelectedValue();
        if(bean==null)return;
	
		progIdTextField.setText(""+bean.getProg_id());
		programNameTextField.setText(bean.getProg_name());
		durationInSemsterTextField.setText(""+bean.getDuration_in_semster());
		remarksTextArea.setText(bean.getRemarks());   
    }
    
    private void updateProgram(){
		
        ProgramBean bean=(ProgramBean)programList.getSelectedValue();
        if(bean==null)return;
       
        String programName=programNameTextField.getText();
        int durationSemster=Integer.parseInt(durationInSemsterTextField.getText());
        String remarks=remarksTextArea.getText();   
       
       try{
            int rows=DataBaseManager.updateProgram(bean.getDept_id(),bean.getProg_id(),programName,durationSemster,remarks);
            if(rows>=1){
                    JOptionPane.showMessageDialog(this,rows+" Record Modified");
                    getProgram();
                    clear();
            }
        }catch(Exception e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(this,"Error: "+e.getMessage()); 
        }
	}
    
    private void addProgram(){
        
        DepartmentBean bean=(DepartmentBean)departmentComboBox.getSelectedItem();
        if(bean==null)return;
       
        String programName=programNameTextField.getText();
        int durationSemster=Integer.parseInt(durationInSemsterTextField.getText());
        String remarks=remarksTextArea.getText();   
       
        try{
                int rows=DataBaseManager.addProgram(bean.getDept_id(),programName,durationSemster,remarks);
                if(rows>=1){
                    JOptionPane.showMessageDialog(this,rows+" Record Modified");
                    getProgram();clear();
                }
            }catch(Exception e){
                e.printStackTrace();
                JOptionPane.showMessageDialog(this,"Error: "+e.getMessage()); 
            }
    }//AddProgram
}
