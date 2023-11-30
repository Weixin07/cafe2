package reference;

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
    private Integer rId;
    private int oId;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date rDateOfSale;
    private String rFeedback;
    private int rRating;

    public Receipt(Integer rId, String rFeedback, int rRating, Date rDateOfSale, int oId) {
        this.rId = rId;
        this.rDateOfSale = rDateOfSale;
        this.rFeedback = rFeedback;
        this.rRating = rRating;
        this.oId = oId;
    }
    public Receipt() {
    }
    
    public Integer getId() {
        return rId;
    }

    public void setId(Integer rId) {
        this.rId = rId;
    }

    public Integer getrId() {
        return rId;
    }

    public void setrId(Integer rId) {
        this.rId = rId;
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

    public int getoId() {
        return oId;
    }

    public void setoId(int oId) {
        this.oId = oId;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (rId != null ? rId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the Id fields are not set
        if (!(object instanceof Receipt)) {
            return false;
        }
        Receipt other = (Receipt) object;
        if ((this.rId == null && other.rId != null) || (this.rId != null && !this.rId.equals(other.rId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Receipt.Receipt[ rId=" + rId + " ]";
    }
    
}
