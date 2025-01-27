package org.serviciosSpei3.controles;

import org.serviciosSpei3.cancelacionOrdenes.datosCancelarOrden;
import org.serviciosSpei3.confirmaOrden.datosConfirmaOrden;
import org.serviciosSpei3.devolucionOrdenes.DatosDevolucion;
import org.serviciosSpei3.registroDeOrdenes.DatosOrdenes;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.*;
import java.security.cert.CertificateException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Base64;
import java.util.Properties;

public class CryptoHandler {
    private static String firma;
    private static String fechaOperacion = fechaOperacion();

    public String devolucionNoAcreditadaT0(DatosDevolucion devolucion) {
        String cadenaOriginal = cadenaDevolucionNoAcreditadaT0(devolucion);
        firma = signMessage(cadenaOriginal);
        return firma;
    }

    public String devolucionNoAcreditadaExtemporaneaT16(DatosDevolucion devolucion) {
        String cadenaOriginal = cadenaDevolucionNoAcreditadaExtemporaneaT16(devolucion);
        firma = signMessage(cadenaOriginal);
        return firma;
    }

    public String devolucionAcreditadaT17(DatosDevolucion devolucion) {
        String cadenaOriginal = cadenaDevolucionAcreditadaT17(devolucion);
        firma = signMessage(cadenaOriginal);
        return firma;
    }
    public String devolucionAcreditadaExtemporaneaT18(DatosDevolucion devolucion) {
        String cadenaOriginal = cadenaDevolucionAcreditadaExtemporaneaT18(devolucion);
        firma = signMessage(cadenaOriginal);
        return firma;
    }

    public String devolucionAEspecialcreditadaT23(DatosDevolucion devolucion) {
        String cadenaOriginal = cadenaDevolucionEspecialAcreditadaT23(devolucion);
        firma = signMessage(cadenaOriginal);
        return firma;
    }

    public String devolucionEspecialAcreditadaExtemporaneaT24(DatosDevolucion devolucion) {
        String cadenaOriginal = cadenaDevolucionEspecialAcreditadaExtemporaneaT24(devolucion);
        firma = signMessage(cadenaOriginal);
        return firma;
    }
    public String ordenTerceroATerceroT1(DatosOrdenes ordenes) {
        String cadenaOriginal = cadenaTerceroATerceroT1(ordenes);
        firma = signMessage(cadenaOriginal);
        return firma;
    }

    public String ordenTerceroAVentanillaT2(DatosOrdenes ordenes) {
        String cadenaOriginal = cadenaTerceroAVentanillaT2(ordenes);
        firma = signMessage(cadenaOriginal);
        return firma;
    }
    public String ordenTerceroATerceroVostroT3(DatosOrdenes ordenes) {
        String cadenaOriginal = cadenaTerceroATerceroVostroT3(ordenes);
        firma = signMessage(cadenaOriginal);
        return firma;
    }
    public String ordenTerceroAParticipanteT4(DatosOrdenes ordenes) {
        String cadenaOriginal = cadenaTerceroAParticipanteT4(ordenes);
        firma = signMessage(cadenaOriginal);
        return firma;
    }
    public String ordenParticipanteATerceroT5(DatosOrdenes ordenes) {
        String cadenaOriginal = cadenaParticipanteATerceroT5(ordenes);
        firma = signMessage(cadenaOriginal);
        return firma;
    }
    public String ordenParticipanteATerceroVostroT6(DatosOrdenes ordenes) {
        String cadenaOriginal = cadenaParticipanteATerceroVostroT6(ordenes);
        firma = signMessage(cadenaOriginal);
        return firma;
    }
    public String ordenParticipanteAParticipanteT7(DatosOrdenes ordenes) {
        String cadenaOriginal = cadenaParticipanteAParticipanteT7(ordenes);
        firma = signMessage(cadenaOriginal);
        return firma;
    }
    public String ordenTerceroATerceroFswT8(DatosOrdenes ordenes) {
        String cadenaOriginal = cadenaTerceroATerceroFswT8(ordenes);
        firma = signMessage(cadenaOriginal);
        return firma;
    }
    public String ordenTerceroATerceroVostroFswT9(DatosOrdenes ordenes) {
        String cadenaOriginal = cadenaTerceroATerceroVostroFswT9(ordenes);
        firma = signMessage(cadenaOriginal);
        return firma;
    }
    public String ordenParticipanteATerceroFswT10(DatosOrdenes ordenes) {
        String cadenaOriginal = cadenaParticipanteATerceroFswT10(ordenes);
        firma = signMessage(cadenaOriginal);
        return firma;
    }
    public String ordenParticipanteATerceroVostroFwsT11(DatosOrdenes ordenes) {
        String cadenaOriginal = cadenaParticipanteATerceroVostroFwsT11(ordenes);
        firma = signMessage(cadenaOriginal);
        return firma;
    }

    public String ordenCobroPresencialUnaOcasionT19(DatosOrdenes ordenes) {
        String cadenaOriginal = cadenaCobroPresencialUnaOcasionT19(ordenes);
        firma = signMessage(cadenaOriginal);
        return firma;
    }
    public String ordenCobroNoPresencialUnaOcasionT20(DatosOrdenes ordenes) {
        String cadenaOriginal = cadenaCobroNoPresencialUnaOcasionT20(ordenes);
        firma = signMessage(cadenaOriginal);
        return firma;
    }
    public String ordenCobroNoPresencialRecurrenteT21(DatosOrdenes ordenes) {
        String cadenaOriginal = cadenaCobroNoPresencialRecurrenteT21(ordenes);
        firma = signMessage(cadenaOriginal);
        return firma;
    }
    public String ordenCobroNoPresencialATerceroT22(DatosOrdenes ordenes) {
        String cadenaOriginal = cadenaCobroNoPresencialATerceroT22(ordenes);
        firma = signMessage(cadenaOriginal);
        return firma;
    }

    public String confirmaOrden(datosConfirmaOrden confirmaOrden) {
        String cadenaOriginal = cadenaConfirmarOrden(confirmaOrden);
        firma = signMessage(cadenaOriginal);
        return firma;
    }

    public String cancelarOrden(datosCancelarOrden cancelarOrden) {
        String cadenaOriginal = cadenaCancelarOrden(cancelarOrden);
        firma = signMessage(cadenaOriginal);
        return firma;
    }

