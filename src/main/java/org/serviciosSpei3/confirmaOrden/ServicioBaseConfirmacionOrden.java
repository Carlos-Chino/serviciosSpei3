package org.serviciosSpei3.confirmaOrden;

import org.serviciosSpei3.controles.ServicioBase;
import org.serviciosSpei3.registroDeOrdenes.GuardarRegistroOperacion;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public abstract class ServicioBaseConfirmacionOrden extends ServicioBase<datosConfirmaOrden> {

    protected abstract String generarPeticion(datosConfirmaOrden confirmaOrden);
    protected abstract String generarFirma(datosConfirmaOrden confirmaOrden);

    @Override
    protected String construirUrl(datosConfirmaOrden confirmaOrden) throws IOException {
        return getUrlServicioBase() + "/api/v1/ordenes/recepciones/confirma?ordenId=" + confirmaOrden.getOrdenId();
    }

    @Override
    protected List<datosConfirmaOrden> cargarDatos(String archivo) throws IOException {
        List<datosConfirmaOrden> confirmaOrdenes = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
            String linea;
            reader.readLine();
            while ((linea = reader.readLine()) != null) {
                String[] datos = linea.split("\\|");
                datosConfirmaOrden confirmaOrden = new datosConfirmaOrden();
                confirmaOrden.setInstitucionBeneficiaria(Integer.parseInt(datos[0]));
                confirmaOrden.setInstancia(datos[1]);
                confirmaOrden.setInstitucionOrdenante(Integer.parseInt(datos[2]));
                confirmaOrden.setClaveRastreo(datos[3]);
                confirmaOrden.setTipoPago(Integer.parseInt(datos[4]));
                confirmaOrden.setOrdenId(datos[5]);
                confirmaOrden.setNombreCliente(datos[6]);
                confirmaOrden.setRfcCliente(datos[7]);
                confirmaOrdenes.add(confirmaOrden);
            }
        }
        return confirmaOrdenes;
    }

    @Override
    protected void procesarRespuesta(String respuesta) {
        new GuardarRegistroOperacion(respuesta, "OrdenIdConfirmada.txt","ordenId");
    }

    @Override
    protected String getMetodoHttp() {return "POST";}

}