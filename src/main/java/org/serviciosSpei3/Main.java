package org.serviciosSpei3;

import org.serviciosSpei3.cancelacionOrdenes.CancelarOrden;
import org.serviciosSpei3.confirmaOrden.ConfirmarOrden;
import org.serviciosSpei3.controles.KeycloakService;
import org.serviciosSpei3.devolucionOrdenes.*;
import org.serviciosSpei3.generacionURLCep.ConsultaConexion;
import org.serviciosSpei3.generacionURLCep.UrlCepOrdenesEnviadas;
import org.serviciosSpei3.generacionURLCep.UrlCepOrdenesRecibidas;
import org.serviciosSpei3.registroDeOrdenes.*;
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
        //new SearchOrdenesEnviadasPendientes().ejecutaServicio("pendientes");
        //new SearchOrdenesRecibidas().ejecutaServicio("search");
        //new ConsultaOrdenesRecibidasV2(1).ejecutaServicio("SearchOrdenesRecibidas.txt");
        //new SearchOrdenesRecibidasPendientes().ejecutaServicio("pendientes");

        //new DevolucionNoAcreditadaT0(1).ejecutarServicio("SearchOrdenesRecibidas.txt");
        //new DevolucionNoAcreditadaExtemporaneaT16(16,"0.01").ejecutarServicio("SearchOrdenesRecibidas.txt");
        //new DevolucionAcreditadaT17(16).ejecutarServicio("SearchOrdenesRecibidas.txt");
        //new DevolucionAcreditadaExtemporaneaT18(16).ejecutarServicio("SearchOrdenesRecibidas.txt");
        //new DevolucionEspecialAcreditadaT23(16,"0.01","STP").ejecutarServicio("SearchOrdenesRecibidas.txt");
        //new DevolucionEspecialAcreditadaExtemporaneaT24(16,"0.01","STP").ejecutarServicio("SearchOrdenesRecibidas.txt");

        //new ConfirmarOrden().ejecutarServicio("SearchOrdenesRecibidas.txt");

        //new CancelarOrden().ejecutarServicio("SearchOrdenesEnviadas.txt");
        //new ConsultaConciliacion("90646","A").ejecutaServicio(null);

        //new UrlCepOrdenesEnviadas().ejecutaServicio("OrdenesIdEnviadas.txt");
        //new UrlCepOrdenesRecibidas().ejecutaServicio("ConsultaOrdenesRecibidas.txt");
        new ConsultaConexion("90646","A").ejecutaServicio(null);

    }
}