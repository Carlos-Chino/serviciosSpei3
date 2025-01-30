package org.serviciosSpei3.registroDeOrdenes;
import org.serviciosSpei3.controles.CryptoHandler;
import java.io.IOException;
import java.util.List;

public class CobroPresencialUnaOcasionT19 extends ServicioBaseGenerarOrden {

    public CobroPresencialUnaOcasionT19() throws IOException {
        super.inicializarDatos();
        datosOrdenes.setTipoPago(19);
    }

    @Override
    protected String generarPeticion(DatosOrdenes datosOrdenes) {
        datosOrdenes.setClaveRastreo("PagoT19SPEI3QA" + (System.currentTimeMillis() % 100000));
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
                .append("\"aliasCelCertOrd\":\"").append(valueNull(datosOrdenes.getAliasCelCertOrd())).append("\",\n")
                .append("\"digitoIdOrdenante\":\"").append(valueNull(datosOrdenes.getDigitoIdOrdenante())).append("\",\n")
                .append("\"nombreBeneficiario\":\"").append(valueNull(datosOrdenes.getNombreBeneficiario())).append("\",\n")
                .append("\"tipoCuentaBeneficiario\":\"").append(valueNull(datosOrdenes.getTipoCuentaBeneficiario())).append("\",\n")
                .append("\"cuentaBeneficiario\":\"").append(valueNull(datosOrdenes.getCuentaBeneficiario())).append("\",\n")
                .append("\"rfcCurpBeneficiario\":\"").append(valueNull(datosOrdenes.getRfcCurpBeneficiario())).append("\",\n")
                .append("\"aliasCelCertBene\":\"").append(valueNull(datosOrdenes.getAliasCelCertBene())).append("\",\n")
                .append("\"digitoIdBeneficiario\":\"").append(valueNull(datosOrdenes.getDigitoIdBeneficiario())).append("\",\n")
                .append("\"conceptoPago\":\"").append(valueNull(datosOrdenes.getConceptoPago())).append("\",\n")
                .append("\"folioPlataforma\":\"").append(valueNull(datosOrdenes.getFolioPlataforma())).append("\",\n")
                .append("\"referenciaNumerica\":\"").append(valueNull(datosOrdenes.getReferenciaNumerica())).append("\",\n")
                .append("\"pagoComision\":\"").append(valueNull(datosOrdenes.getPagoComision())).append("\",\n")
                .append("\"montoComision\":\"").append(valueNull(datosOrdenes.getMontoComision())).append("\"}")
                .append("}");
        return peticion.toString();
    }

    @Override
    protected String generarFirma(DatosOrdenes datosOrdenes) {
        return new CryptoHandler().ordenCobroPresencialUnaOcasionT19(datosOrdenes);

    }
}
