package customer;

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
    private Integer customerid;
    private String cName;
    private String cEmail;
    private String cPassword;
    private int cAge;
    private String cGender;
    private String cLocation;

    public Customer(Integer customerid, String cName, String cEmail, String cPassword, int cAge, String cGender, String cLocation) {
        this.customerid = customerid;
        this.cName = cName;
        this.cEmail = cEmail;
        this.cPassword = cPassword;
        this.cAge = cAge;
        this.cGender = cGender;
        this.cLocation = cLocation;
    }

    public Customer() {
    }

    public Integer getsCustomerid() {
        return customerid;
    }

    public void setsCustomerid(Integer customerid) {
        this.customerid = customerid;
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
        hash += (customerid != null ? customerid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Customer)) {
            return false;
        }
        Customer other = (Customer) object;
        if ((this.customerid == null && other.customerid != null) || (this.customerid != null && !this.customerid.equals(other.customerid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.sStallStaff[ id=" + customerid + " ]";
    }

}
