package com.orderapimanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockMovement extends JpaRepository<StockMovement, Long> {
}
