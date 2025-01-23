package org.serviciosSpei3.devolucionOrdenes;

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

public abstract class ServicioBaseDevolucion {
    private static final String certificado = "1733350443035";
    private static final String servicioBase = "src/main/resources/propiedades.properties";
    private Integer causaDevolucion;
    private String archivo;

    public ServicioBaseDevolucion(Integer causaDevoluion, String archivo) {
        this.archivo=archivo;
        this.causaDevolucion=causaDevoluion;
    }

    protected abstract String generarPeticion(DatosDevolucion devolucion);
    protected abstract String generarFirma(DatosDevolucion devolucion);

    public void ejecutarServicio() throws Exception {
        List<DatosDevolucion> devoluciones = cargarDatosDevolucion(this.archivo);
        for (DatosDevolucion devolucion : devoluciones) {
            String urlServicio = construirUrl(devolucion.getOrdenId());
            String peticion = generarPeticion(devolucion);
            String firma = generarFirma(devolucion);
            String respuesta = sendRequest(urlServicio, "POST", getHeaders(firma), peticion);
            procesarRespuesta(respuesta);
        }
    }

    private String construirUrl(String ordenId) throws IOException {
        return getUrlServicioBase() + "/api/v1/ordenes/recepciones/devuelve?ordenId=" + ordenId;
    }

    protected List<DatosDevolucion> cargarDatosDevolucion(String archivo) throws IOException {
        List<DatosDevolucion> devoluciones = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
            String linea;
            reader.readLine();
            while ((linea = reader.readLine()) != null) {
                String[] datos = linea.split("\\|");
                DatosDevolucion devolucion = new DatosDevolucion();
                devolucion.setInstitucionBeneficiaria(Integer.parseInt(datos[0]));
                devolucion.setInstancia(datos[1]);
                devolucion.setInstitucionOrdenante(Integer.parseInt(datos[2]));
                devolucion.setClaveRastreo("PruebasQA" + System.currentTimeMillis());
                devolucion.setTipoPago(Integer.parseInt(datos[4]));
                devolucion.setCausaDevolucion(this.causaDevolucion);
                devolucion.setOrdenId(datos[5]);
                devoluciones.add(devolucion);
            }
        }
        return devoluciones;
    }

    protected void procesarRespuesta(String respuesta) {
        new GuardarRegistroOrden(respuesta,"claveRastreoOrdenDevuelta.txt");
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
}