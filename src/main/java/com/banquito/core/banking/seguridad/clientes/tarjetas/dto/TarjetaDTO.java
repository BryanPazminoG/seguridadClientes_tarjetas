package com.banquito.core.banking.seguridad.clientes.tarjetas.dto;


import java.util.ArrayList;
import java.util.List;

import lombok.Builder;
import lombok.Data;

import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
@Data
public class TarjetaDTO {
    private String idTarjeta;
    private String numTarjeta;
    private String claveTarjeta;

    private List<LugarUltimoAccesoDTO> direccionesAcceso;

    public void addDireccion(LugarUltimoAccesoDTO direccionesAcceso) {
        if (this.direccionesAcceso == null) {
            this.direccionesAcceso = new ArrayList<>();
        }
        this.direccionesAcceso.add(direccionesAcceso);
    }

    public void removeDireccion(LugarUltimoAccesoDTO direccionesAcceso) {
        if (this.direccionesAcceso != null) {
            this.direccionesAcceso.remove(direccionesAcceso);
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        TarjetaDTO other = (TarjetaDTO) obj;
        if (idTarjeta == null) {
            if (other.idTarjeta != null)
                return false;
        } else if (!idTarjeta.equals(other.idTarjeta))
            return false;
        return true;
    }


    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((idTarjeta == null) ? 0 : idTarjeta.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return "TarjetaDTO [idTarjeta=" + idTarjeta + ", codTarjeta="  + ", numTarjeta=" + numTarjeta
                + ", claveTarjeta=" + claveTarjeta + "]";
    }

}
