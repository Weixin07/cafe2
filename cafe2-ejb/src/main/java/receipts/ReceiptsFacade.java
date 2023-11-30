package receipts;

import jakarta.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import others.AbstractFacade;

@Stateless
public class ReceiptsFacade extends AbstractFacade<Receipts> {

    @PersistenceContext(unitName = "cafe2_persistence")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ReceiptsFacade() {
        super(Receipts.class);
    }
    
}
