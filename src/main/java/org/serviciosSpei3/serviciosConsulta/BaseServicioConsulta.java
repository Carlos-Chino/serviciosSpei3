package org.serviciosSpei3.serviciosConsulta;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.serviciosSpei3.generacionURLCep.UrlCepOrdenesEnviadas;
import org.serviciosSpei3.generacionURLCep.UrlCepOrdenesRecibidas;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

import static org.serviciosSpei3.controles.HttpClient.sendRequest;
import static org.serviciosSpei3.controles.KeycloakService.loadAccessToken;

public abstract class BaseServicioConsulta {
    private String respuesta;
    private static final String propiedades = "src/main/resources/propiedades.properties";

    protected String buildUrl(Map<String, String> parametros, Map<String, String> params) throws IOException {return buildUrlImpl(parametros, params);}
    protected String buildUrl(String ordenId) throws IOException {return buildUrlImpl(ordenId);}
    protected String buildUrl() throws IOException {return buildUrlImpl();}
    private String buildUrlImpl(Object... params) throws IOException {
        throw new UnsupportedOperationException("Método buildUrl debe ser implementado.");
    }
    protected abstract String getTipoConsulta();
    private Map<String, String> getHeaders() throws IOException {
        return Map.of("Content-Type", "application/json", "Authorization", "Bearer " + loadAccessToken());
    }

    public void ejecutaServicio(String datos) throws Exception {
        if (datos == null) {
            procesarConsultaSinDatos();
        } else if ("search".equals(datos)) {
            procesarConsultaSearch(false);
        } else if ("pendientes".equals(datos)) {
            procesarConsultaSearch(true);
        } else {
            procesarConsultaConArchivo(datos);
        }
    }

    private void procesarConsultaSinDatos() throws Exception {
        String url = buildUrl();
        respuesta = sendRequest(url, "GET", getHeaders(), null);
    }

    private void procesarConsultaConArchivo(String archivo) throws Exception {
        List<String> listOrdenId = leerIdOrdenArchivo(archivo);
        for (String ordenId : listOrdenId) {
            String url = buildUrl(ordenId);
            respuesta = sendRequest(url, "GET", getHeaders(), null);
            guardarRespuestaPorTipo();
        }
    }

    private void procesarConsultaSearch(boolean usarPendientes) throws Exception {
        Map<String, String> parametros;
        Map<String, String> params = null;
        if (usarPendientes) {
            parametros = cargarPropiedades("pendientes.");
        } else {
            parametros = cargarPropiedades("parametros.");
            params = cargarPropiedades("params.");
        }
        String url = buildUrl(parametros, params);
        respuesta = sendRequest(url, "GET", getHeaders(), null);
        guardarRespuestaPorTipo();
    }

    private void guardarRespuestaPorTipo() {
        if (this instanceof ConsultaOrdenesEnviadas) {
            ((ConsultaOrdenesEnviadas) this).guardarOrdenesEnviadas(respuesta);
        } else if (this instanceof ConsultaOrdenesEnviadasV2) {
            ((ConsultaOrdenesEnviadasV2) this).guardarOrdenesEnviadasV2(respuesta);
        }
        else if (this instanceof ConsultaOrdenesRecibidasV2) {
            ((ConsultaOrdenesRecibidasV2) this).guardarOrdenesRecV2(respuesta);
        }
        else if (this instanceof SearchOrdenesRecibidas) {
            ((SearchOrdenesRecibidas) this).guardarOrdenesRec(respuesta);
        }
        else if (this instanceof SearchOrdenesEnviadas) {
            ((SearchOrdenesEnviadas) this).guardarOrdenesEnv(respuesta);
        }
        else if (this instanceof SearchOrdenesEnviadasPendientes) {
            ((SearchOrdenesEnviadasPendientes) this).guardarOrdenesEnvPendientes(respuesta);
        }else if (this instanceof SearchOrdenesRecibidasPendientes) {
            ((SearchOrdenesRecibidasPendientes) this).guardarOrdenesRecibidasPendientes(respuesta);
        }
        else if (this instanceof UrlCepOrdenesEnviadas) {
            ((UrlCepOrdenesEnviadas) this).procesarRespuesta(respuesta);
        } else if (this instanceof UrlCepOrdenesRecibidas) {
            ((UrlCepOrdenesRecibidas) this).procesarRespuesta(respuesta);
        }
    }


