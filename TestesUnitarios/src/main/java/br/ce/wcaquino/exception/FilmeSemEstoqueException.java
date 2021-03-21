package br.ce.wcaquino.exception;

public class FilmeSemEstoqueException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public FilmeSemEstoqueException(String msg) {
		super(msg);
	}

}
