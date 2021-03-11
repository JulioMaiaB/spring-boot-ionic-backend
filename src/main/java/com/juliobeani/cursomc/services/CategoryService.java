package com.juliobeani.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.juliobeani.cursomc.domain.Category;
import com.juliobeani.cursomc.repositories.CategoryRepository;
import com.juliobeani.cursomc.services.exceptions.DataIntegrityException;
import com.juliobeani.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class CategoryService {
	
	@Autowired
	private CategoryRepository repository;
	
	public List<Category> findAll() {
		return repository.findAll();
	}
	
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
		findById(id); // Apenas para verificar se o id do objeto nao e nulo
		return repository.save(obj); // Como o id nao e nulo, e feita uma atualizacao de forma automatica
	}

	public void delete(Integer id) {
		findById(id);
		try {
			repository.deleteById(id);
		}
		catch(DataIntegrityViolationException e) {
			throw new DataIntegrityException("It is not possible to delete a category with products!");
		}
	}
}