package reference;

import jakarta.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import others.AbstractFacade;

@Stateless
public class sStallStaffFacade extends AbstractFacade<sStallStaff> {

    @PersistenceContext(unitName = "com.mycompany_cafe-ejb_ejb_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public sStallStaffFacade() {
        super(sStallStaff.class);
    }
    
}
