package com.fiduciaria_banco_bogota.banca.repository;

import com.fiduciaria_banco_bogota.banca.model.CuentaBancaria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICuentaBancariaRepository extends JpaRepository<CuentaBancaria, Long> {
}