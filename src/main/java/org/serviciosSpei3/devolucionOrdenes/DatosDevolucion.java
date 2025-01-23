package org.serviciosSpei3.devolucionOrdenes;

import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;

@Getter
@Setter
public class DatosDevolucion {
    private String claveRastreo;
    private Integer causaDevolucion;
    private BigDecimal montoDevolucion;
    private BigDecimal montoIntereses;
    private String indicadorBeneficiario;
    private String fechaOperacion;
    private Integer institucionOrdenante;
    private String instancia;
    private Integer institucionBeneficiaria;
    private BigDecimal monto;
    private Integer tipoPago;
    private String claveRastreoDet;
    private String ordenId;
}

