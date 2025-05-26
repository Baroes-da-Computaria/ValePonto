package com.map.Vale.Ponto.repositories;

import com.map.Vale.Ponto.model.address.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}