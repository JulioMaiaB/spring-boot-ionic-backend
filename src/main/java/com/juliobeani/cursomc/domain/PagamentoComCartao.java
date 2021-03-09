package com.juliobeani.cursomc.domain;

import javax.persistence.Entity;

import com.juliobeani.cursomc.domain.enums.PaymentStatus;

@Entity
public class PagamentoComCartao extends Payment{
	private static final long serialVersionUID = 1L;
	
	private Integer installmentsNumber;

	public PagamentoComCartao() {}

	public PagamentoComCartao(Integer id, PaymentStatus paymentStatus, Order order, Integer installmentsNumber) {
		super(id, paymentStatus, order);
		this.installmentsNumber = installmentsNumber;
	}

	public Integer getInstallmentsNumber() {
		return installmentsNumber;
	}

	public void setInstallmentsNumber(Integer installmentsNumber) {
		this.installmentsNumber = installmentsNumber;
	}
}