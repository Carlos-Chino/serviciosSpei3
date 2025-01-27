package org.serviciosSpei3.registroDeOrdenes;
import org.serviciosSpei3.controles.CryptoHandler;
import java.io.IOException;

public class OrdenParticipanteAParticipanteT7 extends ServicioBaseGenerarOrden {
    public OrdenParticipanteAParticipanteT7() throws IOException {
        super.inicializarDatos();
        datosOrdenes.setTipoPago(7);
    }

    @Override
    protected String generarPeticion(DatosOrdenes datosOrdenes) {
        datosOrdenes.setClaveRastreo("QASPEI3Cert" + System.currentTimeMillis());
        StringBuilder peticion = new StringBuilder();
        peticion.append("{")
                .append("\"fechaOperacion\":\"").append(datosOrdenes.getFechaOperacion()).append("\",\n")
                .append("\"institucionOrdenante\":\"").append(datosOrdenes.getInstitucionOrdenante()).append("\",\n")
                .append("\"instancia\":\"").append(datosOrdenes.getInstancia()).append("\",\n")
                .append("\"institucionBeneficiaria\":\"").append(datosOrdenes.getInstitucionBeneficiaria()).append("\",\n")
                .append("\"claveRastreo\":\"").append(datosOrdenes.getClaveRastreo()).append("\",\n")
                .append("\"monto\":\"").append(datosOrdenes.getMonto()).append("\",\n")
                .append("\"tipoPago\":\"").append(datosOrdenes.getTipoPago()).append("\",\n")
                .append("\"detalle\":{")
                .append("\"conceptoPago\":\"").append(datosOrdenes.getConceptoPago()).append("\",\n")
                .append("\"referenciaNumerica\":\"").append(datosOrdenes.getReferenciaNumerica()).append("\",\n")
                .append("\"tipoOperacion\":\"").append(datosOrdenes.getTipoOperacion()).append("\"}")
                .append("}");
        return peticion.toString();
    }
    @Override
    protected String generarFirma(DatosOrdenes datosOrdenes) {
        return new CryptoHandler().ordenParticipanteAParticipanteT7(datosOrdenes);
    }
}
