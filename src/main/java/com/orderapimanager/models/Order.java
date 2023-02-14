package com.orderapimanager.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity(name = "TB_ORDER")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime createdAt;

    @ManyToMany
    @JoinColumn(name = "item_id", nullable = false)
    private List<Item> item;

    private Integer quantidade;

    @ManyToMany
    @JoinColumn(name = "user_id", nullable = false)
    private List<User> user;
}
