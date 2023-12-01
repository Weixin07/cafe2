package MStaff;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class MStaff implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String msUsername;
    private String msPassword;

    public MStaff(String msUsername, String msPassword) {
        this.msUsername = msUsername;
        this.msPassword = msPassword;
    }

    public MStaff() {

    }

    public String getMsUsername() {
        return msUsername;
    }

    public void setMsUsername(String msUsername) {
        this.msUsername = msUsername;
    }

    public String getMsPassword() {
        return msPassword;
    }

    public void setMsPassword(String msPassword) {
        this.msPassword = msPassword;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (msUsername != null ? msUsername.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the stEmail fields are not set
        if (!(object instanceof MStaff)) {
            return false;
        }
        MStaff other = (MStaff) object;
        return !((this.msUsername == null && other.msUsername != null) || (this.msUsername != null && !this.msUsername.equals(other.msUsername)));
    }

    @Override
    public String toString() {
        return "MStaff.MStaff[ msUsername=" + msUsername + " ]";
    }

}
