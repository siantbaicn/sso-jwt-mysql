package cn.codesheep.config.custom;

import org.springframework.security.core.AuthenticationException;

public class LoginTo4AException extends AuthenticationException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public LoginTo4AException(String msg, Throwable t) {
		super(msg, t);
		// TODO Auto-generated constructor stub
	}

	// ~ Constructors
	// ===================================================================================================

	/**
	 * Constructs a <code>UsernameNotFoundException</code> with the specified message.
	 *
	 * @param msg the detail message.
	 */
	public LoginTo4AException(String msg) {
		super(msg);
	}

	/**
	 * Constructs a {@code UsernameNotFoundException} with the specified message and root
	 * cause.
	 *
	 * @param msg the detail message.
	 * @param t root cause
	 */
}