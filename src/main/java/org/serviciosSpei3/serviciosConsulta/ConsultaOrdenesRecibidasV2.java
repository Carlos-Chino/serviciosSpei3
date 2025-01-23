package org.serviciosSpei3.serviciosConsulta;

public class ConsultaOrdenesRecibidasV2 extends BaseServicioConsulta {
    private static final String urlServicio = "https://spei-core-stp-core-pruebas-apps-spei-asa.stp-ti.com/api/v2/ordenes/recepciones/consulta?id=";
    private Integer tipoPago;

    public ConsultaOrdenesRecibidasV2(Integer tipoPago) {
        this.tipoPago = tipoPago;
    }

    @Override
    protected String buildUrl(String ordenId) {
        return urlServicio + ordenId + "&tipoPago="+this.tipoPago;
    }

    @Override
    protected String getTipoConsulta() {
        return "search";
    }

    protected void guardarOrdenesRecV2(String respuesta) {
        guardarOrdenes(respuesta, "ConsultaOrdenesRecibidas.txt", true);
    }

}
