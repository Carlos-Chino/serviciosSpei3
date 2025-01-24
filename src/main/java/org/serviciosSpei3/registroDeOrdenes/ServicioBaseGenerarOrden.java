package org.serviciosSpei3.registroDeOrdenes;

import org.serviciosSpei3.controles.ServicioBase;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Properties;

public abstract class ServicioBaseGenerarOrden extends ServicioBase<DatosOrdenes> {
    private static final String propiedades = "src/main/resources/propiedades.properties";
    protected String fechaOperacion = fechaOperacion();
    protected DatosOrdenes datosOrdenes;

    public ServicioBaseGenerarOrden() throws IOException {
        this.datosOrdenes = new DatosOrdenes();
        inicializarDatos();
    }

    protected abstract String generarPeticion(DatosOrdenes datos);
    protected abstract String generarFirma(DatosOrdenes datos);

    @Override
    protected String construirUrl(DatosOrdenes datos) throws IOException {
        return getUrlServicioBase() + "/api/v1/ordenes/envios";
    }

    protected List<DatosOrdenes> inicializarDatos() throws IOException {
        Properties properties = cargarPropiedadesEnvioPagos();
        datosOrdenes.setMonto(new BigDecimal(properties.getProperty("monto")));
        datosOrdenes.setInstancia(properties.getProperty("instancia"));
        datosOrdenes.setNombreOrdenante(properties.getProperty("nombreOrdenante"));
        datosOrdenes.setCuentaOrdenante(properties.getProperty("cuentaOrdenante"));
        datosOrdenes.setNombreBeneficiario(properties.getProperty("nombreOrdenante"));
        datosOrdenes.setCuentaBeneficiario(properties.getProperty("cuentaBeneficiario"));
        datosOrdenes.setRfcCurpBeneficiario(properties.getProperty("rfcCurpBeneficiario"));
        datosOrdenes.setRfcCurpOrdenante(properties.getProperty("rfcCurpOrdenante"));
        datosOrdenes.setFechaOperacion(fechaOperacion);
        datosOrdenes.setInstitucionOrdenante(90646);
        datosOrdenes.setInstitucionBeneficiaria(97846);
        datosOrdenes.setClaveRastreo("QASPEI3Cert" + System.currentTimeMillis());
        datosOrdenes.setTipoOperacion(1);
        datosOrdenes.setTipoCuentaOrdenante(40);
        datosOrdenes.setNumCelularOrdenante("5624128162");
        datosOrdenes.setDigitoIdOrdenante(8);
        datosOrdenes.setTipoCuentaBeneficiario(40);
        datosOrdenes.setNumCelularBeneficiario("5624128162");
        datosOrdenes.setDigitoIdBeneficiario(0);
        datosOrdenes.setConceptoPago("PruebasSTP");
       // datosOrdenes.setIva(new BigDecimal("0.00"));
        datosOrdenes.setReferenciaNumerica(250113);
        datosOrdenes.setReferenciaCobranza(123456);
        datosOrdenes.setPagoComision(0);
        datosOrdenes.setMontoComision(new BigDecimal("0.00"));
        datosOrdenes.setAliasCelCertBene("5624128172");
        datosOrdenes.setFolioPlataforma("31722b1a8131925b1bc7");
        datosOrdenes.setAliasCertComercio("646180318217309586");
        datosOrdenes.setDigitoCertComercio(283748345);
        datosOrdenes.setAliasCelCertOrd("3695045128");
        datosOrdenes.setNombreBeneficiario2("PRUEBA001 SA DE CV");
        datosOrdenes.setTipoCuentaBeneficiario2(40);
        datosOrdenes.setCuentaBeneficiario2("846180000000000016");
        datosOrdenes.setRfcCurpBeneficiario2("PRUE211130H54");
        return List.of(datosOrdenes);
    }

    private String fechaOperacion() {
        LocalTime currentTime = LocalTime.now();
        LocalDate currentDate = LocalDate.now();
        if (currentTime.isAfter(LocalTime.of(18, 0))) {
            currentDate = currentDate.plusDays(1);
        }
        return currentDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    public void ejecutarMultiple() throws IOException {
        Integer numeroEjecuciones;
        Properties properties = new Properties();
        try (FileInputStream input = new FileInputStream(propiedades)) {
            properties.load(input);
            numeroEjecuciones= Integer.parseInt(properties.getProperty("numeroEjecuciones"));
        }
        for (int i = 0; i < numeroEjecuciones; i++) {
            try {
                ejecutarServicio(null);
            } catch (Exception e) {
                System.out.println("Error en la ejecución " + (i + 1) + ": " + e.getMessage());
            }
        }
    }

    @Override
    protected List<DatosOrdenes> cargarDatos(String archivo) {
        return List.of(datosOrdenes);
    }

    @Override
    protected void procesarRespuesta(String respuesta) {
        new GuardarRegistroOperacion(respuesta, "OrdenesIdEnviadas.txt","ordenId");
    }

    protected Properties cargarPropiedadesEnvioPagos() throws IOException {
        Properties properties = new Properties();
        try (FileInputStream input = new FileInputStream(propiedades)) {
            properties.load(input);
        }
        return properties;
    }

    protected String valueNull(Object value) {
        if (value == null) {
            return "";
        }
        if (value instanceof BigDecimal) {
            return ((BigDecimal) value).toPlainString();
        }
        return value.toString();
    }

    @Override
    protected String getMetodoHttp() {return "POST";}
}