    public static String cadenaTerceroATerceroT1(DatosOrdenes orden) {
        StringBuilder cadenaOriginal = new StringBuilder();
        cadenaOriginal.append("||");
        cadenaOriginal.append(fechaOperacion).append("|");
        cadenaOriginal.append(orden.getInstitucionOrdenante() == null ? "" : orden.getInstitucionOrdenante()).append("|");
        cadenaOriginal.append(orden.getInstancia() == null ? "" : orden.getInstancia()).append("|");
        cadenaOriginal.append(orden.getInstitucionBeneficiaria() == null ? "" : orden.getInstitucionBeneficiaria()).append("|");
        cadenaOriginal.append(orden.getClaveRastreo() == null ? "" : orden.getClaveRastreo()).append("|");
        cadenaOriginal.append(orden.getMonto() == null ? "" : orden.getMonto()).append("|");
        cadenaOriginal.append(orden.getTipoPago() == null ? "" : orden.getTipoPago()).append("|");
        cadenaOriginal.append(orden.getNombreOrdenante() == null ? "" : orden.getNombreOrdenante()).append("|");
        cadenaOriginal.append(orden.getTipoCuentaOrdenante() == null ? "" : orden.getTipoCuentaOrdenante()).append("|");
        cadenaOriginal.append(orden.getCuentaOrdenante() == null ? "" : orden.getCuentaOrdenante()).append("|");
        cadenaOriginal.append(orden.getRfcCurpOrdenante() == null ? "" : orden.getRfcCurpOrdenante()).append("|");
        cadenaOriginal.append(orden.getNombreBeneficiario() == null ? "" : orden.getNombreBeneficiario()).append("|");
        cadenaOriginal.append(orden.getTipoCuentaBeneficiario() == null ? "" : orden.getTipoCuentaBeneficiario()).append("|");
        cadenaOriginal.append(orden.getCuentaBeneficiario() == null ? "" : orden.getCuentaBeneficiario()).append("|");
        cadenaOriginal.append(orden.getRfcCurpBeneficiario() == null ? "" : orden.getRfcCurpBeneficiario()).append("|");
        cadenaOriginal.append(orden.getConceptoPago() == null ? "" : orden.getConceptoPago()).append("|");
        cadenaOriginal.append(orden.getIva() == null ? "" : orden.getIva()).append("|");
        cadenaOriginal.append(orden.getReferenciaNumerica() == null ? "" : orden.getReferenciaNumerica()).append("|");
        cadenaOriginal.append(orden.getReferenciaCobranza() == null ? "" : orden.getReferenciaCobranza());
        cadenaOriginal.append("||");
        System.out.println("Cadena original: " + cadenaOriginal);
        return cadenaOriginal.toString();
    }
    public static String cadenaTerceroAVentanillaT2(DatosOrdenes orden) {
        StringBuilder cadenaOriginal = new StringBuilder();
        cadenaOriginal.append("||");
        cadenaOriginal.append(fechaOperacion).append("|");
        cadenaOriginal.append(orden.getInstitucionOrdenante() == null ? "" : orden.getInstitucionOrdenante()).append("|");
        cadenaOriginal.append(orden.getInstancia() == null ? "" : orden.getInstancia()).append("|");
        cadenaOriginal.append(orden.getInstitucionBeneficiaria() == null ? "" : orden.getInstitucionBeneficiaria()).append("|");
        cadenaOriginal.append(orden.getClaveRastreo() == null ? "" : orden.getClaveRastreo()).append("|");
        cadenaOriginal.append(orden.getMonto() == null ? "" : orden.getMonto()).append("|");
        cadenaOriginal.append(orden.getTipoPago() == null ? "" : orden.getTipoPago()).append("|");
        cadenaOriginal.append(orden.getNombreOrdenante() == null ? "" : orden.getNombreOrdenante()).append("|");
        cadenaOriginal.append(orden.getTipoCuentaOrdenante() == null ? "" : orden.getTipoCuentaOrdenante()).append("|");
        cadenaOriginal.append(orden.getCuentaOrdenante() == null ? "" : orden.getCuentaOrdenante()).append("|");
        cadenaOriginal.append(orden.getRfcCurpOrdenante() == null ? "" : orden.getRfcCurpOrdenante()).append("|");
        cadenaOriginal.append(orden.getNombreBeneficiario() == null ? "" : orden.getNombreBeneficiario()).append("|");
        cadenaOriginal.append(orden.getConceptoPago() == null ? "" : orden.getConceptoPago()).append("|");
        cadenaOriginal.append(orden.getClavePago() == null ? "" : orden.getClavePago()).append("|");
        cadenaOriginal.append(orden.getIva() == null ? "" : orden.getIva());
        cadenaOriginal.append("||");
        System.out.println("Cadena original: " + cadenaOriginal);
        return cadenaOriginal.toString();
    }

    public static String cadenaTerceroATerceroVostroT3(DatosOrdenes orden) {
        StringBuilder cadenaOriginal = new StringBuilder();
        cadenaOriginal.append("||");
        cadenaOriginal.append(fechaOperacion).append("|");
        cadenaOriginal.append(orden.getInstitucionOrdenante() == null ? "" : orden.getInstitucionOrdenante()).append("|");
        cadenaOriginal.append(orden.getInstancia() == null ? "" : orden.getInstancia()).append("|");
        cadenaOriginal.append(orden.getInstitucionBeneficiaria() == null ? "" : orden.getInstitucionBeneficiaria()).append("|");
        cadenaOriginal.append(orden.getClaveRastreo() == null ? "" : orden.getClaveRastreo()).append("|");
        cadenaOriginal.append(orden.getMonto() == null ? "" : orden.getMonto()).append("|");
        cadenaOriginal.append(orden.getTipoPago() == null ? "" : orden.getTipoPago()).append("|");
        cadenaOriginal.append(orden.getNombreOrdenante() == null ? "" : orden.getNombreOrdenante()).append("|");
        cadenaOriginal.append(orden.getTipoCuentaOrdenante() == null ? "" : orden.getTipoCuentaOrdenante()).append("|");
        cadenaOriginal.append(orden.getCuentaOrdenante() == null ? "" : orden.getCuentaOrdenante()).append("|");
        cadenaOriginal.append(orden.getRfcCurpOrdenante() == null ? "" : orden.getRfcCurpOrdenante()).append("|");
        cadenaOriginal.append(orden.getNombreBeneficiario() == null ? "" : orden.getNombreBeneficiario()).append("|");
        cadenaOriginal.append(orden.getTipoCuentaBeneficiario() == null ? "" : orden.getTipoCuentaBeneficiario()).append("|");
        cadenaOriginal.append(orden.getCuentaBeneficiario() == null ? "" : orden.getCuentaBeneficiario()).append("|");
        cadenaOriginal.append(orden.getNombreBeneficiario2() == null ? "" : orden.getNombreBeneficiario2()).append("|");
        cadenaOriginal.append(orden.getRfcCurpBeneficiario2() == null ? "" : orden.getRfcCurpBeneficiario2()).append("|");
        cadenaOriginal.append(orden.getTipoCuentaBeneficiario2() == null ? "" : orden.getTipoCuentaBeneficiario2()).append("|");
        cadenaOriginal.append(orden.getCuentaBeneficiario2() == null ? "" : orden.getCuentaBeneficiario2()).append("|");
        cadenaOriginal.append(orden.getConceptoPago() == null ? "" : orden.getConceptoPago()).append("|");
        cadenaOriginal.append(orden.getConceptoPago2() == null ? "" : orden.getConceptoPago2()).append("|");
        cadenaOriginal.append(orden.getIva() == null ? "" : orden.getIva()).append("|");
        cadenaOriginal.append(orden.getReferenciaNumerica() == null ? "" : orden.getReferenciaNumerica());
        cadenaOriginal.append("||");
        System.out.println("Cadena original: " + cadenaOriginal);
        return cadenaOriginal.toString();
    }

