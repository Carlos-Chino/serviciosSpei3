package org.serviciosSpei3.registroDeOrdenes;
import org.serviciosSpei3.controles.CryptoHandler;
import java.io.IOException;

public class CobroNoPresencialATercero22 extends ServicioBaseGenerarOrden{

    public CobroNoPresencialATercero22() throws IOException {
        super.inicializarDatos();
        datosOrdenes.setTipoPago(22);
    }

    @Override
    protected String generarPeticion(DatosOrdenes datosOrdenes) {
        datosOrdenes.setClaveRastreo("PagoT22SPEI3QA" + (System.currentTimeMillis() % 100000));
        StringBuilder peticion = new StringBuilder();
        peticion.append("{");
        peticion.append("\"fechaOperacion\":\"").append(valueNull(datosOrdenes.getFechaOperacion())).append("\",\n");
        peticion.append("\"institucionOrdenante\":\"").append(valueNull(datosOrdenes.getInstitucionOrdenante())).append("\",\n");
        peticion.append("\"instancia\":\"").append(valueNull(datosOrdenes.getInstancia())).append("\",\n");
        peticion.append("\"institucionBeneficiaria\":\"").append(valueNull(datosOrdenes.getInstitucionBeneficiaria())).append("\",\n");
        peticion.append("\"claveRastreo\":\"").append(valueNull(datosOrdenes.getClaveRastreo())).append("\",\n");
        peticion.append("\"monto\":\"").append(valueNull(datosOrdenes.getMonto())).append("\",\n");
        peticion.append("\"tipoPago\":\"").append(valueNull(datosOrdenes.getTipoPago())).append("\",\n");
        peticion.append("\"detalle\":{");
        peticion.append("\"nombreOrdenante\":\"").append(valueNull(datosOrdenes.getNombreOrdenante())).append("\",\n");
        peticion.append("\"tipoCuentaOrdenante\":\"").append(valueNull(datosOrdenes.getTipoCuentaOrdenante())).append("\",\n");
        peticion.append("\"cuentaOrdenante\":\"").append(valueNull(datosOrdenes.getCuentaOrdenante())).append("\",\n");
        peticion.append("\"rfcCurpOrdenante\":\"").append(valueNull(datosOrdenes.getRfcCurpOrdenante())).append("\",\n");
        peticion.append("\"aliasCelCertOrd\":\"").append(valueNull(datosOrdenes.getAliasCelCertOrd())).append("\",\n");
        peticion.append("\"digitoIdOrdenante\":\"").append(valueNull(datosOrdenes.getDigitoIdOrdenante())).append("\",\n");
        peticion.append("\"nombreBeneficiario\":\"").append(valueNull(datosOrdenes.getNombreBeneficiario())).append("\",\n");
        peticion.append("\"tipoCuentaBeneficiario\":\"").append(valueNull(datosOrdenes.getTipoCuentaBeneficiario())).append("\",\n");
        peticion.append("\"cuentaBeneficiario\":\"").append(valueNull(datosOrdenes.getCuentaBeneficiario())).append("\",\n");
        peticion.append("\"rfcCurpBeneficiario\":\"").append(valueNull(datosOrdenes.getRfcCurpBeneficiario())).append("\",\n");
        peticion.append("\"nombreBeneficiario2\":\"").append(valueNull(datosOrdenes.getNombreBeneficiario2())).append("\",\n");
        peticion.append("\"rfcCurpBeneficiario2\":\"").append(valueNull(datosOrdenes.getRfcCurpBeneficiario2())).append("\",\n");
        peticion.append("\"tipoCuentaBeneficiario2\":\"").append(valueNull(datosOrdenes.getTipoCuentaBeneficiario2())).append("\",\n");
        peticion.append("\"cuentaBeneficiario2\":\"").append(valueNull(datosOrdenes.getCuentaBeneficiario2())).append("\",\n");
        peticion.append("\"aliasCertComercio\":\"").append(valueNull(datosOrdenes.getAliasCertComercio())).append("\",\n");
        peticion.append("\"digitoCertComercio\":\"").append(valueNull(datosOrdenes.getDigitoCertComercio())).append("\",\n");
        peticion.append("\"conceptoPago\":\"").append(valueNull(datosOrdenes.getConceptoPago())).append("\",\n");
        peticion.append("\"folioPlataforma\":\"").append(valueNull(datosOrdenes.getFolioPlataforma())).append("\",\n");
        peticion.append("\"referenciaNumerica\":\"").append(valueNull(datosOrdenes.getReferenciaNumerica())).append("\",\n");
        peticion.append("\"fechaLimitePago\":\"").append(valueNull(datosOrdenes.getFechaLimitePago())).append("\",\n");
        peticion.append("\"pagoComision\":\"").append(valueNull(datosOrdenes.getPagoComision())).append("\",\n");
        peticion.append("\"montoComision\":\"").append(valueNull(datosOrdenes.getMontoComision())).append("\"}");
        peticion.append("}");
        return peticion.toString();
    }


    @Override
    protected String generarFirma(DatosOrdenes datosOrdenes) {
        return new CryptoHandler().ordenCobroNoPresencialATerceroT22(datosOrdenes);
    }
}
