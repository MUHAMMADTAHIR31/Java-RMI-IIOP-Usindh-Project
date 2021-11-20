/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.Vector;

/**
 *
 * @author Dell
 */
public interface DBManager extends Remote {
    
    public Vector getFaculty()throws RemoteException,SQLException;  
    public Vector getDepartment(int facId)throws RemoteException,SQLException;
    public Vector getDepartment()throws RemoteException,SQLException;
    public Vector getProgram(int deptId)throws RemoteException,SQLException;
    public Vector getBatch(int progId)throws RemoteException,SQLException;
    public Vector getStudent(int batchId)throws RemoteException,SQLException;
    
    public int deleteFaculty(int facId)throws RemoteException,SQLException;
    public int deleteDepartment(int deptId) throws RemoteException,SQLException;
    public int deleteProgram(int progId)throws RemoteException,SQLException;
    public int deleteBatch(int batchId)throws RemoteException,SQLException;
    public int deleteStudent(int stdId) throws RemoteException,SQLException;
    public int deletePart(int batchId,int part ) throws RemoteException,SQLException;
    
    public int updateFaculty(int facId,String facName,String remarks)throws RemoteException,SQLException;
    public int updateDepartment(int deptId,int facId,String deptName,String remarks)throws RemoteException,SQLException;
    public int updateProgram(int deptId,int progId,String progName,int durationInSems,String remarks)throws RemoteException,SQLException;
    public int updateBatch(int progId,int batchId,String shift,String groupDesc,String batchYear ,String remarks)throws RemoteException,SQLException;
    public int updateStudent(int stdId,int batchId,String stdName,String fName,String surName,String rollNumber,String gender,String remarks)throws RemoteException,SQLException;
    
    public int addFaculty(String facName,String remarks)throws RemoteException,SQLException;
    public int addDepartment(int facId,String deptName,String remarks)throws RemoteException,SQLException;
    public int addProgram(int deptId,String progName,int durationInSems,String remarks)throws RemoteException,SQLException;
    public int addBatch(int progId,String shift,String groupDesc,String batchYear ,String remarks)throws RemoteException,SQLException;
    public int addStudent(int batchId,String stdName,String fName,String surName,String rollNumber,String gender,String remarks)throws RemoteException,SQLException;
}
