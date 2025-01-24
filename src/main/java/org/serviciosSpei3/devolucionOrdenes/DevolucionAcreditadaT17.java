package org.serviciosSpei3.devolucionOrdenes;

import org.serviciosSpei3.controles.CryptoHandler;

public class DevolucionAcreditadaT17 extends ServicioBaseDevolucion {

    public DevolucionAcreditadaT17(Integer causaDevolucion) {
        super(causaDevolucion);
    }

    @Override
    protected String generarPeticion(DatosDevolucion devolucion) {
        devolucion.setClaveRastreo("PruebasQA" + System.currentTimeMillis());
        StringBuilder peticion = new StringBuilder();
        peticion.append("{\n");
        peticion.append("\"claveRastreo\":\"").append(devolucion.getClaveRastreo()).append("\",\n");
        peticion.append("\"causaDevolucion\":\"").append(devolucion.getCausaDevolucion()).append("\"\n");
        peticion.append("}");
        return peticion.toString();
    }

    @Override
    protected String generarFirma(DatosDevolucion devolucion) {
        return new CryptoHandler().devolucionAcreditadaT17(devolucion);
    }

}
