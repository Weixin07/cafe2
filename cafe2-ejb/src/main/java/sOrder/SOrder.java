package sOrder;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class SOrder implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer OrderID;
    private int menuID;
    private String menuName;
    private float price;
    private int quantity;
    private String cEmail;
    private String ssUsername;

    public SOrder(Integer OrderID, int menuID, String menuName, float price, int quantity, String cEmail, String ssUsername) {
        this.OrderID = OrderID;
        this.menuID = menuID;
        this.menuName = menuName;
        this.price = price;
        this.quantity = quantity;
        this.cEmail = cEmail;
        this.ssUsername = ssUsername;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public SOrder() {

    }

    public Integer getOrderID() {
        return OrderID;
    }

    public void setOrderID(Integer OrderID) {
        this.OrderID = OrderID;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getcEmail() {
        return cEmail;
    }

    public void setcEmail(String cEmail) {
        this.cEmail = cEmail;
    }

    public String getSsUsername() {
        return ssUsername;
    }

    public void setSsUsername(String ssUsername) {
        this.ssUsername = ssUsername;
    }

    public int getMenuID() {
        return menuID;
    }

    public void setMenuID(int menuID) {
        this.menuID = menuID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (OrderID != null ? OrderID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof SOrder)) {
            return false;
        }
        SOrder other = (SOrder) object;
        return !((this.OrderID == null && other.OrderID != null) || (this.OrderID != null && !this.OrderID.equals(other.OrderID)));
    }

    @Override
    public String toString() {
        return "SOrder.SOrder[ SOrder=" + OrderID + " ]";
    }

}
