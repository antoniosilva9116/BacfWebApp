/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webservice;

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
@Path("persistence.entities.voluntario")
public class VoluntarioFacadeREST extends AbstractFacade<Voluntario> {

    @PersistenceContext(unitName = "BacfWebAppPU")
    private EntityManager em;

    public VoluntarioFacadeREST() {
        super(Voluntario.class);
    }

    @POST
    @Override
    @Consumes({"application/xml", "application/json"})
    public void create(Voluntario entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({"application/xml", "application/json"})
    public void edit(@PathParam("id") Integer id, Voluntario entity) {
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
    public Voluntario find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @GET
    @Path("/name/{name}")
    @Produces({"application/xml", "application/json"})
    public List<Voluntario> findByName(@PathParam("name") String name) {

        List<Voluntario> voluntarios = null;
        Query query = em.createNamedQuery("Voluntario.findByLetterNome", Voluntario.class);
        String nome = name.replaceAll("\\s+", "%");
        query.setParameter("nome", nome + '%');

        voluntarios = query.getResultList();

        return voluntarios;
    }

    @GET
    @Path("/email/{email}")
    @Produces({"application/xml", "application/json"})
    public List<Voluntario> findByEmail(@PathParam("email") String email) {
        List<Voluntario> voluntarios = null;
        Query query = em.createNamedQuery("Voluntario.findByLetterEmail", Voluntario.class);
        query.setParameter("email", email + "%");

        voluntarios = query.getResultList();

        return voluntarios;
    }

    @GET
    @Path("/bi/{bi}")
    @Produces({"application/xml", "application/json"})
    public List<Voluntario> findByBI(@PathParam("bi") Integer bi) {

        List<Voluntario> voluntarios = null;
        Query query = em.createNamedQuery("Voluntario.findByDigitBi", Voluntario.class);
        query.setParameter("bi", bi);

        voluntarios = query.getResultList();

        return voluntarios;
    }

    @GET
    @Path("telemovel/{telemovel}")
    @Produces({"application/xml", "application/json"})
    public List<Voluntario> findByTelemovel(@PathParam("telemovel") Integer telemovel) {

        List<Voluntario> voluntarios = null;
        Query query = em.createNamedQuery("Voluntario.findByTelemovel", Voluntario.class);
        query.setParameter("telemovel", telemovel);

        voluntarios = query.getResultList();

        return voluntarios;
    }

    @GET
    @Override
    @Produces({"application/xml", "application/json"})
    public List<Voluntario> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({"application/xml", "application/json"})
    public List<Voluntario> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
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

    @GET
    @Path("list")
    @Produces("text/plain")
    public String getVoluntriosErros() {
        List<Voluntario> lista = super.findAll();
        Integer total = 0;
        Integer count = 0;
        Integer wrong = 0;
        
        for (Voluntario v : lista) {
            if (v.getUtilizadorID() == null) {
                count++;
            }
            if(!verify(v)){
                wrong++;
            }
            total++;
        }
        return total+" "+count+" "+wrong;
    }

    public boolean verify(Voluntario v) {
        if (v.getBi() != null && v.getNome() != null && v.getCorreiosID() != null && v.getDataNascimento() != null && v.getEmail() != null && v.getProfissao() != null && v.getSexo() != null && v.getTelefoneCasa() != null && v.getTelemovel() != null && v.getTelefoneTrabalho() != null && v.getUtilizadorID() != null) {
            return true;
        }
        return false;
    }

}
