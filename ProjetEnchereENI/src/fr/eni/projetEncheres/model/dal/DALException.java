package fr.eni.projetEncheres.model.dal;


public class DALException extends Exception {

	private static final long serialVersionUID = 1L;

	public DALException() {
		super();
	}
	
	public DALException(String message) {
		super(message);
	}
	
	public DALException(String message, Throwable exception) {
		super(message, exception);
	}

	@Override
	public String getMessage() {
		StringBuffer sb = new StringBuffer("Niveau DAL - ");
		sb.append(super.getMessage());
		
		return sb.toString() ;
	}
	
	
}
