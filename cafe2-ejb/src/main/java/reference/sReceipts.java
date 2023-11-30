package reference;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class sReceipts implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer sReceiptsid;
    private Integer sOrderID;
    private String sTimeStamp;
    private float totalAmount;
    private String paymentStatus;
    private String paymentMethod;

    public sReceipts(Integer sReceiptsid, Integer sOrderID, String sTimeStamp, float totalAmount, String paymentStatus, String paymentMethod) {
        this.sReceiptsid = sReceiptsid;
        this.sOrderID = sOrderID;
        this.sTimeStamp = sTimeStamp;
        this.totalAmount = totalAmount;
        this.paymentStatus = paymentStatus;
        this.paymentMethod = paymentMethod;
    }

    public sReceipts() {
    }

    public Integer getsReceiptsid() {
        return sReceiptsid;
    }

    public void setsReceiptsid(Integer sReceiptsid) {
        this.sReceiptsid = sReceiptsid;
    }

    public Integer getsOrderID() {
        return sOrderID;
    }

    public void setsOrderID(Integer sOrderID) {
        this.sOrderID = sOrderID;
    }

    public String getsTimeStamp() {
        return sTimeStamp;
    }

    public void setsTimeStamp(String timeStamp) {
        this.sTimeStamp = timeStamp;
    }

    public float getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(float totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (sReceiptsid != null ? sReceiptsid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof sReceipts)) {
            return false;
        }
        sReceipts other = (sReceipts) object;
        if ((this.sReceiptsid == null && other.sReceiptsid != null) || (this.sReceiptsid != null && !this.sReceiptsid.equals(other.sReceiptsid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.sStallStaff[ id=" + sReceiptsid + " ]";
    }

}
