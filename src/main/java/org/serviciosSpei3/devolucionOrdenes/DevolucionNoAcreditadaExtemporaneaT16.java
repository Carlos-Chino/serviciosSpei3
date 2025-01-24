package org.serviciosSpei3.devolucionOrdenes;

import org.serviciosSpei3.controles.CryptoHandler;
import java.math.BigDecimal;

public class DevolucionNoAcreditadaExtemporaneaT16 extends ServicioBaseDevolucion {
    private String montoIntereses;
    public DevolucionNoAcreditadaExtemporaneaT16(Integer causaDevoluion, String montoIntereses) {
        super(causaDevoluion);
        this.montoIntereses=montoIntereses;

    }

    @Override
    protected String generarPeticion(DatosDevolucion devolucion) {
        devolucion.setClaveRastreo("PruebasQA" + System.currentTimeMillis());
        devolucion.setMontoIntereses(new BigDecimal(this.montoIntereses));
        StringBuilder peticion = new StringBuilder();
        peticion.append("{\n");
        peticion.append("\"claveRastreo\":\"").append(devolucion.getClaveRastreo()).append("\",\n");
        peticion.append("\"causaDevolucion\":\"").append(devolucion.getCausaDevolucion()).append("\",\n");
        peticion.append("\"montoIntereses\":\"").append(devolucion.getMontoIntereses()).append("\"\n");
        peticion.append("}");
        return peticion.toString();
    }

    @Override
    protected String generarFirma(DatosDevolucion devolucion) {
        return new CryptoHandler().devolucionNoAcreditadaExtemporaneaT16(devolucion);
    }
}
