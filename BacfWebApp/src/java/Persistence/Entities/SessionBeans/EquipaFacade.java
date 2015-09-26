/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistence.Entities.SessionBeans;

import Persistence.Entities.Equipa;
import Persistence.Entities.Estabelecimento;
import Persistence.Entities.Voluntario;
import Persistence.Entities.VoluntarioEquipa;
import java.util.ArrayList;
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
public class EquipaFacade extends AbstractFacade<Equipa> {

    @PersistenceContext(unitName = "BacfWebAppPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EquipaFacade() {
        super(Equipa.class);
    }

    public List<Voluntario> findVoluntarioByEstabelecimentoID(Estabelecimento estabelecimento) {
        List<Equipa> equipas = null;
        try {
            Query queryEquipa = em.createNamedQuery("Equipa.findByEstabelecimentoID", Equipa.class);
            queryEquipa.setParameter("estabelecimentoID", estabelecimento);
            equipas = queryEquipa.getResultList();
        } catch (javax.persistence.NoResultException ex) {
        } catch (javax.ejb.EJBException e) {
        }
        
        List<Voluntario> voluntarios = new ArrayList<Voluntario>();

        for (Equipa equipa : equipas) {
            
            for (VoluntarioEquipa voluntarioEquipa : equipa.getVoluntarioEquipaList()) {
                voluntarios.add(voluntarioEquipa.getVoluntarioID());
            }
        }

        return voluntarios;
    }

}
