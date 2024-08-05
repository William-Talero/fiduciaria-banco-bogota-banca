package com.fiduciaria_banco_bogota.banca.service;

import com.fiduciaria_banco_bogota.banca.model.CuentaBancaria;
import com.fiduciaria_banco_bogota.banca.model.Transaccion;
import com.fiduciaria_banco_bogota.banca.repository.ICuentaBancariaRepository;
import com.fiduciaria_banco_bogota.banca.repository.ITransaccionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class CuentaBancariaService {

    @Autowired
    private ICuentaBancariaRepository cuentaBancariaRepository;

    @Autowired
    private ITransaccionRepository transaccionRepository;

    @Transactional
    public CuentaBancaria crearCuenta(String titular) {
        CuentaBancaria cuenta = new CuentaBancaria();
        cuenta.setTitular(titular);
        cuenta.setSaldo(BigDecimal.ZERO);
        cuenta.setFechaCreacion(LocalDateTime.now());
        return cuentaBancariaRepository.save(cuenta);
    }

    @Transactional
    public CuentaBancaria realizarDeposito(Long id, BigDecimal monto) {
        CuentaBancaria cuenta = cuentaBancariaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cuenta no encontrada"));

        if (monto.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("El monto del depósito debe ser positivo");
        }

        cuenta.setSaldo(cuenta.getSaldo().add(monto));
        cuentaBancariaRepository.save(cuenta);

        Transaccion transaccion = new Transaccion();
        transaccion.setCuentaBancaria(cuenta);
        transaccion.setTipo(Transaccion.TipoTransaccion.DEPOSITO);
        transaccion.setMonto(monto);
        transaccion.setFecha(LocalDateTime.now());
        transaccionRepository.save(transaccion);

        return cuenta;
    }

    @Transactional
    public CuentaBancaria realizarRetiro(Long id, BigDecimal monto) {
        CuentaBancaria cuenta = cuentaBancariaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cuenta no encontrada"));

        if (monto.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("El monto del retiro debe ser positivo");
        }

        if (cuenta.getSaldo().compareTo(monto) < 0) {
            throw new IllegalArgumentException("Saldo insuficiente");
        }

        cuenta.setSaldo(cuenta.getSaldo().subtract(monto));
        cuentaBancariaRepository.save(cuenta);

        Transaccion transaccion = new Transaccion();
        transaccion.setCuentaBancaria(cuenta);
        transaccion.setTipo(Transaccion.TipoTransaccion.RETIRO);
        transaccion.setMonto(monto);
        transaccion.setFecha(LocalDateTime.now());
        transaccionRepository.save(transaccion);

        return cuenta;
    }

    public BigDecimal consultarSaldo(Long id) {
        CuentaBancaria cuenta = cuentaBancariaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cuenta no encontrada"));
        return cuenta.getSaldo();
    }

    public List<CuentaBancaria> getAll() {
        return cuentaBancariaRepository.findAll();
    }
}