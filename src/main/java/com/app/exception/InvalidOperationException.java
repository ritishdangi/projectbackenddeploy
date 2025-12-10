package com.app.exception;

public class InvalidOperationException extends RuntimeException {

	public InvalidOperationException() {
		super();
	}

	public InvalidOperationException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public InvalidOperationException(String message, Throwable cause) {
		super(message, cause);
	}

	public InvalidOperationException(String message) {
		super(message);
	}

	public InvalidOperationException(Throwable cause) {
		super(cause);
	}

	private static final long serialVersionUID = 1L;

}
