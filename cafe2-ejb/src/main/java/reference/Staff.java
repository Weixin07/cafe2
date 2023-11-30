/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reference;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author pc
 */
@Entity
public class Staff implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String stEmail;
    private String stName;
    private String stPassword;
    private int stAge;
    private String stAddress;
    private int stPhoneNo;

    public Staff(String stEmail, String stName, String stPassword, int stAge, String stAddress, int stPhoneNo) {
        this.stEmail = stEmail;
        this.stName = stName;
        this.stPassword = stPassword;
        this.stAge = stAge;
        this.stAddress = stAddress;
        this.stPhoneNo = stPhoneNo;
    }
    
    public Staff(){
        
    }

    public String getStName() {
        return stName;
    }

    public void setStName(String stName) {
        this.stName = stName;
    }

    public String getStEmail() {
        return stEmail;
    }

    public void setStEmail(String stEmail) {
        this.stEmail = stEmail;
    }

    public String getStPassword() {
        return stPassword;
    }

    public void setStPassword(String stPassword) {
        this.stPassword = stPassword;
    }

    public int getStAge() {
        return stAge;
    }

    public void setStAge(int stAge) {
        this.stAge = stAge;
    }

    public String getStAddress() {
        return stAddress;
    }

    public void setStAddress(String stAddress) {
        this.stAddress = stAddress;
    }

    public int getStPhoneNo() {
        return stPhoneNo;
    }

    public void setStPhoneNo(int stPhoneNo) {
        this.stPhoneNo = stPhoneNo;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (stEmail != null ? stEmail.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the stEmail fields are not set
        if (!(object instanceof Staff)) {
            return false;
        }
        Staff other = (Staff) object;
        if ((this.stEmail == null && other.stEmail != null) || (this.stEmail != null && !this.stEmail.equals(other.stEmail))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Staff.Staff[ stEmail=" + stEmail + " ]";
    }
    
}
