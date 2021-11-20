import javax.naming.InitialContext;
import javax.naming.Context;
import java.rmi.*;
import java.rmi.registry.*;
import java.rmi.server.*;
import java.util.*;
import java.io.*;


public class DynamicServer{
	
	public static void main(String arg[])throws Exception{

		//already set from cmd-line, therefore no need to do hard-code
		//System.setProperty("java.security.policy","sachal.policy");
		
		if(System.getSecurityManager()==null)
		System.setSecurityManager(new RMISecurityManager());

		Properties p=System.getProperties();
		String url=p.getProperty("java.rmi.server.codebase"); 
		
		Class server=RMIClassLoader.loadClass(url,"DBManagerImp");

		Object obj=server.newInstance();	
		Remote remote=(Remote)obj;		// Step 1: Instantiate the Hello servant
       
        Context initialNamingContext = new InitialContext(); 	// Step 2: Publish the reference in the Naming Service  // using JNDI API
        initialNamingContext.rebind("DBManagerImp", remote );
		System.out.println("\n\nHello EverOne..!! USindh Server: Ready For Providing Data ...");
	}  
}