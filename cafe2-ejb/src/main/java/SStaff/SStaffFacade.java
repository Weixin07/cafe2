package SStaff;

import Other.AbstractFacade;
import jakarta.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class SStaffFacade extends AbstractFacade<SStaff> {

    @PersistenceContext(unitName = "Cafe_PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SStaffFacade() {
        super(SStaff.class);
    }
    
}
