package com.banquito.core.banking.seguridad.clientes.tarjetas.dto;


import com.banquito.core.banking.seguridad.clientes.tarjetas.domain.Tarjeta;

public class TarjetaBuilder {

    public static Tarjeta toTarjeta(TarjetaDTO dto) {
        Tarjeta tarjeta = new Tarjeta();
        tarjeta.setCodTarjeta(dto.getCodTarjeta());
        tarjeta.setNumTarjeta(dto.getNumTarjeta());
        tarjeta.setClaveTarjeta(dto.getClaveTarjeta());

        return tarjeta;
    }

    public static TarjetaDTO toDTO(Tarjeta tarjeta){

        TarjetaDTO dto = TarjetaDTO.builder()
            .codTarjeta(tarjeta.getCodTarjeta())
            .numTarjeta(tarjeta.getNumTarjeta())
            .claveTarjeta(tarjeta.getClaveTarjeta())
            .build();

        return dto;
    }

    public static Tarjeta copyTarjeta(Tarjeta source, Tarjeta destiny) {
        if (source.getCodTarjeta() != null) {
            destiny.setCodTarjeta(source.getCodTarjeta());
        }

        if (source.getNumTarjeta() != null) {
            destiny.setNumTarjeta(source.getNumTarjeta());
        }

        if (source.getClaveTarjeta() != null) {
            destiny.setClaveTarjeta(source.getClaveTarjeta());
        }


        return destiny;
    }
}
