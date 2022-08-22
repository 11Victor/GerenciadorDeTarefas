package br.com.manager.tasks.security.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class ResponseMessage implements Serializable {
	private static final long serialVersionUID = 1L;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime timestamp;
	private HttpStatus status;

	@JsonInclude(Include.NON_NULL)
	private Object data;

	@JsonInclude(Include.NON_NULL)
	private String error;

	public ResponseMessage(HttpStatus status, Object data) {
		this.timestamp = LocalDateTime.now();
		this.status = status;
		this.data = data;
	}

	public ResponseMessage(HttpStatus status, String error) {
		this.timestamp = LocalDateTime.now();
		this.status = status;
		this.error = error;
	}

	public static ResponseMessage success(HttpStatus status, Object data) {
		return new ResponseMessage(status, data);
	}

	public static ResponseMessage error(HttpStatus status, String error) {
		return new ResponseMessage(status, error);
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}

	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}
}