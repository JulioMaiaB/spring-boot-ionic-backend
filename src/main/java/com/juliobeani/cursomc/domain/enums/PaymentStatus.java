package com.juliobeani.cursomc.domain.enums;

public enum PaymentStatus {
	
	PENDENTE(1, "Pendente"),
	QUITADO(2, "Quitado"),
	CANCELADO(3, "Cancelado");
	
	private int code;
	private String description;
	
	private PaymentStatus(int code, String description) {
		this.code = code;
		this.description = description;
	}

	public int getCode() {
		return code;
	}

	public String getDescription() {
		return description;
	}
	
	// Precisa ser estático para nao precisar instanciar objetos
	public static PaymentStatus toEnum(Integer code) {
		if(code == null) {
			return null;
		}
		
		for (PaymentStatus x : PaymentStatus.values()) {
			if(code.equals(x.getCode())) {
				return x;
			}
		}
		
		throw new IllegalArgumentException("Invalid ID: " + code);
	}
}