package com.juliobeani.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.juliobeani.cursomc.domain.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
	
}