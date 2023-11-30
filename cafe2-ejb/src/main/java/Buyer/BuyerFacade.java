/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Buyer;

import CommonModels.AbstractFacade;
import jakarta.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author pc
 */
@Stateless
public class BuyerFacade extends AbstractFacade<Buyer> {

    @PersistenceContext(unitName = "Property_Management_System-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public BuyerFacade() {
        super(Buyer.class);
    }
    
}