    public static String cadenaTerceroAParticipanteT4(DatosOrdenes orden) {
        StringBuilder cadenaOriginal = new StringBuilder();
        cadenaOriginal.append("||");
        cadenaOriginal.append(fechaOperacion).append("|");
        cadenaOriginal.append(orden.getInstitucionOrdenante() == null ? "" : orden.getInstitucionOrdenante()).append("|");
        cadenaOriginal.append(orden.getInstancia() == null ? "" : orden.getInstancia()).append("|");
        cadenaOriginal.append(orden.getInstitucionBeneficiaria() == null ? "" : orden.getInstitucionBeneficiaria()).append("|");
        cadenaOriginal.append(orden.getClaveRastreo() == null ? "" : orden.getClaveRastreo()).append("|");
        cadenaOriginal.append(orden.getMonto() == null ? "" : orden.getMonto()).append("|");
        cadenaOriginal.append(orden.getTipoPago() == null ? "" : orden.getTipoPago()).append("|");
        cadenaOriginal.append(orden.getNombreOrdenante() == null ? "" : orden.getNombreOrdenante()).append("|");
        cadenaOriginal.append(orden.getTipoCuentaOrdenante() == null ? "" : orden.getTipoCuentaOrdenante()).append("|");
        cadenaOriginal.append(orden.getCuentaOrdenante() == null ? "" : orden.getCuentaOrdenante()).append("|");
        cadenaOriginal.append(orden.getRfcCurpOrdenante() == null ? "" : orden.getRfcCurpOrdenante()).append("|");
        cadenaOriginal.append(orden.getTipoOperacion() == null ? "" : orden.getTipoOperacion()).append("|");
        cadenaOriginal.append(orden.getConceptoPago() == null ? "" : orden.getConceptoPago()).append("|");
        cadenaOriginal.append(orden.getIva() == null ? "" : orden.getIva()).append("|");
        cadenaOriginal.append(orden.getReferenciaNumerica() == null ? "" : orden.getReferenciaNumerica());
        cadenaOriginal.append("||");
        System.out.println("Cadena original: " + cadenaOriginal);
        return cadenaOriginal.toString();
    }

    public static String cadenaParticipanteATerceroT5(DatosOrdenes orden) {
        StringBuilder cadenaOriginal = new StringBuilder();
        cadenaOriginal.append("||");
        cadenaOriginal.append(fechaOperacion).append("|");
        cadenaOriginal.append(orden.getInstitucionOrdenante() == null ? "" : orden.getInstitucionOrdenante()).append("|");
        cadenaOriginal.append(orden.getInstancia() == null ? "" : orden.getInstancia()).append("|");
        cadenaOriginal.append(orden.getInstitucionBeneficiaria() == null ? "" : orden.getInstitucionBeneficiaria()).append("|");
        cadenaOriginal.append(orden.getClaveRastreo() == null ? "" : orden.getClaveRastreo()).append("|");
        cadenaOriginal.append(orden.getMonto() == null ? "" : orden.getMonto()).append("|");
        cadenaOriginal.append(orden.getTipoPago() == null ? "" : orden.getTipoPago()).append("|");
        cadenaOriginal.append(orden.getNombreBeneficiario() == null ? "" : orden.getNombreBeneficiario()).append("|");
        cadenaOriginal.append(orden.getTipoCuentaBeneficiario() == null ? "" : orden.getTipoCuentaBeneficiario()).append("|");
        cadenaOriginal.append(orden.getCuentaBeneficiario() == null ? "" : orden.getCuentaBeneficiario()).append("|");
        cadenaOriginal.append(orden.getRfcCurpBeneficiario() == null ? "" : orden.getRfcCurpBeneficiario()).append("|");
        cadenaOriginal.append(orden.getConceptoPago() == null ? "" : orden.getConceptoPago()).append("|");
        cadenaOriginal.append(orden.getIva() == null ? "" : orden.getIva()).append("|");
        cadenaOriginal.append(orden.getReferenciaNumerica() == null ? "" : orden.getReferenciaNumerica());
        cadenaOriginal.append("||");
        System.out.println("Cadena original: " + cadenaOriginal);
        return cadenaOriginal.toString();
    }

    public static String cadenaParticipanteATerceroVostroT6(DatosOrdenes orden) {
        StringBuilder cadenaOriginal = new StringBuilder();
        cadenaOriginal.append("||");
        cadenaOriginal.append(fechaOperacion).append("|");
        cadenaOriginal.append(orden.getInstitucionOrdenante() == null ? "" : orden.getInstitucionOrdenante()).append("|");
        cadenaOriginal.append(orden.getInstancia() == null ? "" : orden.getInstancia()).append("|");
        cadenaOriginal.append(orden.getInstitucionBeneficiaria() == null ? "" : orden.getInstitucionBeneficiaria()).append("|");
        cadenaOriginal.append(orden.getClaveRastreo() == null ? "" : orden.getClaveRastreo()).append("|");
        cadenaOriginal.append(orden.getMonto() == null ? "" : orden.getMonto()).append("|");
        cadenaOriginal.append(orden.getTipoPago() == null ? "" : orden.getTipoPago()).append("|");
        cadenaOriginal.append(orden.getNombreBeneficiario() == null ? "" : orden.getNombreBeneficiario()).append("|");
        cadenaOriginal.append(orden.getTipoCuentaBeneficiario() == null ? "" : orden.getTipoCuentaBeneficiario()).append("|");
        cadenaOriginal.append(orden.getCuentaBeneficiario() == null ? "" : orden.getCuentaBeneficiario()).append("|");
        cadenaOriginal.append(orden.getNombreBeneficiario2() == null ? "" : orden.getNombreBeneficiario2()).append("|");
        cadenaOriginal.append(orden.getRfcCurpBeneficiario2() == null ? "" : orden.getRfcCurpBeneficiario2()).append("|");
        cadenaOriginal.append(orden.getTipoCuentaBeneficiario2() == null ? "" : orden.getTipoCuentaBeneficiario2()).append("|");
        cadenaOriginal.append(orden.getCuentaBeneficiario2() == null ? "" : orden.getCuentaBeneficiario2()).append("|");
        cadenaOriginal.append(orden.getConceptoPago() == null ? "" : orden.getConceptoPago()).append("|");
        cadenaOriginal.append(orden.getConceptoPago2() == null ? "" : orden.getConceptoPago2()).append("|");
        cadenaOriginal.append(orden.getIva() == null ? "" : orden.getIva()).append("|");
        cadenaOriginal.append(orden.getReferenciaNumerica() == null ? "" : orden.getReferenciaNumerica());
        cadenaOriginal.append("||");
        System.out.println("Cadena original: " + cadenaOriginal);
        return cadenaOriginal.toString();
    }

