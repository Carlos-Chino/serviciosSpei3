package org.serviciosSpei3.serviciosConsulta;

import org.serviciosSpei3.controles.DateUtil;

import java.io.IOException;
import java.util.Map;
import java.util.StringJoiner;

public class SearchOrdenesEnviadasPendientes extends BaseServicioConsulta {
    private static final String servicio = "/api/v2/ordenes/envios/search-pendientes";
    private String propositoConsulta;
    public SearchOrdenesEnviadasPendientes(String propositoConsulta) {
        super(propositoConsulta);
        this.propositoConsulta=propositoConsulta;
    }

    @Override
    protected String buildUrl(Map<String, String> parametros, Map<String, String> params) throws IOException {
        String fechaOperacion = parametros.get("fechaOperacion");
        if (fechaOperacion == null) {
            throw new IllegalArgumentException("El parámetro 'fechaOperacion' es obligatorio.");
        }
        String[] rangoTiempo = DateUtil.getTimeRangeForOperacion(fechaOperacion);
        String inicio = rangoTiempo[0];
        String fin = rangoTiempo[1];
        StringJoiner url = new StringJoiner("&", getUrlServicioBase()+servicio + "?", "");
        for (Map.Entry<String, String> entry : parametros.entrySet()) {
            if (!entry.getKey().equals("fechaOperacion")) {
                url.add(entry.getKey() + "=" + entry.getValue());
            }
        }
        url.add("capturaInicio=" + inicio);
        url.add("capturaFin=" + fin);
        return url.toString();
    }

    @Override
    protected String getTipoConsulta() {
        return "search";
    }

    protected void guardarOrdenesEnvPendientes(String respuesta) {
        guardarOrdenes(respuesta, "SearchOrdenesEnviadasPendientes"+this.propositoConsulta+".txt", false);
    }

}
