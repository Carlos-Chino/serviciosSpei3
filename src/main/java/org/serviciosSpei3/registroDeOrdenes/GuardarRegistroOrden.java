package org.serviciosSpei3.registroDeOrdenes;

import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.*;
import java.util.HashSet;
import java.util.Set;

public class GuardarRegistroOrden {
    private final String respuesta;
    private final String nombreArchivo;

    public GuardarRegistroOrden(String respuesta, String nombreArchivo) {
        this.respuesta = respuesta;
        this.nombreArchivo = nombreArchivo;
        guardarClaveRastreoEnArchivo();
    }

    public String extraerClaveRastreo() {
        try {
            JSONObject jsonResponse = new JSONObject(respuesta);
            if (!jsonResponse.has("info") || jsonResponse.isNull("info")) {
                throw new IllegalArgumentException("La respuesta no contiene el campo 'info' o está vacío.");
            }
            String claveRastreo = jsonResponse.getString("info");
            if (claveRastreo.isEmpty()) {
                throw new IllegalArgumentException("El campo 'info' está vacío.");
            }
            return claveRastreo;
        } catch (JSONException e) {
            throw new IllegalArgumentException("La respuesta no es un JSON válido.", e);
        }
    }

    public void guardarClaveRastreoEnArchivo() {
        File archivo = new File(nombreArchivo);
        Set<String> existingLines = new HashSet<>();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivo, true))) {
            if (archivo.exists() && archivo.length() > 0) {
                try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
                    reader.lines().forEach(line -> existingLines.add(line.trim()));
                }
            }
            if (archivo.length() == 0) {
                writer.write("ordenId\n");
            }
            String claveRastreo = extraerClaveRastreo();
            if (existingLines.add(claveRastreo)) {
                writer.write(claveRastreo + "\n");
            }
        } catch (IOException e) {
            System.out.println("Error al guardar la clave de rastreo en archivo: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("Error en los datos de la respuesta: " + e.getMessage());
        }
    }
}

