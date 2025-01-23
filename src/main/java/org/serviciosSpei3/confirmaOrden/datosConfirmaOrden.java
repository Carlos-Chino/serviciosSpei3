package org.serviciosSpei3.confirmaOrden;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class datosConfirmaOrden {
    private String rfcCliente;
    private String nombreCliente;
    private String claveRastreo;
    private String indicadorBeneficiario;
    private String fechaOperacion;
    private Integer institucionOrdenante;
    private String instancia;
    private Integer institucionBeneficiaria;
    private BigDecimal monto;
    private Integer tipoPago;
    private String ordenId;
}

