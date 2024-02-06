package com.banquito.core.banking.seguridad.clientes.tarjetas.domain;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@Document(collection = "Tarjetas")
public class Tarjeta {

    @Id
    private String id;
    @Indexed(unique = true)
    private String idTarjeta;

    @Field("codigo_tarjeta")
    private Integer codTarjeta;

    @Field("numero_tarjeta")
    private String numTarjeta;

    @Field("clave_tarjeta")
    private String claveTarjeta;

    @Field("fecha_creacion")
    private LocalDateTime fechaCreacion;

    @Field("fecha_ultima_modificacion")
    private LocalDateTime fechaUltimaModificacion;

    @Version
    private Long version;

    //private List<Acceso> accesos;
    
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
        return "Tarjeta [id=" + id + ", idTarjeta=" + idTarjeta + ", codTarjeta=" + codTarjeta + ", numTarjeta="
                + numTarjeta + ", claveTarjeta=" + claveTarjeta + ", fechaCreacion=" + fechaCreacion
                + ", fechaUltimaModificacion=" + fechaUltimaModificacion + ", version=" + version + "]";
    }


}
