package reference;

import jakarta.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import others.AbstractFacade;

@Stateless
public class sCustomerFacade extends AbstractFacade<sCustomer> {

    @PersistenceContext(unitName = "com.mycompany_cafe-ejb_ejb_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public sCustomerFacade() {
        super(sCustomer.class);
    }
    
}
