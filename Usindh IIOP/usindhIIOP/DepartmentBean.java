/**
 *
 * @author MOHAMMAD TAHIR
 */
  
public class DepartmentBean implements java.io.Serializable {
    
	private int fac_id;
    private int dept_id;
    private String dept_name;
    private String remarks; 

    public int getFac_id() {
        return fac_id;
    }

    public void setFac_id(int fac_id) {
        this.fac_id = fac_id;
    }

    public int getDept_id() {
        return dept_id;
    }

    public void setDept_id(int dept_id) {
        this.dept_id = dept_id;
    }

    public String getDept_name() {
        return dept_name;
    }

    public void setDept_name(String dept_name) {
        this.dept_name = dept_name;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
 
	public String toString(){	
        return dept_name;
    }
}
