import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import javax.rmi.PortableRemoteObject;

public class DBManagerImp extends PortableRemoteObject implements DBManager{
    
    private Connection con;

    public DBManagerImp() throws RemoteException{
        super();
        
        try{
            init(); 
        }catch(ClassNotFoundException | SQLException e ){
            e.printStackTrace();
        }
    }   
    
    private void init() throws ClassNotFoundException,SQLException{
        Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
        String path=new java.io.File("C:\\xampp\\htdocs\\usindhIIOP\\usindh.accdb").getAbsolutePath();
        String url="jdbc:ucanaccess://"+path;
        con=DriverManager.getConnection(url);
    }
    
    public Vector getFaculty()throws RemoteException,SQLException {
        
        String query="select * from faculty";
        System.out.println(query);
        Statement st=null;
        ResultSet result=null;
       
        try{
           
            st=con.createStatement();
            result=st.executeQuery(query);
             
            Vector v=new Vector();
             
            while(result.next())             {
                 FacultyBean bean=new FacultyBean();
                 
                 bean.setFac_id(result.getInt("fac_id"));
                 bean.setFac_name(result.getString("fac_name"));
                 bean.setRemarks(result.getString("remarks"));
                 
                 v.addElement(bean);
             }
             return v;
        }finally {
        if(result!=null)result.close();
        if(st!=null)st.close();
        }//try
    }//getFaculty
   
    public Vector getDepartment(int facId)throws RemoteException,SQLException{
        
        String query="select * from department where fac_id="+facId;
        System.out.println(query);
        
        Statement st=null;
        ResultSet result=null;
       
        try{
           
            st=con.createStatement();
            result=st.executeQuery(query);
           
            Vector v=new Vector();
           
            while(result.next()){
                
				DepartmentBean bean=new DepartmentBean();
                
                bean.setFac_id(result.getInt("fac_id"));
                bean.setDept_id(result.getInt("dept_id"));
                bean.setDept_name(result.getString("dept_name"));
                bean.setRemarks(result.getString("remarks"));
                
				v.addElement(bean);
            }
            return v;
        }finally{
        if(result!=null)result.close();
        if(st!=null)st.close();
        }//try
    }//getDDepartment
    
    public Vector getDepartment()throws RemoteException,SQLException{
        
        String query="select * from department ";
        System.out.println(query);
        
        Statement st=null;
        ResultSet result=null;
       
        try{
           
            st=con.createStatement();
            result=st.executeQuery(query);
           
            Vector v=new Vector();
            while(result.next()){
                
                DepartmentBean bean=new DepartmentBean();
                
                bean.setFac_id(result.getInt("fac_id"));
                bean.setDept_id(result.getInt("dept_id"));
                bean.setDept_name(result.getString("dept_name"));
                bean.setRemarks(result.getString("remarks"));
               
                v.addElement(bean);
            }
            return v;
        }finally{
            if(result!=null)result.close();
            if(st!=null)st.close();
        }//try
    }//get Department
    
    public Vector getProgram(int deptId)throws RemoteException,SQLException{
        
        String query="select * from program where dept_id="+deptId;
        System.out.println(query);
        Statement st=null;
        ResultSet result=null;
       
       try{
           
            st=con.createStatement();
            result=st.executeQuery(query);
            
            Vector v=new Vector();
            
            while(result.next()){
                
                ProgramBean bean=new ProgramBean();
                
                bean.setProg_id(result.getInt("prog_id"));
                bean.setDept_id(result.getInt("dept_id"));
                bean.setProg_name(result.getString("prog_name"));
                bean.setDuration_in_semster(result.getInt("duration_in_sems"));
                bean.setRemarks(result.getString("remarks"));
                 
                v.addElement(bean);
            }
            return v;
        }finally{
            if(result!=null)result.close();
            if(st!=null)st.close();
        }//try
    }//getProgram
    
    public Vector getBatch(int progId)throws RemoteException,SQLException{
        
        String query="select * from batch where prog_id="+progId;
        System.out.println(query);
        Statement st=null;
        
        ResultSet result=null;
       
         try
         {
           
             st=con.createStatement();
             result=st.executeQuery(query);
             
             Vector v=new Vector();
             
             while(result.next())
             {
                 BatchBean bean=new BatchBean();
                 
                 bean.setProg_id(result.getInt("prog_id"));
                 bean.setBatch_id(result.getInt("batch_id"));
                 bean.setBatch_year(result.getInt("batch_year"));
                 bean.setShift(result.getString("shift"));
                 bean.setGroup_desc(result.getString("group_desc"));
                 bean.setRemarks(result.getString("remarks"));
                 
                 v.addElement(bean);
            }
            return v;
         }finally{
            if(result!=null)result.close();
            if(st!=null)st.close();
         }//try
    }//getBatch
    
