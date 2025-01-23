package org.serviciosSpei3.cancelacionOrdenes;


import org.serviciosSpei3.controles.CryptoHandler;
import org.serviciosSpei3.registroDeOrdenes.GuardarRegistroOrden;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import static org.serviciosSpei3.controles.HttpClient.sendRequest;
import static org.serviciosSpei3.controles.KeycloakService.loadAccessToken;

public class CancelarOrden {
    private static final String certificado = "1733350443035";
    private static final String servicioBase = "src/main/resources/propiedades.properties";

    public void ejecutarServicio(String razonRechazo, String archivo) throws Exception {
        List<datosCancelarOrden> cancelarOrdenes = cargarDatosCancelarOrden(archivo);
        for (datosCancelarOrden cancelarOrden : cancelarOrdenes) {
            String urlServicio = construirUrl(cancelarOrden.getOrdenId(), razonRechazo);
            String firma = generarFirma(cancelarOrden);
            String respuesta = sendRequest(urlServicio, "PUT", getHeaders(firma), null);
            procesarRespuesta(respuesta);
        }
    }

    private String construirUrl(String ordenId,String razonRechazo) throws IOException {
        return getUrlServicioBase() + "/api/v1/ordenes/envios/cancela?id=" + ordenId+"&razonRechazo="+razonRechazo;
    }

    protected List<datosCancelarOrden> cargarDatosCancelarOrden(String archivo) throws IOException {
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

    protected void procesarRespuesta(String respuesta) {
        new GuardarRegistroOrden(respuesta, "ordenIdCancelada.txt");
    }

    protected Map<String, String> getHeaders(String firma) throws IOException {
        return Map.of(
                "Content-Type", "application/json",
                "Authorization", "Bearer " + loadAccessToken(),
                "X-EF-Firma", firma.trim(),
                "X-EF-Certificado", certificado
        );
    }

    protected String getUrlServicioBase() throws IOException {
        Properties properties = new Properties();
        try (FileInputStream input = new FileInputStream(servicioBase)) {
            properties.load(input);
            return properties.getProperty("urlServicioBase");
        }
    }
    protected String generarFirma(datosCancelarOrden cancelarOrden) {
        return new CryptoHandler().cancelarOrden(cancelarOrden);
    }
}