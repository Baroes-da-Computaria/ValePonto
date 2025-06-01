package com.map.Vale.Ponto.validador;

import com.map.Vale.Ponto.controllers.error.EntityExistsException;
import com.map.Vale.Ponto.model.client.ClientRequestDTO;
import com.map.Vale.Ponto.repositories.ClientRepository;
import org.springframework.stereotype.Component;

@Component
public class ValidadorClient {

    private ClientRepository clientRepository;

    public ValidadorClient(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public void validar(ClientRequestDTO dto) {
        validarEmail(dto.getEmail());
        validarCpf(dto.getCpf());
        validarTelefone(dto.getTelefone());

    }

    private void validarTelefone(String telefone) {
        if (clientRepository.existsByTelefone(telefone)) {
            throw new EntityExistsException("Já existe um cliente cadastrado com esse telefone: " + telefone);
        }
    }

    private void validarCpf(String cpf) {
        if (clientRepository.existsByCpf(cpf)) {
            throw new EntityExistsException("Já existe um cliente cadastrado com esse CPF: " + cpf);
        }
    }

    private void validarEmail(String email) {
        if (clientRepository.existsByEmailIgnoreCase(email)) {
            throw new EntityExistsException("Já existe um cliente cadastrado com esse email: " + email);
        }
    }
}