    public Vector getStudent(int batchId)throws RemoteException,SQLException{
        
        //String query="select * from student where batchId="+batchId;
        String q = "select * from student where batch_id="+batchId;
           
        System.out.println(q);
        Statement st=null;
        
        ResultSet result=null;
       
        try
         {
           
             st=con.createStatement();
             result=st.executeQuery(q);
             
             Vector v=new Vector();
             
             while(result.next())
             {
                 StudentBean bean=new StudentBean();
                 
                 bean.setBatch_id(result.getInt("batch_id"));
                 bean.setStd_id(result.getInt("std_id"));
                 bean.setStd_name(result.getString("std_name"));
                 bean.setFname(result.getString("fname"));
                 bean.setSur_name(result.getString("sur_name"));
                 bean.setRoll_number(result.getString("roll_number"));
                 bean.setGender(result.getString("gender"));
                 
                 bean.setRemarks(result.getString("remarks"));
                 
                 v.addElement(bean);
                 
             }
             return v;
         }finally
           {
             if(result!=null)result.close();
             if(st!=null)st.close();
           }//try
         
        }//getStudent
           
               
    public int deleteFaculty(int facId)throws RemoteException,SQLException{
        String query="delete from faculty where fac_id="+facId;
        System.out.println(query);
        Statement st=null;
               
        try{
                     
            st=con.createStatement();
            int rows=st.executeUpdate(query);
            return rows; 
        }finally
        {
           if(st!=null)st.close();
        }
    }//Faculty delete method

           
    public int deleteDepartment(int deptId) throws RemoteException,SQLException {
        String query="delete from department where dept_id="+deptId;
        System.out.println(query);
               
        Statement st=null;
               
        try{
            st=con.createStatement();
            int rows=st.executeUpdate(query);
            return rows;
            }finally{
                if(st!=null)st.close();
            }
    }//Department delete method   
           
    public int deleteProgram(int progId) throws  RemoteException,SQLException{
        
        String query="delete from program where prog_id="+progId;
        System.out.println(query);
               
        Statement st=null;
               
        try{
                st=con.createStatement();
                int rows=st.executeUpdate(query);
                return rows;
            }finally{
                   if(st!=null)st.close();
            }
    }//Program delete method

    public int deleteBatch(int batchId)throws RemoteException,SQLException{
        
        String query="delete from batch where batch_id="+batchId;
        System.out.println(query);
        Statement st=null;
               
               try{
                   st=con.createStatement();
                   int rows=st.executeUpdate(query);
                   return rows;
                }finally
               {
                   if(st!=null)st.close();
               }
    }//Batch delete method
   
    public int deleteStudent(int stdId) throws RemoteException,SQLException{
        
        String query="delete from student where std_id="+stdId;
        System.out.println(query);
        Statement st=null;
               
        try{
                 st=con.createStatement();
                 int rows=st.executeUpdate(query);
                  return rows;
                }finally
               {
                   if(st!=null)st.close();
               }
           }//Student delete method
           
           //Update Methods
    public int deletePart(int batchId,int part ) throws RemoteException,SQLException{
        
        String query="delete from part where batch_id="+batchId+" AND part="+part;
		Statement st=null;
       
        try{
            st=con.createStatement();
            int rows=st.executeUpdate(query);
            return rows;
            }finally{
                if(st!=null)st.close();
            }
    }
    
    public int updateFaculty(int facId,String facName,String remarks)throws RemoteException,SQLException{
        
        String query="update faculty set fac_name='"+facName+"',remarks='"+remarks+"' where fac_id="+facId;
        System.out.println(query);
        Statement st=null;
               
        try{
                st=con.createStatement();
                int rows=st.executeUpdate(query);
                 return rows;
                }finally
               {
                if(st!=null)st.close();
               }
           }// Update Faculty Method
   
    public int addFaculty(String facName,String remarks)throws RemoteException,SQLException{
        
        String query="insert into faculty(fac_name,remarks)values('"+facName+"','"+remarks+"');";
        System.out.println(query);
        Statement st=null;
               
        try{
            st=con.createStatement();
            int rows=st.executeUpdate(query);
            return rows;
            }finally
            {
             if(st!=null)st.close();
            }
        }
    
   
          
