import java.util.Vector;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.rmi.Naming;
import java.rmi.*;

public class BatchFrame extends javax.swing.JFrame implements ActionListener, ListSelectionListener {
 
	JLabel batchLabel=new JLabel("BATCH");
	JLabel facultyLabel=new JLabel("FACULTY");
	JLabel deptLabel=new JLabel("DEPARTMENT");
	JLabel programLabel=new JLabel("PROGRAM");
	JLabel batchIdLabel=new JLabel("BATCH ID");
	JLabel batchYearLabel=new JLabel("BATCH YEAR");
	JLabel shiftLabel=new JLabel("SHIFT");
	JLabel groupLabel=new JLabel("GROUP");
	JLabel remarksLabel=new JLabel("REMARKS");
	JLabel batchListLabel=new JLabel("BATCH LIST");
	
	JComboBox facultyComboBox=new JComboBox();
	JComboBox departmentComboBox=new JComboBox();
	JComboBox programComboBox=new JComboBox();
	JTextField batchIdTextField=new JTextField();
	JTextField batchYearTextField=new JTextField();
	JComboBox shiftComboBox=new JComboBox();
	JComboBox groupComboBox=new JComboBox();
	JTextArea remarksTextArea=new JTextArea();
	JList batchYearList=new JList();
	
	JButton addButton=new JButton("ADD");
	JButton updateButton=new JButton("UPDATE");
	JButton clearButton=new JButton("CLEAR");
	JButton backButton=new JButton("BACK");
	JButton deleteButton=new JButton("DELETE");
	
	Font headerFont=new Font("Times New Roman",Font.BOLD,50);
	Font labelFont=new Font("arial",Font.BOLD,15);
	
	DBManager DataBaseManager;
	
	BatchFrame(DBManager DataBaseManager)throws Exception{
		this.DataBaseManager= DataBaseManager;
		
		Container con=this.getContentPane();
		con.setLayout(null);
		
		setBounds(150,60,1300,750);
		batchLabel.setBounds(560,-100,500,400);
		
		facultyLabel.setBounds(135,5,200,350);
		deptLabel.setBounds(100,35,200,350);
		programLabel.setBounds(126,65,200,350);
		batchIdLabel.setBounds(130,95,200,350);
		batchYearLabel.setBounds(108,125,200,350);
		shiftLabel.setBounds(360,125,200,350);
		groupLabel.setBounds(530,125,200,350);
		remarksLabel.setBounds(129,155,200,350);		
		
		facultyComboBox.setBounds(222,167,430,25);
		departmentComboBox.setBounds(222,196,430,25);
		programComboBox.setBounds(222,227,430,25);
		batchIdTextField.setBounds(222,257,120,25);
		batchYearTextField.setBounds(222,288,120,25);
		shiftComboBox.setBounds(422,288,100,25);
		groupComboBox.setBounds(602,288,120,25);
		remarksTextArea.setBounds(222,320,500,250);		
		
		batchListLabel.setBounds(738,95,200,350);
		batchYearList.setBounds(735,288,450,283);
		
		addButton.setBounds(280,590,80,25);
		updateButton.setBounds(380,590,80,25);
		clearButton.setBounds(480,590,80,25);
		backButton.setBounds(580,590,80,25);
		deleteButton.setBounds(1103,590,80,25);
		
		con.add(batchLabel);
		con.add(facultyLabel);
		con.add(deptLabel);
		con.add(programLabel);
		con.add(batchIdLabel);
		con.add(batchYearLabel);
		con.add(shiftLabel);
		con.add(groupLabel);
		con.add(remarksLabel);
		con.add(facultyComboBox);
		con.add(departmentComboBox);
		con.add(programComboBox);
		con.add(batchIdTextField);
		con.add(batchYearTextField);
		con.add(shiftComboBox);
		con.add(groupComboBox);
		con.add(remarksTextArea);
		con.add(batchListLabel);
		con.add(batchYearList);
		con.add(addButton);
		con.add(updateButton);
		con.add(clearButton);
		con.add(backButton);
		con.add(deleteButton);
		
		batchLabel.setFont(headerFont);
		facultyLabel.setFont(labelFont);
		deptLabel.setFont(labelFont);
		programLabel.setFont(labelFont);
		batchIdLabel.setFont(labelFont);
		batchYearLabel.setFont(labelFont);
		shiftLabel.setFont(labelFont);
		groupLabel.setFont(labelFont);
		remarksLabel.setFont(labelFont);
		batchListLabel.setFont(labelFont);
		
		batchYearList.addListSelectionListener(this);
		
		facultyComboBox.addActionListener(this);
		departmentComboBox.addActionListener(this);
		programComboBox.addActionListener(this);
		
		addButton.addActionListener(this);
		deleteButton.addActionListener(this);
		updateButton.addActionListener(this);
		clearButton.addActionListener(this);
		
		getFaculty();	
	}
	
