package org.serviciosSpei3;

import org.serviciosSpei3.cancelacionOrdenes.CancelarOrden;
import org.serviciosSpei3.confirmaOrden.ConfirmarOrden;
import org.serviciosSpei3.controles.KeycloakService;
import org.serviciosSpei3.devolucionOrdenes.DevolucionAcreditadaExtemporaneaT18;
import org.serviciosSpei3.generacionURLCep.ConsultaConexion;
import org.serviciosSpei3.generacionURLCep.UrlCepOrdenesEnviadas;
import org.serviciosSpei3.generacionURLCep.UrlCepOrdenesRecibidas;
import org.serviciosSpei3.registroDeOrdenes.OrdenTerceroATerceroT1;
import org.serviciosSpei3.serviciosConsulta.*;

public class Main {
    public static void main(String[] args) throws Exception {
        //new KeycloakService().login();

        //new OrdenTerceroATerceroT1().ejecutarMultiple();
        //new OrdenParticipanteAParticipanteT7().ejecutarMultiple();
        //new CobroPresencialUnaOcasionT19().ejecutarMultiple();
        //new CobroNoPresencialUnaOcasion20().ejecutarMultiple();
        //new CobroNoPresencialRecurrente21().ejecutarMultiple();
        //new CobroNoPresencialATercero22().ejecutarMultiple();

        //new ConsultaOrdenesEnviadas().ejecutaServicio("OrdenesIdEnviadas.txt");
        //new ConsultaOrdenesEnviadasV2(1).ejecutaServicio("OrdenesIdEnviadas.txt");

        //new SearchOrdenesEnviadas().ejecutaServicio("search");
        new SearchOrdenesEnviadasPendientes().ejecutaServicio("pendientes");
        //new SearchOrdenesRecibidas().ejecutaServicio(new ParametrizedRequest(parametros(), params()));
        //new ConsultaOrdenesRecibidasV2(1).ejecutaServicio("SearchOrdenesRecibidas.txt");
        //new SearchOrdenesRecibidasPendientes().ejecutaServicio(new ParametrizedRequest(parametrosSearchPendientes(), null));

        //new DevolucionNoAcreditadaT0(1,"ConsultaOrdenesRecibidas.txt").ejecutarServicio();
        //new DevolucionNoAcreditadaExtemporaneaT16(16,"SearchOrdenesRecibidas.txt","0.01").ejecutarServicio();
        //new DevolucionAcreditadaT17(16,"SearchOrdenesRecibidas.txt").ejecutarServicio();
        //new DevolucionAcreditadaExtemporaneaT18(16,"SearchOrdenesRecibidas.txt").ejecutarServicio();
        //new DevolucionEspecialAcreditadaT23(16,"0.01","SearchOrdenesRecibidas.txt","STP").ejecutarServicio();
        //new DevolucionEspecialAcreditadaExtemporaneaT24(16,"0.01","SearchOrdenesRecibidas.txt","STP").ejecutarServicio();

        //new ConfirmarOrden().ejecutarServicio("SearchOrdenesRecibidas.txt");
        //new CancelarOrden().ejecutarServicio("cancelacion","SearchOrdenesEnviadas.txt");
        //new ConsultaConciliacion("90646","B").ejecutaServicio(null);

        //new UrlCepOrdenesEnviadas().ejecutaServicio("OrdenesIdEnviadas.txt");
        //new UrlCepOrdenesRecibidas().ejecutaServicio("ConsultaOrdenesRecibidas.txt");
        //new ConsultaConexion("90646","B").ejecutaServicio(null);

    }
}