package dev.trifonov.order_service.entity;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "t_order")
@Accessors(chain = true)
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", nul)
    @Nullable
    private Long userId;

    @Column(name = "price")
    private Integer price;

    @Column(name = "date_time")
    private LocalDateTime dateTime;

    @OneToMany(mappedBy = "order")
    private List<OrderProduct> products;
}