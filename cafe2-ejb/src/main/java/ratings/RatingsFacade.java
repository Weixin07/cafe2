package ratings;

import jakarta.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import others.AbstractFacade;

@Stateless
public class RatingsFacade extends AbstractFacade<Ratings> {

    @PersistenceContext(unitName = "cafe2_persistence")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RatingsFacade() {
        super(Ratings.class);
    }
    
}
