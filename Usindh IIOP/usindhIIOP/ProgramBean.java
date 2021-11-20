/**
 *
 * @author MOHAMMAD TAHIR
 */
 

public class ProgramBean implements java.io.Serializable {
    
    private int dept_id;
    private int prog_id;
    private String prog_name;
    private String remarks;
    private int duration_in_semster;

    public int getDept_id() {
        return dept_id;
    }

    public void setDept_id(int dept_id) {
        this.dept_id = dept_id;
    }

    public int getProg_id() {
        return prog_id;
    }

    public void setProg_id(int prog_id) {
        this.prog_id = prog_id;
    }

    public String getProg_name() {
        return prog_name;
    }

    public void setProg_name(String prog_name) {
        this.prog_name = prog_name;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public int getDuration_in_semster() {
        return duration_in_semster;
    }

    public void setDuration_in_semster(int duration_in_semster) {
        this.duration_in_semster = duration_in_semster;
    }
    @Override
         public String toString()
      {
          return prog_name;
      }
}

