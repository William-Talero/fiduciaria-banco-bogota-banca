package com.fiduciaria_banco_bogota.banca.dto;

import com.fiduciaria_banco_bogota.banca.model.CuentaBancaria;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class CuentaBancariaDTO {
    private Long id;

    @NotBlank
    @Size(min = 1, max = 100, message = "El titular debe tener entre 1 y 100 caracteres")
    private String titular;

    private BigDecimal saldo;
    private LocalDateTime fechaCreacion;

    public CuentaBancariaDTO() {}

    public CuentaBancariaDTO(CuentaBancaria cuenta) {
        this.id = cuenta.getId();
        this.titular = cuenta.getTitular();
        this.saldo = cuenta.getSaldo();
        this.fechaCreacion = cuenta.getFechaCreacion();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotBlank @Size(min = 1, max = 100, message = "El titular debe tener entre 1 y 100 caracteres") String getTitular() {
        return titular;
    }

    public void setTitular(@NotBlank @Size(min = 1, max = 100, message = "El titular debe tener entre 1 y 100 caracteres") String titular) {
        this.titular = titular;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }
}