    protected List<String> leerIdOrdenArchivo(String archivo) throws Exception {
        int columna = getColumnaPorTipoConsulta();
        List<String> listOrdenId = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
            reader.readLine();
            String linea;
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

    private int getColumnaPorTipoConsulta() {
        switch (getTipoConsulta()) {
            case "consulta":
                return 0;
            case "search":
                return 5;
            default:
                throw new IllegalArgumentException("Tipo de columna no válido: " + getTipoConsulta());
        }
    }

    protected void guardarOrdenes(String respuesta, String nombreArchivo, boolean isConsulta) {
        JSONObject respuestaJson = new JSONObject(respuesta);
        JSONArray ordenes = isConsulta ? new JSONArray().put(respuestaJson.optJSONObject("info")) : respuestaJson.getJSONArray("pagina");
        File archivo = new File(nombreArchivo);
        Set<String> existingLines = loadExistingLines(archivo);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivo, true))) {
            if (!archivo.exists() || archivo.length() == 0) {
                escribirCabecera(writer);
            }
            for (int i = 0; i < ordenes.length(); i++) {
                String nuevoRegistro = construirRegistro(ordenes.getJSONObject(i));
                if (existingLines.add(nuevoRegistro)) {
                    writer.write(nuevoRegistro + "\n");
                }
            }
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
    }

    private Set<String> loadExistingLines(File archivo) {
        Set<String> existingLines = new HashSet<>();
        if (archivo.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
                reader.lines().forEach(line -> existingLines.add(line.trim()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return existingLines;
    }

    private void escribirCabecera(BufferedWriter writer) throws IOException {
        writer.write("institucionOrdenante|instancia|institucionBeneficiaria|claveRastreo|tipoPago|ordenId|"
                + "nombreOrdenante|rfcCurpOrdenante|nombreBeneficiario|rfcCurpBeneficiario|monto|estado|estadoCDA|estadoDevolucion\n");
    }

    private String construirRegistro(JSONObject orden) {
        JSONObject detalle = orden.optJSONObject("detalle");
        return String.join("|",
                orden.optString("institucionOrdenante", "N/A"),
                orden.optString("instancia", "N/A"),
                orden.optString("institucionBeneficiaria", "N/A"),
                orden.optString("claveRastreo", "N/A"),
                String.valueOf(orden.optInt("tipoPago", 1)),
                orden.optString("ordenId", "N/A"),
                detalle.optString("nombreOrdenante", "N/A"),
                detalle.optString("rfcCurpOrdenante", "N/A"),
                detalle.optString("nombreBeneficiario", "N/A"),
                detalle.optString("rfcCurpBeneficiario", "N/A"),
                orden.optString("monto", "N/A"),
                orden.optString("estado", "SNE"),
                orden.optString("estadoCDA", "SNE"),
                orden.optString("estadoDevolucion", "SNE"));
    }


    private static Properties cargarPropiedadesDesdeArchivo() throws IOException {
        Properties properties = new Properties();
        try (FileInputStream fis = new FileInputStream(propiedades)) {
            properties.load(fis);
        }
        return properties;
    }

    protected String getUrlServicioBase() throws IOException {
        Properties properties = cargarPropiedadesDesdeArchivo();
        return properties.getProperty("urlServicioBase");
    }

    public static Map<String, String> cargarPropiedades(String prefijo) throws IOException {
        Properties properties = cargarPropiedadesDesdeArchivo();
        return properties.entrySet().stream()
                .filter(entry -> ((String) entry.getKey()).startsWith(prefijo))
                .collect(Collectors.toMap(
                        entry -> ((String) entry.getKey()).substring(prefijo.length()),
                        entry -> (String) entry.getValue()
                ));
    }
}

