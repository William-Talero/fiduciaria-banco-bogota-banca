package com.fiduciaria_banco_bogota.banca.dto;


import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public class TransaccionDTO {
    @Positive(message = "El monto debe ser positivo")
    private BigDecimal monto;

    public @Positive(message = "El monto debe ser positivo") BigDecimal getMonto() {
        return monto;
    }

    public void setMonto(@Positive(message = "El monto debe ser positivo") BigDecimal monto) {
        this.monto = monto;
    }
}
