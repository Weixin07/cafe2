package Customer;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Customer implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String cEmail;
    private String cPassword;
    private String cName;
    private int cAge;
    private String cGender;
    private String msUsername;

    public Customer(String cEmail, String cPassword, String cName, int cAge, String cGender, String msUsername) {
        this.cEmail = cEmail;
        this.cPassword = cPassword;
        this.cName = cName;
        this.cAge = cAge;
        this.cGender = cGender;
        this.msUsername = msUsername;
    }

    public Customer() {

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

    public String getcName() {
        return cName;
    }

    public void setcName(String cName) {
        this.cName = cName;
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

    public String getMsUsername() {
        return msUsername;
    }

    public void setMsUsername(String msUsername) {
        this.msUsername = msUsername;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cEmail != null ? cEmail.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the stEmail fields are not set
        if (!(object instanceof Customer)) {
            return false;
        }
        Customer other = (Customer) object;
        return !((this.cEmail == null && other.cEmail != null) || (this.cEmail != null && !this.cEmail.equals(other.cEmail)));
    }

    @Override
    public String toString() {
        return "Customer.Customer[ cEmail=" + cEmail + " ]";
    }
}
