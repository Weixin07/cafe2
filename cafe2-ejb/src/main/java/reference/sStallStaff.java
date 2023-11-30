package reference;

import reference.sManagingStaff;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class sStallStaff implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer stallStaffid;
    private String stallStaffUsername;
    private String stallStaffPassword;
    private Integer approvedBy;

    public sStallStaff(Integer stallStaffid, String stallStaffUsername, String stallStaffPassword, Integer approvedBy) {
        this.stallStaffid = stallStaffid;
        this.stallStaffUsername = stallStaffUsername;
        this.stallStaffPassword = stallStaffPassword;
        this.approvedBy = approvedBy;
    }

    public sStallStaff() {
    }

    public Integer getStallStaffid() {
        return stallStaffid;
    }

    public void setStallStaffid(Integer stallStaffid) {
        this.stallStaffid = stallStaffid;
    }

    public String getStallStaffUsername() {
        return stallStaffUsername;
    }

    public void setStallStaffUsername(String stallStaffUsername) {
        this.stallStaffUsername = stallStaffUsername;
    }

    public String getStallStaffPassword() {
        return stallStaffPassword;
    }

    public void setStallStaffPassword(String stallStaffPassword) {
        this.stallStaffPassword = stallStaffPassword;
    }

    public Integer getApprovedBy() {
        return approvedBy;
    }

    public void setApprovedBy(Integer approvedBy) {
        this.approvedBy = approvedBy;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (stallStaffid != null ? stallStaffid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof sStallStaff)) {
            return false;
        }
        sStallStaff other = (sStallStaff) object;
        if ((this.stallStaffid == null && other.stallStaffid != null) || (this.stallStaffid != null && !this.stallStaffid.equals(other.stallStaffid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.sStallStaff[ id=" + stallStaffid + " ]";
    }

}
