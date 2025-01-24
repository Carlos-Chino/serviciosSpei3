package org.serviciosSpei3.cancelacionOrdenes;
import org.serviciosSpei3.controles.CryptoHandler;
import org.serviciosSpei3.controles.ServicioBase;
import org.serviciosSpei3.registroDeOrdenes.GuardarRegistroOperacion;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CancelarOrden extends ServicioBase<datosCancelarOrden> {

    @Override
    protected String construirUrl(datosCancelarOrden datos) throws IOException {
        String razonRechazo = "cancelacion";
        return getUrlServicioBase() + "/api/v1/ordenes/envios/cancela?id=" + datos.getOrdenId() + "&razonRechazo=" + razonRechazo;
    }

    @Override
    protected List<datosCancelarOrden> cargarDatos(String archivo) throws IOException {
        List<datosCancelarOrden> cancelarOrdenes = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
            String linea;
            reader.readLine();
            while ((linea = reader.readLine()) != null) {
                String[] datos = linea.split("\\|");
                datosCancelarOrden cancelarOrden = new datosCancelarOrden();
                cancelarOrden.setInstitucionBeneficiaria(Integer.parseInt(datos[0]));
                cancelarOrden.setInstancia(datos[1]);
                cancelarOrden.setInstitucionOrdenante(Integer.parseInt(datos[2]));
                cancelarOrden.setClaveRastreo(datos[3]);
                cancelarOrden.setTipoPago(Integer.parseInt(datos[4]));
                cancelarOrden.setOrdenId(datos[5]);
                cancelarOrden.setNombreCliente(datos[6]);
                cancelarOrden.setRfcCliente(datos[7]);
                cancelarOrden.setEstado(datos[11]);
                cancelarOrdenes.add(cancelarOrden);
            }
        }
        return cancelarOrdenes;
    }

    @Override
    protected void procesarRespuesta(String respuesta) {
        new GuardarRegistroOperacion(respuesta, "ordenIdCancelada.txt","ordenId");
    }

    @Override
    protected String generarPeticion(datosCancelarOrden datos) {return null;}

    @Override
    protected String generarFirma(datosCancelarOrden datos) {
        return new CryptoHandler().cancelarOrden(datos);}

    @Override
    protected String getMetodoHttp() {return "PUT";}
}