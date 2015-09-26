/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webservice;

import Persistence.Entities.Email;
import Persistence.Entities.Utilizador;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

/**
 *
 * @author António Silva
 */
@Stateless
@Path("persistence.entities.email")
public class EmailFacadeREST extends AbstractFacade<Email> {

    @PersistenceContext(unitName = "BacfWebAppPU")
    private EntityManager em;

    public EmailFacadeREST() {
        super(Email.class);
    }

    @POST
    @Override
    @Consumes({"application/xml", "application/json"})
    public void create(Email entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({"application/xml", "application/json"})
    public void edit(@PathParam("id") Integer id, Email entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Integer id) {
        super.remove(super.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({"application/xml", "application/json"})
    public Email find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({"application/xml", "application/json"})
    public List<Email> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({"application/xml", "application/json"})
    public List<Email> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("UtilizadorDestinatario/{utilizadorDestinatarioID}")
    @Produces({"application/xml", "application/json"})
    public List<Email> findByDestinatario(@PathParam("utilizadorDestinatarioID") Integer utilizadorDestinatarioID) {
        List<Email> emails = null;

        Utilizador utilizador = null;
        Query query = em.createNamedQuery("Utilizador.findByUtilizadorID", Utilizador.class);
        query.setParameter("utilizadorID", utilizadorDestinatarioID);

        utilizador = (Utilizador) query.getSingleResult();
        try {
            Query query1 = em.createNamedQuery("Email.findByDestinatarioID", Email.class);
            query1.setParameter("utilizadorDestinatarioID", utilizador);

            emails = query1.getResultList();
        } catch (NoResultException ex) {

        }
        return emails;

    }

    @GET
    @Path("count")
    @Produces("text/plain")
    public String countREST() {
        return String.valueOf(super.count());
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

}
