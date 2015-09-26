/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Persistence.Entities.SessionBeans;

import Persistence.Entities.Email;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author António Silva
 */
@Stateless
public class EmailFacade extends AbstractFacade<Email> {
    @PersistenceContext(unitName = "BacfWebAppPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EmailFacade() {
        super(Email.class);
    }
    
}
