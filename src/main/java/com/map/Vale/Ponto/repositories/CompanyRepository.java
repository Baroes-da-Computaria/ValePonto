package com.map.Vale.Ponto.repositories;

import com.map.Vale.Ponto.model.company.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {
    boolean existsByName(String name);
    boolean existsByCnpj(String cnpj);
}