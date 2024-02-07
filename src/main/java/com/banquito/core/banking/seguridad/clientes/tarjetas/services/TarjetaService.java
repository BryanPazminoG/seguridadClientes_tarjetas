package com.banquito.core.banking.seguridad.clientes.tarjetas.services;

import java.time.LocalDateTime;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.apache.commons.codec.digest.DigestUtils;
import com.banquito.core.banking.seguridad.clientes.tarjetas.dao.TarjetaRepository;
import com.banquito.core.banking.seguridad.clientes.tarjetas.domain.Tarjeta;
import com.banquito.core.banking.seguridad.clientes.tarjetas.dto.TarjetaBuilder;
import com.banquito.core.banking.seguridad.clientes.tarjetas.dto.TarjetaDTO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class TarjetaService {

    private final TarjetaRepository tarjetaRepository;

    public TarjetaService(TarjetaRepository tarjetaRepository) {
        this.tarjetaRepository = tarjetaRepository;
    }

    public void crear(TarjetaDTO dto) {
        try {
            Tarjeta tarjeta = TarjetaBuilder.toTarjeta(dto);
            tarjeta.setClaveTarjeta(new DigestUtils("MD2").digestAsHex(tarjeta.getClaveTarjeta()));
            tarjeta.setCodTarjeta(new DigestUtils("MD2").digestAsHex(tarjeta.toString()));
            tarjeta.setFechaCreacion(LocalDateTime.now());
            tarjeta.setFechaUltimaModificacion(LocalDateTime.now());
            tarjetaRepository.save(tarjeta);
            log.info("Se creó el registro de logueo de la tarjeta: {}", tarjeta.getNumTarjeta());
        } catch (Exception e) {
            throw new RuntimeException("Error al crear el registro de logueo de la tarjeta...");
        }
    }

    public Boolean validarClave(String numTarjeta, String claveTarjeta) {
        try {
            String contrasenaHash = new DigestUtils("MD5").digestAsHex(claveTarjeta);
            log.info("la contrasena se ha encriptado");
            Tarjeta tarjeta = this.tarjetaRepository.findByNumTarjetaAndClaveTarjeta(numTarjeta, contrasenaHash);
            if (tarjeta != null) {
                log.info("Se ha encontrado la tarjeta ");
                return true;
            } else {
                throw new RuntimeException("Credenciales incorrectas");
            }

        } catch (Exception e) {
            throw new RuntimeException("Credenciales incorrectas");
        }

    }


    @Transactional
    public Boolean actualizarClave(TarjetaDTO dto) {
        try {
            log.info("realiza busqueda del numero de la tarjeta: {}", dto.getNumTarjeta());
            Tarjeta tarjetaAux = this.tarjetaRepository.findByNumTarjeta(dto.getNumTarjeta());
            if (tarjetaAux != null) {
                log.info("Numero de tarjeta encontrada");
                dto.setClaveTarjeta(new DigestUtils("MD5").digestAsHex(dto.getClaveTarjeta()));
                log.info("Clave de la tarjeta encriptada"); 
                Tarjeta tarjetaTmp = TarjetaBuilder.toTarjeta(dto);
                Tarjeta tarjeta = TarjetaBuilder.copyTarjeta(tarjetaTmp, tarjetaAux);
                tarjeta.setFechaUltimaModificacion(LocalDateTime.now());
                log.info("Guardando datos");
                this.tarjetaRepository.save(tarjeta);
                log.info("Se actualizo la contrasena del la tarjeta: {}", tarjeta.getNumTarjeta());
                return true;
            } else {
                log.error("Numero de tarjeta no encontrada");
                return false;
            }

        } catch (Exception e) {
            throw new RuntimeException("Error al actualizar la contrasena.", e);
        }
    }

}
