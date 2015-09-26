/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webservice;

import Persistence.Entities.Material;
import Persistence.Entities.Relogioponto;
import Persistence.Entities.VoluntarioMaterial;
import Persistence.Entities.VoluntarioMaterialPK;
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
import javax.ws.rs.core.PathSegment;

/**
 *
 * @author António Silva
 */
@Stateless
@Path("persistence.entities.voluntariomaterial")
public class VoluntarioMaterialFacadeREST extends AbstractFacade<VoluntarioMaterial> {

    @PersistenceContext(unitName = "BacfWebAppPU")
    private EntityManager em;

    private VoluntarioMaterialPK getPrimaryKey(PathSegment pathSegment) {
        /*
         * pathSemgent represents a URI path segment and any associated matrix parameters.
         * URI path part is supposed to be in form of 'somePath;voluntarioMaterialID=voluntarioMaterialIDValue;relogioPontoID=relogioPontoIDValue;materialID=materialIDValue'.
         * Here 'somePath' is a result of getPath() method invocation and
         * it is ignored in the following code.
         * Matrix parameters are used as field names to build a primary key instance.
         */
        Persistence.Entities.VoluntarioMaterialPK key = new Persistence.Entities.VoluntarioMaterialPK();
        javax.ws.rs.core.MultivaluedMap<String, String> map = pathSegment.getMatrixParameters();
        java.util.List<String> voluntarioMaterialID = map.get("voluntarioMaterialID");
        if (voluntarioMaterialID != null && !voluntarioMaterialID.isEmpty()) {
            key.setVoluntarioMaterialID(new java.lang.Integer(voluntarioMaterialID.get(0)));
        }
        java.util.List<String> relogioPontoID = map.get("relogioPontoID");
        if (relogioPontoID != null && !relogioPontoID.isEmpty()) {
            key.setRelogioPontoID(new java.lang.Integer(relogioPontoID.get(0)));
        }
        java.util.List<String> materialID = map.get("materialID");
        if (materialID != null && !materialID.isEmpty()) {
            key.setMaterialID(new java.lang.Integer(materialID.get(0)));
        }
        return key;
    }

    public VoluntarioMaterialFacadeREST() {
        super(VoluntarioMaterial.class);
    }

    @POST
    @Override
    @Consumes({"application/xml", "application/json"})
    public void create(VoluntarioMaterial entity) {
        super.create(entity);
    }

    @PUT
    @Path("Material/{relogiopontoid}&{materialid}")
    @Consumes({"application/xml", "application/json"})
    public void updateEntregueMaterials(@PathParam("relogiopontoid") Integer relogiopontoid, @PathParam("materialid") Integer materialid) {
        Relogioponto relogioponto = null;
        Material material = null;
        List<VoluntarioMaterial> voluntarioMaterials = null;

        Query queryMaterial = em.createNamedQuery("Material.findByMaterialID", Material.class);
        queryMaterial.setParameter("materialID", materialid);

        material = (Material) queryMaterial.getSingleResult();

        Query queryRelogioPonto = em.createNamedQuery("Relogioponto.findByRelogioPontoID", Relogioponto.class);
        queryRelogioPonto.setParameter("relogioPontoID", relogiopontoid);

        relogioponto = (Relogioponto) queryRelogioPonto.getSingleResult();

        Query queryVoluntarioMaterial = em.createNamedQuery("VoluntarioMaterial.findByMaterialIRelogioPontoID", VoluntarioMaterial.class);
        queryVoluntarioMaterial.setParameter("materialID", materialid);
        queryVoluntarioMaterial.setParameter("relogioPontoID", relogiopontoid);

        voluntarioMaterials = queryVoluntarioMaterial.getResultList();
        voluntarioMaterials.get(voluntarioMaterials.size() - 1).setEntregue(true);

        super.edit(voluntarioMaterials.get(voluntarioMaterials.size() - 1));

    }

    @POST
    @Path("{materialid}&{relogiopontoid}")
    @Consumes({"application/xml", "application/json"})
    public void registarMaterialCedido(@PathParam("materialid") Integer materialid, @PathParam("relogiopontoid") Integer relogiopontoid) {
        Material material = null;
        Relogioponto relogioponto = null;

        Query queryMaterial = em.createNamedQuery("Material.findByMaterialID", Material.class);
        queryMaterial.setParameter("materialID", materialid);

        material = (Material) queryMaterial.getSingleResult();

        Query queryRelogioPonto = em.createNamedQuery("Relogioponto.findByRelogioPontoID", Relogioponto.class);
        queryRelogioPonto.setParameter("relogioPontoID", relogiopontoid);

        VoluntarioMaterial entity = new VoluntarioMaterial();

        VoluntarioMaterialPK voluntarioMaterialPK = new VoluntarioMaterialPK(0, relogiopontoid, materialid);

        voluntarioMaterialPK.setVoluntarioMaterialID(voluntarioMaterialPK.hashCode());

        entity.setVoluntarioMaterialPK(voluntarioMaterialPK);
        entity.setMaterial(material);
        entity.setRelogioponto(relogioponto);
        entity.setEntregue(false);

        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({"application/xml", "application/json"})
    public void edit(@PathParam("id") PathSegment id, VoluntarioMaterial entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") PathSegment id) {
        Persistence.Entities.VoluntarioMaterialPK key = getPrimaryKey(id);
        super.remove(super.find(key));
    }

    @GET
    @Path("{id}")
    @Produces({"application/xml", "application/json"})
    public VoluntarioMaterial find(@PathParam("id") PathSegment id) {
        Persistence.Entities.VoluntarioMaterialPK key = getPrimaryKey(id);
        return super.find(key);
    }

    @GET
    @Path("relogioponto/{id}")
    @Produces({"application/xml", "application/json"})
    public List<VoluntarioMaterial> findByRelogioPonto(@PathParam("id") Integer id) {
        Relogioponto relogioponto = null;
        List<VoluntarioMaterial> voluntarioMaterials = null;

        Query queryRelogioPonto = em.createNamedQuery("Relogioponto.findByRelogioPontoID", Relogioponto.class);
        queryRelogioPonto.setParameter("relogioPontoID", id);

        relogioponto = (Relogioponto) queryRelogioPonto.getSingleResult();

        Query query = em.createNamedQuery("VoluntarioMaterial.findByRelogioPontoID", VoluntarioMaterial.class);
        query.setParameter("relogioPontoID", relogioponto.getRelogioPontoID());

        voluntarioMaterials = query.getResultList();

        return voluntarioMaterials;
    }

    @GET
    @Override
    @Produces({"application/xml", "application/json"})
    public List<VoluntarioMaterial> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({"application/xml", "application/json"})
    public List<VoluntarioMaterial> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
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

}
