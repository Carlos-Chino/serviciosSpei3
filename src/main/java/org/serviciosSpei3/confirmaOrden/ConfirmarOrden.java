package org.serviciosSpei3.confirmaOrden;

import org.serviciosSpei3.controles.CryptoHandler;

public class ConfirmarOrden extends ServicioBaseConfirmacionOrden {

    @Override
    protected void inicializarDatos(datosConfirmaOrden confirmaOrden) {
        //confirmaOrden.setNombreCliente("DatoParaModificarNombreALaOrdenRecibida");
    }

    @Override
    protected String generarPeticion(datosConfirmaOrden confirmaOrden) {
        StringBuilder sb = new StringBuilder();
        sb.append("{\n");
        sb.append("\"nombreCliente\":\"").append(confirmaOrden.getNombreCliente()).append("\",\n");
        sb.append("\"rfcCliente\":\"").append(confirmaOrden.getRfcCliente()).append("\"\n");
        sb.append("}");
        return sb.toString();    }

    @Override
    protected String generarFirma(datosConfirmaOrden confirmaOrden) {
        return new CryptoHandler().confirmaOrden(confirmaOrden);
    }

}
