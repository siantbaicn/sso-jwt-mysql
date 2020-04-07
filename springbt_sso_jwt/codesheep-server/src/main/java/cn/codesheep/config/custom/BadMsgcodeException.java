package cn.codesheep.config.custom;

import org.springframework.security.core.AuthenticationException;

public class BadMsgcodeException extends AuthenticationException {
	// ~ Constructors
	// ===================================================================================================

	/**
	 * Constructs a <code>BadCredentialsException</code> with the specified message.
	 *
	 * @param msg the detail message
	 */
	public BadMsgcodeException(String msg) {
		super(msg);
	}

	/**
	 * Constructs a <code>BadCredentialsException</code> with the specified message and
	 * root cause.
	 *
	 * @param msg the detail message
	 * @param t root cause
	 */
	public BadMsgcodeException(String msg, Throwable t) {
		super(msg, t);
	}
}