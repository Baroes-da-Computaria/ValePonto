package com.map.Vale.Ponto.services;

import com.map.Vale.Ponto.controllers.error.DataBaseException;
import com.map.Vale.Ponto.controllers.error.ResourceNotFoundException;
import com.map.Vale.Ponto.model.address.Address;
import com.map.Vale.Ponto.model.address.AddressForClient;
import com.map.Vale.Ponto.model.address.AddressForOrder;
import com.map.Vale.Ponto.model.client.Client;
import com.map.Vale.Ponto.model.order.Order;
import com.map.Vale.Ponto.model.order.OrderBuilder;
import com.map.Vale.Ponto.model.order.OrderItem;
import com.map.Vale.Ponto.model.order.OrderResponseDTO;
import com.map.Vale.Ponto.model.product.Product;
import com.map.Vale.Ponto.repositories.AddressRepository;
import com.map.Vale.Ponto.repositories.ClientRepository;
import com.map.Vale.Ponto.repositories.OrderRepository;
import com.map.Vale.Ponto.repositories.ProductRepository;
import com.map.Vale.Ponto.validador.ValidadorCriacaoAddress;
import com.map.Vale.Ponto.validador.ValidadorCriacaoOrder;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final ClientRepository clientRepository;
    private final AddressRepository addressRepository;
    private final ProductRepository productRepository;
    private final ValidadorCriacaoOrder validadorCriacaoOrder;
    private final ValidadorCriacaoAddress validadorCriacaoAddress;

    public OrderService(
            OrderRepository orderRepository,
            ClientRepository clientRepository,
            ProductRepository productRepository,
            ValidadorCriacaoOrder validadorCriacaoOrder,
            AddressRepository addressRepository,
            ValidadorCriacaoAddress validadorCriacaoAddress
    ) {
        this.orderRepository = orderRepository;
        this.clientRepository = clientRepository;
        this.productRepository = productRepository;
        this.validadorCriacaoOrder = validadorCriacaoOrder;
        this.addressRepository = addressRepository;
        this.validadorCriacaoAddress = validadorCriacaoAddress;

    }

    @Transactional(readOnly = true)
    public OrderResponseDTO findById(Long id) {

        var Order = orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order não encontrado com id: " + id));

        return new OrderResponseDTO(Order);

    }

    @Transactional(readOnly = true)
    public Page<OrderResponseDTO> findAll(PageRequest pageable) {

        return orderRepository.findAll(pageable).map(OrderResponseDTO::new);

    }

    @Transactional
    public OrderResponseDTO create(Long clientId, AddressForClient address, Map<String, Integer> productIdToQuantity) {

        validadorCriacaoOrder.validar(clientId, productIdToQuantity);
        //validadorCriacaoAddress.validar(address);

        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new ResourceNotFoundException("Client not found"));

        Order order = new Order();
        order.setClient(client);
        var addressSaved = addressRepository.save(new Address(address));
        //order.setShippingAddress(addressSaved.getId());

        for (Map.Entry<String, Integer> entry : productIdToQuantity.entrySet()) {
            var key = Long.parseLong(entry.getKey());

            Product product = productRepository.findById(key)
                    .orElseThrow(() -> new ResourceNotFoundException("Product not found"));
            OrderItem item = new OrderItem();
            item.setOrder(order);
            item.setProduct(product);
            item.setQuantity(entry.getValue());

            order.getItems().add(item);
        }

        order.calculateTotal();
        Order saved = orderRepository.save(order);
        return new OrderResponseDTO(saved);
    }

    @Transactional
    public OrderResponseDTO createBuilder(Long clientId, AddressForOrder address, Map<String, Integer> productIdToQuantity) {

        // validate the orderBuilder creation
        validadorCriacaoOrder.validar(clientId, productIdToQuantity);
        validadorCriacaoAddress.validar(address);

        // find the client by id
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new ResourceNotFoundException("Client não encontrado com id: " + clientId));

        // create a new orderBuilder using OrderBuilder
        OrderBuilder orderBuilder = new OrderBuilder();
        orderBuilder.withClient(client);
        var addressSaved = addressRepository.save(new Address(address));
        orderBuilder.withShippingAddress(addressSaved);

        productIdToQuantity.forEach((productId, quantity) -> {
            Long key = Long.parseLong(productId);
            Product product = productRepository.findById(key)
                    .orElseThrow(() -> new ResourceNotFoundException("Product não encontrado com id: " + key));
            orderBuilder.addItem(new OrderItem(product, quantity));
        });

        // build the order from the orderBuilder
        Order saved = orderRepository.save(orderBuilder.build());
        return new OrderResponseDTO(saved);
    }

    @Transactional
    public void delete(Long id) {

        if (!orderRepository.existsById(id)) {
            throw new ResourceNotFoundException("Order não encontrado com id: " + id);
        }
        try {
            orderRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataBaseException("Falha de integridade referencial");
        }

    }
}