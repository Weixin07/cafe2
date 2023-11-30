package cafeAdmin;

import jakarta.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import others.AbstractFacade;

@Stateless
public class CafeAdminFacade extends AbstractFacade<CafeAdmin> {

    @PersistenceContext(unitName = "cafe2_persistence")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CafeAdminFacade() {
        super(CafeAdmin.class);
    }
    
}

