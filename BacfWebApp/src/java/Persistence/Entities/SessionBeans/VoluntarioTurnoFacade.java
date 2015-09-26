/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistence.Entities.SessionBeans;

import Persistence.Entities.Turno;
import Persistence.Entities.Voluntario;
import Persistence.Entities.VoluntarioTurno;
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
public class VoluntarioTurnoFacade extends AbstractFacade<VoluntarioTurno> {

    @PersistenceContext(unitName = "BacfWebAppPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public VoluntarioTurnoFacade() {
        super(VoluntarioTurno.class);
    }

    public List<Voluntario> findVoluntariosByTurnoID(Turno turnoID) {
        List<VoluntarioTurno> voluntariosTurno = null;

        try {
            Query queryEstabelecimento = em.createNamedQuery("VoluntarioTurno.findByTurnoID", VoluntarioTurno.class);
            queryEstabelecimento.setParameter("turnoID", turnoID);
            voluntariosTurno = queryEstabelecimento.getResultList();
        } catch (javax.persistence.NoResultException ex) {
        } catch (javax.ejb.EJBException e) {
        }
        
        List<Voluntario> voluntarios = new ArrayList<Voluntario>();
        
        for (VoluntarioTurno voluntarioTurno : voluntariosTurno) {
            voluntarios.add(voluntarioTurno.getVoluntarioID());
        }
        
        return  voluntarios;
    }
    
    public List<VoluntarioTurno> findByTurnoID(Turno turnoID) {
        List<VoluntarioTurno> voluntariosTurno = null;

        try {
            Query queryEstabelecimento = em.createNamedQuery("VoluntarioTurno.findByTurnoID", VoluntarioTurno.class);
            queryEstabelecimento.setParameter("turnoID", turnoID);
            voluntariosTurno = queryEstabelecimento.getResultList();
        } catch (javax.persistence.NoResultException ex) {
        } catch (javax.ejb.EJBException e) {
        }
               
        return  voluntariosTurno;
    }

}
