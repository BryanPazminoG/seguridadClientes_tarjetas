package com.banquito.core.banking.seguridad.clientes.tarjetas.dto;

import java.util.ArrayList;

import com.banquito.core.banking.seguridad.clientes.tarjetas.domain.LugarUltimoAcceso;
import com.banquito.core.banking.seguridad.clientes.tarjetas.domain.Tarjeta;

public class TarjetaBuilder {

    public static Tarjeta toTarjeta(TarjetaDTO dto) {
        Tarjeta tarjeta = new Tarjeta();
        tarjeta.setId(dto.getIdTarjeta());
        tarjeta.setNumTarjeta(dto.getNumTarjeta());
        tarjeta.setClaveTarjeta(dto.getClaveTarjeta());
        tarjeta.setDirecciones(new ArrayList<>());
        
        
        if (dto.getDireccionesAcceso() != null) {
            LugarUltimoAcceso lugarUltimoAcceso = new LugarUltimoAcceso();
            lugarUltimoAcceso.setTipo(dto.getDireccionesAcceso().getTipo());
            lugarUltimoAcceso.setLinea1(dto.getDireccionesAcceso().getLinea1());
            lugarUltimoAcceso.setLinea2(dto.getDireccionesAcceso().getLinea2());
            lugarUltimoAcceso.setCodigoPostal(dto.getDireccionesAcceso().getCodigoPostal());
            lugarUltimoAcceso.setEstado(dto.getDireccionesAcceso().getEstado());
            tarjeta.setLugarUltimoAcceso(lugarUltimoAcceso);
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
            dto.setDireccionesAcceso(null);(lugarUltimoAccesoDTO);
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
