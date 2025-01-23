package org.serviciosSpei3.registroDeOrdenes;
import org.serviciosSpei3.controles.CryptoHandler;
import java.io.IOException;

public class CobroNoPresencialUnaOcasion20 extends ServicioBaseGenerarOrden {

    @Override
    protected void inicializarDatos() throws IOException {
        super.inicializarDatos();
        datosOrdenes.setTipoPago(20);
    }

    @Override
    protected String generarPeticion() {
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
        peticion.append("\"aliasCelCertBene\":\"").append(valueNull(datosOrdenes.getAliasCelCertBene())).append("\",\n");
        peticion.append("\"digitoIdBeneficiario\":\"").append(valueNull(datosOrdenes.getDigitoIdBeneficiario())).append("\",\n");
        peticion.append("\"conceptoPago\":\"").append(valueNull(datosOrdenes.getConceptoPago())).append("\",\n");
        peticion.append("\"folioPlataforma\":\"").append(valueNull(datosOrdenes.getFolioPlataforma())).append("\",\n");
        peticion.append("\"referenciaNumerica\":\"").append(valueNull(datosOrdenes.getReferenciaNumerica())).append("\",\n");
        peticion.append("\"pagoComision\":\"").append(valueNull(datosOrdenes.getPagoComision())).append("\",\n");
        peticion.append("\"montoComision\":\"").append(valueNull(datosOrdenes.getMontoComision())).append("\"}");
        peticion.append("}");
        return peticion.toString();
    }

    @Override
    protected String generarFirma() {
        return new CryptoHandler().ordenCobroNoPresencialUnaOcasionT20(datosOrdenes);
    }
}
