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
 
public class StudentFrame extends javax.swing.JFrame implements ActionListener , ListSelectionListener {

    JLabel studentLabel=new JLabel("STUDENT REGISTRATION");
	JLabel facultyLabel=new JLabel("FACULTY");
	JLabel deptLabel=new JLabel("DEPARTMENT");
	JLabel programLabel=new JLabel("PROGRAM");
	JLabel batchYearLabel=new JLabel("BATCH YEAR");
	JLabel shiftLabel=new JLabel("SHIFT");
	JLabel groupLabel=new JLabel("GROUP");
	JLabel studentIdLabel=new JLabel("STUDENT ID");
	JLabel rollNoLabel=new JLabel("ROLL NO");
	JLabel nameLabel=new JLabel("NAME");
	JLabel fNameLabel=new JLabel("FATHER'S NAME");
	JLabel surnameLabel=new JLabel("SURNAME");
	JLabel genderLabel=new JLabel("GENDER");
	JLabel remarksLabel=new JLabel("REMARKS");
	JLabel rollNoListLabel=new JLabel("ROLL NOS");
	
	JButton addButton=new JButton("ADD");
	JButton updateButton=new JButton("UPDATE");
	JButton clearButton=new JButton("CLEAR");
	JButton backButton=new JButton("BACK");
	JButton deleteButton=new JButton("DELETE");
	
	JComboBox facultyComboBox=new JComboBox();
	JComboBox departmentComboBox=new JComboBox();
	JComboBox programComboBox=new JComboBox();
	JComboBox batchYearComboBox=new JComboBox();
	JTextField shiftTextField=new JTextField();
	JTextField groupTextField=new JTextField();
	JTextField StdIdTextField=new JTextField();
	JTextField rollNumberTextField=new JTextField();
	JTextField nameTextField=new JTextField();
	JTextField fnameTextField=new JTextField();
	JTextField surnameTextField=new JTextField();
	JComboBox genderComboBox=new JComboBox();
	JTextArea remarksTextArea=new JTextArea();
	JList rollNumberList=new JList();
	
	Font headerFont=new Font("Times New Roman",Font.BOLD,50);
	Font labelFont=new Font("arial",Font.BOLD,15);
	
	DBManager DataBaseManager;
	
