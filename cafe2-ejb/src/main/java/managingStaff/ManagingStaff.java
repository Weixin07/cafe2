package managingStaff;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ManagingStaff implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String managingStaffUsername;
    private String managingStaffPassword;
    private String adminID;

    public ManagingStaff(String managingStaffUsername, String managingStaffPassword, String adminID) {
        this.managingStaffUsername = managingStaffUsername;
        this.managingStaffPassword = managingStaffPassword;
        this.adminID = adminID;
    }

    public ManagingStaff() {
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

    public String getAdminID() {
        return adminID;
    }

    public void setAdminID(String adminID) {
        this.adminID = adminID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (managingStaffUsername != null ? managingStaffUsername.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ManagingStaff)) {
            return false;
        }
        ManagingStaff other = (ManagingStaff) object;
        if ((this.managingStaffUsername == null && other.managingStaffUsername != null) || (this.managingStaffUsername != null && !this.managingStaffUsername.equals(other.managingStaffUsername))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.sManagingStaff[ id=" + managingStaffUsername + " ]";
    }

}
