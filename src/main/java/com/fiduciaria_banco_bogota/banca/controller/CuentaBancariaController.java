package com.fiduciaria_banco_bogota.banca.controller;

import com.fiduciaria_banco_bogota.banca.dto.CuentaBancariaDTO;
import com.fiduciaria_banco_bogota.banca.dto.TransaccionDTO;
import com.fiduciaria_banco_bogota.banca.model.CuentaBancaria;
import com.fiduciaria_banco_bogota.banca.service.CuentaBancariaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.math.BigDecimal;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/accounts")
public class CuentaBancariaController {

    @Autowired
    private CuentaBancariaService cuentaBancariaService;

    @PostMapping
    public ResponseEntity<CuentaBancariaDTO> crearCuenta(@Valid @RequestBody CuentaBancariaDTO cuentaDTO) {
        CuentaBancaria cuenta = cuentaBancariaService.crearCuenta(cuentaDTO.getTitular());
        return ResponseEntity.ok(new CuentaBancariaDTO(cuenta));
    }

    @PostMapping("/{id}/deposit")
    public ResponseEntity<CuentaBancariaDTO> realizarDeposito(@PathVariable Long id, @Valid @RequestBody TransaccionDTO transaccionDTO) {
        CuentaBancaria cuenta = cuentaBancariaService.realizarDeposito(id, transaccionDTO.getMonto());
        return ResponseEntity.ok(new CuentaBancariaDTO(cuenta));
    }

    @PostMapping("/{id}/withdraw")
    public ResponseEntity<CuentaBancariaDTO> realizarRetiro(@PathVariable Long id, @Valid @RequestBody TransaccionDTO transaccionDTO) {
        CuentaBancaria cuenta = cuentaBancariaService.realizarRetiro(id, transaccionDTO.getMonto());
        return ResponseEntity.ok(new CuentaBancariaDTO(cuenta));
    }

    @GetMapping("/{id}/balance")
    public ResponseEntity<BigDecimal> consultarSaldo(@PathVariable Long id) {
        BigDecimal saldo = cuentaBancariaService.consultarSaldo(id);
        return ResponseEntity.ok(saldo);
    }

    @GetMapping("/GetAll")
    public ResponseEntity<Page<CuentaBancaria>> getAll(
            @RequestParam(required = false, defaultValue = "0") Integer page,
            @RequestParam(required = false, defaultValue = "10")Integer size,
            @RequestParam(required = false, defaultValue = "false")Boolean enablePagination) {
        return ResponseEntity.ok(cuentaBancariaService.getAll(enablePagination, page, size));
    }
}