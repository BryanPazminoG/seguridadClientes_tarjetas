package com.banquito.core.banking.seguridad.clientes.tarjetas.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.banquito.core.banking.seguridad.clientes.tarjetas.dto.TarjetaDTO;
import com.banquito.core.banking.seguridad.clientes.tarjetas.services.TarjetaService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/v1/seguridad-tarjeta")
public class TarjetaController {
    private final TarjetaService tarjetaService;

    public TarjetaController(TarjetaService tarjetaService) {
        this.tarjetaService = tarjetaService;
    }

    @PostMapping("/nuevo")
    public ResponseEntity<Void> crear(@RequestBody TarjetaDTO tarjeta) {
        log.info("Se va a crear el registro de loguear una tarjeta: {}", tarjeta);
        try {
            this.tarjetaService.crear(tarjeta);
            return ResponseEntity.ok().build();
        } catch (RuntimeException rte) {
            log.error("Error al crear el registro de loguear una tarjeta", rte);
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/nueva-clave")
    public ResponseEntity<Void> actualizarClave(@RequestBody TarjetaDTO tarjeta) {
        try {
            log.info("Se va a actualizar la clave de la tarjeta numero: {}", tarjeta.getNumTarjeta());
            tarjetaService.actualizarClave(tarjeta);
            return ResponseEntity.ok().build();
        } catch (RuntimeException rte) {
            return ResponseEntity.badRequest().build();
        }
    }


    @PostMapping("/sesion")
    public ResponseEntity<Boolean> iniciar (@RequestBody TarjetaDTO tarjetaDTO) {
        log.info("Se va a iniciar sesion: {}", tarjetaDTO);
        try {
            if (this.tarjetaService.validarClave(tarjetaDTO.getNumTarjeta(),tarjetaDTO.getClaveTarjeta())) {
                return ResponseEntity.ok().build();
            }else{
                return ResponseEntity.badRequest().build();
            }
            
        } catch (RuntimeException rte) {
            log.error("Error al iniciar sesion", rte);
            return ResponseEntity.badRequest().build();
        }
    }
}
