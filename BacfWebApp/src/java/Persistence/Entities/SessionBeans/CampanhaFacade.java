/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistence.Entities.SessionBeans;

import Persistence.Entities.Campanha;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author António Silva
 */
@Stateless
public class CampanhaFacade extends AbstractFacade<Campanha> {

    @PersistenceContext(unitName = "BacfWebAppPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CampanhaFacade() {
        super(Campanha.class);
    }

    public Campanha findByDateNow() {
        Campanha campanha = null;

        try {

            Query queryCampanha = em.createNamedQuery("Campanha.findByDateNow", Campanha.class);
            campanha = (Campanha) queryCampanha.getSingleResult();
        } catch (javax.persistence.NoResultException ex) {
        } catch (javax.ejb.EJBException e) {
        }

        if (campanha == null) {
            campanha = new Campanha();
        }
        
        return campanha;
    }

}
