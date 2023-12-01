package SStaff;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class SStaff implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String ssUsername;
    private String ssPassword;
    private String msUsername;

    public SStaff(String ssUsername, String ssPassword, String msUsername) {
        this.ssUsername = ssUsername;
        this.ssPassword = ssPassword;
        this.msUsername = msUsername;
    }

    public SStaff() {

    }

    public String getSsUsername() {
        return ssUsername;
    }

    public void setSsUsername(String ssUsername) {
        this.ssUsername = ssUsername;
    }

    public String getSsPassword() {
        return ssPassword;
    }

    public void setSsPassword(String ssPassword) {
        this.ssPassword = ssPassword;
    }

    public String getMsUsername() {
        return msUsername;
    }

    public void setMsUsername(String msUsername) {
        this.msUsername = msUsername;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ssUsername != null ? ssUsername.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof SStaff)) {
            return false;
        }
        SStaff other = (SStaff) object;
        return !((this.ssUsername == null && other.ssUsername != null) || (this.ssUsername != null && !this.ssUsername.equals(other.ssUsername)));
    }

    @Override
    public String toString() {
        return "SStaff.SStaff[ id=" + ssUsername + " ]";
    }

}