	StudentFrame(DBManager DataBaseManager)throws Exception{
		
		this.DataBaseManager=DataBaseManager;
		
		Container con=this.getContentPane();
		con.setLayout(null);
		
		setBounds(150,90,1300,850);
		studentLabel.setBounds(360,-110,700,400);
		
		facultyLabel.setBounds(134,-5,200,350);
		deptLabel.setBounds(100,25,200,350);
		programLabel.setBounds(126,55,200,350);
		batchYearLabel.setBounds(108,85,200,350);
		shiftLabel.setBounds(360,85,200,350);
		groupLabel.setBounds(530,85,200,350);
		studentIdLabel.setBounds(114,115,200,350);
		rollNoLabel.setBounds(137,145,200,350);
		nameLabel.setBounds(160,175,200,350);
		fNameLabel.setBounds(82,205,200,350);
		surnameLabel.setBounds(129,235,200,350);
		genderLabel.setBounds(530,235,200,350);
		remarksLabel.setBounds(129,265,200,350);		
		
		facultyComboBox.setBounds(222,156,490,25);
		departmentComboBox.setBounds(222,187,490,25);
		programComboBox.setBounds(222,217,490,25);
		batchYearComboBox.setBounds(222,248,120,25);
		shiftTextField.setBounds(422,248,100,25);
		groupTextField.setBounds(602,248,110,25);
		StdIdTextField.setBounds(222,278,120,25);
		rollNumberTextField.setBounds(222,307,120,25);
		nameTextField.setBounds(222,337,300,25);
		fnameTextField.setBounds(222,367,300,25);
		surnameTextField.setBounds(222,397,300,25);
		genderComboBox.setBounds(602,397,110,25);
		remarksTextArea.setBounds(222,428,490,250);		
		
		rollNoListLabel.setBounds(732,200,200,350);
		rollNumberList.setBounds(730,390,450,290);
		
		addButton.setBounds(280,700,80,25);
		updateButton.setBounds(380,700,80,25);
		clearButton.setBounds(480,700,80,25);
		backButton.setBounds(580,700,80,25);
		deleteButton.setBounds(1100,700,80,25);
		
		con.add(studentLabel);
		con.add(facultyLabel);
		con.add(deptLabel);
		con.add(programLabel);
		con.add(batchYearLabel);
		con.add(shiftLabel);
		con.add(groupLabel);
		con.add(studentIdLabel);
		con.add(rollNoLabel);
		con.add(nameLabel);
		con.add(fNameLabel);
		con.add(surnameLabel);
		con.add(genderLabel);
		con.add(remarksLabel);
		con.add(facultyComboBox);
		con.add(departmentComboBox);
		con.add(programComboBox);
		con.add(batchYearComboBox);
		con.add(shiftTextField);
		con.add(groupTextField);
		con.add(StdIdTextField);
		con.add(rollNumberTextField);
		con.add(nameTextField);
		con.add(fnameTextField);
		con.add(surnameTextField);
		con.add(genderComboBox);
		con.add(remarksTextArea);
		con.add(rollNoListLabel);
		con.add(rollNumberList);
		con.add(addButton);
		con.add(updateButton);
		con.add(clearButton);
		con.add(backButton);
		con.add(deleteButton);
		
		studentLabel.setFont(headerFont);
		facultyLabel.setFont(labelFont);
		deptLabel.setFont(labelFont);
		programLabel.setFont(labelFont);
		batchYearLabel.setFont(labelFont);
		shiftLabel.setFont(labelFont);
		groupLabel.setFont(labelFont);
		studentIdLabel.setFont(labelFont);
		rollNoLabel.setFont(labelFont);
		nameLabel.setFont(labelFont);
		fNameLabel.setFont(labelFont);
		surnameLabel.setFont(labelFont);
		genderLabel.setFont(labelFont);
		remarksLabel.setFont(labelFont);
		rollNoListLabel.setFont(labelFont);
		
		rollNumberList.addListSelectionListener(this);
		
		facultyComboBox.addActionListener(this);
		departmentComboBox.addActionListener(this);
		programComboBox.addActionListener(this);
		batchYearComboBox.addActionListener(this);
		
		
		addButton.addActionListener(this);
		deleteButton.addActionListener(this);
		updateButton.addActionListener(this);
		clearButton.addActionListener(this);
	
		getFaculty();	
	}
	
