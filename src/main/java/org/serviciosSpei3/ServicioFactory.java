package org.serviciosSpei3;

import lombok.SneakyThrows;
import org.serviciosSpei3.cancelacionOrdenes.CancelarOrden;
import org.serviciosSpei3.confirmaOrden.ConfirmarOrden;
import org.serviciosSpei3.devolucionOrdenes.*;
import org.serviciosSpei3.generacionURLCep.ConsultaConexion;
import org.serviciosSpei3.generacionURLCep.UrlCepOrdenesEnviadas;
import org.serviciosSpei3.generacionURLCep.UrlCepOrdenesRecibidas;
import org.serviciosSpei3.registroDeOrdenes.*;
import org.serviciosSpei3.serviciosConsulta.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ServicioFactory {
    private static final Map<String, Runnable> SERVICIOS = new HashMap<>();
    public ServicioFactory(String servicio){
        inicializarServicios();
        ejecutarServicio(servicio);
    }

    @SneakyThrows
    private void inicializarServicios() {
        SERVICIOS.put("OrdenTerceroATerceroT1", Exception(() -> new OrdenTerceroATerceroT1().ejecutarServicio()));
        SERVICIOS.put("OrdenTerceroAVentanillaT2", Exception(() -> new OrdenTerceroAVentanillaT2().ejecutarServicio()));
        SERVICIOS.put("OrdenTerceroATerceroVostroT3", Exception(() -> new OrdenTerceroATerceroVostroT3().ejecutarServicio()));
        SERVICIOS.put("OrdenTerceroAParticipanteT4", Exception(() -> new OrdenTerceroAParticipanteT4().ejecutarServicio()));
        SERVICIOS.put("OrdenParticipanteATerceroT5", Exception(() -> new OrdenParticipanteATerceroT5().ejecutarServicio()));
        SERVICIOS.put("OrdenParticipanteATerceroVostroT6", Exception(() -> new OrdenParticipanteATerceroVostroT6().ejecutarServicio()));
        SERVICIOS.put("OrdenParticipanteAParticipanteT7", Exception(() -> new OrdenParticipanteAParticipanteT7().ejecutarServicio()));
        SERVICIOS.put("OrdenTerceroATerceroFswT8", Exception(() -> new OrdenTerceroATerceroFswT8().ejecutarServicio()));
        SERVICIOS.put("OrdenTerceroATerceroVostroFswT9", Exception(() -> new OrdenTerceroATerceroVostroFswT9().ejecutarServicio()));
        SERVICIOS.put("OrdenParticipanteATerceroFswT10", Exception(() -> new OrdenParticipanteATerceroFswT10().ejecutarServicio()));
        SERVICIOS.put("OrdenParticipanteATerceroVostroFwsT11", Exception(() -> new OrdenParticipanteATerceroVostroFwsT11().ejecutarServicio()));
        SERVICIOS.put("CobroPresencialUnaOcasionT19", Exception(() -> new CobroPresencialUnaOcasionT19().ejecutarServicio()));
        SERVICIOS.put("CobroNoPresencialUnaOcasion20", Exception(() -> new CobroNoPresencialUnaOcasion20().ejecutarServicio()));
        SERVICIOS.put("CobroNoPresencialRecurrente21", Exception(() -> new CobroNoPresencialRecurrente21().ejecutarServicio()));
        SERVICIOS.put("CobroNoPresencialATercero22", Exception(() -> new CobroNoPresencialATercero22().ejecutarServicio()));
        SERVICIOS.put("ConsultaOrdenesEnviadas", Exception(() -> new ConsultaOrdenesEnviadas("").ejecutaServicio("OrdenesIdEnviadas.txt")));
        SERVICIOS.put("ConsultaOrdenesEnviadasV2", Exception(() -> new ConsultaOrdenesEnviadasV2(1, "").ejecutaServicio("OrdenesIdEnviadas.txt")));
        SERVICIOS.put("SearchOrdenesEnviadas", Exception(() -> new SearchOrdenesEnviadas("").ejecutaServicio("search")));
        SERVICIOS.put("SearchOrdenesRecibidas", Exception(() -> new SearchOrdenesRecibidas("").ejecutaServicio("search")));
        SERVICIOS.put("ConsultaOrdenesRecibidasV2", Exception(() -> new ConsultaOrdenesRecibidasV2("").ejecutaServicio("OrdenesRecibidas.txt")));
        SERVICIOS.put("SearchOrdenesEnviadasPendientes", Exception(() -> new SearchOrdenesEnviadasPendientes("").ejecutaServicio("pendientes")));
        SERVICIOS.put("SearchOrdenesRecibidasPendientes", Exception(() -> new SearchOrdenesRecibidasPendientes("").ejecutaServicio("pendientes")));
        SERVICIOS.put("DevolucionNoAcreditadaT0", Exception(this::devolucionT0));
        SERVICIOS.put("DevolucionNoAcreditadaExtemporaneaT16", Exception(this::devolucionT16));
        SERVICIOS.put("DevolucionAcreditadaT17", Exception(this::devolucionT17));
        SERVICIOS.put("DevolucionAcreditadaExtemporaneaT18",  Exception(this::devolucionT18));
        SERVICIOS.put("DevolucionEspecialAcreditadaT23",Exception(this::devolucionT23));
        SERVICIOS.put("DevolucionEspecialAcreditadaExtemporaneaT24", Exception(this::devolucionT24));
        SERVICIOS.put("CancelarOrden", Exception(this::cancelacionOrdenes));
        SERVICIOS.put("ConfirmarOrdenes", Exception(this::confirmarOrdenes));
        SERVICIOS.put("UrlCepOrdenesEnviadas", Exception(this::generaUrlCepOrdenesEnviadas));
        SERVICIOS.put("UrlCepOrdenesRecibidas", Exception(this::generaUrlCepOrdenesRecibidas));
        SERVICIOS.put("ConsultaConciliacion", Exception(() -> new ConsultaConciliacion("90646", "A").ejecutaServicio(null)));
        SERVICIOS.put("ConsultaConexion", Exception(() -> new ConsultaConexion("90646", "A").ejecutaServicio(null)));
        SERVICIOS.put("PagosVariosTipos", Exception(this::pagosVariosTipos));
    }

    private void pagosVariosTipos() throws IOException {
        new OrdenTerceroAVentanillaT2().ejecutarServicio();
        new OrdenTerceroATerceroVostroT3().ejecutarServicio();
        new OrdenTerceroAParticipanteT4().ejecutarServicio();
        new OrdenParticipanteATerceroT5().ejecutarServicio();
        new OrdenParticipanteATerceroVostroT6().ejecutarServicio();
        new OrdenParticipanteAParticipanteT7().ejecutarServicio();
        new OrdenTerceroATerceroFswT8().ejecutarServicio();
        new OrdenTerceroATerceroVostroFswT9().ejecutarServicio();
        new OrdenParticipanteATerceroFswT10().ejecutarServicio();
        new OrdenParticipanteATerceroVostroFwsT11().ejecutarServicio();
        /*new OrdenTerceroATerceroT1().ejecutarMultiple();
        new CobroPresencialUnaOcasionT19().ejecutarMultiple();
        new CobroNoPresencialUnaOcasion20().ejecutarMultiple();
        new CobroNoPresencialRecurrente21().ejecutarMultiple();
        new CobroNoPresencialATercero22().ejecutarMultiple();*/
    }
    private void confirmarOrdenes() throws Exception {
        new SearchOrdenesRecibidas("ConfirmarOrdenes").ejecutaServicio("search");
        new ConfirmarOrden().ejecutarServicio("OrdenesRecibidasConfirmarOrdenes.txt");

        /*new ConsultaOrdenesRecibidasV2("ConfirmarOrdenes").ejecutaServicio("OrdenesRecibidasConfirmarOrdenes.txt");
        new ConfirmarOrden().ejecutarServicio("ConsultaOrdenesRecibidasConfirmarOrdenes.txt");*/

    }

    private void cancelacionOrdenes() throws Exception {
        //new ConsultaOrdenesEnviadas("CancelarOrdenes").ejecutaServicio("OrdenesIdEnviadas.txt");
        new SearchOrdenesEnviadas("CancelarOrdenes").ejecutaServicio("search");
        new CancelarOrden().ejecutarServicio("ConsultaOrdenesEnviadasCancelarOrdenes.txt");
    }

    private void generaUrlCepOrdenesRecibidas() throws Exception {
        new SearchOrdenesRecibidas("UrlCepOrdenesR").ejecutaServicio("search");
        new UrlCepOrdenesRecibidas().ejecutaServicio("OrdenesRecibidasUrlCepOrdenesR.txt");

        //new ConsultaOrdenesRecibidasV2("UrlCepOrdenesR").ejecutaServicio("OrdenesRecibidasUrlCepOrdenesR.txt");
        //new UrlCepOrdenesRecibidas().ejecutaServicio("ConsultaOrdenesRecibidasUrlCepOrdenesR.txt");

    }

    private void generaUrlCepOrdenesEnviadas() throws Exception {
        new SearchOrdenesEnviadas("UrlCepOrdenesE").ejecutaServicio("search");
        new UrlCepOrdenesEnviadas().ejecutaServicio("ConsultaOrdenesEnviadasUrlCepOrdenesE.txt");
    }

    private void devolucionT0() throws Exception {
        new SearchOrdenesRecibidas("DevolucionT0").ejecutaServicio("search");
        //new ConsultaOrdenesRecibidasV2("DevolucionT0").ejecutaServicio("OrdenesRecibidasDevolucionT0.txt");
        //new DevolucionNoAcreditadaT0(16).ejecutarServicio("ConsultaOrdenesRecibidasDevolucionT0.txt");
        new DevolucionNoAcreditadaT0(16,"DevolucionT0").ejecutarServicio("OrdenesRecibidasDevolucionT0.txt");

    }
    //
    private void devolucionT16() throws Exception {
        new SearchOrdenesRecibidas("DevolucionT16").ejecutaServicio("search");
        new DevolucionNoAcreditadaExtemporaneaT16(16,"DevolucionT16").ejecutarServicio("OrdenesRecibidasDevolucionT16.txt");
    }

    private void devolucionT17() throws Exception {
        new SearchOrdenesRecibidas("DevolucionT17").ejecutaServicio("search");
        new DevolucionAcreditadaT17(16,"DevolucionT17").ejecutarServicio("OrdenesRecibidasDevolucionT17.txt");
    }

    //
    private void devolucionT18() throws Exception {
        new SearchOrdenesRecibidas("DevolucionT18").ejecutaServicio("search");
        new DevolucionAcreditadaExtemporaneaT18(16,"DevolucionT18").ejecutarServicio("OrdenesRecibidasDevolucionT18.txt");
    }

    private void devolucionT23() throws Exception {
        new SearchOrdenesRecibidas("DevolucionT23").ejecutaServicio("search");
        new DevolucionEspecialAcreditadaT23(16,"DevolucionT23").ejecutarServicio("OrdenesRecibidasDevolucionT23.txt");
    }

    //
    private void devolucionT24() throws Exception {
        new SearchOrdenesRecibidas("DevolucionT24").ejecutaServicio("search");
        new DevolucionEspecialAcreditadaExtemporaneaT24(16,"DevolucionT24").ejecutarServicio("OrdenesRecibidasDevolucionT24.txt");
    }


    public void ejecutarServicio(String servicio) {
        Runnable accion = SERVICIOS.get(servicio);
        if (accion != null) {
            accion.run();
        } else {
            throw new RuntimeException("Servicio no reconocido: " + servicio);
        }
    }

    private Runnable Exception(ExceptionConsumer consumer) {
        return () -> {
            try {
                consumer.accept();
            } catch (Exception e) {
                throw new RuntimeException("Error al ejecutar servicio", e);
            }
        };
    }

    @FunctionalInterface
    interface ExceptionConsumer {
        void accept() throws Exception;
    }
}



