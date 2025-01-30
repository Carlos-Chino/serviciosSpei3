package org.serviciosSpei3;

import org.serviciosSpei3.controles.KeycloakService;

public class Main {
    public static void main(String[] args) throws Exception {
        //new KeycloakService().login();
        new ServicioFactory("OrdenTerceroAVentanillaT2");
    }
}

/*Copia y pega en el main el servicio que se necesita ejecutar

****Envío de ordenes****
OrdenTerceroATerceroT1
OrdenTerceroAVentanillaT2
OrdenTerceroATerceroVostroT3
OrdenTerceroAParticipanteT4
OrdenParticipanteATerceroT5
OrdenParticipanteATerceroVostroT6
OrdenParticipanteAParticipanteT7
OrdenTerceroATerceroFswT8
OrdenTerceroATerceroVostroFswT9
OrdenParticipanteATerceroFswT10
OrdenParticipanteATerceroVostroFwsT11
CobroPresencialUnaOcasionT19
CobroNoPresencialUnaOcasion20
CobroNoPresencialRecurrente21
CobroNoPresencialATercero22

****Servicios de Operacion****
ConfirmarOrdenes
CancelarOrden
UrlCepOrdenesEnviadas
UrlCepOrdenesRecibidas
PagosVariosTipos

****Devoluciones****
DevolucionNoAcreditadaT0
DevolucionNoAcreditadaExtemporaneaT16
DevolucionAcreditadaT17
DevolucionAcreditadaExtemporaneaT18
DevolucionEspecialAcreditadaT23
DevolucionEspecialAcreditadaExtemporaneaT24

****Otros Servicios individuales****
ConsultaOrdenesEnviadas
ConsultaOrdenesEnviadasV2
SearchOrdenesEnviadas
SearchOrdenesRecibidas
ConsultaOrdenesRecibidasV2
SearchOrdenesEnviadasPendientes
SearchOrdenesRecibidasPendientes
ConsultaConciliacion
ConsultaConexion

*/
