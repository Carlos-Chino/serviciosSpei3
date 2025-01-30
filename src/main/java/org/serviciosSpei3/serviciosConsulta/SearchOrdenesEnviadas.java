package org.serviciosSpei3.serviciosConsulta;
import java.io.*;
import java.util.Map;
import java.util.StringJoiner;
import static org.serviciosSpei3.controles.DateUtil.getTimeRangeForOperacion;

public class SearchOrdenesEnviadas extends BaseServicioConsulta {
    private final String servicio="/api/v1/ordenes/envios/search";
    private String propositoConsulta;
    public SearchOrdenesEnviadas(String propositoConsulta) {
        this.propositoConsulta=propositoConsulta;
    }
    @Override
    protected String buildUrl(Map<String, String> parametros, Map<String, String> params) throws IOException {

        String fechaOperacion = parametros.get("fechaOperacion");
        if (fechaOperacion == null) {
            throw new IllegalArgumentException("El parámetro 'fechaOperacion' es obligatorio.");
        }
        String[] timeRange = getTimeRangeForOperacion(fechaOperacion);
        String timeStart = timeRange[0];
        String timeEnd = timeRange[1];
        StringJoiner url = new StringJoiner("&", getUrlServicioBase()+servicio + "?", "");
        for (Map.Entry<String, String> entry : parametros.entrySet()) {
            url.add(entry.getKey() + "=" + entry.getValue());
        }

        StringJoiner paramsJoiner = new StringJoiner(",", "params=", "");
        paramsJoiner.add("tiempos.captura>:" + timeStart);
        paramsJoiner.add("tiempos.captura<:" + timeEnd);

        for (Map.Entry<String, String> entry : params.entrySet()) {
            paramsJoiner.add(entry.getKey() + entry.getValue());
        }
        url.add(paramsJoiner.toString());
        return url.toString();
    }

    protected void guardarOrdenesEnv(String respuesta) {
        guardarOrdenes(respuesta, "ConsultaOrdenesEnviadas"+this.propositoConsulta+".txt", false);
    }
}
