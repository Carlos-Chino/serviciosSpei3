package org.serviciosSpei3.serviciosConsulta;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.serviciosSpei3.controles.ParametrizedRequest;
import org.serviciosSpei3.registroDeOrdenes.GuardarRegistroOrden;

import java.io.*;
import java.util.*;
import static org.serviciosSpei3.controles.HttpClient.sendRequest;
import static org.serviciosSpei3.controles.KeycloakService.loadAccessToken;

public abstract class BaseServicioConsulta {
    private String respuesta;
    private static final String servicioBase = "src/main/resources/propiedades.properties";
    protected  String buildUrl(Map<String, String> parametros, Map<String, String> params) throws IOException{
        throw new UnsupportedOperationException("El método buildUrl() con parámetros debe ser implementado en la clase hija");}
    protected  String buildUrl(String ordenId) throws IOException{
        throw new UnsupportedOperationException("El método buildUrl() sin parámetros debe ser implementado en la clase hija");}
    protected String buildUrl() throws IOException {
        throw new UnsupportedOperationException("El método buildUrl() sin parámetros debe ser implementado en la clase hija");}

    protected Map<String, String> getHeaders() throws IOException {
        return Map.of("Content-Type", "application/json",
                "Authorization", "Bearer " + loadAccessToken());
    }

    protected abstract String getTipoConsulta();

    public void ejecutaServicio(Object datos) throws Exception {
        if (datos == null) {
            String url = buildUrl();
            respuesta = sendRequest(url, "GET", getHeaders(), null);
        } else if (datos instanceof String) {
            String archivo = (String) datos;
            List<String> listOrdenId = leerIdOrdenArchivo(archivo);
            for (String ordenId : listOrdenId) {
                String url = buildUrl(ordenId);
                respuesta = sendRequest(url, "GET", getHeaders(), null);
                if (this instanceof ConsultaOrdenesEnviadas) {
                    ((ConsultaOrdenesEnviadas) this).guardarOrdenesEnviadas(respuesta);
                }else if (this instanceof ConsultaOrdenesEnviadasV2) {
                    ((ConsultaOrdenesEnviadasV2) this).guardarOrdenesEnviadasV2(respuesta);
                }
                else if (this instanceof ConsultaOrdenesRecibidasV2) {
                    ((ConsultaOrdenesRecibidasV2) this).guardarOrdenesRecV2(respuesta);
                }
            }
        } else if (datos instanceof ParametrizedRequest) {
            ParametrizedRequest request = (ParametrizedRequest) datos;
            String url = buildUrl(request.getParametros(), request.getParams());
            respuesta = sendRequest(url, "GET", getHeaders(), null);
            if (this instanceof SearchOrdenesRecibidas) {
                ((SearchOrdenesRecibidas) this).guardarOrdenesRec(respuesta);
            } else if (this instanceof SearchOrdenesEnviadas) {
                ((SearchOrdenesEnviadas) this).guardarOrdenesEnv(respuesta);
            }
            else if (this instanceof SearchOrdenesEnviadasPendientes) {
                ((SearchOrdenesEnviadasPendientes) this).guardarOrdenesEnvPendientes(respuesta);
            }else if (this instanceof SearchOrdenesRecibidasPendientes) {
                ((SearchOrdenesRecibidasPendientes) this).guardarOrdenesRecibidasPendientes(respuesta);
            }
        } else {
            throw new IllegalArgumentException("Datos incorrectos");
        }
    }

    protected List<String>  leerIdOrdenArchivo(String archivo) throws Exception {
        List<String> listOrdenId = new ArrayList<>();
        int columna;
        String tipoConsulta=getTipoConsulta();
        switch (tipoConsulta) {
            case "consulta":
                columna = 0;
                break;
            case "search":
                columna = 5;
                break;
            default:
                throw new IllegalArgumentException("Tipo de columna no válido: " + tipoConsulta);
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
            String linea;
            reader.readLine();
            while ((linea = reader.readLine()) != null) {
                String[] datos = linea.split("\\|");
                if (columna < datos.length) {
                    listOrdenId.add(datos[columna]);
                } else {
                    throw new IllegalArgumentException("El índice de la columna excede el número de columnas en la línea.");
                }
            }
        }
        return listOrdenId;
    }


    protected void guardarOrdenes(String respuesta, String nombreArchivo, boolean isConsulta) {
        JSONObject respuestaJson = new JSONObject(respuesta);
        JSONArray ordenes = isConsulta ? new JSONArray().put(respuestaJson.optJSONObject("info")) : respuestaJson.getJSONArray("pagina");
        File archivo = new File(nombreArchivo);
        Set<String> existingLines = new HashSet<>();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivo, true))) {
            if (!archivo.exists() || archivo.length() == 0) {
                writer.write("institucionOrdenante|instancia|institucionBeneficiaria|claveRastreo|tipoPago|ordenId|"
                        + "nombreOrdenante|rfcCurpOrdenante|nombreBeneficiario|rfcCurpBeneficiario|monto|estado|estadoCDA|estadoDevolucion\n");
            }
            if (archivo.exists()) {
                try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
                    reader.lines().forEach(line -> existingLines.add(line.trim()));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            for (int i = 0; i < ordenes.length(); i++) {
                JSONObject orden = ordenes.getJSONObject(i);
                String institucionOrdenante = orden.optString("institucionOrdenante", "N/A");
                String instancia = orden.optString("instancia", "N/A");
                String institucionBeneficiaria = orden.optString("institucionBeneficiaria", "N/A");
                String claveRastreo = orden.optString("claveRastreo", "N/A");
                String tipoPago = String.valueOf(orden.optInt("tipoPago", 1));
                String ordenId = orden.optString("ordenId", "N/A");
                String monto = orden.optString("monto", "N/A");
                String estado = orden.optString("estado", "SNE");
                String estadoCDA = orden.optString("estadoCDA", "SNE");
                String estadoDevolucion = orden.optString("estadoDevolucion", "SNE");
                JSONObject detalle = orden.optJSONObject("detalle");
                String nombreOrdenante = detalle.optString("nombreOrdenante", "N/A");
                String rfcCurpOrdenante = detalle.optString("rfcCurpOrdenante", "N/A");
                String nombreBeneficiario = detalle.optString("nombreBeneficiario", "N/A");
                String rfcCurpBeneficiario = detalle.optString("rfcCurpBeneficiario", "N/A");
                String nuevoRegistro = String.join("|", institucionOrdenante, instancia, institucionBeneficiaria, claveRastreo, tipoPago, ordenId,
                        nombreOrdenante, rfcCurpOrdenante, nombreBeneficiario, rfcCurpBeneficiario, monto, estado, estadoCDA, estadoDevolucion
                );
                if (existingLines.add(nuevoRegistro)) {
                    writer.write(nuevoRegistro + "\n");
                }
            }
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
    }

    protected void procesarRespuesta(String respuesta) {
        new GuardarRegistroOrden(respuesta,"OrdenesIdEnviadas.txt");
    }

    protected String getUrlServicioBase() throws IOException {
        Properties properties = new Properties();
        try (FileInputStream input = new FileInputStream(servicioBase)) {
            properties.load(input);
            return properties.getProperty("urlServicioBase");
        }
    }

}
