/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistence.Entities.SessionBeans;

import Persistence.Entities.Voluntario;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author António Silva
 */
@Stateless
public class VoluntarioFacade extends AbstractFacade<Voluntario> {

    @PersistenceContext(unitName = "BacfWebAppPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public VoluntarioFacade() {
        super(Voluntario.class);
    }

    public Voluntario findByEmail(String email) {
        Voluntario voluntario = null;
        Query query = em.createNamedQuery("Voluntario.findByLetterEmail", Voluntario.class);
        query.setParameter("email", email + "%");

        voluntario = (Voluntario) query.getSingleResult();

        return voluntario;
    }

}
