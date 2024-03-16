package com.banquito.core.banking.seguridad.clientes.tarjetas.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.banquito.core.banking.seguridad.clientes.tarjetas.dto.TarjetaDTO;
import com.banquito.core.banking.seguridad.clientes.tarjetas.services.TarjetaService;

import lombok.extern.log4j.Log4j2;

@Log4j2
@CrossOrigin(origins = {"http://localhost:4200", "http://34.173.161.134:4201", "http://34.176.205.203:4202", 
                        "http://34.176.102.118:4203", "http://34.176.137.180:4204"})
//@CrossOrigin(origins = "", allowedHeaders = "", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT})
@RestController
@RequestMapping("/api/v1/seguridad-tarjeta")
public class TarjetaController {
    private final TarjetaService tarjetaService;

    public TarjetaController(TarjetaService tarjetaService) {
        this.tarjetaService = tarjetaService;
    }

    @PostMapping
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

    @PutMapping
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
