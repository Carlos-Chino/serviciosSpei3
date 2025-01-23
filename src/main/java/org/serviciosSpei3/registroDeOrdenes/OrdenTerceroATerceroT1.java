package org.serviciosSpei3.registroDeOrdenes;
import org.serviciosSpei3.controles.CryptoHandler;
import java.io.IOException;

public class OrdenTerceroATerceroT1 extends ServicioBaseGenerarOrden {
    @Override
    protected void inicializarDatos() throws IOException {
        super.inicializarDatos();
        datosOrdenes.setTipoPago(1);
    }

    @Override
    protected String generarPeticion() {
        StringBuilder peticion = new StringBuilder();
        peticion.append("{");
        peticion.append("\"fechaOperacion\":\"").append(datosOrdenes.getFechaOperacion()).append("\",\n");
        peticion.append("\"institucionOrdenante\":\"").append(datosOrdenes.getInstitucionOrdenante()).append("\",\n");
        peticion.append("\"instancia\":\"").append(datosOrdenes.getInstancia()).append("\",\n");
        peticion.append("\"institucionBeneficiaria\":\"").append(datosOrdenes.getInstitucionBeneficiaria()).append("\",\n");
        peticion.append("\"claveRastreo\":\"").append(datosOrdenes.getClaveRastreo()).append("\",\n");
        peticion.append("\"monto\":\"").append(datosOrdenes.getMonto()).append("\",\n");
        peticion.append("\"tipoPago\":\"").append(datosOrdenes.getTipoPago()).append("\",\n");
        peticion.append("\"detalle\":{");
        peticion.append("\"nombreOrdenante\":\"").append(datosOrdenes.getNombreOrdenante()).append("\",\n");
        peticion.append("\"tipoCuentaOrdenante\":\"").append(datosOrdenes.getTipoCuentaOrdenante()).append("\",\n");
        peticion.append("\"cuentaOrdenante\":\"").append(datosOrdenes.getCuentaOrdenante()).append("\",\n");
        peticion.append("\"rfcCurpOrdenante\":\"").append(datosOrdenes.getRfcCurpOrdenante()).append("\",\n");
        peticion.append("\"nombreBeneficiario\":\"").append(datosOrdenes.getNombreBeneficiario()).append("\",\n");
        peticion.append("\"tipoCuentaBeneficiario\":\"").append(datosOrdenes.getTipoCuentaBeneficiario()).append("\",\n");
        peticion.append("\"cuentaBeneficiario\":\"").append(datosOrdenes.getCuentaBeneficiario()).append("\",\n");
        peticion.append("\"rfcCurpBeneficiario\":\"").append(datosOrdenes.getRfcCurpBeneficiario()).append("\",\n");
        peticion.append("\"conceptoPago\":\"").append(datosOrdenes.getConceptoPago()).append("\",\n");
       // peticion.append("\"iva\":\"").append(datosOrdenes.getIva()).append("\",\n");
        peticion.append("\"referenciaNumerica\":\"").append(datosOrdenes.getReferenciaNumerica()).append("\",\n");
        peticion.append("\"referenciaCobranza\":\"").append(datosOrdenes.getReferenciaCobranza()).append("\"}");
        peticion.append("}");
        return peticion.toString();
    }

    @Override
    protected String generarFirma() {
        return new CryptoHandler().ordenTerceroATerceroT1(datosOrdenes);
    }
}
