package com.map.Vale.Ponto.repositories;

import com.map.Vale.Ponto.model.client.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    // Verifica se já existe um cliente com o mesmo email, ignorando maiúsculas e minúsculas
    boolean existsByEmailIgnoreCase(String email);

    // Verifica se já existe um cliente com o mesmo CPF
    boolean existsByCpf(String cpf);

    // Verifica se já existe um cliente com o mesmo telefone
    boolean existsByTelefone(String telefone);

    // Verifica se já existe um cliente com o mesmo primeiro e último nome, ignorando maiúsculas e minúsculas
    boolean existsByFirstNameIgnoreCaseAndLastNameIgnoreCase(String firstName, String lastName);
}