/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reference;

import others.AbstractFacade;
import java.util.List;
import jakarta.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author pc
 */
@Stateless
public class OfferFacade extends AbstractFacade<Offer> {

    @PersistenceContext(unitName = "Property_Management_System-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public OfferFacade() {
        super(Offer.class);
    }
    
    public void deleteOffersByPropertyId(int pId) {
        Query query = em.createQuery("DELETE FROM Offer o WHERE o.pId = :pId AND o.oStatus = 'P'");
        query.setParameter("pId", pId);
        query.executeUpdate();
    }
    
    public int getLastOfferId() {
        Query query = em.createQuery("SELECT o.oId FROM Offer o ORDER BY o.oId DESC");
        query.setMaxResults(1);
        List<Integer> results = query.getResultList();

        if (!results.isEmpty()) {
            return results.get(0);
        } else {
            return -1; // Return a default value or handle accordingly
        }
    }
    
}
