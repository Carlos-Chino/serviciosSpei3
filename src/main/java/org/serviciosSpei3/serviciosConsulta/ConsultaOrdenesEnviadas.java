package org.serviciosSpei3.serviciosConsulta;


public class ConsultaOrdenesEnviadas extends BaseServicioConsulta {
    private static final String urlServicio = "https://spei-core-stp-core-pruebas-apps-spei-asa.stp-ti.com/api/v1/ordenes/envios/consulta?id=";

    @Override
    public String buildUrl(String claveRastreo) {
        return urlServicio + claveRastreo;
    }

    @Override
    protected String getTipoConsulta() {
        return "consulta";
    }

    protected void guardarOrdenesEnviadas(String respuesta) {
        guardarOrdenes(respuesta, "ConsultaOrdenesEnviadas.txt", true);
    }
}
