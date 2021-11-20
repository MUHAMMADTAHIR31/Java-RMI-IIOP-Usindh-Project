 
/**
 *
 * @author MOHAMMAD TAHIR
 */


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Encoder implements java.io.Serializable {
    
    public static String simpleDateFormat(String date)throws ParseException{
        Calendar cal = Calendar.getInstance();
        cal.setTime(new SimpleDateFormat("dd-MMM-yyyy").parse(date));
      java.text.SimpleDateFormat f=new java.text.SimpleDateFormat("yyyy-MM-dd");
      String d=f.format(cal.getTime());
      return d;
    }
    
    public static String shiftEncode(String shift)
    {
        switch(shift)
        {
            case "MORNING": return "M";
            case "EVENING": return "E";
            case "NOON": return "N";    
        }
        return shift;
    }
    public static String groupEncode(String group)
    {
        switch(group)
        {
            case "ENGINEERING":  return "E";
            case "MEDICAL":  return "M";
            case "COMMERCE":  return "C";
            case "GENERAL":  return "G";
        }
          return group;
    }
    public static String genederEncoder(String gender)
    {
        switch(gender)
        {
            case "MALE": return "M";
            case "FEMALE": return "F";    
        }
          return gender;
    }
    public static String typeEncoder(String type)
    {
        switch(type)
        {
            case "REGULAR": return "R";
            case "IMPROVER": return "I";
            case "SPECIAL": return "S";    
        }
          return type;
    }
     public static String duesEncoder(String dues)
    {
        switch(dues)
        {
            case "REGULAR": return "R";
            case "IMPROVER": return "I";
            case "SPECIAL": return "S";    
        }
          return dues;
    }
     
    
}
