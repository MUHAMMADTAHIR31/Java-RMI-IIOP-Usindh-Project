
/**
 *
 * @author MOHAMMAD TAHIR
 */

public class Decoder implements java.io.Serializable {

    public static String simpleDateFormat(java.util.Date date){
      java.text.SimpleDateFormat f=new java.text.SimpleDateFormat("dd-MMM-yyyy");
      String d=f.format(date);
      return d;
    }
    
    public static String shiftDecode(String shift){
        switch(shift)
        {
            case "M": return "MORNING";
            case "E": return "EVENING";
            case "N": return "NOON";    
                
        }
        return shift;
    }
	
    public static String groupDecode(String group){
        switch(group)
        {
            case "E":  return "ENGINEERING";
            case "M":  return "MEDICAL";
            case "C":  return "COMMERCE";
            case "G":  return "GENERAL";
        }
          return group;
    }
	
    public static String genderDecode(String gender){
        switch(gender)
        {
            case "M": return "MALE";
            case "F": return "FEMALE";    
        }
          return gender;
    }
	
    public static String typeDecode(String type){
        switch(type){
            case "R": return "REGULAR";
            case "I": return "IMPROVER";
            case "S": return "SPECIAL";    
        }
        return type;
    }
    public static String duesDecode(String dues){
		
        switch(dues){
            case "N": return "NO";
            case "Y": return "YES";    
        }
        return dues;
    }
}