	public void actionPerformed(ActionEvent e){

		if(e.getSource()==addButton){
			addStudent();
		}
		
		else if(e.getSource()==updateButton){
			updateStudent();
		}
		
		else if(e.getSource()==deleteButton){
			deleteStudent();
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
		
		else if(e.getSource()==programComboBox){
			getBatch();
		}
		
		else if(e.getSource()==batchYearComboBox){
			getStudent();
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
           JOptionPane.showMessageDialog(this,"Error ");
        }
    }
   
    private void getDepartment(){
		
        FacultyBean bean=(FacultyBean)facultyComboBox.getSelectedItem();
        try{
        
            Vector v=DataBaseManager.getDepartment(bean.getFac_id());
            
            departmentComboBox.removeAllItems();
            
            for(int i=0; i<v.size(); i++)
            departmentComboBox.addItem(v.elementAt(i));
		}catch(Exception e){
            e.printStackTrace();
           JOptionPane.showMessageDialog(this,"Error"+e);
        }
    }
   
    private void getProgram(){
       DepartmentBean bean=(DepartmentBean)departmentComboBox.getSelectedItem(); 
       if(bean==null)return;
        try{
            Vector v=DataBaseManager.getProgram(bean.getDept_id());
             
            programComboBox.removeAllItems();
           
            for(int i=0; i<v.size(); i++)
            
                programComboBox.addItem(v.elementAt(i));
       
        }catch(Exception e)
        {
            e.printStackTrace();
           JOptionPane.showMessageDialog(this,"Error"+e);
        }
    } 
       private void getBatch()
    {
        ProgramBean bean=(ProgramBean)programComboBox.getSelectedItem();
        if(bean==null)return;
        
        try{
            Vector v=DataBaseManager.getBatch(bean.getProg_id());
        
            batchYearComboBox.removeAllItems();
            
            for(int i=0; i<v.size(); i++)
                batchYearComboBox.addItem(v.elementAt(i));
        }catch(Exception e)
        {
            e.printStackTrace();
           
            JOptionPane.showMessageDialog(this,"Error"+e);
        }
    }
    
	private void getStudent(){
		
        BatchBean bean=(BatchBean)batchYearComboBox.getSelectedItem();
        if(bean==null)return;
    
        try{
          
            Vector v=DataBaseManager.getStudent(bean.getBatch_id());
            rollNumberList.setListData(v);
         
            String shift = Decoder.shiftDecode(bean.getShift());
            String group = Decoder.groupDecode(bean.getGroup_desc());
            
            shiftTextField.setText(shift);
            groupTextField.setText(group);
        }catch(Exception e){
			e.printStackTrace();
            JOptionPane.showMessageDialog(this,"Error"+e);
        }
    }
    
	private void deleteStudent(){
		
        try{   
		
			int[] size =rollNumberList.getSelectedIndices();
			rollNumberList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
            
			int rows=0;
          
            for(int i=0; i<size.length; i++){
                StudentBean bean=(StudentBean)rollNumberList.getModel().getElementAt(size[i]);
                if(bean==null)continue;
                rows+=DataBaseManager.deleteStudent(bean.getStd_id());
            }//end for
          
            if(rows>=1){
                JOptionPane.showMessageDialog(this,rows+" record Deleted");
                getStudent(); clear();
            }
			
        }catch(Exception ee){
            ee.printStackTrace();
            JOptionPane.showMessageDialog(this,"Error: "+ee.getMessage());                 
        }
    }
    
	private void clear(){
		
        facultyComboBox.setSelectedIndex(0);
        departmentComboBox.setSelectedIndex(0);
        programComboBox.setSelectedIndex(0);
        batchYearComboBox.setSelectedIndex(0);
        StdIdTextField.setText("");
        rollNumberTextField.setText("");
        genderComboBox.setSelectedIndex(0);
        nameTextField.setText("");
        fnameTextField.setText("");
        surnameTextField.setText("");
        remarksTextArea.setText("");
    }
	
	public void valueChanged(ListSelectionEvent e){                                         
        
		StudentBean bean=(StudentBean)rollNumberList.getSelectedValue();
        if(bean==null)return;
        
        String gender= Decoder.genderDecode(bean.getGender());
        genderComboBox.setSelectedItem(gender);
        genderComboBox.addItem(gender);
       
	   StdIdTextField.setText(""+bean.getStd_id());
        rollNumberTextField.setText(""+bean.getRoll_number());
        nameTextField.setText(bean.getStd_name());
        fnameTextField.setText(bean.getFname());
        surnameTextField.setText(bean.getSur_name());
        remarksTextArea.setText(""+bean.getRemarks());  
    }
	
	private void updateStudent(){
        StudentBean bean=(StudentBean)rollNumberList.getSelectedValue();
        if(bean==null)return;
        
        try
        {
            String rollNumber=rollNumberTextField.getText();
            String gender= Encoder.genederEncoder((String) genderComboBox.getSelectedItem());
            genderComboBox.setSelectedItem(gender);
            String name=nameTextField.getText();
            String fname=fnameTextField.getText();
            String surName=surnameTextField.getText();
            String remarks=remarksTextArea.getText();
            
            int rows=DataBaseManager.updateStudent(bean.getStd_id(), bean.getBatch_id(), name, fname, surName, rollNumber, gender, remarks);
            if(rows>=1)
            {
                 JOptionPane.showMessageDialog(this,rows+" record Modifiy");
                    getStudent();
                    clear();
            }
        }catch(Exception e)
        {
            e.printStackTrace();
             JOptionPane.showMessageDialog(this,"Error: "+e.getMessage()); 
        }
    }   
    
	private void addStudent(){
		
        BatchBean bean=(BatchBean)batchYearComboBox.getSelectedItem();
        if(bean==null)return;
        
        try
        {
            String rollNumber=rollNumberTextField.getText();
            String gender= Encoder.genederEncoder((String) genderComboBox.getSelectedItem());
           genderComboBox.setSelectedItem(gender);
            String name=nameTextField.getText();
            String fname=fnameTextField.getText();
            String surName=surnameTextField.getText();
            String remarks=remarksTextArea.getText();
            
            int rows=DataBaseManager.addStudent( bean.getBatch_id(), name, fname, surName, rollNumber, gender, remarks);
            if(rows>=1)
            {
                 JOptionPane.showMessageDialog(this,rows+" record Modifiy");
                    getStudent();
                    clear();
            }
        }catch(Exception e)
        {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this,"Error: "+e.getMessage()); 
        }
    } 
}

