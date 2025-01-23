package org.serviciosSpei3.devolucionOrdenes;

import org.serviciosSpei3.controles.CryptoHandler;
import java.math.BigDecimal;

public class DevolucionEspecialAcreditadaExtemporaneaT24 extends ServicioBaseDevolucion{
    String indicadorBeneficiario;
    String montoDevolucion;
    public DevolucionEspecialAcreditadaExtemporaneaT24(Integer causaDevoluion, String montoDevolucion ,String archivo, String indicadorBeneficiario) {
        super(causaDevoluion, archivo);
        this.indicadorBeneficiario=indicadorBeneficiario;
        this.montoDevolucion=montoDevolucion;
    }

    @Override
    protected String generarPeticion(DatosDevolucion devolucion) {
        devolucion.setIndicadorBeneficiario(indicadorBeneficiario);
        devolucion.setMontoDevolucion(new BigDecimal(this.montoDevolucion));
        StringBuilder peticion = new StringBuilder();
        peticion.append("{\n");
        peticion.append("\"claveRastreo\":\"").append(devolucion.getClaveRastreo()).append("\",\n");
        peticion.append("\"causaDevolucion\":\"").append(devolucion.getCausaDevolucion()).append("\",\n");
        peticion.append("\"indicadorBeneficiario\":\"").append(devolucion.getIndicadorBeneficiario()).append("\",\n");
        peticion.append("\"montoDevolucion\":\"").append(devolucion.getMontoDevolucion()).append("\"\n");
        peticion.append("}");
        return peticion.toString();
    }

    @Override
    protected String generarFirma(DatosDevolucion devolucion) {
        return new CryptoHandler().devolucionEspecialAcreditadaExtemporaneaT24(devolucion);
    }
}