    public static String cadenaParticipanteAParticipanteT7(DatosOrdenes orden) {
        StringBuilder cadenaOriginal = new StringBuilder();
        cadenaOriginal.append("||");
        cadenaOriginal.append(fechaOperacion).append("|");
        cadenaOriginal.append(orden.getInstitucionOrdenante() == null ? "" : orden.getInstitucionOrdenante()).append("|");
        cadenaOriginal.append(orden.getInstancia() == null ? "" : orden.getInstancia()).append("|");
        cadenaOriginal.append(orden.getInstitucionBeneficiaria() == null ? "" : orden.getInstitucionBeneficiaria()).append("|");
        cadenaOriginal.append(orden.getClaveRastreo() == null ? "" : orden.getClaveRastreo()).append("|");
        cadenaOriginal.append(orden.getMonto() == null ? "" : orden.getMonto()).append("|");
        cadenaOriginal.append(orden.getTipoPago() == null ? "" : orden.getTipoPago()).append("|");
        cadenaOriginal.append(orden.getTipoOperacion() == null ? "" : orden.getTipoOperacion()).append("|");
        cadenaOriginal.append(orden.getConceptoPago() == null ? "" : orden.getConceptoPago()).append("|");
        cadenaOriginal.append(orden.getIva() == null ? "" : orden.getIva()).append("|");
        cadenaOriginal.append(orden.getReferenciaNumerica() == null ? "" : orden.getReferenciaNumerica());
        cadenaOriginal.append("||");
        System.out.println("Cadena original: " + cadenaOriginal);
        return cadenaOriginal.toString();
    }
    public static String cadenaTerceroATerceroFswT8(DatosOrdenes orden) {
        StringBuilder cadenaOriginal = new StringBuilder();
        cadenaOriginal.append("||");
        cadenaOriginal.append(fechaOperacion).append("|");
        cadenaOriginal.append(orden.getInstitucionOrdenante() == null ? "" : orden.getInstitucionOrdenante()).append("|");
        cadenaOriginal.append(orden.getInstancia() == null ? "" : orden.getInstancia()).append("|");
        cadenaOriginal.append(orden.getInstitucionBeneficiaria() == null ? "" : orden.getInstitucionBeneficiaria()).append("|");
        cadenaOriginal.append(orden.getClaveRastreo() == null ? "" : orden.getClaveRastreo()).append("|");
        cadenaOriginal.append(orden.getMonto() == null ? "" : orden.getMonto()).append("|");
        cadenaOriginal.append(orden.getTipoPago() == null ? "" : orden.getTipoPago()).append("|");
        cadenaOriginal.append(orden.getNombreOrdenante() == null ? "" : orden.getNombreOrdenante()).append("|");
        cadenaOriginal.append(orden.getTipoCuentaOrdenante() == null ? "" : orden.getTipoCuentaOrdenante()).append("|");
        cadenaOriginal.append(orden.getCuentaOrdenante() == null ? "" : orden.getCuentaOrdenante()).append("|");
        cadenaOriginal.append(orden.getRfcCurpOrdenante() == null ? "" : orden.getRfcCurpOrdenante()).append("|");
        cadenaOriginal.append(orden.getNombreBeneficiario() == null ? "" : orden.getNombreBeneficiario()).append("|");
        cadenaOriginal.append(orden.getTipoCuentaBeneficiario() == null ? "" : orden.getTipoCuentaBeneficiario()).append("|");
        cadenaOriginal.append(orden.getCuentaBeneficiario() == null ? "" : orden.getCuentaBeneficiario()).append("|");
        cadenaOriginal.append(orden.getRfcCurpBeneficiario() == null ? "" : orden.getRfcCurpBeneficiario()).append("|");
        cadenaOriginal.append(orden.getConceptoPago() == null ? "" : orden.getConceptoPago()).append("|");
        cadenaOriginal.append(orden.getIva() == null ? "" : orden.getIva()).append("|");
        cadenaOriginal.append(orden.getReferenciaNumerica() == null ? "" : orden.getReferenciaNumerica()).append("|");
        cadenaOriginal.append(orden.getReferenciaCobranza() == null ? "" : orden.getReferenciaCobranza());
        cadenaOriginal.append("||");
        System.out.println("Cadena original: " + cadenaOriginal);
        return cadenaOriginal.toString();
    }

    public static String cadenaTerceroATerceroVostroFswT9(DatosOrdenes orden) {
        StringBuilder cadenaOriginal = new StringBuilder();
        cadenaOriginal.append("||");
        cadenaOriginal.append(fechaOperacion).append("|");
        cadenaOriginal.append(orden.getInstitucionOrdenante() == null ? "" : orden.getInstitucionOrdenante()).append("|");
        cadenaOriginal.append(orden.getInstancia() == null ? "" : orden.getInstancia()).append("|");
        cadenaOriginal.append(orden.getInstitucionBeneficiaria() == null ? "" : orden.getInstitucionBeneficiaria()).append("|");
        cadenaOriginal.append(orden.getClaveRastreo() == null ? "" : orden.getClaveRastreo()).append("|");
        cadenaOriginal.append(orden.getMonto() == null ? "" : orden.getMonto()).append("|");
        cadenaOriginal.append(orden.getTipoPago() == null ? "" : orden.getTipoPago()).append("|");
        cadenaOriginal.append(orden.getNombreOrdenante() == null ? "" : orden.getNombreOrdenante()).append("|");
        cadenaOriginal.append(orden.getTipoCuentaOrdenante() == null ? "" : orden.getTipoCuentaOrdenante()).append("|");
        cadenaOriginal.append(orden.getCuentaOrdenante() == null ? "" : orden.getCuentaOrdenante()).append("|");
        cadenaOriginal.append(orden.getRfcCurpOrdenante() == null ? "" : orden.getRfcCurpOrdenante()).append("|");
        cadenaOriginal.append(orden.getNombreBeneficiario() == null ? "" : orden.getNombreBeneficiario()).append("|");
        cadenaOriginal.append(orden.getTipoCuentaBeneficiario() == null ? "" : orden.getTipoCuentaBeneficiario()).append("|");
        cadenaOriginal.append(orden.getCuentaBeneficiario() == null ? "" : orden.getCuentaBeneficiario()).append("|");
        cadenaOriginal.append(orden.getNombreBeneficiario2() == null ? "" : orden.getNombreBeneficiario2()).append("|");
        cadenaOriginal.append(orden.getRfcCurpBeneficiario2() == null ? "" : orden.getRfcCurpBeneficiario2()).append("|");
        cadenaOriginal.append(orden.getTipoCuentaBeneficiario2() == null ? "" : orden.getTipoCuentaBeneficiario2()).append("|");
        cadenaOriginal.append(orden.getCuentaBeneficiario2() == null ? "" : orden.getCuentaBeneficiario2()).append("|");
        cadenaOriginal.append(orden.getConceptoPago() == null ? "" : orden.getConceptoPago()).append("|");
        cadenaOriginal.append(orden.getConceptoPago2() == null ? "" : orden.getConceptoPago2()).append("|");
        cadenaOriginal.append(orden.getIva() == null ? "" : orden.getIva()).append("|");
        cadenaOriginal.append(orden.getReferenciaNumerica() == null ? "" : orden.getReferenciaNumerica());
        cadenaOriginal.append("||");
        System.out.println("Cadena original: " + cadenaOriginal);
        return cadenaOriginal.toString();
    }

    public static String cadenaParticipanteATerceroFswT10(DatosOrdenes orden) {
        StringBuilder cadenaOriginal = new StringBuilder();
        cadenaOriginal.append("||");
        cadenaOriginal.append(fechaOperacion).append("|");
        cadenaOriginal.append(orden.getInstitucionOrdenante() == null ? "" : orden.getInstitucionOrdenante()).append("|");
        cadenaOriginal.append(orden.getInstancia() == null ? "" : orden.getInstancia()).append("|");
        cadenaOriginal.append(orden.getInstitucionBeneficiaria() == null ? "" : orden.getInstitucionBeneficiaria()).append("|");
        cadenaOriginal.append(orden.getClaveRastreo() == null ? "" : orden.getClaveRastreo()).append("|");
        cadenaOriginal.append(orden.getMonto() == null ? "" : orden.getMonto()).append("|");
        cadenaOriginal.append(orden.getTipoPago() == null ? "" : orden.getTipoPago()).append("|");
        cadenaOriginal.append(orden.getNombreBeneficiario() == null ? "" : orden.getNombreBeneficiario()).append("|");
        cadenaOriginal.append(orden.getTipoCuentaBeneficiario() == null ? "" : orden.getTipoCuentaBeneficiario()).append("|");
        cadenaOriginal.append(orden.getCuentaBeneficiario() == null ? "" : orden.getCuentaBeneficiario()).append("|");
        cadenaOriginal.append(orden.getRfcCurpBeneficiario() == null ? "" : orden.getRfcCurpBeneficiario()).append("|");
        cadenaOriginal.append(orden.getConceptoPago() == null ? "" : orden.getConceptoPago()).append("|");
        cadenaOriginal.append(orden.getIva() == null ? "" : orden.getIva()).append("|");
        cadenaOriginal.append(orden.getReferenciaNumerica() == null ? "" : orden.getReferenciaNumerica());
        cadenaOriginal.append("||");
        System.out.println("Cadena original: " + cadenaOriginal);
        return cadenaOriginal.toString();
    }

