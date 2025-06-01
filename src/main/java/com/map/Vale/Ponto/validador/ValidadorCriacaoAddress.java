package com.map.Vale.Ponto.validador;

import com.map.Vale.Ponto.controllers.error.ResourceNotFoundException;
import com.map.Vale.Ponto.model.address.AddressForOrder;
import com.map.Vale.Ponto.repositories.AddressRepository;
import com.map.Vale.Ponto.validador.cep.ViaCepValidator;
import org.springframework.stereotype.Component;

@Component
public class ValidadorCriacaoAddress {

    private final AddressRepository addressRepository;
    private final ViaCepValidator viaCepValidator;

    public ValidadorCriacaoAddress(
            AddressRepository addressRepository,
            ViaCepValidator viaCepValidator
    ){
        this.addressRepository = addressRepository;
        this.viaCepValidator = viaCepValidator;
    }

    public void validar(AddressForOrder address){
        validarExistenciaCep(address.getCep());
    }

    private void validarExistenciaCep(String cep) {
        if (!viaCepValidator.isValid(cep)) {
            throw new ResourceNotFoundException("CEP " + cep + " não encontrado");
        }
    }
}
