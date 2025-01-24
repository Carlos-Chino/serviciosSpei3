package org.serviciosSpei3.devolucionOrdenes;

import org.serviciosSpei3.controles.CryptoHandler;
import java.math.BigDecimal;

public class DevolucionAcreditadaExtemporaneaT18 extends ServicioBaseDevolucion{

    public DevolucionAcreditadaExtemporaneaT18(Integer causaDevoluion, String archivo) {
        super(causaDevoluion, archivo);
    }

    @Override
    protected String generarPeticion(DatosDevolucion devolucion) {
        devolucion.setMontoIntereses(new BigDecimal("0.01"));
        StringBuilder peticion = new StringBuilder();
        peticion.append("{\n");
        peticion.append("\"claveRastreo\":\"").append(devolucion.getClaveRastreo()).append("\",\n");
        peticion.append("\"montoIntereses\":\"").append(devolucion.getMontoIntereses()).append("\",\n");
        peticion.append("\"causaDevolucion\":\"").append(devolucion.getCausaDevolucion()).append("\"\n");
        peticion.append("}");
        return peticion.toString();
    }

    @Override
    protected String generarFirma(DatosDevolucion devolucion) {
        return new CryptoHandler().devolucionAcreditadaExtemporaneaT18(devolucion);
    }
}
