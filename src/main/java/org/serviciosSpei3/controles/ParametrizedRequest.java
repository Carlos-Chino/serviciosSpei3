package org.serviciosSpei3.controles;

import java.util.Map;

public class ParametrizedRequest {
    private Map<String, String> parametros;
    private Map<String, String> params;

    public ParametrizedRequest(Map<String, String> parametros, Map<String, String> params) {
        this.parametros = parametros;
        this.params = params;
    }

    public Map<String, String> getParametros() {
        return parametros;
    }
    public Map<String, String> getParams() {
        return params;
    }
}
