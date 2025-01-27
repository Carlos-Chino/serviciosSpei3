package org.serviciosSpei3.generacionURLCep;
import org.serviciosSpei3.registroDeOrdenes.GuardarRegistroOperacion;
import org.serviciosSpei3.serviciosConsulta.BaseServicioConsulta;
import java.io.IOException;

public class UrlCepOrdenesEnviadas extends BaseServicioConsulta {
    private static final String urlServicio = "/api/v1/ordenes/envios/url-cep?id=";

    public UrlCepOrdenesEnviadas() {
        super(null);
    }

    @Override
    protected String buildUrl(String claveRastreo) throws IOException {
        return getUrlServicioBase()+urlServicio+ claveRastreo;
    }


    public void procesarRespuesta(String respuesta) {
        new GuardarRegistroOperacion(respuesta,"UrlCepOrdenesEnviadas.txt","UrlCep");
    }

    @Override
    protected String getTipoConsulta() {
        return "search";
    }
}
