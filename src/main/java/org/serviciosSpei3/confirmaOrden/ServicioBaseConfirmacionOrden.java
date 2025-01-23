package org.serviciosSpei3.confirmaOrden;

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

public abstract class ServicioBaseConfirmacionOrden {
    private static final String certificado = "1733350443035";
    private static final String servicioBase = "src/main/resources/propiedades.properties";

    protected abstract String generarPeticion(datosConfirmaOrden confirmaOrden);
    protected abstract String generarFirma(datosConfirmaOrden confirmaOrden);
    protected abstract void inicializarDatos(datosConfirmaOrden confirmaOrden);

    public void ejecutarServicio(String archivo) throws Exception {
        List<datosConfirmaOrden> confirmaOrdenes = cargarDatosConfirmaOrden(archivo);
        for (datosConfirmaOrden confirmaOrden : confirmaOrdenes) {
            inicializarDatos(confirmaOrden);
            String urlServicio = construirUrl(confirmaOrden.getOrdenId());
            String peticion = generarPeticion(confirmaOrden);
            String firma = generarFirma(confirmaOrden);
            String respuesta = sendRequest(urlServicio, "POST", getHeaders(firma), peticion);
            procesarRespuesta(respuesta);
        }
    }

    private String construirUrl(String ordenId) throws IOException {
        return getUrlServicioBase() + "/api/v1/ordenes/recepciones/confirma?ordenId=" + ordenId;
    }

    protected List<datosConfirmaOrden> cargarDatosConfirmaOrden(String archivo) throws IOException {
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

    protected void procesarRespuesta(String respuesta) {
        new GuardarRegistroOrden(respuesta, "claveRastreoOrdenConfirmada.txt");
    }

    protected Map<String, String> getHeaders(String firma) throws IOException {
        return Map.of(
                "Content-Type", "application/json",
                "Authorization", "Bearer " + loadAccessToken(),
                "X-EF-Firma", firma.trim(),
                "X-EF-Certificado", certificado
        );
    }

    public String getUrlServicioBase() throws IOException {
        Properties properties = new Properties();
        try (FileInputStream input = new FileInputStream(servicioBase)) {
            properties.load(input);
            return properties.getProperty("urlServicioBase");
        }
    }
}