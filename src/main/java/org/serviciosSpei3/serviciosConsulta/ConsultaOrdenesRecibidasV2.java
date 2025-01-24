package org.serviciosSpei3.serviciosConsulta;
import java.io.IOException;

public class ConsultaOrdenesRecibidasV2 extends BaseServicioConsulta {
    private static final String servicio = "/api/v2/ordenes/recepciones/consulta?id=";
    private Integer tipoPago;
    public ConsultaOrdenesRecibidasV2(Integer tipoPago) {
        this.tipoPago = tipoPago;
    }

    @Override
    protected String buildUrl(String ordenId) throws IOException {
        return getUrlServicioBase()+servicio + ordenId + "&tipoPago="+this.tipoPago;
    }

    protected void guardarOrdenesRecV2(String respuesta) {
        guardarOrdenes(respuesta, "ConsultaOrdenesRecibidas.txt", true);
    }

    @Override
    protected String getTipoConsulta() {
        return "search";
    }
}
