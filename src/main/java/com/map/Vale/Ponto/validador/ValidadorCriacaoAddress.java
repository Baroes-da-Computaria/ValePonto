package com.map.Vale.Ponto.validador;

import com.map.Vale.Ponto.model.address.AddressForOrder;
import com.map.Vale.Ponto.repositories.AddressRepository;
import org.springframework.stereotype.Component;

@Component
public class ValidadorCriacaoAddress {

    private final AddressRepository addressRepository;

    public ValidadorCriacaoAddress(AddressRepository addressRepository){
        this.addressRepository = addressRepository;
    }

    public void validar(AddressForOrder address){
        validarExistenciaCep(address.getCep());
    }

    private void validarExistenciaCep(String cep){
        // todo: validar cep
    }

}
