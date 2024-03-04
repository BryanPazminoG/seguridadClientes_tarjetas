package com.banquito.core.banking.seguridad.clientes.tarjetas.domain;


import org.springframework.data.mongodb.core.mapping.Field;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LugarUltimoAcceso {
    private String tipo;    
    private String linea1;
    private String linea2;
    @Field("codigo_postal")
    private String codigoPostal;
    private String estado;
}


