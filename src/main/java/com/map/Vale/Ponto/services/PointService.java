package com.map.Vale.Ponto.services;

import com.map.Vale.Ponto.controllers.error.ResourceNotFoundException;
import com.map.Vale.Ponto.model.client.Client;
import com.map.Vale.Ponto.model.order.Order;
import com.map.Vale.Ponto.model.order.OrderItem;
import com.map.Vale.Ponto.model.points.Points;
import com.map.Vale.Ponto.repositories.ClientRepository;
import com.map.Vale.Ponto.repositories.OrderRepository;
import com.map.Vale.Ponto.repositories.PointsRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class PointService {

    private final PointsRepository pointsRepository;
    private final ClientRepository clientRepository;
    private final OrderRepository orderRepository;

    public PointService(
            PointsRepository pointsRepository,
            ClientRepository clientRepository,
            OrderRepository orderRepository
    ) {
        this.pointsRepository = pointsRepository;
        this.clientRepository = clientRepository;
        this.orderRepository = orderRepository;
    }

    public void addPoints(Long clientId,Long order_id) {
        var client = clientRepository.findById(clientId).orElseThrow(() -> new ResourceNotFoundException("Client not found with id: " + clientId));
        var order = orderRepository.findById(order_id).orElseThrow(() -> new ResourceNotFoundException("Order not found with id: " + order_id));
        validar(client, order);
        var totalPontos = calculateTotalPoints(order);
        var points = new Points(client, totalPontos);
        pointsRepository.save(points);
    }

    public Page<Points> findAllByClientId(Long clientId, Pageable pageable) {
        var client = clientRepository.findById(clientId).orElseThrow(() -> new ResourceNotFoundException("Client not found with id: " + clientId));
        return pointsRepository.findAllByClientId(client.getId(), pageable);
    }

    private Long calculateTotalPoints(Order order) {
        long totalPoints = 0L;
        for (OrderItem item : order.getItems()) {
            totalPoints += item.getProduct().getPoints() * item.getQuantity();
        }
        return totalPoints;
    }

    private void validar(Client client, Order order) {
        if(!Objects.equals(order.getClient().getId(), client.getId())) {
            throw new ResourceNotFoundException("Client with id: " + client.getId() + " does not match the order's client id: " + order.getClient().getId());
        }
    }
}
