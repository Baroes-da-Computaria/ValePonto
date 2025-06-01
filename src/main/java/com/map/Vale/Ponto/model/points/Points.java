package com.map.Vale.Ponto.model.points;

import com.map.Vale.Ponto.model.client.Client;
import com.map.Vale.Ponto.model.order.Order;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Table(name = "tb_points")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Points {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @OneToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @Column(name = "total_points")
    private Long totalPoints;

    @Column(updatable = false, name = "created_date")
    @CreatedDate
    private LocalDateTime createdDate;

    @Column(name = "expirationd_date")
    private LocalDateTime expirationDate;

    public Points(Client client, Long totalPontos) {
        this.client = client;
        this.totalPoints = totalPontos;
    }

    @PrePersist
    public void calcularDataExpiracao() {
        if (createdDate != null) {
            expirationDate = createdDate.plusYears(1); // 1 ano após a criação
        }
    }
}
