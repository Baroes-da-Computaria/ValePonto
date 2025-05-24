package com.map.Vale.Ponto.repositories;

import com.map.Vale.Ponto.model.company.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Long> {
}