    public static String cadenaParticipanteATerceroVostroFwsT11(DatosOrdenes orden) {
        StringBuilder cadenaOriginal = new StringBuilder();
        cadenaOriginal.append("||");
        cadenaOriginal.append(fechaOperacion).append("|");
        cadenaOriginal.append(orden.getInstitucionOrdenante() == null ? "" : orden.getInstitucionOrdenante()).append("|");
        cadenaOriginal.append(orden.getInstancia() == null ? "" : orden.getInstancia()).append("|");
        cadenaOriginal.append(orden.getInstitucionBeneficiaria() == null ? "" : orden.getInstitucionBeneficiaria()).append("|");
        cadenaOriginal.append(orden.getClaveRastreo() == null ? "" : orden.getClaveRastreo()).append("|");
        cadenaOriginal.append(orden.getMonto() == null ? "" : orden.getMonto()).append("|");
        cadenaOriginal.append(orden.getTipoPago() == null ? "" : orden.getTipoPago()).append("|");
        cadenaOriginal.append(orden.getNombreBeneficiario() == null ? "" : orden.getNombreBeneficiario()).append("|");
        cadenaOriginal.append(orden.getTipoCuentaBeneficiario() == null ? "" : orden.getTipoCuentaBeneficiario()).append("|");
        cadenaOriginal.append(orden.getCuentaBeneficiario() == null ? "" : orden.getCuentaBeneficiario()).append("|");
        cadenaOriginal.append(orden.getNombreBeneficiario2() == null ? "" : orden.getNombreBeneficiario2()).append("|");
        cadenaOriginal.append(orden.getRfcCurpBeneficiario2() == null ? "" : orden.getRfcCurpBeneficiario2()).append("|");
        cadenaOriginal.append(orden.getTipoCuentaBeneficiario2() == null ? "" : orden.getTipoCuentaBeneficiario2()).append("|");
        cadenaOriginal.append(orden.getCuentaBeneficiario2() == null ? "" : orden.getCuentaBeneficiario2()).append("|");
        cadenaOriginal.append(orden.getConceptoPago() == null ? "" : orden.getConceptoPago()).append("|");
        cadenaOriginal.append(orden.getConceptoPago2() == null ? "" : orden.getConceptoPago2()).append("|");
        cadenaOriginal.append(orden.getIva() == null ? "" : orden.getIva()).append("|");
        cadenaOriginal.append(orden.getReferenciaNumerica() == null ? "" : orden.getReferenciaNumerica());
        cadenaOriginal.append("||");
        System.out.println("Cadena original: " + cadenaOriginal);
        return cadenaOriginal.toString();
    }

    public static String cadenaCobroPresencialUnaOcasionT19(DatosOrdenes orden) {
        StringBuilder cadenaOriginal = new StringBuilder();
        cadenaOriginal.append("||");
        cadenaOriginal.append(fechaOperacion).append("|");
        cadenaOriginal.append(orden.getInstitucionOrdenante() == null ? "" : orden.getInstitucionOrdenante()).append("|");
        cadenaOriginal.append(orden.getInstancia() == null ? "" : orden.getInstancia()).append("|");
        cadenaOriginal.append(orden.getInstitucionBeneficiaria() == null ? "" : orden.getInstitucionBeneficiaria()).append("|");
        cadenaOriginal.append(orden.getClaveRastreo() == null ? "" : orden.getClaveRastreo()).append("|");
        cadenaOriginal.append(orden.getMonto() == null ? "" : orden.getMonto()).append("|");
        cadenaOriginal.append(orden.getTipoPago() == null ? "" : orden.getTipoPago()).append("|");
        cadenaOriginal.append(orden.getNombreOrdenante() == null ? "" : orden.getNombreOrdenante()).append("|");
        cadenaOriginal.append(orden.getTipoCuentaOrdenante() == null ? "" : orden.getTipoCuentaOrdenante()).append("|");
        cadenaOriginal.append(orden.getCuentaOrdenante() == null ? "" : orden.getCuentaOrdenante()).append("|");
        cadenaOriginal.append(orden.getRfcCurpOrdenante() == null ? "" : orden.getRfcCurpOrdenante()).append("|");
        cadenaOriginal.append(orden.getAliasCelCertOrd() == null ? "" : orden.getAliasCelCertOrd()).append("|");
        cadenaOriginal.append(orden.getDigitoIdOrdenante() == null ? "" : orden.getDigitoIdOrdenante()).append("|");
        cadenaOriginal.append(orden.getNombreBeneficiario() == null ? "" : orden.getNombreBeneficiario()).append("|");
        cadenaOriginal.append(orden.getTipoCuentaBeneficiario() == null ? "" : orden.getTipoCuentaBeneficiario()).append("|");
        cadenaOriginal.append(orden.getCuentaBeneficiario() == null ? "" : orden.getCuentaBeneficiario()).append("|");
        cadenaOriginal.append(orden.getRfcCurpBeneficiario() == null ? "" : orden.getRfcCurpBeneficiario()).append("|");
        cadenaOriginal.append(orden.getAliasCelCertBene() == null ? "" : orden.getAliasCelCertBene()).append("|");
        cadenaOriginal.append(orden.getDigitoIdBeneficiario() == null ? "" : orden.getDigitoIdBeneficiario()).append("|");
        cadenaOriginal.append(orden.getConceptoPago() == null ? "" : orden.getConceptoPago()).append("|");
        cadenaOriginal.append(orden.getFolioPlataforma() == null ? "" : orden.getFolioPlataforma()).append("|");
        cadenaOriginal.append(orden.getReferenciaNumerica() == null ? "" : orden.getReferenciaNumerica()).append("|");
        cadenaOriginal.append(orden.getPagoComision() == null ? "" : orden.getPagoComision()).append("|");
        cadenaOriginal.append(orden.getMontoComision() == null ? "" : orden.getMontoComision());
        cadenaOriginal.append("||");
        System.out.println("Cadena original: " + cadenaOriginal);
        return cadenaOriginal.toString();
    }