	public void actionPerformed(ActionEvent e){

		if(e.getSource()==addButton){
			addBatch();
		}
		
		else if(e.getSource()==updateButton){
			updateBatch();
		}
		
		else if(e.getSource()==deleteButton){
			deleteBatch();
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
		
	}
    
	private void getFaculty(){
		
        try{
            Vector v=DataBaseManager.getFaculty();
            facultyComboBox.removeAllItems();
		
            for(int i=0; i<v.size(); i++)
			facultyComboBox.addItem(v.elementAt(i));
            
        }catch(Exception e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(this,"Error"+e);
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
		}catch(Exception e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(this,"Error"+e);
        }
    } 
	
    private void getBatch(){
        
        ProgramBean bean=(ProgramBean)programComboBox.getSelectedItem();
        if(bean==null)return;
       
        try{
            
            Vector v=DataBaseManager.getBatch(bean.getProg_id());
            batchYearList.setListData(v);
            
        }catch(Exception e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(this,"Error"+e);
        }
    }
    
    private void deleteBatch(){
        
        try{
             
        int[] size =batchYearList.getSelectedIndices();
        batchYearList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        int rows=0;
          
            for(int i=0; i<size.length; i++){
			BatchBean bean=(BatchBean)batchYearList.getModel().getElementAt(size[i]);
            if(bean==null)continue;
            rows+=DataBaseManager.deleteBatch(bean.getBatch_id());
          
            }//end for
          
        if(rows>=1){
            
            JOptionPane.showMessageDialog(this,rows+" record Deleted");
            getBatch(); clear();
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
        batchIdTextField.setText("");
        batchYearTextField.setText("");
        shiftComboBox.setSelectedIndex(0);
        groupComboBox.setSelectedIndex(0);
        remarksTextArea.setText("");
       
    }
	
	public void valueChanged(ListSelectionEvent e){                                         
        
		BatchBean bean=(BatchBean)batchYearList.getSelectedValue();
            if(bean==null)return;
            
            String shift=Decoder.shiftDecode(bean.getShift());
            String group=Decoder.groupDecode(bean.getGroup_desc());
            
            batchIdTextField.setText(""+bean.getBatch_id());
            batchYearTextField.setText(""+bean.getBatch_year());
            groupComboBox.setSelectedItem(group);
            shiftComboBox.setSelectedItem(shift);
           
		   shiftComboBox.addItem(shift);
            groupComboBox.addItem(group);
            remarksTextArea.setText(""+bean.getRemarks());
    }	
		
		
	private void updateBatch(){
           
           BatchBean bean=(BatchBean)batchYearList.getSelectedValue();
           if(bean==null) return;
           
            String BatchYear=batchYearTextField.getText();
            String shift= Encoder.genederEncoder((String) shiftComboBox.getSelectedItem());
            String groupDesc= Encoder.genederEncoder((String) groupComboBox.getSelectedItem());
            String remarks=remarksTextArea.getText();
           
            try{
                int rows=DataBaseManager.updateBatch(bean.getProg_id(),bean.getBatch_id(), shift, groupDesc, BatchYear, remarks);
                if(rows>=1)
                {
                    JOptionPane.showMessageDialog(this,rows+" record Modifiy");
                    getBatch();
                    clear();
                }
            }catch(Exception e)
            {
                e.printStackTrace();
                 JOptionPane.showMessageDialog(this,"Error: "+e.getMessage());
            }
       } //Update
    
	private void addBatch(){
        ProgramBean bean=(ProgramBean)programComboBox.getSelectedItem();
        if(bean==null) return;
       
        String BatchYear=batchYearTextField.getText();
		String shift= Encoder.genederEncoder((String) shiftComboBox.getSelectedItem());
        String groupDesc= Encoder.genederEncoder((String) groupComboBox.getSelectedItem());
        String remarks=remarksTextArea.getText();
        
        try{
                int rows=DataBaseManager.addBatch(bean.getProg_id(), shift, groupDesc, BatchYear, remarks);
                if(rows>=1)
                {
                    JOptionPane.showMessageDialog(this,rows+" record Modifiy");
                    getBatch();
                    clear();
                }
            }catch(Exception e)
            {
                e.printStackTrace();
                 JOptionPane.showMessageDialog(this,"Error: "+e.getMessage());
            }
    }
	
}
