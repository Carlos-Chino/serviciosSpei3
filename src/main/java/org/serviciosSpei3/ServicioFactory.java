package org.serviciosSpei3;

import org.serviciosSpei3.cancelacionOrdenes.CancelarOrden;
import org.serviciosSpei3.confirmaOrden.ConfirmarOrden;
import org.serviciosSpei3.devolucionOrdenes.*;
import org.serviciosSpei3.generacionURLCep.ConsultaConexion;
import org.serviciosSpei3.generacionURLCep.UrlCepOrdenesEnviadas;
import org.serviciosSpei3.generacionURLCep.UrlCepOrdenesRecibidas;
import org.serviciosSpei3.registroDeOrdenes.*;
import org.serviciosSpei3.serviciosConsulta.*;

public class ServicioFactory {
    private String servicio;
    public ServicioFactory(String servicio) throws Exception {
        this.servicio=servicio;
        ejecutarServicio();
    }


    public void ejecutarServicio() throws Exception {
        switch (this.servicio) {
            case "OrdenTerceroATerceroT1":
                new OrdenTerceroATerceroT1().ejecutarMultiple();
                break;
            case "OrdenTerceroAVentanillaT2":
                new OrdenTerceroAVentanillaT2().ejecutarMultiple();
                break;
            case "OrdenTerceroATerceroVostroT3":
                new OrdenTerceroATerceroVostroT3().ejecutarMultiple();
                break;
            case "OrdenTerceroAParticipanteT4":
                new OrdenTerceroAParticipanteT4().ejecutarMultiple();
                break;
            case "OrdenParticipanteATerceroT5":
                new OrdenParticipanteATerceroT5().ejecutarMultiple();
                break;
            case "OrdenParticipanteATerceroVostroT6":
                new OrdenParticipanteATerceroVostroT6().ejecutarMultiple();
                break;
            case "OrdenParticipanteAParticipanteT7":
                new OrdenParticipanteAParticipanteT7().ejecutarMultiple();
                break;
            case "OrdenTerceroATerceroFswT8":
                new OrdenTerceroATerceroFswT8().ejecutarMultiple();
                break;
            case "OrdenTerceroATerceroVostroFswT9":
                new OrdenTerceroATerceroVostroFswT9().ejecutarMultiple();
                break;
            case "OrdenParticipanteATerceroFswT10":
                new OrdenParticipanteATerceroFswT10().ejecutarMultiple();
                break;
            case "OrdenParticipanteATerceroVostroFwsT11":
                new OrdenParticipanteATerceroVostroFwsT11().ejecutarMultiple();
                break;
            case "CobroPresencialUnaOcasionT19":
                new CobroPresencialUnaOcasionT19().ejecutarMultiple();
                break;
            case "CobroNoPresencialUnaOcasion20":
                new CobroNoPresencialUnaOcasion20().ejecutarMultiple();
                break;
            case "CobroNoPresencialRecurrente21":
                new CobroNoPresencialRecurrente21().ejecutarMultiple();
                break;
            case "CobroNoPresencialATercero22":
                new CobroNoPresencialATercero22().ejecutarMultiple();
                break;
            case "ConsultaOrdenesEnviadas":
                new ConsultaOrdenesEnviadas().ejecutaServicio("OrdenesIdEnviadas.txt");
                break;
            case "ConsultaOrdenesEnviadasV2":
                new ConsultaOrdenesEnviadasV2(1).ejecutaServicio("OrdenesIdEnviadas.txt");
                break;
            case "SearchOrdenesEnviadas":
                new SearchOrdenesEnviadas().ejecutaServicio("search");
                break;
            case "SearchOrdenesRecibidas":
                new SearchOrdenesRecibidas().ejecutaServicio("search");
                break;
            case "ConsultaOrdenesRecibidasV2":
                new ConsultaOrdenesRecibidasV2(1).ejecutaServicio("SearchOrdenesRecibidas.txt");
                break;
            case "SearchOrdenesEnviadasPendientes":
                new SearchOrdenesEnviadasPendientes().ejecutaServicio("pendientes");
                break;
            case "SearchOrdenesRecibidasPendientes":
                new SearchOrdenesRecibidasPendientes().ejecutaServicio("pendientes");
                break;
            case "DevolucionNoAcreditadaT0":
                new DevolucionNoAcreditadaT0(16).ejecutarServicio("SearchOrdenesRecibidas.txt");
                break;
            case "DevolucionNoAcreditadaExtemporaneaT16":
                new DevolucionNoAcreditadaExtemporaneaT16(16,"0.01").ejecutarServicio("SearchOrdenesRecibidas.txt");
                break;
            case "DevolucionAcreditadaT17":
                new DevolucionAcreditadaT17(16).ejecutarServicio("SearchOrdenesRecibidas.txt");
                break;
            case "DevolucionAcreditadaExtemporaneaT18":
                new DevolucionAcreditadaExtemporaneaT18(16).ejecutarServicio("SearchOrdenesRecibidas.txt");
                break;
            case "DevolucionEspecialAcreditadaT23":
                new DevolucionEspecialAcreditadaT23(16,"0.01","STP").ejecutarServicio("SearchOrdenesRecibidas.txt");
                break;
            case "CancelarOrden":
                new CancelarOrden().ejecutarServicio("SearchOrdenesEnviadas.txt");
                break;
            case "ConfirmarOrden":
                new ConfirmarOrden().ejecutarServicio("SearchOrdenesRecibidas.txt");
                break;
            case "UrlCepOrdenesEnviadas":
                new UrlCepOrdenesEnviadas().ejecutaServicio("OrdenesIdEnviadas.txt");
                break;
            case "UrlCepOrdenesRecibidas":
                new UrlCepOrdenesRecibidas().ejecutaServicio("ConsultaOrdenesRecibidas.txt");
                break;
            case "ConsultaConciliacion":
                new ConsultaConciliacion("90646","A").ejecutaServicio(null);
                break;
            case "ConsultaConexion":
                new ConsultaConexion("90646","A").ejecutaServicio(null);
                break;
            default:
                throw new RuntimeException("Servicio no reconocido: " + servicio);
        }






    }
}

