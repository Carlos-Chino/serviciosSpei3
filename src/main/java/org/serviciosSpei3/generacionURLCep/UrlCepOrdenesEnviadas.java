package org.serviciosSpei3.generacionURLCep;
import org.serviciosSpei3.serviciosConsulta.BaseServicioConsulta;
import java.io.IOException;

public class UrlCepOrdenesEnviadas extends BaseServicioConsulta {
    private static final String urlServicio = "/api/v1/ordenes/envios/url-cep?id=";

    @Override
    protected String buildUrl(String claveRastreo) throws IOException {
        return getUrlServicioBase()+urlServicio+ claveRastreo;
    }

    public void guardarUrlCepOrdenesEnviadas(String respuesta) {
        guardarOrdenes(respuesta, "ConsultaOrdenesRecibidas.txt", true);
    }


    @Override
    protected String getTipoConsulta() {
        return "consulta";
    }
}
