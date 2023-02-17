package com.orderapimanager.repository;

import com.orderapimanager.models.Item;
import com.orderapimanager.models.StockMovement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StockMovementRepository extends JpaRepository<StockMovement, Long> {
    List<StockMovement> findByItemOrderByCreatedAtAsc(Item item);
}
