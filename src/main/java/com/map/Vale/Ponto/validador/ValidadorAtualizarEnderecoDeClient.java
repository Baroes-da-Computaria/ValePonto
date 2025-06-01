package com.map.Vale.Ponto.validador;

import com.map.Vale.Ponto.controllers.error.ResourceNotFoundException;
import com.map.Vale.Ponto.model.address.AddressDTO;
import com.map.Vale.Ponto.repositories.ClientRepository;
import com.map.Vale.Ponto.validador.cep.ViaCepValidator;
import org.springframework.stereotype.Component;

@Component
public class ValidadorAtualizarEnderecoDeClient {
    
    private final ClientRepository clientRepository;
    private final ViaCepValidator viaCepValidator;
    
    public ValidadorAtualizarEnderecoDeClient(
            ClientRepository clientRepository,
            ViaCepValidator viaCepValidator
    ){
        this.clientRepository = clientRepository;
        this.viaCepValidator = viaCepValidator;
    }
    
    public void validar(Long id, AddressDTO address) {
        validarExistenciaCLient(id);
        validarExistenciaCep(address.getCep());
    }

    private void validarExistenciaCep(String cep) {
        if (!viaCepValidator.isValid(cep)) {
            throw new ResourceNotFoundException("CEP " + cep + " não encontrado");
        }
    }

    private void validarExistenciaCLient(Long id) {
        if(!clientRepository.existsById(id)){
            throw new ResourceNotFoundException("Client com id "+ id + " não encontrado");
        }
    }
}
