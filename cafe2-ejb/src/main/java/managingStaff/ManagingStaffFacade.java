package managingStaff;

import jakarta.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import others.AbstractFacade;

@Stateless
public class ManagingStaffFacade extends AbstractFacade<ManagingStaff> {

    @PersistenceContext(unitName = "cafe2_persistence")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ManagingStaffFacade() {
        super(ManagingStaff.class);
    }
    
}
