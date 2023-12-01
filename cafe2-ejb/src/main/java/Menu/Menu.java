package Menu;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Menu implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer menuID;
    private String menuName;
    private float price;
    private String ssUsername;

    public Menu(Integer menuID, String menuName, float price, String ssUsername) {
        this.menuID = menuID;
        this.menuName = menuName;
        this.price = price;
        this.ssUsername = ssUsername;
    }

    public Menu() {
    }

    public Integer getMenuID() {
        return menuID;
    }

    public void setMenuID(Integer menuID) {
        this.menuID = menuID;
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

    public String getSsUsername() {
        return ssUsername;
    }

    public void setSsUsername(String ssUsername) {
        this.ssUsername = ssUsername;
    }



    @Override
    public int hashCode() {
        int hash = 0;
        hash += (menuID != null ? menuID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the pId fields are not set
        if (!(object instanceof Menu)) {
            return false;
        }
        Menu other = (Menu) object;
        return !((this.menuID == null && other.menuID != null) || (this.menuID != null && !this.menuID.equals(other.menuID)));
    }

    @Override
    public String toString() {
        return "Menu.Menu[ menuID=" + menuID + " ]";
    }

}
