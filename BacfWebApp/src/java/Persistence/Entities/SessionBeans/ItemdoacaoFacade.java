/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Persistence.Entities.SessionBeans;

import Persistence.Entities.Itemdoacao;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Marcos Magalhaes
 */
@Stateless
public class ItemdoacaoFacade extends AbstractFacade<Itemdoacao> {
    @PersistenceContext(unitName = "BacfWebAppPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ItemdoacaoFacade() {
        super(Itemdoacao.class);
    }
    
}
