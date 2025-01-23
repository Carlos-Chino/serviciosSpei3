package org.serviciosSpei3.controles;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;
import java.util.Scanner;

public class HttpClient {
    public static String sendRequest(String urlServicio, String metodo, Map<String, String> headers, String peticion) throws Exception {
        System.out.println("URL Servicio: " + urlServicio);
        System.out.println("Petición: " + (peticion != null ? peticion : "{No aplica}"));
        HttpURLConnection conexion = null;
        StringBuilder respuesta = new StringBuilder();
        int responseCode = 0;
        try {
            conexion = (HttpURLConnection) new URL(urlServicio).openConnection();
            conexion.setRequestMethod(metodo);
            agregarHeaders(conexion, headers);
            if (metodo.equalsIgnoreCase("POST") || metodo.equalsIgnoreCase("PUT")) {
                conexion.setDoOutput(true);
                if (peticion != null && !peticion.isEmpty()) {
                    try (OutputStream os = conexion.getOutputStream()) {
                        os.write(peticion.getBytes());
                    }
                }
            } else if (metodo.equalsIgnoreCase("GET") && peticion != null && !peticion.isEmpty()) {
                throw new IllegalArgumentException("Las solicitudes GET no deben incluir un cuerpo de petición.");
            }
            responseCode = conexion.getResponseCode();
            InputStream responseStream = responseCode / 100 == 2 ? conexion.getInputStream() : conexion.getErrorStream();
            respuesta.append(leerFlujo(responseStream, conexion.getResponseMessage()));

        } catch (IOException e) {
            respuesta.append("Error en la conexión: ").append(e.getMessage());
            if (conexion != null) {
                respuesta.append(" - ").append(leerFlujo(conexion.getErrorStream(), "Error desconocido"));
            }
        } catch (Exception e) {
            respuesta.append("Excepción inesperada: ").append(e.getMessage());
        } finally {
            if (conexion != null) {
                conexion.disconnect();
            }
        }
        System.out.println("Response code: " + responseCode + ", Respuesta: " + respuesta+"\n");
        return respuesta.toString();
    }

    private static String leerFlujo(InputStream flujo, String mensajeDefault) throws Exception {
        if (flujo == null){
            return mensajeDefault + ", El servidor no proporcionó un cuerpo de respuesta.";
        }
        try (Scanner scanner = new Scanner(flujo).useDelimiter("\\A")) {
            return scanner.hasNext() ? scanner.next() : "Sin contenido.";
        }
    }
    private static void agregarHeaders(HttpURLConnection conexion, Map<String, String> headers) {
        if (headers != null) {
            headers.forEach(conexion::setRequestProperty);
        }
    }
}


