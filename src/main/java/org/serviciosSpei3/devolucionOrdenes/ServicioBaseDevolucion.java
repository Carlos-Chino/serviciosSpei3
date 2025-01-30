package org.serviciosSpei3.devolucionOrdenes;

import org.serviciosSpei3.controles.ServicioBase;
import org.serviciosSpei3.registroDeOrdenes.GuardarRegistroOperacion;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public abstract class ServicioBaseDevolucion extends ServicioBase<DatosDevolucion> {
    private Integer causaDevolucion;
    private String tipoDevolucion;
    public ServicioBaseDevolucion(Integer causaDevoluion,String tipoDevolucion) {
        this.causaDevolucion=causaDevoluion;
        this.tipoDevolucion=tipoDevolucion;
    }

    protected abstract String generarPeticion(DatosDevolucion devolucion);
    protected abstract String generarFirma(DatosDevolucion devolucion);

    @Override
    protected String getMetodoHttp() {return "POST";}

    @Override
    protected String construirUrl(DatosDevolucion devolucion) throws IOException {
        return getUrlServicioBase() + "/api/v1/ordenes/recepciones/devuelve?ordenId=" + devolucion.getOrdenId();
    }

    @Override
    protected List<DatosDevolucion> cargarDatos(String archivo) throws IOException {
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
                devolucion.setFechaOperacion(datos[10]);
                devolucion.setMonto(new BigDecimal(datos[11]));
                devolucion.setMontoIntereses(new BigDecimal("0.01"));
                devolucion.setIndicadorBeneficiario("1");
                devolucion.setMontoDevolucion(new BigDecimal("0.01"));
                devoluciones.add(devolucion);
            }
        }
        return devoluciones;
    }

    protected void procesarRespuesta(String respuesta) {
        new GuardarRegistroOperacion(respuesta,"OrdenId"+this.tipoDevolucion+".txt","ordenId");
    }
}