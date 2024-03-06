package com.banquito.core.banking.seguridad.clientes.tarjetas.dto;

import java.util.ArrayList;

import com.banquito.core.banking.seguridad.clientes.tarjetas.domain.LugarUltimoAcceso;
import com.banquito.core.banking.seguridad.clientes.tarjetas.domain.Tarjeta;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TarjetaBuilder {

    public static Tarjeta toTarjeta(TarjetaDTO dto) {
        Tarjeta tarjeta = new Tarjeta();
        log.info("datos tarjeta dto  {}", dto);
        tarjeta.setId(dto.getIdTarjeta());
        tarjeta.setNumTarjeta(dto.getNumTarjeta());
        tarjeta.setClaveTarjeta(dto.getClaveTarjeta());

        if (tarjeta.getLugarUltimoAcceso() != null) {
        for (LugarUltimoAccesoDTO lugarUltimoAccesoDTO : dto.getDireccionesAcceso()) {
            LugarUltimoAcceso lugarUltimoAcceso = new LugarUltimoAcceso();
            lugarUltimoAcceso.setTipo(lugarUltimoAccesoDTO.getTipo());
            lugarUltimoAcceso.setLinea1(lugarUltimoAccesoDTO.getLinea1());
            lugarUltimoAcceso.setLinea2(lugarUltimoAccesoDTO.getLinea2());
            lugarUltimoAcceso.setCodigoPostal(lugarUltimoAccesoDTO.getCodigoPostal());
            lugarUltimoAcceso.setEstado(lugarUltimoAccesoDTO.getEstado());
            tarjeta.getDirecciones().add(lugarUltimoAcceso);
        }
        log.info("tarjeta datos 1=", tarjeta);
        }
        else {
            return tarjeta;
        }
        return tarjeta;
    }


    public static TarjetaDTO toDTO(Tarjeta tarjeta){

        TarjetaDTO dto = TarjetaDTO.builder()
            .idTarjeta(tarjeta.getId())
            .numTarjeta(tarjeta.getNumTarjeta())
            .claveTarjeta(tarjeta.getClaveTarjeta())
            .build();
                    
        if (tarjeta.getLugarUltimoAcceso() != null) {
            LugarUltimoAccesoDTO lugarUltimoAccesoDTO = LugarUltimoAccesoDTO.builder()
                .tipo(tarjeta.getLugarUltimoAcceso().getTipo())
                .linea1(tarjeta.getLugarUltimoAcceso().getLinea1())
                .linea2(tarjeta.getLugarUltimoAcceso().getLinea2())
                .codigoPostal(tarjeta.getLugarUltimoAcceso().getCodigoPostal())
                .estado(tarjeta.getLugarUltimoAcceso().getEstado())
                .build();
            dto.getDireccionesAcceso().add(lugarUltimoAccesoDTO);
        }

        return dto;
    }

    public static Tarjeta copyTarjeta(Tarjeta source, Tarjeta destiny) {
        if (source.getId() != null) {
            destiny.setId(source.getId());
        }

        if (source.getNumTarjeta() != null) {
            destiny.setNumTarjeta(source.getNumTarjeta());
        }

        if (source.getClaveTarjeta() != null) {
            destiny.setClaveTarjeta(source.getClaveTarjeta());
        }

        if (source.getLugarUltimoAcceso() != null) {
            if (destiny.getLugarUltimoAcceso() == null) {
                destiny.setLugarUltimoAcceso(new LugarUltimoAcceso());
            }
            destiny.getLugarUltimoAcceso().setTipo(source.getLugarUltimoAcceso().getTipo());
            destiny.getLugarUltimoAcceso().setLinea1(source.getLugarUltimoAcceso().getLinea1());
            destiny.getLugarUltimoAcceso().setLinea2(source.getLugarUltimoAcceso().getLinea2());
            destiny.getLugarUltimoAcceso().setCodigoPostal(source.getLugarUltimoAcceso().getCodigoPostal());
            destiny.getLugarUltimoAcceso().setEstado(source.getLugarUltimoAcceso().getEstado());
        }

        return destiny;
    }
}
