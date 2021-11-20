import java.awt.*;  
import javax.naming.InitialContext;
import javax.naming.Context;
import javax.rmi.PortableRemoteObject;
import java.awt.event.*;  
import javax.swing.*;  
import java.rmi.*;  
import java.rmi.server.*;  
import java.rmi.Naming.*;  

public class MainFrameUsindh extends JFrame implements ActionListener{  
    
	JLabel facultyLabel=new JLabel("Main Frame ");
	JButton fac=new JButton("Faculty");     
	JButton dept=new JButton("Department");   
	JButton prog=new JButton("Program");   
	JButton batch=new JButton("Batch");   
	JButton std=new JButton("Student");   
	JButton exit=new JButton("Exit");   
	Font headerFont=new Font("Times New Roman",Font.BOLD,50);
	
	DBManager DataBaseManager;
	
	public MainFrameUsindh(){
		try{
			Context ic = new InitialContext();  
			Object objref = ic.lookup("DBManagerImp");
			this.DataBaseManager=(DBManager)PortableRemoteObject.narrow(objref, DBManager.class);
		}catch(Exception e){
			e.printStackTrace();
		}
		Container con=this.getContentPane();
		con.setLayout(null);
		setVisible(true);
		setBounds(0,0,700,400);
		
		facultyLabel.setBounds(200,-150,500,400);
		fac.setBounds(30,100,100,40);    
		dept.setBounds(150,100,100,40);    
		prog.setBounds(270,100,100,40);    
		batch.setBounds(390,100,100,40);    
		std.setBounds(510,100,100,40);    
		exit.setBounds(200,250,100,40);    
		
		con.add(facultyLabel);
		con.add(fac);
		con.add(dept);
		con.add(prog);
		con.add(batch);
		con.add(std);
		con.add(exit);
		facultyLabel.setFont(headerFont);
		fac.addActionListener(this);
		prog.addActionListener(this);
		dept.addActionListener(this);
		batch.addActionListener(this);
		std.addActionListener(this);
		exit.addActionListener(this);
		
	}  
	
	public void actionPerformed(ActionEvent e){
		try{
			if(e.getSource()==fac){
				new FacultyFrame(DataBaseManager).setVisible(true);
			}
			
			else if(e.getSource()==dept){
				new DepartmentFrame(DataBaseManager).setVisible(true);
			}
			
			else if(e.getSource()==prog){
				new ProgramFrame(DataBaseManager).setVisible(true);
			}
			
			else if(e.getSource()==batch){
				new BatchFrame(DataBaseManager).setVisible(true);
			}
			
			else if(e.getSource()==std){
				new StudentFrame(DataBaseManager).setVisible(true);
			}
			
			else if(e.getSource()==exit){
				System.exit(0);
			}
		}catch(Exception ee){
			ee.printStackTrace();
		}
	} 
 }  