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
public class Seller implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String sEmail;
    private String sName;
    private String sPassword;
    private int sPhoneNo;
    private int sAge;
    private char sGender;
    private String sAddress;
    private char sApproval;

    public Seller(String sEmail, String sName, String sPassword, int sPhoneNo, int sAge, char sGender, String sAddress, char sApproval) {
        this.sEmail = sEmail;
        this.sName = sName;
        this.sPassword = sPassword;
        this.sPhoneNo = sPhoneNo;
        this.sAge = sAge;
        this.sGender = sGender;
        this.sAddress = sAddress;
        this.sApproval = sApproval;
    }

    public Seller(){
        
    }

    public int getsAge() {
        return sAge;
    }

    public void setsAge(int sAge) {
        this.sAge = sAge;
    }

    public char getsGender() {
        return sGender;
    }

    public void setsGender(char sGender) {
        this.sGender = sGender;
    }
    
    public String getsName() {
        return sName;
    }

    public void setsName(String sName) {
        this.sName = sName;
    }

    public String getsEmail() {
        return sEmail;
    }

    public void setsEmail(String sEmail) {
        this.sEmail = sEmail;
    }

    public String getsPassword() {
        return sPassword;
    }

    public void setsPassword(String sPassword) {
        this.sPassword = sPassword;
    }

    public int getsPhoneNo() {
        return sPhoneNo;
    }

    public void setsPhoneNo(int sPhoneNo) {
        this.sPhoneNo = sPhoneNo;
    }

    public String getsAddress() {
        return sAddress;
    }

    public void setsAddress(String sAddress) {
        this.sAddress = sAddress;
    }

    public char getsApproval() {
        return sApproval;
    }

    public void setsApproval(char sApproval) {
        this.sApproval = sApproval;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (sEmail != null ? sEmail.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Seller)) {
            return false;
        }
        Seller other = (Seller) object;
        if ((this.sEmail == null && other.sEmail != null) || (this.sEmail != null && !this.sEmail.equals(other.sEmail))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Seller[ id=" + sEmail + " ]";
    }
    
}
