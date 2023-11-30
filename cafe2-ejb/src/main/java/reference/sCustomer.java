package reference;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class sCustomer implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer sCustomerid;
    private String cName;
    private String cEmail;
    private String cPassword;
    private int cAge;
    private String cGender;
    private String cLocation;

    public sCustomer(Integer sCustomerid, String cName, String cEmail, String cPassword, int cAge, String cGender, String cLocation) {
        this.sCustomerid = sCustomerid;
        this.cName = cName;
        this.cEmail = cEmail;
        this.cPassword = cPassword;
        this.cAge = cAge;
        this.cGender = cGender;
        this.cLocation = cLocation;
    }

    public sCustomer() {
    }

    public Integer getsCustomerid() {
        return sCustomerid;
    }

    public void setsCustomerid(Integer sCustomerid) {
        this.sCustomerid = sCustomerid;
    }

    public String getcName() {
        return cName;
    }

    public void setcName(String cName) {
        this.cName = cName;
    }

    public String getcEmail() {
        return cEmail;
    }

    public void setcEmail(String cEmail) {
        this.cEmail = cEmail;
    }

    public String getcPassword() {
        return cPassword;
    }

    public void setcPassword(String cPassword) {
        this.cPassword = cPassword;
    }

    public int getcAge() {
        return cAge;
    }

    public void setcAge(int cAge) {
        this.cAge = cAge;
    }

    public String getcGender() {
        return cGender;
    }

    public void setcGender(String cGender) {
        this.cGender = cGender;
    }

    public String getcLocation() {
        return cLocation;
    }

    public void setcLocation(String cLocation) {
        this.cLocation = cLocation;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (sCustomerid != null ? sCustomerid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof sCustomer)) {
            return false;
        }
        sCustomer other = (sCustomer) object;
        if ((this.sCustomerid == null && other.sCustomerid != null) || (this.sCustomerid != null && !this.sCustomerid.equals(other.sCustomerid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.sStallStaff[ id=" + sCustomerid + " ]";
    }

}
