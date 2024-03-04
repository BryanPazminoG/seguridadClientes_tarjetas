package com.banquito.core.banking.seguridad.clientes.tarjetas.domain;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@Document(collection = "segtarjetas")
public class Tarjeta {

    @Id
    private String id;

    @Field("numero_tarjeta")
    private String numTarjeta;

    @Field("pin")
    private String claveTarjeta;

    @Field("fecha_creacion")
    private Date fechaCreacion;

    @Field("fecha_ultima_modificacion")
    private Date fechaUltimaModificacion;

    @Field("fecha_ultimo_acceso")
    private Date fechaUltimoAcceso;

    private List<LugarUltimoAcceso> direcciones;

    @Field("lugar_ultimo_acceso")
    private LugarUltimoAcceso lugarUltimoAcceso;

    @Field("numero_intentos")
    private Integer numeroIntentos;

    @Version
    private Long version;

        public Tarjeta(String id) {
        this.id = id;
    }

        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + ((id == null) ? 0 : id.hashCode());
            return result;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            Tarjeta other = (Tarjeta) obj;
            if (id == null) {
                if (other.id != null)
                    return false;
            } else if (!id.equals(other.id))
                return false;
            return true;
        }

        @Override
        public String toString() {
            return "Tarjeta [id=" + id + ", numTarjeta=" + numTarjeta + ", claveTarjeta=" + claveTarjeta
                    + ", fechaCreacion=" + fechaCreacion + ", fechaUltimaModificacion=" + fechaUltimaModificacion
                    + ", fechaUltimoAcceso=" + fechaUltimoAcceso + ", direcciones=" + direcciones
                    + ", lugarUltimoAcceso=" + lugarUltimoAcceso + ", numeroIntentos=" + numeroIntentos + ", version="
                    + version + "]";
        }


 
    
}
