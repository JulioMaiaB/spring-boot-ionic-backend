package com.juliobeani.cursomc.domain;

import java.util.Date;

import javax.persistence.Entity;

import com.juliobeani.cursomc.domain.enums.PaymentStatus;

@Entity
public class PagamentoComBoleto extends Payment{
	private static final long serialVersionUID = 1L;
	
	private Date dueDate;
	private Date paymentDate;
	
	public PagamentoComBoleto () {}

	public PagamentoComBoleto(Integer id, PaymentStatus paymentStatus, Order order, Date dueDate, Date paymentDate) {
		super(id, paymentStatus, order);
		this.dueDate = dueDate;
		this.paymentDate = paymentDate;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public Date getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}
}