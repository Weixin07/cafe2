package sOrder;

import Other.AbstractFacade;
import java.util.List;
import jakarta.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class SOrderFacade extends AbstractFacade<SOrder> {

    @PersistenceContext(unitName = "Cafe_PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SOrderFacade() {
        super(SOrder.class);
    }

}
