package com.map.Vale.Ponto.repositories;

import com.map.Vale.Ponto.model.points.Points;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PointsRepository extends JpaRepository<Points, Long> {
}