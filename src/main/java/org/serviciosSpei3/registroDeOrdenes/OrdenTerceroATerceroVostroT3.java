package org.serviciosSpei3.registroDeOrdenes;

import org.serviciosSpei3.controles.CryptoHandler;

import java.io.IOException;

public class OrdenTerceroATerceroVostroT3 extends ServicioBaseGenerarOrden{
    public OrdenTerceroATerceroVostroT3() throws IOException {
        super.inicializarDatos();
        datosOrdenes.setTipoPago(3);
    }

    @Override
    protected String generarPeticion(DatosOrdenes datos) {
        datosOrdenes.setClaveRastreo("PagoT3SPEI3QA" + (System.currentTimeMillis() % 1000000));
        StringBuilder peticion = new StringBuilder();
        peticion.append("{")
        .append("\"fechaOperacion\":\"").append(valueNull(datosOrdenes.getFechaOperacion())).append("\",\n")
        .append("\"institucionOrdenante\":\"").append(valueNull(datosOrdenes.getInstitucionOrdenante())).append("\",\n")
        .append("\"instancia\":\"").append(valueNull(datosOrdenes.getInstancia())).append("\",\n")
        .append("\"institucionBeneficiaria\":\"").append(valueNull(datosOrdenes.getInstitucionBeneficiaria())).append("\",\n")
        .append("\"claveRastreo\":\"").append(valueNull(datosOrdenes.getClaveRastreo())).append("\",\n")
        .append("\"monto\":\"").append(valueNull(datosOrdenes.getMonto())).append("\",\n")
        .append("\"tipoPago\":\"").append(valueNull(datosOrdenes.getTipoPago())).append("\",\n")
        .append("\"detalle\":{")
        .append("\"nombreOrdenante\":\"").append(valueNull(datosOrdenes.getNombreOrdenante())).append("\",\n")
        .append("\"tipoCuentaOrdenante\":\"").append(valueNull(datosOrdenes.getTipoCuentaOrdenante())).append("\",\n")
        .append("\"cuentaOrdenante\":\"").append(valueNull(datosOrdenes.getCuentaOrdenante())).append("\",\n")
        .append("\"rfcCurpOrdenante\":\"").append(valueNull(datosOrdenes.getRfcCurpOrdenante())).append("\",\n")
        .append("\"nombreBeneficiario\":\"").append(valueNull(datosOrdenes.getNombreBeneficiario())).append("\",\n")
        .append("\"tipoCuentaBeneficiario\":\"").append(valueNull(datosOrdenes.getTipoCuentaBeneficiario())).append("\",\n")
        .append("\"cuentaBeneficiario\":\"").append(valueNull(datosOrdenes.getCuentaBeneficiario())).append("\",\n")
        .append("\"nombreBeneficiario2\":\"").append(valueNull(datosOrdenes.getNombreBeneficiario2())).append("\",\n")
        .append("\"rfcCurpBeneficiario2\":\"").append(valueNull(datosOrdenes.getRfcCurpBeneficiario2())).append("\",\n")
        .append("\"tipoCuentaBeneficiario2\":\"").append(valueNull(datosOrdenes.getTipoCuentaBeneficiario2())).append("\",\n")
        .append("\"cuentaBeneficiario2\":\"").append(valueNull(datosOrdenes.getCuentaBeneficiario2())).append("\",\n")
        .append("\"conceptoPago\":\"").append(valueNull(datosOrdenes.getConceptoPago())).append("\",\n")
        .append("\"conceptoPago2\":\"").append(valueNull(datosOrdenes.getConceptoPago2())).append("\",\n")
        .append("\"referenciaNumerica\":\"").append(valueNull(datosOrdenes.getReferenciaNumerica())).append("\"}")
        .append("}");
        return peticion.toString();
    }

    @Override
    protected String generarFirma(DatosOrdenes datos) {
        return new CryptoHandler().ordenTerceroATerceroVostroT3(datosOrdenes);
    }
}
