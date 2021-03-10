package com.juliobeani.cursomc.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.juliobeani.cursomc.domain.enums.ClientType;

@Entity(name = "tb_client")
public class Client implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;
	private String email;
	private String cpfOrCnpj;
	private Integer clientType;
	
	@OneToMany(mappedBy = "client")
	private List<Address> addresses = new ArrayList<>();
	
	// Por ser uma entidade fraca, foi utilizado as duas anotacoes para criar uma entidade no banco de dados
	@ElementCollection
	@CollectionTable(name = "TELEPHONE")
	// Set nao aceita repeticoes
	private Set<String> phoneNumbers = new HashSet<>();
	
	@OneToMany(mappedBy = "client")
	@JsonIgnore
	private List<Order> orders = new ArrayList<>();
	
	public Client () {}

	public Client(Integer id, String name, String email, String cpfOrCnpj, ClientType clientType) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.cpfOrCnpj = cpfOrCnpj;
		this.clientType = clientType.getCode();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCpfOrCnpj() {
		return cpfOrCnpj;
	}

	public void setCpfOrCnpj(String cpfOrCnpj) {
		this.cpfOrCnpj = cpfOrCnpj;
	}

	public ClientType getClientType() {
		return ClientType.toEnum(clientType);
	}

	public void setClientType(ClientType clientType) {
		this.clientType = clientType.getCode();
	}
	
	public List<Address> getAddresses() {
		return addresses;
	}

	public Set<String> getPhoneNumbers() {
		return phoneNumbers;
	}
	
	public List<Order> getOrders() {
		return orders;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Client other = (Client) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}