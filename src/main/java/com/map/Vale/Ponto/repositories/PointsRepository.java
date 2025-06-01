package com.map.Vale.Ponto.repositories;

import com.map.Vale.Ponto.model.points.Points;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.time.LocalDateTime;

public interface PointsRepository extends JpaRepository<Points, Long> {
    @Query("SELECT SUM(p.totalPoints) FROM Points p WHERE p.client.id = ?1 AND p.expirationDate >= ?2")
    Long getPointsFromClientId(Long clientId, LocalDateTime now);

}