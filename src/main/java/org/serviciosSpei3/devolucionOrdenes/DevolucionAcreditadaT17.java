package org.serviciosSpei3.devolucionOrdenes;
import org.serviciosSpei3.controles.CryptoHandler;

public class DevolucionAcreditadaT17 extends ServicioBaseDevolucion {
    public DevolucionAcreditadaT17(Integer causaDevoluion, String tipoDevolucion) {
        super(causaDevoluion, tipoDevolucion);
    }

    @Override
    protected String generarPeticion(DatosDevolucion devolucion) {
        devolucion.setClaveRastreo("DevT17SPEI3QA" + (System.currentTimeMillis() % 100000));
        StringBuilder peticion = new StringBuilder();
        peticion.append("{\n")
                .append("\"claveRastreo\":\"").append(devolucion.getClaveRastreo()).append("\",\n")
                .append("\"montoDevolucion\":\"").append(devolucion.getMontoDevolucion()).append("\"\n")
                .append("}");
        return peticion.toString();
    }

    @Override
    protected String generarFirma(DatosDevolucion devolucion) {
        return new CryptoHandler().devolucionAcreditadaT17(devolucion);
    }

}
