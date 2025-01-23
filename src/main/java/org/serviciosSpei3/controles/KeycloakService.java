package org.serviciosSpei3.controles;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Properties;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import static org.serviciosSpei3.controles.HttpClient.sendRequest;

public class KeycloakService {
    private static final String urlServicio = "https://keycloak-stp-core-pruebas-apps-spei-asa.stp-ti.com/realms/spei/protocol/openid-connect/token";
    private static final String idCliente = "SpeiPruebasRest";
    private static final String secretClient = "gHaRjfIeY6wXV04AH7syRJUfB7uO75Tw";
    private static final String accessTokenProperties = "src/main/resources/access_token.properties";

    public static void login() throws Exception {
        String peticion = String.format("grant_type=client_credentials&client_id=%s&client_secret=%s",
                URLEncoder.encode(idCliente, StandardCharsets.UTF_8),
                URLEncoder.encode(secretClient, StandardCharsets.UTF_8));
        Map<String, String> headers = Map.of("Content-Type", "application/x-www-form-urlencoded");
        String respuesta = sendRequest(urlServicio, "POST", headers, peticion);
        accessToken(getAccessToken(respuesta));
    }

    public static void accessToken(String accessToken) throws Exception {
        String peticion = "grant_type=urn:ietf:params:oauth:grant-type:uma-ticket&audience=spei-core";
        Map<String, String> headers = Map.of(
                "Content-Type", "application/x-www-form-urlencoded",
                "Authorization", "Bearer " + accessToken);
        String respuesta = sendRequest(urlServicio, "POST", headers, peticion);
        saveAccessToken(getAccessToken(respuesta));
    }

    private static String getAccessToken(String jsonResponse) {
        JsonObject respuesta = JsonParser.parseString(jsonResponse).getAsJsonObject();
        if (!respuesta.has("access_token")) {
            throw new IllegalArgumentException("La respuesta no contiene un access_token: " + jsonResponse);
        }
        return respuesta.get("access_token").getAsString();
    }

    public static void saveAccessToken(String accessToken) throws IOException {
        Properties properties = new Properties();
        try (FileOutputStream output = new FileOutputStream(accessTokenProperties)) {
            properties.setProperty("access_token", accessToken);
            properties.store(output, "Archivo de configuración con el access token");
        }
    }
    public static String loadAccessToken() throws IOException {
        Properties properties = new Properties();
        try (FileInputStream input = new FileInputStream(accessTokenProperties)) {
            properties.load(input);
            return properties.getProperty("access_token");
        }
    }
}