package org.serviciosSpei3.serviciosConsulta;

import java.io.IOException;

public class ConsultaOrdenesEnviadasV2 extends BaseServicioConsulta {
    private static final String servicio = "/api/v2/ordenes/envios/consulta?id=";
    private Integer tipoPago;
    private String propositoConsulta;
    public ConsultaOrdenesEnviadasV2(Integer tipoPago, String propositoConsulta) {
        this.tipoPago=tipoPago;
        this.propositoConsulta=propositoConsulta;
    }

    @Override
    protected String buildUrl(String ordenID) throws IOException {
        return getUrlServicioBase()+servicio + ordenID + "&tipoPago="+this.tipoPago;
    }

    protected void guardarOrdenesEnviadasV2(String respuesta) {
        guardarOrdenes(respuesta, "ConsultaOrdenesEnviadas"+this.propositoConsulta+".txt", true);
    }

}
