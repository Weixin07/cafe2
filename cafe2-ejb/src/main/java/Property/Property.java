package Property;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class Property implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer pId;
    private String pSaleType;
    private int pPrice;
    private String pAddress; 
    private String pDescription;
    private String pType;
    private int pSize ;
    private String pFurnish;
    private String pBuild;
    private String pSeller;

    public Property(Integer pId, String pSaleType, int pPrice, String pAddress, String pDescription, String pType, int pSize, String pFurnish, String pBuild, String pSeller) {
        this.pId = pId;
        this.pSaleType = pSaleType;
        this.pPrice = pPrice;
        this.pAddress = pAddress;
        this.pDescription = pDescription;
        this.pType = pType;
        this.pSize = pSize;
        this.pFurnish = pFurnish;
        this.pBuild = pBuild;
        this.pSeller = pSeller;
    }
   
    
     public Property() {
    }


    public Integer getId() {
        return pId;
    }

    public void setId(Integer pId) {
        this.pId = pId;
    }

    public Integer getpId() {
        return pId;
    }

    public void setpId(Integer pId) {
        this.pId = pId;
    }

    public int getpPrice() {
        return pPrice;
    }

    public void setpPrice(int pPrice) {
        this.pPrice = pPrice;
    }

    public String getpAddress() {
        return pAddress;
    }

    public void setpAddress(String pAddress) {
        this.pAddress = pAddress;
    }

    public String getpType() {
        return pType;
    }

    public void setpType(String pType) {
        this.pType = pType;
    }

    public int getpSize() {
        return pSize;
    }

    public void setpSize(int pSize) {
        this.pSize = pSize;
    }

    public String getpFurnish() {
        return pFurnish;
    }

    public void setpFurnish(String pFurnish) {
        this.pFurnish = pFurnish;
    }

    public String getpBuild() {
        return pBuild;
    }

    public void setpBuild(String pBuild) {
        this.pBuild = pBuild;
    }

    public String getpSeller() {
        return pSeller;
    }

    public void setpSeller(String pSeller) {
        this.pSeller = pSeller;
    }

    public String getpSaleType() {
        return pSaleType;
    }

    public void setpSaleType(String pSaleType) {
        this.pSaleType = pSaleType;
    }

    public String getpDescription() {
        return pDescription;
    }

    public void setpDescription(String pDescription) {
        this.pDescription = pDescription;
    }
    
    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pId != null ? pId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the pId fields are not set
        if (!(object instanceof Property)) {
            return false;
        }
        Property other = (Property) object;
        if ((this.pId == null && other.pId != null) || (this.pId != null && !this.pId.equals(other.pId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Property.NewEntity[ pId=" + pId + " ]";
    }
    
}
