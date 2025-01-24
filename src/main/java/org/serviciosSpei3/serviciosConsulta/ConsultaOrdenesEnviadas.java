package org.serviciosSpei3.serviciosConsulta;


import java.io.IOException;

public class ConsultaOrdenesEnviadas extends BaseServicioConsulta {
    private static final String servicio = "/api/v1/ordenes/envios/consulta?id=";

    @Override
    public String buildUrl(String claveRastreo) throws IOException {
        return getUrlServicioBase()+servicio + claveRastreo;
    }

    @Override
    protected String getTipoConsulta() {
        return "consulta";
    }

    protected void guardarOrdenesEnviadas(String respuesta) {
        guardarOrdenes(respuesta, "ConsultaOrdenesEnviadas.txt", true);
    }
}
