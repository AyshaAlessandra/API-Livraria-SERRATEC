package com.residencia.academia.exeption;

public class NoSuchElementFoundException extends RuntimeException {
	public NoSuchElementFoundException(String message) {
		super(message);
	}
}