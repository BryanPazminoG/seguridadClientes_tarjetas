package com.banquito.core.banking.seguridad.clientes.tarjetas.dao;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.banquito.core.banking.seguridad.clientes.tarjetas.domain.Tarjeta;

public interface TarjetaRepository extends MongoRepository<Tarjeta, String> {
    
    Tarjeta findByNumTarjeta(String numTarjeta);
    Tarjeta findByClaveTarjeta(String claveTarjeta);
    Tarjeta findByNumTarjetaAndClaveTarjeta(String numTarjeta, String claveTarjeta);
    
}