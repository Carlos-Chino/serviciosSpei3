package org.serviciosSpei3;

import java.util.Map;

public class ConfiguracionParametrosOtros {
    public static Map<String, String> parametros() {
        return Map.of(
                "fechaOperacion", "20250122",
                "tipoPago", "1",
                "page", "0",
                "size", "3"
        );
    }

    public static Map<String, String> params() {
        return Map.of(
                //"estado.clave;", ":LQ"
               // "cda.estado.clave;", ":SNE"
                //"estadoDevolucion.clave;", ":SNE"
        );
    }

    public static Map<String, String> parametrosSearchPendientes() {
        return Map.of(
                "tipoPago", "1",
                "page", "0",
                "size", "3",
                "estadoCDA", "SNE",
                "estadoDevolucion", "SNE",
                "fechaOperacion", "20250122"
        );
    }
}
