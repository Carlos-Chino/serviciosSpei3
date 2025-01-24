package org.serviciosSpei3.controles;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import static org.serviciosSpei3.controles.HttpClient.sendRequest;
import static org.serviciosSpei3.controles.KeycloakService.loadAccessToken;

public abstract class ServicioBase<T> {
    private static final String CERTIFICADO = "1733350443035";
    private static final String PROPIEDADES = "src/main/resources/propiedades.properties";

    protected abstract String generarPeticion(T datos);
    protected abstract String generarFirma(T datos);
    protected abstract void procesarRespuesta(String respuesta);
    protected abstract List<T> cargarDatos(String archivo) throws IOException;
    protected abstract String construirUrl(T datos) throws IOException;

    public void ejecutarServicio(String archivo) throws Exception {
        List<T> datosLista = cargarDatos(archivo);
        for (T datos : datosLista) {
            String urlServicio = construirUrl(datos);
            String peticion = generarPeticion(datos);
            String firma = generarFirma(datos);
            String respuesta = sendRequest(urlServicio, "POST", getHeaders(firma), peticion);
            procesarRespuesta(respuesta);
        }
    }

    protected Map<String, String> getHeaders(String firma) throws IOException {
        return Map.of(
                "Content-Type", "application/json",
                "Authorization", "Bearer " + loadAccessToken(),
                "X-EF-Firma", firma.trim(),
                "X-EF-Certificado", CERTIFICADO
        );
    }

    protected String getUrlServicioBase() throws IOException {
        Properties properties = new Properties();
        try (FileInputStream input = new FileInputStream(PROPIEDADES)) {
            properties.load(input);
            return properties.getProperty("urlServicioBase");
        }
    }
}
