package org.serviciosSpei3.serviciosConsulta;

import org.serviciosSpei3.controles.DateUtil;

import java.io.IOException;
import java.util.Map;
import java.util.StringJoiner;

public class SearchOrdenesRecibidasPendientes extends BaseServicioConsulta {

    private static final String urlServicio = "/api/v2/ordenes/recepciones/search-pendientes";
    @Override
    protected String buildUrl(Map<String, String> parametros, Map<String, String> params) throws IOException {
        String fechaOperacion = parametros.get("fechaOperacion");
        if (fechaOperacion == null) {
            throw new IllegalArgumentException("El parámetro 'fechaOperacion' es obligatorio.");
        }
        String[] rangoTiempo = DateUtil.getTimeRangeForOperacion(fechaOperacion);
        String inicio = rangoTiempo[0];
        String fin = rangoTiempo[1];
        StringJoiner url = new StringJoiner("&", getUrlServicioBase() + urlServicio + "?", "");
        for (Map.Entry<String, String> entry : parametros.entrySet()) {
            if (!entry.getKey().equals("fechaOperacion")) {
                url.add(entry.getKey() + "=" + entry.getValue());
            }
        }
        url.add("capturaInicio=" + inicio);
        url.add("capturaFin=" + fin);
        return url.toString();
    }

    protected void guardarOrdenesRecibidasPendientes(String respuesta) {
        guardarOrdenes(respuesta, "SearchOrdenesRecibidasPendientes.txt", false);
    }

    @Override
    protected String getTipoConsulta() {
        return "search";
    }

}
