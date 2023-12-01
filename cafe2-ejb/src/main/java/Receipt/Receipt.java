package Receipt;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;


@Entity
public class Receipt implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer receiptID;
    private int OrderID;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date rDateOfSale;
    private String rFeedback;
    private int rRating;

    public Receipt(Integer receiptID, int OrderID, Date rDateOfSale, String rFeedback, int rRating) {
        this.receiptID = receiptID;
        this.OrderID = OrderID;
        this.rDateOfSale = rDateOfSale;
        this.rFeedback = rFeedback;
        this.rRating = rRating;
    }
    
    public Receipt() {
    }

    public Integer getReceiptID() {
        return receiptID;
    }

    public void setReceiptID(Integer receiptID) {
        this.receiptID = receiptID;
    }

    public int getOrderID() {
        return OrderID;
    }

    public void setOrderID(int OrderID) {
        this.OrderID = OrderID;
    }
    
    public Date getrDateOfSale() {
        return rDateOfSale;
    }

    public void setrDateOfSale(Date rDateOfSale) {
        this.rDateOfSale = rDateOfSale;
    }

    public String getrFeedback() {
        return rFeedback;
    }

    public void setrFeedback(String rFeedback) {
        this.rFeedback = rFeedback;
    }

    public int getrRating() {
        return rRating;
    }

    public void setrRating(int rRating) {
        this.rRating = rRating;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (receiptID != null ? receiptID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the Id fields are not set
        if (!(object instanceof Receipt)) {
            return false;
        }
        Receipt other = (Receipt) object;
        if ((this.receiptID == null && other.receiptID != null) || (this.receiptID != null && !this.receiptID.equals(other.receiptID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Receipt.Receipt[ rId=" + receiptID + " ]";
    }
    
}
