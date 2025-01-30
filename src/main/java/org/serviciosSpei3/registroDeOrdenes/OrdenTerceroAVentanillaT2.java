package org.serviciosSpei3.registroDeOrdenes;


import org.serviciosSpei3.controles.CryptoHandler;

import java.io.IOException;

public class OrdenTerceroAVentanillaT2 extends ServicioBaseGenerarOrden {
    public OrdenTerceroAVentanillaT2() throws IOException {
        super.inicializarDatos();
        datosOrdenes.setTipoPago(2);
    }

    @Override
    protected String generarPeticion(DatosOrdenes datos) {
        datosOrdenes.setClaveRastreo("PagoT2SPEI3QA" + (System.currentTimeMillis() % 1000000));
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
                .append("\"conceptoPago\":\"").append(valueNull(datosOrdenes.getConceptoPago())).append("\",\n")
                .append("\"clavePago\":\"").append(valueNull(datosOrdenes.getClavePago())).append("\"}")
                .append("}");
        return peticion.toString();
    }

    @Override
    protected String generarFirma(DatosOrdenes datos) {
        return new CryptoHandler().ordenTerceroAVentanillaT2(datosOrdenes);
    }
}
