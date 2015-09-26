/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package webservice;

import Persistence.Entities.Modulogrupo;
import Persistence.Entities.ModulogrupoPK;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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
@Path("persistence.entities.modulogrupo")
public class ModulogrupoFacadeREST extends AbstractFacade<Modulogrupo> {
    @PersistenceContext(unitName = "BacfWebAppPU")
    private EntityManager em;

    private ModulogrupoPK getPrimaryKey(PathSegment pathSegment) {
        /*
         * pathSemgent represents a URI path segment and any associated matrix parameters.
         * URI path part is supposed to be in form of 'somePath;moduloGrupoID=moduloGrupoIDValue;grupoID=grupoIDValue;moduloID=moduloIDValue'.
         * Here 'somePath' is a result of getPath() method invocation and
         * it is ignored in the following code.
         * Matrix parameters are used as field names to build a primary key instance.
         */
        Persistence.Entities.ModulogrupoPK key = new Persistence.Entities.ModulogrupoPK();
        javax.ws.rs.core.MultivaluedMap<String, String> map = pathSegment.getMatrixParameters();
        java.util.List<String> moduloGrupoID = map.get("moduloGrupoID");
        if (moduloGrupoID != null && !moduloGrupoID.isEmpty()) {
            key.setModuloGrupoID(new java.lang.Integer(moduloGrupoID.get(0)));
        }
        java.util.List<String> grupoID = map.get("grupoID");
        if (grupoID != null && !grupoID.isEmpty()) {
            key.setGrupoID(new java.lang.Integer(grupoID.get(0)));
        }
        java.util.List<String> moduloID = map.get("moduloID");
        if (moduloID != null && !moduloID.isEmpty()) {
            key.setModuloID(new java.lang.Integer(moduloID.get(0)));
        }
        return key;
    }

    public ModulogrupoFacadeREST() {
        super(Modulogrupo.class);
    }

    @POST
    @Override
    @Consumes({"application/xml", "application/json"})
    public void create(Modulogrupo entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({"application/xml", "application/json"})
    public void edit(@PathParam("id") PathSegment id, Modulogrupo entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") PathSegment id) {
        Persistence.Entities.ModulogrupoPK key = getPrimaryKey(id);
        super.remove(super.find(key));
    }

    @GET
    @Path("{id}")
    @Produces({"application/xml", "application/json"})
    public Modulogrupo find(@PathParam("id") PathSegment id) {
        Persistence.Entities.ModulogrupoPK key = getPrimaryKey(id);
        return super.find(key);
    }

    @GET
    @Override
    @Produces({"application/xml", "application/json"})
    public List<Modulogrupo> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({"application/xml", "application/json"})
    public List<Modulogrupo> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
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
