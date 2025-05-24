package com.map.Vale.Ponto.repositories;

import com.map.Vale.Ponto.model.client.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
}