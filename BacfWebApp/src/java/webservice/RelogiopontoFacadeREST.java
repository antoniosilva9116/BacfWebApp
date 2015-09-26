/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webservice;

import Persistence.Entities.Campanha;
import Persistence.Entities.Controllers.util.JsfUtil;
import Persistence.Entities.Relogioponto;
import Persistence.Entities.Voluntario;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import javax.persistence.QueryTimeoutException;
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
@Path("persistence.entities.relogioponto")
public class RelogiopontoFacadeREST extends AbstractFacade<Relogioponto> {

    @PersistenceContext(unitName = "BacfWebAppPU")
    private EntityManager em;

    public RelogiopontoFacadeREST() {
        super(Relogioponto.class);
    }

    @POST
    @Override
    @Consumes({"application/xml", "application/json"})
    public void create(Relogioponto entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({"application/xml", "application/json"})
    public void edit(@PathParam("id") Integer id, Relogioponto entity) {
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
    public Relogioponto find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({"application/xml", "application/json"})
    public List<Relogioponto> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({"application/xml", "application/json"})
    public List<Relogioponto> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces("text/plain")
    public String countREST() {
        return String.valueOf(super.count());
    }

    @POST
    @Path("test")
    @Consumes({"application/xml", "application/json"})
    public void test() {
        System.out.println("CHEGUEI");
    }

    @GET
    @Path("voluntarios/entradas")
    @Produces({"application/xml", "application/json"})
    public List<Voluntario> findByVoluntariosEntradasByDateNow() {
        List<Voluntario> voluntarios = new ArrayList<Voluntario>();
        List<Relogioponto> relogiosponto = null;

        try {
            Query queryRelogioPonto = em.createNamedQuery("Relogioponto.findVoluntariosByDateNow", Relogioponto.class);

            relogiosponto = queryRelogioPonto.getResultList();

            for (Relogioponto relogioponto : relogiosponto) {
                if (relogioponto.getTipoRegisto() == false) {
                    voluntarios.add(relogioponto.getVoluntarioID());
                }
            }

        } catch (EJBException e) {

        } catch (NoResultException ex) {

        } catch (NonUniqueResultException ex) {

        } catch (IllegalStateException ex) {

        } catch (QueryTimeoutException ex) {

        } catch (PersistenceException ex) {

        } catch (NullPointerException ex) {

        } catch (Exception ex) {

        }
        return voluntarios;
    }

    @GET
    @Path("voluntarios/saidas")
    @Produces({"application/xml", "application/json"})
    public List<Voluntario> findByVoluntariosSaidasByDateNow() {
        List<Voluntario> voluntarios = new ArrayList<Voluntario>();
        List<Relogioponto> relogiosponto = null;

        try {
            Query queryRelogioPonto = em.createNamedQuery("Relogioponto.findVoluntariosByDateNow", Relogioponto.class);

            relogiosponto = queryRelogioPonto.getResultList();
            System.out.println("\n-----------------------\nRelogio" + relogiosponto.size());
            for (Relogioponto relogioponto : relogiosponto) {
                if (relogioponto.getTipoRegisto() == true) {
                    voluntarios.add(relogioponto.getVoluntarioID());
                }
            }

        } catch (EJBException e) {

        } catch (NoResultException ex) {

        } catch (NonUniqueResultException ex) {

        } catch (IllegalStateException ex) {

        } catch (QueryTimeoutException ex) {

        } catch (PersistenceException ex) {

        } catch (NullPointerException ex) {

        } catch (Exception ex) {

        }
        return voluntarios;
    }

    @GET
    @Path("VoluntarioCampanha/{id}")
    @Produces({"application/xml", "application/json"})
    public Relogioponto findByVoluntarioIDDataRegisto(@PathParam("id") Integer id) {
        Voluntario voluntario = null;
        Relogioponto relogioponto = null;

        try {

            Query queryVoluntario = em.createNamedQuery("Voluntario.findByVoluntarioID", Voluntario.class);
            queryVoluntario.setParameter("voluntarioID", id);

            voluntario = (Voluntario) queryVoluntario.getSingleResult();

            Query queryRelogioPonto = em.createNamedQuery("Relogioponto.findByVoluntarioIDDataRegisto", Relogioponto.class);

            queryRelogioPonto.setParameter("voluntarioID", voluntario);

            List<Relogioponto> relogiopontos = queryRelogioPonto.getResultList();
            relogioponto = relogiopontos.get(relogiopontos.size() - 1);

        } catch (EJBException e) {

        } catch (NoResultException ex) {

        } catch (NonUniqueResultException ex) {

        } catch (IllegalStateException ex) {

        } catch (QueryTimeoutException ex) {

        } catch (PersistenceException ex) {

        } catch (NullPointerException ex) {

        } catch (Exception ex) {

        }
        return relogioponto;
    }

    @POST
    @Path("RegistarEntradaVoluntario/{id}")
    @Consumes({"application/xml", "application/json"})
    public String registarEntrada(@PathParam("id") Integer id) {
        Campanha campanha = null;
        Voluntario voluntario = null;

        Query queryCampanha = em.createNamedQuery("Campanha.findByDateNow", Campanha.class
        );

        campanha = (Campanha) queryCampanha.getSingleResult();

        Query queryVoluntario = em.createNamedQuery("Voluntario.findByVoluntarioID", Voluntario.class);

        queryVoluntario.setParameter(
                "voluntarioID", id);

        voluntario = (Voluntario) queryVoluntario.getSingleResult();

        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date dataAtual = new Date();

        Relogioponto entity = new Relogioponto(0);

        try {
            entity.setDataRegisto(dateFormat.parse(dateFormat.format(dataAtual)));
            System.out.println("" + entity.getDataRegisto());
        } catch (ParseException ex) {
            Logger.getLogger(RelogiopontoFacadeREST.class.getName()).log(Level.SEVERE, null, ex);
        }

        entity.setVoluntarioID(voluntario);

        entity.setTipoRegisto(false);
        entity.setCampanhaID(campanha);
        /* Set de Material List */

        super.create(entity);

        return entity.getRelogioPontoID()
                .toString();
    }

    @PUT
    @Path("RegistarSaidaVoluntario/{id}")
    @Consumes({"application/xml", "application/json"})
    public void registarSaida(@PathParam("id") Integer id) {
        Voluntario voluntario = null;
        Relogioponto relogioponto = null;

        try {

            Query queryVoluntario = em.createNamedQuery("Voluntario.findByVoluntarioID", Voluntario.class
            );
            queryVoluntario.setParameter(
                    "voluntarioID", id);

            voluntario = (Voluntario) queryVoluntario.getSingleResult();

            Date date = new Date();

            Query queryRelogioPonto = em.createNamedQuery("Relogioponto.findByVoluntarioID", Relogioponto.class);

            queryRelogioPonto.setParameter(
                    "voluntarioID", voluntario);

            List<Relogioponto> relogiopontos = queryRelogioPonto.getResultList();

            relogioponto = relogiopontos.get(relogiopontos.size() - 1);

            relogioponto.setTipoRegisto(true);
        } catch (EJBException e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/resources/Bundle").getString("EmailIncorrect"));
        } catch (NoResultException ex) {

        } catch (NonUniqueResultException ex) {

        } catch (IllegalStateException ex) {

        } catch (QueryTimeoutException ex) {

        } catch (PersistenceException ex) {

        } catch (NullPointerException ex) {

        } catch (Exception ex) {

        }
        /* Set de Material List */

        super.edit(relogioponto);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

 
    
@GET
    @Path("list")
    @Produces("text/plain")
    public String getVoluntariosEmCampanha() {
        List<Relogioponto> lista = super.findAll();
        Integer count = 0;
        Integer count1 = 0;
        for (Relogioponto v : lista) {
            System.out.println("Sexo: " + v.getVoluntarioID().getSexo() + "");
            if (v.getVoluntarioID().getSexo().equals("Feminino")) {
                count++;
            } else {
                count1++;
            }
        }
        System.out.println(count1 + " " + count);
        return count1 + " " + count;
    }
    
    @GET
    @Path("moreThan3Hours")
    @Produces("text/plain")
    public String getVoluntariosEmCampanhaMais3Horas() {
        List<Relogioponto> lista = super.findAll();
        Integer count = 0;
        Integer count1 = 0;
        Date nowDate = null;
        long diff = 0;
        for (Relogioponto v : lista) {
            nowDate = new Date();
            System.out.println("NOW:" + nowDate.getTime() );
            System.out.println("Afetr:" + v.getDataRegisto().getTime());
            diff = nowDate.getTime() - v.getDataRegisto().getTime();
            System.out.println("DIFF: " + diff);
            if (diff > 360000*5&&diff>0) {
                System.out.println("Sexo: " + v.getVoluntarioID().getSexo() + "");
                if (v.getVoluntarioID().getSexo().equals("Feminino")) {
                    count++;
                } else {
                    count1++;
                }
            }
        }
        System.out.println(count1 + " " + count);
        return count1 + " " + count;
    }

}
