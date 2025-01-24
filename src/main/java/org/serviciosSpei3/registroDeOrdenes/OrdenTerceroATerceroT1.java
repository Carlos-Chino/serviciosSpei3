package org.serviciosSpei3.registroDeOrdenes;
import org.serviciosSpei3.controles.CryptoHandler;
import java.io.IOException;

public class OrdenTerceroATerceroT1 extends ServicioBaseGenerarOrden {

    public OrdenTerceroATerceroT1() throws IOException {
        super.inicializarDatos();
        datosOrdenes.setTipoPago(1);
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
                .append("\"nombreOrdenante\":\"").append(datosOrdenes.getNombreOrdenante()).append("\",\n")
                .append("\"tipoCuentaOrdenante\":\"").append(datosOrdenes.getTipoCuentaOrdenante()).append("\",\n")
                .append("\"cuentaOrdenante\":\"").append(datosOrdenes.getCuentaOrdenante()).append("\",\n")
                .append("\"rfcCurpOrdenante\":\"").append(datosOrdenes.getRfcCurpOrdenante()).append("\",\n")
                .append("\"nombreBeneficiario\":\"").append(datosOrdenes.getNombreBeneficiario()).append("\",\n")
                .append("\"tipoCuentaBeneficiario\":\"").append(datosOrdenes.getTipoCuentaBeneficiario()).append("\",\n")
                .append("\"cuentaBeneficiario\":\"").append(datosOrdenes.getCuentaBeneficiario()).append("\",\n")
                .append("\"rfcCurpBeneficiario\":\"").append(datosOrdenes.getRfcCurpBeneficiario()).append("\",\n")
                .append("\"conceptoPago\":\"").append(datosOrdenes.getConceptoPago()).append("\",\n")
                // .append("\"iva\":\"").append(datosOrdenes.getIva()).append("\",\n")
                .append("\"referenciaNumerica\":\"").append(datosOrdenes.getReferenciaNumerica()).append("\",\n")
                .append("\"referenciaCobranza\":\"").append(datosOrdenes.getReferenciaCobranza()).append("\"}")
                .append("}");
        return peticion.toString();
    }

    @Override
    protected String generarFirma(DatosOrdenes datosOrdenes) {
        return new CryptoHandler().ordenTerceroATerceroT1(datosOrdenes);
    }
}
