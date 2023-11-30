package stallStaff;

import jakarta.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import others.AbstractFacade;

@Stateless
public class StallStaffFacade extends AbstractFacade<StallStaff> {

    @PersistenceContext(unitName = "cafe2_persistence")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public StallStaffFacade() {
        super(StallStaff.class);
    }
    
}