    public static String cadenaCobroNoPresencialUnaOcasionT20(DatosOrdenes orden) {
        StringBuilder cadenaOriginal = new StringBuilder();
        cadenaOriginal.append("||");
        cadenaOriginal.append(fechaOperacion).append("|");
        cadenaOriginal.append(orden.getInstitucionOrdenante() == null ? "" : orden.getInstitucionOrdenante()).append("|");
        cadenaOriginal.append(orden.getInstancia() == null ? "" : orden.getInstancia()).append("|");
        cadenaOriginal.append(orden.getInstitucionBeneficiaria() == null ? "" : orden.getInstitucionBeneficiaria()).append("|");
        cadenaOriginal.append(orden.getClaveRastreo() == null ? "" : orden.getClaveRastreo()).append("|");
        cadenaOriginal.append(orden.getMonto() == null ? "" : orden.getMonto()).append("|");
        cadenaOriginal.append(orden.getTipoPago() == null ? "" : orden.getTipoPago()).append("|");
        cadenaOriginal.append(orden.getNombreOrdenante() == null ? "" : orden.getNombreOrdenante()).append("|");
        cadenaOriginal.append(orden.getTipoCuentaOrdenante() == null ? "" : orden.getTipoCuentaOrdenante()).append("|");
        cadenaOriginal.append(orden.getCuentaOrdenante() == null ? "" : orden.getCuentaOrdenante()).append("|");
        cadenaOriginal.append(orden.getRfcCurpOrdenante() == null ? "" : orden.getRfcCurpOrdenante()).append("|");
        cadenaOriginal.append(orden.getAliasCelCertOrd() == null ? "" : orden.getAliasCelCertOrd()).append("|");
        cadenaOriginal.append(orden.getDigitoIdOrdenante() == null ? "" : orden.getDigitoIdOrdenante()).append("|");
        cadenaOriginal.append(orden.getNombreBeneficiario() == null ? "" : orden.getNombreBeneficiario()).append("|");
        cadenaOriginal.append(orden.getTipoCuentaBeneficiario() == null ? "" : orden.getTipoCuentaBeneficiario()).append("|");
        cadenaOriginal.append(orden.getCuentaBeneficiario() == null ? "" : orden.getCuentaBeneficiario()).append("|");
        cadenaOriginal.append(orden.getRfcCurpBeneficiario() == null ? "" : orden.getRfcCurpBeneficiario()).append("|");
        cadenaOriginal.append(orden.getAliasCelCertBene() == null ? "" : orden.getAliasCelCertBene()).append("|");
        cadenaOriginal.append(orden.getDigitoIdBeneficiario() == null ? "" : orden.getDigitoIdBeneficiario()).append("|");
        cadenaOriginal.append(orden.getConceptoPago() == null ? "" : orden.getConceptoPago()).append("|");
        cadenaOriginal.append(orden.getFolioPlataforma() == null ? "" : orden.getFolioPlataforma()).append("|");
        cadenaOriginal.append(orden.getReferenciaNumerica() == null ? "" : orden.getReferenciaNumerica()).append("|");
        cadenaOriginal.append(orden.getPagoComision() == null ? "" : orden.getPagoComision()).append("|");
        cadenaOriginal.append(orden.getMontoComision() == null ? "" : orden.getMontoComision());
        cadenaOriginal.append("||");
        System.out.println("Cadena original: " + cadenaOriginal);
        return cadenaOriginal.toString();
    }

    public static String cadenaCobroNoPresencialRecurrenteT21(DatosOrdenes orden) {
        StringBuilder cadenaOriginal = new StringBuilder();
        cadenaOriginal.append("||");
        cadenaOriginal.append(fechaOperacion).append("|");
        cadenaOriginal.append(orden.getInstitucionOrdenante() == null ? "" : orden.getInstitucionOrdenante()).append("|");
        cadenaOriginal.append(orden.getInstancia() == null ? "" : orden.getInstancia()).append("|");
        cadenaOriginal.append(orden.getInstitucionBeneficiaria() == null ? "" : orden.getInstitucionBeneficiaria()).append("|");
        cadenaOriginal.append(orden.getClaveRastreo() == null ? "" : orden.getClaveRastreo()).append("|");
        cadenaOriginal.append(orden.getMonto() == null ? "" : orden.getMonto()).append("|");
        cadenaOriginal.append(orden.getTipoPago() == null ? "" : orden.getTipoPago()).append("|");
        cadenaOriginal.append(orden.getNombreOrdenante() == null ? "" : orden.getNombreOrdenante()).append("|");
        cadenaOriginal.append(orden.getTipoCuentaOrdenante() == null ? "" : orden.getTipoCuentaOrdenante()).append("|");
        cadenaOriginal.append(orden.getCuentaOrdenante() == null ? "" : orden.getCuentaOrdenante()).append("|");
        cadenaOriginal.append(orden.getRfcCurpOrdenante() == null ? "" : orden.getRfcCurpOrdenante()).append("|");
        cadenaOriginal.append(orden.getAliasCelCertOrd() == null ? "" : orden.getAliasCelCertOrd()).append("|");
        cadenaOriginal.append(orden.getDigitoIdOrdenante() == null ? "" : orden.getDigitoIdOrdenante()).append("|");
        cadenaOriginal.append(orden.getNombreBeneficiario() == null ? "" : orden.getNombreBeneficiario()).append("|");
        cadenaOriginal.append(orden.getTipoCuentaBeneficiario() == null ? "" : orden.getTipoCuentaBeneficiario()).append("|");
        cadenaOriginal.append(orden.getCuentaBeneficiario() == null ? "" : orden.getCuentaBeneficiario()).append("|");
        cadenaOriginal.append(orden.getRfcCurpBeneficiario() == null ? "" : orden.getRfcCurpBeneficiario()).append("|");
        cadenaOriginal.append(orden.getAliasCertComercio() == null ? "" : orden.getAliasCertComercio()).append("|");
        cadenaOriginal.append(orden.getDigitoCertComercio() == null ? "" : orden.getDigitoCertComercio()).append("|");
        cadenaOriginal.append(orden.getConceptoPago() == null ? "" : orden.getConceptoPago()).append("|");
        cadenaOriginal.append(orden.getFolioPlataforma() == null ? "" : orden.getFolioPlataforma()).append("|");
        cadenaOriginal.append(orden.getReferenciaNumerica() == null ? "" : orden.getReferenciaNumerica()).append("|");
        cadenaOriginal.append(orden.getFechaLimitePago() == null ? "" : orden.getFechaLimitePago()).append("|");
        cadenaOriginal.append(orden.getPagoComision() == null ? "" : orden.getPagoComision()).append("|");
        cadenaOriginal.append(orden.getMontoComision() == null ? "" : orden.getMontoComision());
        cadenaOriginal.append("||");
        System.out.println("Cadena original: " + cadenaOriginal);
        return cadenaOriginal.toString();
    }

