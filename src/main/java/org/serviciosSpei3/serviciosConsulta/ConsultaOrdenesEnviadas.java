package org.serviciosSpei3.serviciosConsulta;


import java.io.IOException;

public class ConsultaOrdenesEnviadas extends BaseServicioConsulta {
    private static final String servicio = "/api/v1/ordenes/envios/consulta?id=";
    private String propositoConsulta;
    public ConsultaOrdenesEnviadas(String propositoConsulta) {
        this.propositoConsulta=propositoConsulta;
    }

    @Override
    public String buildUrl(String claveRastreo) throws IOException {
        return getUrlServicioBase()+servicio + claveRastreo;
    }

    protected void guardarOrdenesEnviadas(String respuesta) {
        guardarOrdenes(respuesta, "ConsultaOrdenesEnviadas"+this.propositoConsulta+".txt", true);
    }
}
