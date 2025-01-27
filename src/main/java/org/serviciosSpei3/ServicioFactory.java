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

    public ServicioFactory(String servicio) throws Exception {
        inicializarServicios();
        ejecutarServicio(servicio);
    }

    @SneakyThrows
    private void inicializarServicios() {
        SERVICIOS.put("OrdenTerceroATerceroT1", Exception(() -> new OrdenTerceroATerceroT1().ejecutarMultiple()));
        SERVICIOS.put("OrdenTerceroAVentanillaT2", Exception(() -> new OrdenTerceroAVentanillaT2().ejecutarMultiple()));
        SERVICIOS.put("OrdenTerceroATerceroVostroT3", Exception(() -> new OrdenTerceroATerceroVostroT3().ejecutarMultiple()));
        SERVICIOS.put("OrdenTerceroAParticipanteT4", Exception(() -> new OrdenTerceroAParticipanteT4().ejecutarMultiple()));
        SERVICIOS.put("OrdenParticipanteATerceroT5", Exception(() -> new OrdenParticipanteATerceroT5().ejecutarMultiple()));
        SERVICIOS.put("OrdenParticipanteATerceroVostroT6", Exception(() -> new OrdenParticipanteATerceroVostroT6().ejecutarMultiple()));
        SERVICIOS.put("OrdenParticipanteAParticipanteT7", Exception(() -> new OrdenParticipanteAParticipanteT7().ejecutarMultiple()));
        SERVICIOS.put("OrdenTerceroATerceroFswT8", Exception(() -> new OrdenTerceroATerceroFswT8().ejecutarMultiple()));
        SERVICIOS.put("OrdenTerceroATerceroVostroFswT9", Exception(() -> new OrdenTerceroATerceroVostroFswT9().ejecutarMultiple()));
        SERVICIOS.put("OrdenParticipanteATerceroFswT10", Exception(() -> new OrdenParticipanteATerceroFswT10().ejecutarMultiple()));
        SERVICIOS.put("OrdenParticipanteATerceroVostroFwsT11", Exception(() -> new OrdenParticipanteATerceroVostroFwsT11().ejecutarMultiple()));
        SERVICIOS.put("CobroPresencialUnaOcasionT19", Exception(() -> new CobroPresencialUnaOcasionT19().ejecutarMultiple()));
        SERVICIOS.put("CobroNoPresencialUnaOcasion20", Exception(() -> new CobroNoPresencialUnaOcasion20().ejecutarMultiple()));
        SERVICIOS.put("CobroNoPresencialRecurrente21", Exception(() -> new CobroNoPresencialRecurrente21().ejecutarMultiple()));
        SERVICIOS.put("CobroNoPresencialATercero22", Exception(() -> new CobroNoPresencialATercero22().ejecutarMultiple()));
        SERVICIOS.put("ConsultaOrdenesEnviadas", Exception(() -> new ConsultaOrdenesEnviadas("").ejecutaServicio("OrdenesIdEnviadas.txt")));
        SERVICIOS.put("ConsultaOrdenesEnviadasV2", Exception(() -> new ConsultaOrdenesEnviadasV2(1, "").ejecutaServicio("OrdenesIdEnviadas.txt")));
        SERVICIOS.put("SearchOrdenesEnviadas", Exception(() -> new SearchOrdenesEnviadas("").ejecutaServicio("search")));
        SERVICIOS.put("SearchOrdenesRecibidas", Exception(() -> new SearchOrdenesRecibidas("").ejecutaServicio("search")));
        SERVICIOS.put("ConsultaOrdenesRecibidasV2", Exception(() -> new ConsultaOrdenesRecibidasV2(1, "").ejecutaServicio("SearchOrdenesRecibidas.txt")));
        SERVICIOS.put("SearchOrdenesEnviadasPendientes", Exception(() -> new SearchOrdenesEnviadasPendientes("").ejecutaServicio("pendientes")));
        SERVICIOS.put("SearchOrdenesRecibidasPendientes", Exception(() -> new SearchOrdenesRecibidasPendientes("").ejecutaServicio("pendientes")));
        SERVICIOS.put("DevolucionNoAcreditadaT0", Exception(this::devolucionT0));
        SERVICIOS.put("DevolucionNoAcreditadaExtemporaneaT16", Exception(this::devolucionT16));
        SERVICIOS.put("DevolucionAcreditadaT17", Exception(this::devolucionT17));
        SERVICIOS.put("DevolucionAcreditadaExtemporaneaT18", Exception(() -> new DevolucionAcreditadaExtemporaneaT18(16).ejecutarServicio("SearchOrdenesRecibidas.txt")));
        SERVICIOS.put("DevolucionEspecialAcreditadaT23", Exception(() -> new DevolucionEspecialAcreditadaT23(16, "0.01", "STP").ejecutarServicio("SearchOrdenesRecibidas.txt")));
        SERVICIOS.put("DevolucionEspecialAcreditadaExtemporaneaT24", Exception(() -> new DevolucionEspecialAcreditadaExtemporaneaT24(16, "0.01", "STP").ejecutarServicio("SearchOrdenesRecibidas.txt")));
        SERVICIOS.put("CancelarOrden", Exception(this::cancelacionOrdenes));
        SERVICIOS.put("ConfirmarOrdenes", Exception(this::confirmarOrdenes));
        SERVICIOS.put("UrlCepOrdenesEnviadas", Exception(this::generaUrlCepOrdenesEnviadas));
        SERVICIOS.put("UrlCepOrdenesRecibidas", Exception(this::generaUrlCepOrdenesRecibidas));
        SERVICIOS.put("ConsultaConciliacion", Exception(() -> new ConsultaConciliacion("90646", "A").ejecutaServicio(null)));
        SERVICIOS.put("ConsultaConexion", Exception(() -> new ConsultaConexion("90646", "A").ejecutaServicio(null)));
        SERVICIOS.put("PagosVariosTipos", Exception(this::pagosVariosTipos));
    }

    private void pagosVariosTipos() throws IOException {
        new OrdenTerceroAVentanillaT2().ejecutarMultiple();
        new OrdenTerceroATerceroVostroT3().ejecutarMultiple();
        new OrdenTerceroAParticipanteT4().ejecutarMultiple();
        new OrdenParticipanteATerceroT5().ejecutarMultiple();
        new OrdenParticipanteATerceroVostroT6().ejecutarMultiple();
        new OrdenParticipanteAParticipanteT7().ejecutarMultiple();
        new OrdenTerceroATerceroFswT8().ejecutarMultiple();
        new OrdenTerceroATerceroVostroFswT9().ejecutarMultiple();
        new OrdenParticipanteATerceroFswT10().ejecutarMultiple();
        new OrdenParticipanteATerceroVostroFwsT11().ejecutarMultiple();
    }
    private void confirmarOrdenes() throws Exception {
        new SearchOrdenesRecibidas("ConfirmarOrdenes").ejecutaServicio("search");
        new ConfirmarOrden().ejecutarServicio("SearchOrdenesRecibidasConfirmarOrdenes.txt");
    }

    private void cancelacionOrdenes() throws Exception {
        new SearchOrdenesEnviadas("CancelarOrdenes").ejecutaServicio("search");
        new CancelarOrden().ejecutarServicio("SearchOrdenesEnviadasCancelarOrdenes.txt");
    }

    private void generaUrlCepOrdenesRecibidas() throws Exception {
        new SearchOrdenesRecibidas("UrlCepOrdenesR").ejecutaServicio("search");
        new UrlCepOrdenesRecibidas().ejecutaServicio("SearchOrdenesRecibidasUrlCepOrdenesR.txt");
    }

    private void generaUrlCepOrdenesEnviadas() throws Exception {
        new SearchOrdenesEnviadas("UrlCepOrdenesE").ejecutaServicio("search");
        new UrlCepOrdenesEnviadas().ejecutaServicio("SearchOrdenesEnviadasUrlCepOrdenesE.txt");
    }

    private void devolucionT0() throws Exception {
        new SearchOrdenesRecibidas("DevolucionT0").ejecutaServicio("search");
        new DevolucionNoAcreditadaT0(16).ejecutarServicio("SearchOrdenesRecibidasDevolucionT0.txt");
    }

    private void devolucionT16() throws Exception {
        new SearchOrdenesRecibidas("DevolucionT16").ejecutaServicio("search");
        new DevolucionNoAcreditadaT0(16).ejecutarServicio("SearchOrdenesRecibidasDevolucionT16.txt");
    }

    private void devolucionT17() throws Exception {
        new SearchOrdenesRecibidas("DevolucionT17").ejecutaServicio("search");
        new DevolucionNoAcreditadaT0(16).ejecutarServicio("SearchOrdenesRecibidasDevolucionT17.txt");
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



