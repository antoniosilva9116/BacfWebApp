/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package webservice;

import java.util.Set;
import javax.ws.rs.core.Application;

/**
 *
 * @author António Silva
 */
@javax.ws.rs.ApplicationPath("webresources")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    /**
     * Do not modify addRestResourceClasses() method.
     * It is automatically populated with
     * all resources defined in the project.
     * If required, comment out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(webservice.CampanhaFacadeREST.class);
        resources.add(webservice.CorreiosFacadeREST.class);
        resources.add(webservice.DisponibilidadecampanhasFacadeREST.class);
        resources.add(webservice.EmailFacadeREST.class);
        resources.add(webservice.EquipaFacadeREST.class);
        resources.add(webservice.EquipaTurnoFacadeREST.class);
        resources.add(webservice.EstabelecimentoFacadeREST.class);
        resources.add(webservice.GrupoFacadeREST.class);
        resources.add(webservice.HistoricocampanhaFacadeREST.class);
        resources.add(webservice.MaterialFacadeREST.class);
        resources.add(webservice.ModuloFacadeREST.class);
        resources.add(webservice.ModulogrupoFacadeREST.class);
        resources.add(webservice.NoticiaFacadeREST.class);
        resources.add(webservice.RelogiopontoFacadeREST.class);
        resources.add(webservice.TurnoEstabelecimentoFacadeREST.class);
        resources.add(webservice.TurnoFacadeREST.class);
        resources.add(webservice.UtilizadorFacadeREST.class);
        resources.add(webservice.VoluntarioEquipaFacadeREST.class);
        resources.add(webservice.VoluntarioFacadeREST.class);
        resources.add(webservice.VoluntarioMaterialFacadeREST.class);
        resources.add(webservice.VoluntarioTurnoFacadeREST.class);
        resources.add(webservice.VoluntariojuniorFacadeREST.class);
        resources.add(webservice.VoluntariosCampanhasFacadeREST.class);
    }
    
}
