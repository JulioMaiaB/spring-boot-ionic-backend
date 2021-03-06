package com.juliobeani.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.juliobeani.cursomc.domain.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
	
}
