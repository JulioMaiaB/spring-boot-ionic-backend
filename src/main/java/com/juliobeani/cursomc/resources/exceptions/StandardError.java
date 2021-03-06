package com.juliobeani.cursomc.resources.exceptions;

import java.io.Serializable;
import java.time.Instant;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class StandardError implements Serializable {
	private static final long serialVersionUID = 1L;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
	private Instant timestamp;
	private Integer status;
	private String msg;

	public StandardError(Integer status, String msg, Instant timeStamp) {
		this.status = status;
		this.msg = msg;
		this.timestamp = timeStamp;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	@JsonIgnore
	public Instant getTimeStamp() {
		return timestamp;
	}

	public void setTimeStamp(Instant timeStamp) {
		this.timestamp = timeStamp;
	}
}