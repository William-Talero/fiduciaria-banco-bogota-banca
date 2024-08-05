package com.fiduciaria_banco_bogota.banca.repository;

import com.fiduciaria_banco_bogota.banca.model.Transaccion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ITransaccionRepository extends JpaRepository<Transaccion, Long> {
}