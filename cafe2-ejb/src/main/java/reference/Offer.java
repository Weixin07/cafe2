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
public class Offer implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer oId;
    private int pId;
    private String pAddress;
    private String pSeller;
    private String bEmail;
    private char oStatus;

    
    public Offer(Integer oId, int pId, String pAddress, String pSeller, String bEmail, char oStatus) {
        this.oId = oId;
        this.pId = pId;
        this.bEmail = bEmail;
        this.oStatus = oStatus;
        this.pAddress = pAddress;
        this.pSeller = pSeller;
    }
    
     public Offer() {
         
    }

    public Integer getoId() {
        return oId;
    }

    public void setoId(Integer oId) {
        this.oId = oId;
    }

    public int getpId() {
        return pId;
    }

    public void setpId(int pId) {
        this.pId = pId;
    }

    public String getbEmail() {
        return bEmail;
    }

    public void setbEmail(String bEmail) {
        this.bEmail = bEmail;
    }

    public char getoStatus() {
        return oStatus;
    }

    public void setoStatus(char oStatus) {
        this.oStatus = oStatus;
    }

    public String getpAddress() {
        return pAddress;
    }

    public void setpAddress(String pAddress) {
        this.pAddress = pAddress;
    }

    public String getpSeller() {
        return pSeller;
    }

    public void setpSeller(String pSeller) {
        this.pSeller = pSeller;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (oId != null ? oId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the oId fields are not set
        if (!(object instanceof Offer)) {
            return false;
        }
        Offer other = (Offer) object;
        if ((this.oId == null && other.oId != null) || (this.oId != null && !this.oId.equals(other.oId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Offer.Offer[ oId=" + oId + " ]";
    }
    
}
