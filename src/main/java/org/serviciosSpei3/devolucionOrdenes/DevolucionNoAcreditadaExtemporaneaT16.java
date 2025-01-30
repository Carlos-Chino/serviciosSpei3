package org.serviciosSpei3.devolucionOrdenes;
import org.serviciosSpei3.controles.CryptoHandler;

public class DevolucionNoAcreditadaExtemporaneaT16 extends ServicioBaseDevolucion {
    public DevolucionNoAcreditadaExtemporaneaT16(Integer causaDevoluion, String tipoDevolucion) {
        super(causaDevoluion, tipoDevolucion);
    }

    @Override
    protected String generarPeticion(DatosDevolucion devolucion) {
        devolucion.setClaveRastreo("DevT16SPEI3QA" + (System.currentTimeMillis() % 100000));
        StringBuilder peticion = new StringBuilder();
        peticion.append("{\n")
                .append("\"claveRastreo\":\"").append(devolucion.getClaveRastreo()).append("\",\n")
                .append("\"causaDevolucion\":\"").append(devolucion.getCausaDevolucion()).append("\",\n")
                .append("\"montoIntereses\":\"").append(devolucion.getMontoIntereses()).append("\"\n")
                .append("}");
        return peticion.toString();
    }

    @Override
    protected String generarFirma(DatosDevolucion devolucion) {
        return new CryptoHandler().devolucionNoAcreditadaExtemporaneaT16(devolucion);
    }
}
