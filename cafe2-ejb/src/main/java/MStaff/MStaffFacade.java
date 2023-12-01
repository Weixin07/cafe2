package MStaff;

import Other.AbstractFacade;
import jakarta.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class MStaffFacade extends AbstractFacade<MStaff> {

    @PersistenceContext(unitName = "Cafe_PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MStaffFacade() {
        super(MStaff.class);
    }
    
}
