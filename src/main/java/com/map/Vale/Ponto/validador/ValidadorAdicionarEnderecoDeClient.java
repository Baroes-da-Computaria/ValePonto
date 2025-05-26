package com.map.Vale.Ponto.validador;

import com.map.Vale.Ponto.controllers.error.ResourceNotFoundException;
import com.map.Vale.Ponto.model.address.Address;
import com.map.Vale.Ponto.repositories.ClientRepository;
import org.springframework.stereotype.Component;

@Component
public class ValidadorAdicionarEnderecoDeClient {

    private final ClientRepository clientRepository;

    public ValidadorAdicionarEnderecoDeClient(ClientRepository clientRepository){
        this.clientRepository = clientRepository;
    }

    public void validar(Long id, Address address) {
        validarExistenciaCLient(id);
    }

    private void validarExistenciaCLient(Long id) {
        if(!clientRepository.existsById(id)){
            throw new ResourceNotFoundException("Client com id"+ id + "não encontrado");
        }
    }
}
