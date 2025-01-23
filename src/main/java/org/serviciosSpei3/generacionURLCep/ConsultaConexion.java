package org.serviciosSpei3.generacionURLCep;

import org.serviciosSpei3.serviciosConsulta.BaseServicioConsulta;

import java.io.IOException;

public class ConsultaConexion extends BaseServicioConsulta {
    private String claveInstitucion;
    private String instancia;

    public ConsultaConexion(String claveInstitucion, String instancia) {
        this.claveInstitucion = claveInstitucion;
        this.instancia = instancia;
    }

    @Override
    protected String buildUrl() throws IOException {
        return getUrlServicioBase()+"/api/v1/instituciones/"+this.claveInstitucion+"/"+this.instancia+"/sesion";
    }

    @Override
    protected String getTipoConsulta() {
        return "";
    }
}