    public int updateDepartment(int deptId,int facId,String deptName,String remarks)throws RemoteException,SQLException{
        String query="update department set fac_id="+facId+",dept_name='"+deptName+"',remarks='"+remarks+"' where dept_id="+deptId;
        System.out.println(query);
        Statement st=null;
               
        try{
            st=con.createStatement();
            int rows=st.executeUpdate(query);
            return rows;
            }finally
            {
                   if(st!=null)st.close();
            }
    }// Update department
    public int addDepartment(int facId,String deptName,String remarks)throws RemoteException,SQLException{
        
            String query="insert into department(fac_id,dept_name,remarks) values ("+facId+",'"+deptName+"','"+remarks+"');";
            System.out.println(query);
            Statement st=null;
               
            try{
                st=con.createStatement();
                int rows=st.executeUpdate(query);
                return rows;
        }finally{
            if(st!=null)st.close();
        }
    }
            
    public int updateProgram(int deptId,int progId,String progName,int durationInSems,String remarks)throws RemoteException,SQLException{
        
        String query="update program set dept_id="+deptId+",prog_name='"+progName+"',duration_in_sems="+durationInSems+",remarks='"+remarks+"' where prog_id="+progId;
        System.out.println(query);
        Statement st=null;
        
        try{
            
            st=con.createStatement();
            int rows=st.executeUpdate(query);
            System.out.print("Program: "+rows);
            return rows;
        
        }finally{
            if(st!=null)st.close();
        }
    }// Update program 
    
    public int addProgram(int deptId,String progName,int durationInSems,String remarks)throws RemoteException,SQLException{
        String query="insert into program(dept_id,prog_name,remarks,duration_in_sems) values"
        + " ("+deptId+",'"+progName+"','"+remarks+"',"+durationInSems+"); ";
        System.out.println(query);
        Statement st=null;
               
            try{
                 st=con.createStatement();
                 int rows=st.executeUpdate(query);
                  return rows;
                }finally{
                if(st!=null)st.close();
            }
    }//add Program
             
    public int updateBatch(int progId,int batchId,String shift,String groupDesc,String batchYear ,String remarks)throws RemoteException,SQLException{
        String query="update batch set prog_id="+progId+",shift='"+shift+"',group_desc='"+groupDesc+"',batch_year='"+batchYear+"',remarks='"+remarks+"' where batch_id="+batchId;
        System.out.println(query);
               
        Statement st=null;
               
        try{
                 st=con.createStatement();
                 int rows=st.executeUpdate(query);
                  return rows;
                }finally
               {
                   if(st!=null)st.close();
               }
    }// Update batch

    @Override
    public int addBatch(int progId,String shift,String groupDesc,String batchYear ,String remarks)throws RemoteException,SQLException{
                String query="insert into batch(prog_id,shift,group_desc,batch_year,remarks) values ("+progId+",'"+shift+"','"+groupDesc+"','"+batchYear+"','"+remarks+"');";
                System.out.println(query);
               
               Statement st=null;
               
               try{
                 st=con.createStatement();
                 int rows=st.executeUpdate(query);
                  return rows;
                }finally{
                   if(st!=null)st.close();
               }
    }// add batch
   
    @Override
    public int updateStudent(int stdId,int batchId,String stdName,String fName,String surName,String rollNumber,String gender,String remarks)throws RemoteException,SQLException{
        String query="update student set batch_id="+batchId+",std_name='"+stdName+"',fname='"+fName+"',sur_Name='"+surName+"',roll_number='"+rollNumber+"',gender='"+gender+"',remarks='"+remarks+"' where std_id="+stdId;
             
        System.out.println(query);
        Statement st=null;
        
        try{
            st=con.createStatement();
            int rows=st.executeUpdate(query);
            return rows;
        }finally{
        if(st!=null)st.close();
        }
    }// Update student 
    
    public  int addStudent(int batchId,String stdName,String fName,String surName,String rollNumber,String gender,String remarks)throws RemoteException,SQLException{
        
		String query="insert into student(batch_id,std_name,fname,sur_Name,roll_number,gender,remarks) values ("+batchId+",'"+stdName+"','"+fName+"','"+surName+"','"+rollNumber+"','"+gender+"','"+remarks+"');";
        System.out.println(query);
       
        Statement st=null;
        
      try{
            
			st=con.createStatement();
            int rows=st.executeUpdate(query);
            return rows;
            
		}finally{
			if(st!=null)st.close();
        }
    }// addStudent
} 