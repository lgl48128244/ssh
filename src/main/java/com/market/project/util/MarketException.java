package com.market.project.util;

public class MarketException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3426480281896465382L;

	public MarketException() {
		super();
	}

	public MarketException(String message, Throwable cause) {
		super(message, cause);
	}

	public MarketException(String message) {
		super(message);
	}

	public MarketException(Throwable cause) {
		super(cause);
	}
}
