/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webservice;

import Persistence.Entities.Disponibilidadecampanhas;
import Persistence.Entities.Utilizador;
import Persistence.Entities.Voluntario;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
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
@Path("persistence.entities.disponibilidadecampanhas")
public class DisponibilidadecampanhasFacadeREST extends AbstractFacade<Disponibilidadecampanhas> {

    @PersistenceContext(unitName = "BacfWebAppPU")
    private EntityManager em;

    public DisponibilidadecampanhasFacadeREST() {
        super(Disponibilidadecampanhas.class);
    }

    @POST
    @Override
    @Consumes({"application/xml", "application/json"})
    public void create(Disponibilidadecampanhas entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({"application/xml", "application/json"})
    public void edit(@PathParam("id") Integer id, Disponibilidadecampanhas entity) {
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
    public Disponibilidadecampanhas find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({"application/xml", "application/json"})
    public List<Disponibilidadecampanhas> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({"application/xml", "application/json"})
    public List<Disponibilidadecampanhas> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
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
    
    @PUT
    @Path("/SaveDispByUtilizadorID/{id}")
    @Consumes({"application/xml", "application/json"})
    public void edit1(@PathParam("id") Integer id, Disponibilidadecampanhas entity) {
        
        Voluntario vol = null;        
        Utilizador u = null;
        Disponibilidadecampanhas disp = null;
        
        Query queryUtilizador = em.createNamedQuery("Utilizador.findByUtilizadorID", Utilizador.class);
        queryUtilizador.setParameter("utilizadorID", id);
        u = (Utilizador) queryUtilizador.getSingleResult();
        
        Query query1 = em.createNamedQuery("Voluntario.findByUtilizadorID", Voluntario.class);
        query1.setParameter("utilizadorID", u);
        vol = (Voluntario) query1.getSingleResult();
        
        Query query2 = em.createNamedQuery("Disponibilidadecampanhas.findByVoluntarioID", Disponibilidadecampanhas.class);
        query2.setParameter("voluntarioVoluntarioID", vol);
        disp = (Disponibilidadecampanhas) query2.getSingleResult(); 
        
        disp.setSegunda(entity.getSegunda());
        disp.setTerca(entity.getTerca());
        disp.setQuarta(entity.getQuarta());
        disp.setQuinta(entity.getQuinta());
        disp.setSexta(entity.getSexta());
        disp.setSabado(entity.getSabado());
        disp.setDomingo(entity.getDomingo());
        
        super.edit(disp);
    }

    @GET
    @Path("Voluntario/{id}")
    @Produces({"application/xml", "application/json"})
    public Disponibilidadecampanhas findByUtilizadorID(@PathParam("id") Integer id) {

        Voluntario vol = null;
        Utilizador u = null;
        Disponibilidadecampanhas disp = null;

        Query queryUtilizador = em.createNamedQuery("Utilizador.findByUtilizadorID", Utilizador.class);
        queryUtilizador.setParameter("utilizadorID", id);
        u = (Utilizador) queryUtilizador.getSingleResult();

        Query query1 = em.createNamedQuery("Voluntario.findByUtilizadorID", Voluntario.class);
        query1.setParameter("utilizadorID", u);
        vol = (Voluntario) query1.getSingleResult();

        Query query2 = em.createNamedQuery("Disponibilidadecampanhas.findByVoluntarioID", Disponibilidadecampanhas.class);
        query2.setParameter("voluntarioVoluntarioID", vol);
        disp = (Disponibilidadecampanhas) query2.getSingleResult();

        return disp;
    }

   

}
