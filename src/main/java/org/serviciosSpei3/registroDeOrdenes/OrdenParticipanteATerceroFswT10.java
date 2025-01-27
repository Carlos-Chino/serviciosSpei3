package org.serviciosSpei3.registroDeOrdenes;

import org.serviciosSpei3.controles.CryptoHandler;

import java.io.IOException;

public class OrdenParticipanteATerceroFswT10 extends ServicioBaseGenerarOrden{
    public OrdenParticipanteATerceroFswT10() throws IOException {
        super.inicializarDatos();
        datosOrdenes.setTipoPago(10);
    }

    @Override
    protected String generarPeticion(DatosOrdenes datos) {
        datosOrdenes.setClaveRastreo("QASPEI3Cert" + System.currentTimeMillis());
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
                .append("\"nombreBeneficiario\":\"").append(valueNull(datosOrdenes.getNombreBeneficiario())).append("\",\n")
                .append("\"tipoCuentaBeneficiario\":\"").append(valueNull(datosOrdenes.getTipoCuentaBeneficiario())).append("\",\n")
                .append("\"cuentaBeneficiario\":\"").append(valueNull(datosOrdenes.getCuentaBeneficiario())).append("\",\n")
                .append("\"rfcCurpBeneficiario\":\"").append(valueNull(datosOrdenes.getRfcCurpBeneficiario())).append("\",\n")
                .append("\"conceptoPago\":\"").append(valueNull(datosOrdenes.getConceptoPago())).append("\",\n")
                .append("\"referenciaNumerica\":\"").append(valueNull(datosOrdenes.getReferenciaNumerica())).append("\"}")
                .append("}");
        return peticion.toString();
    }

    @Override
    protected String generarFirma(DatosOrdenes datos) {
        return new CryptoHandler().ordenParticipanteATerceroFswT10(datosOrdenes);
    }
}
