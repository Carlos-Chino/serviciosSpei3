package org.serviciosSpei3.serviciosConsulta;

import java.io.IOException;
import java.util.Map;

public class ConsultaConciliacion extends BaseServicioConsulta {
    private String institucion;
    private String instancia;
    public ConsultaConciliacion(String institucion, String instancia) {
        this.institucion = institucion;
        this.instancia = instancia;
    }

    @Override
    protected String buildUrl() throws IOException {
        return getUrlServicioBase() + "/api/v1/instituciones/" + this.institucion + "/" + this.instancia + "/consolidadoTransacconal";
    }
}
