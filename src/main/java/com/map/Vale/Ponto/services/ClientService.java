package com.map.Vale.Ponto.services;

import com.map.Vale.Ponto.controllers.error.DataBaseException;
import com.map.Vale.Ponto.controllers.error.ResourceNotFoundException;
import com.map.Vale.Ponto.model.address.Address;
import com.map.Vale.Ponto.model.client.Client;
import com.map.Vale.Ponto.model.client.ClientRequestDTO;
import com.map.Vale.Ponto.model.client.ClientResponseDTO;
import com.map.Vale.Ponto.model.client.ClientWithAddressDTO;
import com.map.Vale.Ponto.repositories.AddressRepository;
import com.map.Vale.Ponto.repositories.ClientRepository;
import com.map.Vale.Ponto.validador.ValidadorAdicionarEnderecoDeClient;
import com.map.Vale.Ponto.validador.ValidadorAtualizarEnderecoDeClient;
import jakarta.transaction.Transactional;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class ClientService {

    private final ClientRepository clientRepository;
    private final AddressRepository addressRepository;
    private final ValidadorAdicionarEnderecoDeClient validadorAdicionarEndereco;
    private final ValidadorAtualizarEnderecoDeClient validadorAtualizarEndereco;


    public ClientService(
            ClientRepository clientRepository,
            ValidadorAdicionarEnderecoDeClient validadorAdicionarEndereco,
            ValidadorAtualizarEnderecoDeClient validadorAtualizarEndereco,
            AddressRepository addressRepository

    ) {
        this.clientRepository = clientRepository;
        this.validadorAdicionarEndereco = validadorAdicionarEndereco;
        this.validadorAtualizarEndereco = validadorAtualizarEndereco;
        this.addressRepository = addressRepository;
    }

    public ClientResponseDTO findById(Long id) {
        var client = clientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Client não encontrado com id: " + id));

        return new ClientResponseDTO(client);
    }

    public Page<ClientResponseDTO> findAll(PageRequest pageable) {
        return clientRepository.findAll(pageable).map(ClientResponseDTO::new);
    }

    public ClientResponseDTO save(ClientRequestDTO dto) {
        var client = new Client(dto);
        var saved = clientRepository.save(client);
        return new ClientResponseDTO(saved);
    }

    public ClientResponseDTO update(Long id, ClientRequestDTO dto) {
        var client = clientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Client não encontrado com id: " + id));

        client.updateFromRequest(dto);
        var updated = clientRepository.save(client);
        return new ClientResponseDTO(updated);
    }

    public void delete(Long id) {

        // verifica se esse client existe
        if (!clientRepository.existsById(id)) {
            throw new ResourceNotFoundException("Cliente não encontrado com id: " + id);
        }
        try {
            clientRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataBaseException("Falha de integridade referencial");
        }

    }

    @Transactional
    public void atualizarEndereco(Long id, Address address) {
        validadorAtualizarEndereco.validar(id, address);
        var client = clientRepository.getReferenceById(id);
        address.update(address);
        clientRepository.save(client);
    }

//    @Transactional
//    public void adicionarEndereco(Long id, Address address) {
//        validadorAdicionarEndereco.validar(id, address);
//        var client = clientRepository.getReferenceById(id);
//        var addressSaved = addressRepository.save(address);
//        client.setAddress(addressSaved);
//        addressSaved.setClient(client);
//        clientRepository.save(client);
//    }
    @Transactional
    public void adicionarEndereco(Long id, Address address) {
        validadorAdicionarEndereco.validar(id, address);
        var client = clientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        client.setAddress(address);
        address.setClient(client); // se for bidirecional

        clientRepository.save(client); // salva tudo de uma vez
    }


    public ClientWithAddressDTO getDetails(Long id) {
        var client = clientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Client não encontrado com id: "));

        return new ClientWithAddressDTO(client);
    }
}
