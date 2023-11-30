package ratings;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Ratings implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer sRatingsid;
    private Integer sCustomerid;
    private Integer sStallStaffid;
    private Integer ratingValue;
    private String feedback;

    public Ratings(Integer sRatingsid, Integer sCustomerid, Integer sStallStaffid, Integer ratingValue, String feedback) {
        this.sRatingsid = sRatingsid;
        this.sCustomerid = sCustomerid;
        this.sStallStaffid = sStallStaffid;
        this.ratingValue = ratingValue;
        this.feedback = feedback;
    }

    public Ratings() {
    }

    public Integer getsRatingsid() {
        return sRatingsid;
    }

    public void setsRatingsid(Integer sRatingsid) {
        this.sRatingsid = sRatingsid;
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

    public Integer getRatingValue() {
        return ratingValue;
    }

    public void setRatingValue(Integer ratingValue) {
        this.ratingValue = ratingValue;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (sRatingsid != null ? sRatingsid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ratings)) {
            return false;
        }
        Ratings other = (Ratings) object;
        if ((this.sRatingsid == null && other.sRatingsid != null) || (this.sRatingsid != null && !this.sRatingsid.equals(other.sRatingsid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.sStallStaff[ id=" + sRatingsid + " ]";
    }

}
