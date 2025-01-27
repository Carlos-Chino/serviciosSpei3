package org.serviciosSpei3;

import org.serviciosSpei3.controles.KeycloakService;

public class Main {
    public static void main(String[] args) throws Exception {
        //new KeycloakService().login();
        new ServicioFactory("OrdenTerceroATerceroT1");
    }
}


//Tipos de Pago
//OrdenTerceroATerceroT1
//OrdenTerceroAVentanillaT2
//OrdenTerceroATerceroVostroT3
//OrdenTerceroAParticipanteT4
//OrdenParticipanteATerceroT5
//OrdenParticipanteATerceroVostroT6
//OrdenParticipanteAParticipanteT7
//OrdenTerceroATerceroFswT8
//OrdenTerceroATerceroVostroFswT9
//OrdenParticipanteATerceroFswT10
//OrdenParticipanteATerceroVostroFwsT11