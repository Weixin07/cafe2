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
public class Buyer implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String bUsername;
    private String bPassword;

    public Buyer(String bUsername, String bPassword) {
        this.bUsername = bUsername;
        this.bPassword = bPassword;
    }
    
    public Buyer() {
    }

    public String getbUsername() {
        return bUsername;
    }

    public void setbUsername(String bUsername) {
        this.bUsername = bUsername;
    }

    public String getbPassword() {
        return bPassword;
    }

    public void setbPassword(String bPassword) {
        this.bPassword = bPassword;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (bUsername != null ? bUsername.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Buyer)) {
            return false;
        }
        Buyer other = (Buyer) object;
        if ((this.bUsername == null && other.bUsername != null) || (this.bUsername != null && !this.bUsername.equals(other.bUsername))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Buyer[ id=" + bUsername + " ]";
    }
    
}
