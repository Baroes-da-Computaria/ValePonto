package com.map.Vale.Ponto.model.payments;

import com.map.Vale.Ponto.controllers.error.BusinessException;
import com.map.Vale.Ponto.repositories.PointsRepository;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Component
public class CalcularValorCompra {

    private final PointsRepository pointsRepository;

    public CalcularValorCompra(PointsRepository pointsRepository) {
        this.pointsRepository = pointsRepository;
    }

    public BigDecimal execute(Long clientId, BigDecimal valor, Long pontosParaTrocar) {
        if (isValidPontosParaTrocar(valor, pontosParaTrocar)) return valor;
        existePontosSuficientes(clientId, pontosParaTrocar);
        return valor.subtract(BigDecimal.valueOf(pontosParaTrocar)).max(BigDecimal.ZERO);
    }

    private boolean isValidPontosParaTrocar(BigDecimal valor, Long pontosParaTrocar) {
        return pontosParaTrocar <= 0;
    }

    private void existePontosSuficientes(Long clientId, Long pontosParaTrocar) {
        Long clientPoints = pointsRepository.getPointsFromClientId(clientId, LocalDateTime.now());
        if(!pontosSuficientes(clientPoints, pontosParaTrocar)) {
            if(clientPoints == null) clientPoints = 0L;
            throw new BusinessException("Pontos para troca: " + pontosParaTrocar + " pontos, pontos cliente: " + clientPoints);
        }
    }
    private boolean pontosSuficientes(Long clientPoints, Long pontosParaTrocar) {
        if (clientPoints == null) {
            clientPoints = 0L;
        }
        return clientPoints >= pontosParaTrocar;
    }
}
