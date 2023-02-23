package org.saurav.simpletests.io;



/**
 * Exception is thrown in below cases.
 * 
 * <ul>
 * <li>When you try to read a non-exist xls sheet</li>
 * <li>When you try to read a non-exist xls file sheet with index</li>
 * <li>When you try to read a non-exist xls file sheet with name</li>
 * <li>When you try to read a non-exist column in the xls sheet</li>
 * <li>When you try to read a non-exist row in the xls sheet</li>
 * <li>When you try to create an xls sheet with name as null or empty</li>
 * </ul>
 * 
 * @author Saurav Sarkar
 * @version 1
 * @since 1
 * 
 * @see XLSReadUtils
 *
 */
public class XLSException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public XLSException(String message) {
		super(message);
	}

	public XLSException(Throwable t) {
		super(t);
	}
}
