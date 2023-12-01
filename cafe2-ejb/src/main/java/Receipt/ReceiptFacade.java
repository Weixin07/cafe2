package Receipt;

import Other.AbstractFacade;
import jakarta.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class ReceiptFacade extends AbstractFacade<Receipt> {

    @PersistenceContext(unitName = "Property_Management_System-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ReceiptFacade() {
        super(Receipt.class);
    }
    
}
