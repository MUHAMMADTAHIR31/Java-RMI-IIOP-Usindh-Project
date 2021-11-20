/**
 *
 * @author MOHAMMAD TAHIR
 */
  
public class FacultyBean implements java.io.Serializable {
    
	private int fac_id;
    private String fac_name;
    private String remarks;

    public int getFac_id() {
        return fac_id;
    }

    public void setFac_id(int fac_id) {
        this.fac_id = fac_id;
    }

    public String getFac_name() {
        return fac_name;
    }

    public void setFac_name(String fac_name) {
        this.fac_name = fac_name;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
    
    public String toString(){
		return fac_name;
    }
}

