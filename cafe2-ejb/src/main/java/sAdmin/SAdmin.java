package sAdmin;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class SAdmin implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String aUsername;
    private String aPassword;

    public SAdmin(String aUsername, String aPassword) {
        this.aUsername = aUsername;
        this.aPassword = aPassword;
    }

    public SAdmin() {
    }

    public String getaUsername() {
        return aUsername;
    }

    public void setaUsername(String aUsername) {
        this.aUsername = aUsername;
    }

    public String getaPassword() {
        return aPassword;
    }

    public void setaPassword(String aPassword) {
        this.aPassword = aPassword;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (aUsername != null ? aUsername.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SAdmin)) {
            return false;
        }
        SAdmin other = (SAdmin) object;
        if ((this.aUsername == null && other.aUsername != null) || (this.aUsername != null && !this.aUsername.equals(other.aUsername))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sAdmin.SAdmin[ aUsername=" + aUsername + " ]";
    }

}
