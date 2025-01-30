package org.serviciosSpei3.serviciosConsulta;
import java.io.IOException;

public class ConsultaOrdenesRecibidasV2 extends BaseServicioConsulta {
    private static final String servicio = "/api/v2/ordenes/recepciones/consulta?id=";
    private String propositoConsulta;
    public ConsultaOrdenesRecibidasV2(String propositoConsulta) {
        this.propositoConsulta=propositoConsulta;
    }

    @Override
    protected String buildUrl(String ordenId) throws IOException {
        //return getUrlServicioBase()+servicio + ordenId + "&tipoPago="+this.tipoPago;
        return getUrlServicioBase()+servicio + ordenId;

    }

    protected void guardarOrdenesRecV2(String respuesta) {
        guardarOrdenes(respuesta, "ConsultaOrdenesRecibidas"+this.propositoConsulta+".txt", true);
    }

}
