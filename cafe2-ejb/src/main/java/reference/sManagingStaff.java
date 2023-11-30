package reference;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class sManagingStaff implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer sManagingStaffid;
    private String managingStaffUsername;
    private String managingStaffPassword;
    private Integer sAdminStaffid;

    public sManagingStaff(Integer sManagingStaffid, String managingStaffUsername, String managingStaffPassword, Integer sAdminStaffid) {
        this.sManagingStaffid = sManagingStaffid;
        this.managingStaffUsername = managingStaffUsername;
        this.managingStaffPassword = managingStaffPassword;
        this.sAdminStaffid = sAdminStaffid;
    }

    public sManagingStaff() {
    }

    public Integer getsManagingStaffid() {
        return sManagingStaffid;
    }

    public void setsManagingStaffid(Integer sManagingStaffid) {
        this.sManagingStaffid = sManagingStaffid;
    }

    public String getManagingStaffUsername() {
        return managingStaffUsername;
    }

    public void setManagingStaffUsername(String managingStaffUsername) {
        this.managingStaffUsername = managingStaffUsername;
    }

    public String getManagingStaffPassword() {
        return managingStaffPassword;
    }

    public void setManagingStaffPassword(String managingStaffPassword) {
        this.managingStaffPassword = managingStaffPassword;
    }

    public Integer getsAdminStaffid() {
        return sAdminStaffid;
    }

    public void setsAdminStaffid(Integer sAdminStaffid) {
        this.sAdminStaffid = sAdminStaffid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (sManagingStaffid != null ? sManagingStaffid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof sManagingStaff)) {
            return false;
        }
        sManagingStaff other = (sManagingStaff) object;
        if ((this.sManagingStaffid == null && other.sManagingStaffid != null) || (this.sManagingStaffid != null && !this.sManagingStaffid.equals(other.sManagingStaffid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.sManagingStaff[ id=" + sManagingStaffid + " ]";
    }

}