    public static String cadenaCobroNoPresencialATerceroT22(DatosOrdenes orden) {
        StringBuilder cadenaOriginal = new StringBuilder();
        cadenaOriginal.append("||");
        cadenaOriginal.append(fechaOperacion).append("|");
        cadenaOriginal.append(orden.getInstitucionOrdenante() == null ? "" : orden.getInstitucionOrdenante()).append("|");
        cadenaOriginal.append(orden.getInstancia() == null ? "" : orden.getInstancia()).append("|");
        cadenaOriginal.append(orden.getInstitucionBeneficiaria() == null ? "" : orden.getInstitucionBeneficiaria()).append("|");
        cadenaOriginal.append(orden.getClaveRastreo() == null ? "" : orden.getClaveRastreo()).append("|");
        cadenaOriginal.append(orden.getMonto() == null ? "" : orden.getMonto()).append("|");
        cadenaOriginal.append(orden.getTipoPago() == null ? "" : orden.getTipoPago()).append("|");
        cadenaOriginal.append(orden.getNombreOrdenante() == null ? "" : orden.getNombreOrdenante()).append("|");
        cadenaOriginal.append(orden.getTipoCuentaOrdenante() == null ? "" : orden.getTipoCuentaOrdenante()).append("|");
        cadenaOriginal.append(orden.getCuentaOrdenante() == null ? "" : orden.getCuentaOrdenante()).append("|");
        cadenaOriginal.append(orden.getRfcCurpOrdenante() == null ? "" : orden.getRfcCurpOrdenante()).append("|");
        cadenaOriginal.append(orden.getAliasCelCertOrd() == null ? "" : orden.getAliasCelCertOrd()).append("|");
        cadenaOriginal.append(orden.getDigitoIdOrdenante() == null ? "" : orden.getDigitoIdOrdenante()).append("|");
        cadenaOriginal.append(orden.getNombreBeneficiario() == null ? "" : orden.getNombreBeneficiario()).append("|");
        cadenaOriginal.append(orden.getTipoCuentaBeneficiario() == null ? "" : orden.getTipoCuentaBeneficiario()).append("|");
        cadenaOriginal.append(orden.getCuentaBeneficiario() == null ? "" : orden.getCuentaBeneficiario()).append("|");
        cadenaOriginal.append(orden.getRfcCurpBeneficiario() == null ? "" : orden.getRfcCurpBeneficiario()).append("|");
        cadenaOriginal.append(orden.getNombreBeneficiario2() == null ? "" : orden.getNombreBeneficiario2()).append("|");
        cadenaOriginal.append(orden.getRfcCurpBeneficiario2() == null ? "" : orden.getRfcCurpBeneficiario2()).append("|");
        cadenaOriginal.append(orden.getTipoCuentaBeneficiario2() == null ? "" : orden.getTipoCuentaBeneficiario2()).append("|");
        cadenaOriginal.append(orden.getCuentaBeneficiario2() == null ? "" : orden.getCuentaBeneficiario2()).append("|");
        cadenaOriginal.append(orden.getAliasCertComercio() == null ? "" : orden.getAliasCertComercio()).append("|");
        cadenaOriginal.append(orden.getDigitoCertComercio() == null ? "" : orden.getDigitoCertComercio()).append("|");
        cadenaOriginal.append(orden.getConceptoPago() == null ? "" : orden.getConceptoPago()).append("|");
        cadenaOriginal.append(orden.getFolioPlataforma() == null ? "" : orden.getFolioPlataforma()).append("|");
        cadenaOriginal.append(orden.getReferenciaNumerica() == null ? "" : orden.getReferenciaNumerica()).append("|");
        cadenaOriginal.append(orden.getFechaLimitePago() == null ? "" : orden.getFechaLimitePago()).append("|");
        cadenaOriginal.append(orden.getPagoComision() == null ? "" : orden.getPagoComision()).append("|");
        cadenaOriginal.append(orden.getMontoComision() == null ? "" : orden.getMontoComision());
        cadenaOriginal.append("||");

        System.out.println("Cadena original: " + cadenaOriginal);
        return cadenaOriginal.toString();
    }


    public static String cadenaDevolucionNoAcreditadaT0(DatosDevolucion devolucion) {
        StringBuilder cadenaOriginal = new StringBuilder();
        cadenaOriginal.append("||");
        cadenaOriginal.append(fechaOperacion).append("|");
        cadenaOriginal.append(devolucion.getInstitucionOrdenante() == null ? "" : devolucion.getInstitucionOrdenante()).append("|");
        cadenaOriginal.append(devolucion.getInstancia() == null ? "" : devolucion.getInstancia()).append("|");
        cadenaOriginal.append(devolucion.getInstitucionBeneficiaria() == null ? "" : devolucion.getInstitucionBeneficiaria()).append("|");
        cadenaOriginal.append(devolucion.getClaveRastreo() == null ? "" : devolucion.getClaveRastreo()).append("|");
        cadenaOriginal.append(devolucion.getMonto() == null ? "" : devolucion.getMonto()).append("|");
        cadenaOriginal.append(devolucion.getTipoPago() == null ? "" : devolucion.getTipoPago()).append("|");
        cadenaOriginal.append(devolucion.getClaveRastreoDet() == null ? "" : devolucion.getClaveRastreoDet()).append("|");
        cadenaOriginal.append(devolucion.getCausaDevolucion() == null ? "" : devolucion.getCausaDevolucion());
        cadenaOriginal.append("||");
        System.out.println("Cadena original: " + cadenaOriginal);
        return cadenaOriginal.toString();
    }

    public static String cadenaDevolucionNoAcreditadaExtemporaneaT16(DatosDevolucion devolucion) {
        StringBuilder cadenaOriginal = new StringBuilder();
        cadenaOriginal.append("||");
        cadenaOriginal.append(fechaOperacion).append("|");
        cadenaOriginal.append(devolucion.getInstitucionOrdenante() == null ? "" : devolucion.getInstitucionOrdenante()).append("|");
        cadenaOriginal.append(devolucion.getInstancia() == null ? "" : devolucion.getInstancia()).append("|");
        cadenaOriginal.append(devolucion.getInstitucionBeneficiaria() == null ? "" : devolucion.getInstitucionBeneficiaria()).append("|");
        cadenaOriginal.append(devolucion.getClaveRastreo() == null ? "" : devolucion.getClaveRastreo()).append("|");
        cadenaOriginal.append(devolucion.getMonto() == null ? "" : devolucion.getMonto()).append("|");
        cadenaOriginal.append(devolucion.getTipoPago() == null ? "" : devolucion.getTipoPago()).append("|");
        cadenaOriginal.append(devolucion.getClaveRastreoDet() == null ? "" : devolucion.getClaveRastreoDet()).append("|");
        cadenaOriginal.append(devolucion.getMontoIntereses() == null ? "" : devolucion.getMontoIntereses()).append("|");
        cadenaOriginal.append(devolucion.getCausaDevolucion() == null ? "" : devolucion.getCausaDevolucion());
        cadenaOriginal.append("||");
        System.out.println("Cadena original: " + cadenaOriginal);
        return cadenaOriginal.toString();
    }

    public static String cadenaDevolucionAcreditadaT17(DatosDevolucion devolucion) {
        StringBuilder cadenaOriginal = new StringBuilder();
        cadenaOriginal.append("||");
        cadenaOriginal.append(fechaOperacion).append("|");
        cadenaOriginal.append(devolucion.getInstitucionOrdenante() == null ? "" : devolucion.getInstitucionOrdenante()).append("|");
        cadenaOriginal.append(devolucion.getInstancia() == null ? "" : devolucion.getInstancia()).append("|");
        cadenaOriginal.append(devolucion.getInstitucionBeneficiaria() == null ? "" : devolucion.getInstitucionBeneficiaria()).append("|");
        cadenaOriginal.append(devolucion.getClaveRastreo() == null ? "" : devolucion.getClaveRastreo()).append("|");
        cadenaOriginal.append(devolucion.getMonto() == null ? "" : devolucion.getMonto()).append("|");
        cadenaOriginal.append(devolucion.getTipoPago() == null ? "" : devolucion.getTipoPago()).append("|");
        cadenaOriginal.append(devolucion.getClaveRastreoDet() == null ? "" : devolucion.getClaveRastreoDet());
        cadenaOriginal.append("||");
        System.out.println("Cadena original: " + cadenaOriginal);
        return cadenaOriginal.toString();
    }

    public static String cadenaDevolucionAcreditadaExtemporaneaT18(DatosDevolucion devolucion) {
        StringBuilder cadenaOriginal = new StringBuilder();
        cadenaOriginal.append("||");
        cadenaOriginal.append(fechaOperacion).append("|");
        cadenaOriginal.append(devolucion.getInstitucionOrdenante() == null ? "" : devolucion.getInstitucionOrdenante()).append("|");
        cadenaOriginal.append(devolucion.getInstancia() == null ? "" : devolucion.getInstancia()).append("|");
        cadenaOriginal.append(devolucion.getInstitucionBeneficiaria() == null ? "" : devolucion.getInstitucionBeneficiaria()).append("|");
        cadenaOriginal.append(devolucion.getClaveRastreo() == null ? "" : devolucion.getClaveRastreo()).append("|");
        cadenaOriginal.append(devolucion.getMonto() == null ? "" : devolucion.getMonto()).append("|");
        cadenaOriginal.append(devolucion.getTipoPago() == null ? "" : devolucion.getTipoPago()).append("|");
        cadenaOriginal.append(devolucion.getClaveRastreoDet() == null ? "" : devolucion.getClaveRastreoDet());
        cadenaOriginal.append("||");
        System.out.println("Cadena original: " + cadenaOriginal);
        return cadenaOriginal.toString();
    }

