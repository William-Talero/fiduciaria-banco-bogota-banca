package com.fiduciaria_banco_bogota.banca.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fiduciaria_banco_bogota.banca.dto.CuentaBancariaDTO;
import com.fiduciaria_banco_bogota.banca.dto.TransaccionDTO;
import com.fiduciaria_banco_bogota.banca.model.CuentaBancaria;
import com.fiduciaria_banco_bogota.banca.service.CuentaBancariaService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import java.math.BigDecimal;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CuentaBancariaController.class)
public class CuentaBancariaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CuentaBancariaService cuentaBancariaService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testCrearCuenta() throws Exception {
        CuentaBancariaDTO cuentaDTO = new CuentaBancariaDTO();
        cuentaDTO.setTitular("Juan Pérez");

        when(cuentaBancariaService.crearCuenta(any())).thenReturn(new CuentaBancaria());

        mockMvc.perform(post("/accounts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(cuentaDTO)))
                .andExpect(status().isOk());
    }

    @Test
    public void testRealizarDeposito() throws Exception {
        TransaccionDTO transaccionDTO = new TransaccionDTO();
        transaccionDTO.setMonto(new BigDecimal("100.00"));

        when(cuentaBancariaService.realizarDeposito(anyLong(), any())).thenReturn(new CuentaBancaria());

        mockMvc.perform(post("/accounts/1/deposit")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(transaccionDTO)))
                .andExpect(status().isOk());
    }

    @Test
    public void testRealizarRetiro() throws Exception {
        TransaccionDTO transaccionDTO = new TransaccionDTO();
        transaccionDTO.setMonto(new BigDecimal("50.00"));

        when(cuentaBancariaService.realizarRetiro(anyLong(), any())).thenReturn(new CuentaBancaria());

        mockMvc.perform(post("/accounts/1/withdraw")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(transaccionDTO)))
                .andExpect(status().isOk());
    }

    @Test
    public void testConsultarSaldo() throws Exception {
        when(cuentaBancariaService.consultarSaldo(anyLong())).thenReturn(new BigDecimal("150.00"));

        mockMvc.perform(get("/accounts/1/balance"))
                .andExpect(status().isOk())
                .andExpect((ResultMatcher) content().string("150.00"));
    }
}