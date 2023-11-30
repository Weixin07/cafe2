package reference;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class sReport implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer sReportid;
    private String type;
    private int generatedBy;
    private String sTimeStamp;

    public sReport(Integer sReportid, String type, int generatedBy, String sTimeStamp) {
        this.sReportid = sReportid;
        this.type = type;
        this.generatedBy = generatedBy;
        this.sTimeStamp = sTimeStamp;
    }

    public sReport() {
    }

    public Integer getsReportid() {
        return sReportid;
    }

    public void setsReportid(Integer sReportid) {
        this.sReportid = sReportid;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getGeneratedBy() {
        return generatedBy;
    }

    public void setGeneratedBy(int generatedBy) {
        this.generatedBy = generatedBy;
    }

    public String getsTimeStamp() {
        return sTimeStamp;
    }

    public void setsTimeStamp(String sTimeStamp) {
        this.sTimeStamp = sTimeStamp;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (sReportid != null ? sReportid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof sReport)) {
            return false;
        }
        sReport other = (sReport) object;
        if ((this.sReportid == null && other.sReportid != null) || (this.sReportid != null && !this.sReportid.equals(other.sReportid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.sStallStaff[ id=" + sReportid + " ]";
    }

}
