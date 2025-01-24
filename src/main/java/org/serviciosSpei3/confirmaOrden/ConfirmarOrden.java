package org.serviciosSpei3.confirmaOrden;

import org.serviciosSpei3.controles.CryptoHandler;

public class ConfirmarOrden extends ServicioBaseConfirmacionOrden {

    @Override
    protected String generarPeticion(datosConfirmaOrden confirmaOrden) {
        StringBuilder peticion = new StringBuilder();
        peticion.append("{\n");
        peticion.append("\"nombreCliente\":\"").append(confirmaOrden.getNombreCliente()).append("\",\n");
        peticion.append("\"rfcCliente\":\"").append(confirmaOrden.getRfcCliente()).append("\"\n");
        peticion.append("}");
        return peticion.toString();    }

    @Override
    protected String generarFirma(datosConfirmaOrden confirmaOrden) {
        return new CryptoHandler().confirmaOrden(confirmaOrden);
    }


}
