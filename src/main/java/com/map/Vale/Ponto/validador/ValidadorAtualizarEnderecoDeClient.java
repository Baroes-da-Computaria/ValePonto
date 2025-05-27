package com.map.Vale.Ponto.validador;

import com.map.Vale.Ponto.controllers.error.ResourceNotFoundException;
import com.map.Vale.Ponto.model.address.AddressForClient;
import com.map.Vale.Ponto.repositories.ClientRepository;
import org.springframework.stereotype.Component;

@Component
public class ValidadorAtualizarEnderecoDeClient {
    
    private final ClientRepository clientRepository;
    
    public ValidadorAtualizarEnderecoDeClient(ClientRepository clientRepository){
        this.clientRepository = clientRepository;
    }
    
    public void validar(Long id, AddressForClient address) {
        validarExistenciaCLient(id);
        validarExistenciaCep(address.getCep());
    }

    private void validarExistenciaCep(String cep) {
//        if(!exists(cep)){
//            throw new ResourceNotFoundException("Cep "+ id + " não encontrado");
//        }
    }

    private void validarExistenciaCLient(Long id) {
        if(!clientRepository.existsById(id)){
            throw new ResourceNotFoundException("Client com id "+ id + " não encontrado");
        }
    }
}
