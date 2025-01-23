package org.serviciosSpei3.devolucionOrdenes;
import org.serviciosSpei3.controles.CryptoHandler;

public class DevolucionNoAcreditadaT0 extends  ServicioBaseDevolucion{
    public DevolucionNoAcreditadaT0(Integer causaDevoluion, String archivo) {super(causaDevoluion, archivo);}

    @Override
    protected String generarPeticion(DatosDevolucion devolucion) {
        StringBuilder peticion = new StringBuilder();
        peticion.append("{\n");
        peticion.append("\"claveRastreo\":\"").append(devolucion.getClaveRastreo()).append("\",\n");
        peticion.append("\"causaDevolucion\":\"").append(devolucion.getCausaDevolucion()).append("\"\n");
        peticion.append("}");
        return peticion.toString();
    }

    @Override
    protected String generarFirma(DatosDevolucion devolucion) {
        return new CryptoHandler().devolucionNoAcreditadaT0(devolucion);
    }

}
