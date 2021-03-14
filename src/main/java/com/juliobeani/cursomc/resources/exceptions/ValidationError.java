package com.juliobeani.cursomc.resources.exceptions;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError {
	private static final long serialVersionUID = 1L;

	List<FieldMessage> list = new ArrayList<>();

	public ValidationError(Integer status, String msg, Instant timeStamp) {
		super(status, msg, timeStamp);
	}
	
	// O nome do metodo get que importa ao retornar o nome do campo no json, no caso retorna "errors"
	public List<FieldMessage> getErrors() {
		return list;
	}

	public void addError(String fieldName, String message) {
		list.add(new FieldMessage(fieldName, message));
	}
}