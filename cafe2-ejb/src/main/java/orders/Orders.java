package orders;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Orders implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer sOrdersid;
    private Integer sCustomerid;
    private Integer sStallStaffid;
    private String status;
    private String paymentReferenceID;

    public Orders(Integer sOrdersid, Integer sCustomerid, Integer sStallStaffid, String status, String paymentReferenceID) {
        this.sOrdersid = sOrdersid;
        this.sCustomerid = sCustomerid;
        this.sStallStaffid = sStallStaffid;
        this.status = status;
        this.paymentReferenceID = paymentReferenceID;
    }

    public Orders() {
    }

    public Integer getsOrdersid() {
        return sOrdersid;
    }

    public void setsOrdersid(Integer sOrdersid) {
        this.sOrdersid = sOrdersid;
    }

    public Integer getsCustomerid() {
        return sCustomerid;
    }

    public void setsCustomerid(Integer sCustomerid) {
        this.sCustomerid = sCustomerid;
    }

    public Integer getsStallStaffid() {
        return sStallStaffid;
    }

    public void setsStallStaffid(Integer sStallStaffid) {
        this.sStallStaffid = sStallStaffid;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPaymentReferenceID() {
        return paymentReferenceID;
    }

    public void setPaymentReferenceID(String paymentReferenceID) {
        this.paymentReferenceID = paymentReferenceID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (sOrdersid != null ? sOrdersid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Orders)) {
            return false;
        }
        Orders other = (Orders) object;
        if ((this.sOrdersid == null && other.sOrdersid != null) || (this.sOrdersid != null && !this.sOrdersid.equals(other.sOrdersid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.sStallStaff[ id=" + sOrdersid + " ]";
    }

}
