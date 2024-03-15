package com.banquito.core.banking.seguridad.clientes.tarjetas.services;

import java.sql.Date;
import java.time.LocalDate;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.banquito.core.banking.seguridad.clientes.tarjetas.dao.TarjetaRepository;
import com.banquito.core.banking.seguridad.clientes.tarjetas.domain.Tarjeta;
import com.banquito.core.banking.seguridad.clientes.tarjetas.dto.TarjetaBuilder;
import com.banquito.core.banking.seguridad.clientes.tarjetas.dto.TarjetaDTO;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class TarjetaService {

    private final TarjetaRepository tarjetaRepository;

    public TarjetaService(TarjetaRepository tarjetaRepository) {
        this.tarjetaRepository = tarjetaRepository;
    }

    public void crear(TarjetaDTO dto) {
        try {
            log.info("tarjeta datos= {}", dto);
            Tarjeta tarjeta = TarjetaBuilder.toTarjeta(dto);
            log.info("datos nueva tarjeta= {}", tarjeta);
            tarjeta.setClaveTarjeta(new DigestUtils("MD2").digestAsHex(tarjeta.getClaveTarjeta()));
            tarjeta.setId(new DigestUtils("MD2").digestAsHex(tarjeta.toString()));
            LocalDate fechaActualDate = LocalDate.now();
            tarjeta.setFechaCreacion(Date.valueOf(fechaActualDate));
            tarjeta.setFechaUltimoAcceso(Date.valueOf(fechaActualDate));
            tarjetaRepository.save(tarjeta);
            log.info("Se creó el registro de logueo de la tarjeta: {}", tarjeta.getNumTarjeta());
        } catch (Exception e) {
            throw new RuntimeException("Error al crear el registro de logueo de la tarjeta...");
        }
    }

    public Boolean validarClave(String numTarjeta, String claveTarjeta) {
        try {
            String contrasenaHash = new DigestUtils("MD5").digestAsHex(claveTarjeta);
            //agregar la direccion 

            log.info("la contrasena se ha encriptado");
            Tarjeta tarjeta = this.tarjetaRepository.findByNumTarjetaAndClaveTarjeta(numTarjeta, contrasenaHash);
            //tarjeta.setLugarUltimoAcceso(null);
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
            log.info("Iniciando busqueda del numero de la tarjeta: {}", dto.getNumTarjeta());
            Tarjeta tarjetaAux = this.tarjetaRepository.findByNumTarjeta(dto.getNumTarjeta());
            if (tarjetaAux != null) {
                log.info("Numero de tarjeta encontrada");
                dto.setClaveTarjeta(new DigestUtils("MD5").digestAsHex(dto.getClaveTarjeta()));
                log.info("Clave de la tarjeta encriptada"); 
                Tarjeta tarjetaTmp = TarjetaBuilder.toTarjeta(dto);
                Tarjeta tarjeta = TarjetaBuilder.copyTarjeta(tarjetaTmp, tarjetaAux);
                LocalDate fechaActualDate = LocalDate.now();
                tarjeta.setFechaUltimaModificacion(Date.valueOf(fechaActualDate));
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
