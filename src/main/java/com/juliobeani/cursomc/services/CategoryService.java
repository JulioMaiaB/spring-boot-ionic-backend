package com.juliobeani.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.juliobeani.cursomc.domain.Category;
import com.juliobeani.cursomc.repositories.CategoryRepository;
import com.juliobeani.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class CategoryService {
	
	@Autowired
	private CategoryRepository repository;
	
	public Category findById(Integer id) {
		Optional <Category> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Category.class.getName()));
	}
	
	public Category insert(Category obj) {
		obj.setId(null); // Garante que estou inserindo um objeto novo
		return repository.save(obj);
	}
	
	public Category update(Category obj, Integer id) {
		findById(id);
		return repository.save(obj);
	}
}