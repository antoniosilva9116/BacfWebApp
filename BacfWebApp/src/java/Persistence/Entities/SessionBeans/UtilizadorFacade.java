/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistence.Entities.SessionBeans;

import Persistence.Entities.Controllers.util.JsfUtil;
import Persistence.Entities.Utilizador;
import java.util.ResourceBundle;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import javax.persistence.QueryTimeoutException;

/**
 *
 * @author António Silva
 */
@Stateless
public class UtilizadorFacade extends AbstractFacade<Utilizador> {

    @PersistenceContext(unitName = "BacfWebAppPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UtilizadorFacade() {
        super(Utilizador.class);
    }

    public Utilizador findUtilizadorByEmail(String email) {
        Utilizador utilizador = null;
        try {
            Query query = em.createNamedQuery("Utilizador.findByEmail", Utilizador.class);
            query.setParameter("email", email);

            System.out.println("QUERY BY EMAIL " + query.toString());

            utilizador = (Utilizador) query.getSingleResult();
        } catch (NoResultException | NonUniqueResultException | IllegalStateException | QueryTimeoutException ex) {
            JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("../resources/Bundle").getString("EmailIncorrect"));

        } catch (PersistenceException ex) {
            JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("../resources/Bundle").getString("EmailIncorrect"));
        }
        return utilizador;
    }

}