    public static String cadenaDevolucionEspecialAcreditadaT23(DatosDevolucion devolucion) {
        StringBuilder cadenaOriginal = new StringBuilder();
        cadenaOriginal.append("||");
        cadenaOriginal.append(fechaOperacion).append("|");
        cadenaOriginal.append(devolucion.getInstitucionOrdenante() == null ? "" : devolucion.getInstitucionOrdenante()).append("|");
        cadenaOriginal.append(devolucion.getInstancia() == null ? "" : devolucion.getInstancia()).append("|");
        cadenaOriginal.append(devolucion.getInstitucionBeneficiaria() == null ? "" : devolucion.getInstitucionBeneficiaria()).append("|");
        cadenaOriginal.append(devolucion.getClaveRastreo() == null ? "" : devolucion.getClaveRastreo()).append("|");
        cadenaOriginal.append(devolucion.getMonto() == null ? "" : devolucion.getMonto()).append("|");
        cadenaOriginal.append(devolucion.getTipoPago() == null ? "" : devolucion.getTipoPago()).append("|");
        cadenaOriginal.append(devolucion.getClaveRastreoDet() == null ? "" : devolucion.getClaveRastreoDet()).append("|");
        cadenaOriginal.append(devolucion.getIndicadorBeneficiario() == null ? "" : devolucion.getIndicadorBeneficiario());
        cadenaOriginal.append("||");
        System.out.println("Cadena original: " + cadenaOriginal);
        return cadenaOriginal.toString();
    }

    public static String cadenaDevolucionEspecialAcreditadaExtemporaneaT24(DatosDevolucion devolucion) {
        StringBuilder cadenaOriginal = new StringBuilder();
        cadenaOriginal.append("||");
        cadenaOriginal.append(fechaOperacion).append("|");
        cadenaOriginal.append(devolucion.getInstitucionOrdenante() == null ? "" : devolucion.getInstitucionOrdenante()).append("|");
        cadenaOriginal.append(devolucion.getInstancia() == null ? "" : devolucion.getInstancia()).append("|");
        cadenaOriginal.append(devolucion.getInstitucionBeneficiaria() == null ? "" : devolucion.getInstitucionBeneficiaria()).append("|");
        cadenaOriginal.append(devolucion.getClaveRastreo() == null ? "" : devolucion.getClaveRastreo()).append("|");
        cadenaOriginal.append(devolucion.getMonto() == null ? "" : devolucion.getMonto()).append("|");
        cadenaOriginal.append(devolucion.getTipoPago() == null ? "" : devolucion.getTipoPago()).append("|");
        cadenaOriginal.append(devolucion.getClaveRastreoDet() == null ? "" : devolucion.getClaveRastreoDet()).append("|");
        cadenaOriginal.append(devolucion.getIndicadorBeneficiario() == null ? "" : devolucion.getIndicadorBeneficiario());
        cadenaOriginal.append("||");
        System.out.println("Cadena original: " + cadenaOriginal);
        return cadenaOriginal.toString();
    }


    public static String cadenaConfirmarOrden(datosConfirmaOrden confirmaOrden) {
        StringBuilder cadenaOriginal = new StringBuilder();
        cadenaOriginal.append("||");
        cadenaOriginal.append(fechaOperacion).append("|");
        cadenaOriginal.append(confirmaOrden.getInstitucionBeneficiaria() == null ? "" : confirmaOrden.getInstitucionOrdenante()).append("|");
        cadenaOriginal.append(confirmaOrden.getInstancia() == null ? "" : confirmaOrden.getInstancia()).append("|");
        cadenaOriginal.append(confirmaOrden.getInstitucionOrdenante() == null ? "" : confirmaOrden.getInstitucionBeneficiaria()).append("|");
        cadenaOriginal.append(confirmaOrden.getClaveRastreo() == null ? "" : confirmaOrden.getClaveRastreo()).append("|");
        cadenaOriginal.append(confirmaOrden.getMonto() == null ? "" : confirmaOrden.getMonto()).append("|");
        cadenaOriginal.append(confirmaOrden.getTipoPago() == null ? "" : confirmaOrden.getTipoPago());
        cadenaOriginal.append("||");
        System.out.println("Cadena original: " + cadenaOriginal);
        return cadenaOriginal.toString();
    }

    public static String cadenaCancelarOrden(datosCancelarOrden cancelarOrden) {
        StringBuilder cadenaOriginal = new StringBuilder();
        cadenaOriginal.append("||");
        cadenaOriginal.append(fechaOperacion).append("|");
        cadenaOriginal.append(cancelarOrden.getInstitucionBeneficiaria() == null ? "" : cancelarOrden.getInstitucionOrdenante()).append("|");
        cadenaOriginal.append(cancelarOrden.getInstancia() == null ? "" : cancelarOrden.getInstancia()).append("|");
        cadenaOriginal.append(cancelarOrden.getInstitucionOrdenante() == null ? "" : cancelarOrden.getInstitucionBeneficiaria()).append("|");
        cadenaOriginal.append(cancelarOrden.getClaveRastreo() == null ? "" : cancelarOrden.getClaveRastreo()).append("|");
        cadenaOriginal.append(cancelarOrden.getMonto() == null ? "" : cancelarOrden.getMonto()).append("|");
        cadenaOriginal.append(cancelarOrden.getTipoPago() == null ? "" : cancelarOrden.getTipoPago());
        cadenaOriginal.append("||");
        System.out.println("Cadena original: " + cadenaOriginal);
        return cadenaOriginal.toString();
    }


    public static String signMessage(String mensaje) {
        try {
            Properties props = new Properties();
            props.load(new FileInputStream("src/main/resources/propiedades.properties"));
            String keystorePath = props.getProperty("fileName");
            String keystorePassword = props.getProperty("password");
            String keyAlias = props.getProperty("alias");
            KeyStore ks = KeyStore.getInstance("PKCS12");
            ks.load(new FileInputStream(keystorePath), keystorePassword.toCharArray());
            PrivateKey privateKey = (PrivateKey) ks.getKey(keyAlias, keystorePassword.toCharArray());
            Signature sig = Signature.getInstance("Ed25519");
            sig.initSign(privateKey);
            char[] chars = mensaje.toCharArray();
            byte[] bytes = new byte[chars.length];
            for (int n = 0; n < chars.length; n++)
                bytes[n] = (byte) chars[n];
            sig.update(bytes, 0, bytes.length);
            byte[] signature = sig.sign();
            System.out.println("Firma: "+Base64.getEncoder().encodeToString(signature));
            return Base64.getEncoder().encodeToString(signature);
        } catch (IOException | NoSuchAlgorithmException | KeyStoreException | CertificateException | UnrecoverableKeyException | InvalidKeyException | SignatureException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static String fechaOperacion() {
        LocalTime currentTime = LocalTime.now();
        LocalDate currentDate = LocalDate.now();
        if (currentTime.isAfter(LocalTime.of(18, 0))) {
            currentDate = currentDate.plusDays(1);
        }
        return currentDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }
}