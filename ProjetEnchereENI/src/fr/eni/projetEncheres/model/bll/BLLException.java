package fr.eni.projetEncheres.model.bll;

public class BLLException extends Exception {
	
	private static final long serialVersionUID = 1L;
	
	public BLLException() {
		super();
	}
	
	public BLLException(String message) {
		super(message);
	}
	
	public BLLException(String message, Throwable exception) {
		super(message, exception);
	}

	@Override
	public String getMessage() {
		StringBuffer sb = new StringBuffer("Problème au niveau BLL - ");
		sb.append(super.getMessage());
		
		return sb.toString() ;
	}

}
