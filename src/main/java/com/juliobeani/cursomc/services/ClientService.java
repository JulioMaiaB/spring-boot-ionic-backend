package com.juliobeani.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.juliobeani.cursomc.domain.Client;
import com.juliobeani.cursomc.domain.dto.ClientDTO;
import com.juliobeani.cursomc.repositories.ClientRepository;
import com.juliobeani.cursomc.services.exceptions.DataIntegrityException;
import com.juliobeani.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class ClientService {

	@Autowired
	private ClientRepository repository;

	public List<Client> findAll() {
		return repository.findAll();
	}

	public Client findById(Integer id) {
		Optional<Client> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Client.class.getName()));
	}

//	public Client insert(Client obj) {
//		obj.setId(null); // Garante que estou inserindo um objeto novo
//		return repository.save(obj);
//	}

	public Client update(Client obj) {
		findById(obj.getId()); // Apenas para verificar se o id do objeto nao e nulo
		Client newObj = findById(obj.getId());
		updateData(newObj, obj);
		return repository.save(newObj); // Como o id nao e nulo, e feita uma atualizacao de forma automatica
	}

	public void delete(Integer id) {
		findById(id);
		try {
			repository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("It is not possible to delete because there are related entities!");
		}
	}

	public Page<Client> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repository.findAll(pageRequest);
	}

	public Client fromDTO(ClientDTO obj) {
		return new Client(obj.getId(), obj.getName(), obj.getEmail(), null, null);
	}
	
	// Necessario para nao atualizar o CPF e o ClientType
	private void updateData(Client newObj, Client obj) {
		newObj.setName(obj.getName());
		newObj.setEmail(obj.getEmail());;
	}
}