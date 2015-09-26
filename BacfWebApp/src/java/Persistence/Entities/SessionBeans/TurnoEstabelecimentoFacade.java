/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistence.Entities.SessionBeans;

import Persistence.Entities.Estabelecimento;
import Persistence.Entities.Turno;
import Persistence.Entities.TurnoEstabelecimento;
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
public class TurnoEstabelecimentoFacade extends AbstractFacade<TurnoEstabelecimento> {

    @PersistenceContext(unitName = "BacfWebAppPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TurnoEstabelecimentoFacade() {
        super(TurnoEstabelecimento.class);
    }

    public Estabelecimento findEstabelecimentoByTurnoID(Turno turnoID) {
        List<TurnoEstabelecimento> turnosEstabelecimento = null;

        try {
            Query queryEstabelecimento = em.createNamedQuery("TurnoEstabelecimento.findByTurnoID", TurnoEstabelecimento.class);
            queryEstabelecimento.setParameter("turnoID", turnoID);
            turnosEstabelecimento = queryEstabelecimento.getResultList();
        } catch (javax.persistence.NoResultException ex) {
        } catch (javax.ejb.EJBException e) {
        }

        return turnosEstabelecimento.get(0).getEstabelecimentoID();
    }

    public List<Turno> findTurnosByEstabelecimentoID(Estabelecimento estabelecimentoID) {
        List<TurnoEstabelecimento> turnosEstabelecimento = null;

        try {
            Query queryEstabelecimento = em.createNamedQuery("TurnoEstabelecimento.findByEstabelecimentoID", TurnoEstabelecimento.class);
            queryEstabelecimento.setParameter("estabelecimentoID", estabelecimentoID);
            turnosEstabelecimento = queryEstabelecimento.getResultList();
        } catch (javax.persistence.NoResultException ex) {
        } catch (javax.ejb.EJBException e) {
        }

        List<Turno> turnos = new ArrayList<Turno>();

        for (TurnoEstabelecimento turnoEstabelecimento : turnosEstabelecimento) {
            turnos.add(turnoEstabelecimento.getTurnoID());
        }

        return turnos;
    }

}
