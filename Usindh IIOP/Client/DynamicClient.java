import java.rmi.*;
import java.rmi.server.*;
import java.util.*;

public class DynamicClient{

	public static void main(String arg[]){
		
		//already set from cmd-line, therefore no need to do hard-code
	    //	System.setProperty("java.security.policy","sachal.policy");
		
		if(System.getSecurityManager()==null)
	    System.setSecurityManager(new RMISecurityManager());

		Properties p=System.getProperties();
		String url=p.getProperty("java.rmi.server.code");

	 try{
			Class client=RMIClassLoader.loadClass(url,"MainFrameUsindh");
			client.newInstance();
		}catch(Exception e){
			e.printStackTrace();        
		}   
	}
} 