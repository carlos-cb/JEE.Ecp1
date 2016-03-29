package business.api.exceptions;

public class AlreadyExistTrainingIdException extends ApiException {



    /**
	 * 
	 */
	private static final long serialVersionUID = -6211717846289996116L;

	public static final String DESCRIPTION = "Ya existe el entrenamiento";

    public static final int CODE = 1;

    public AlreadyExistTrainingIdException() {
        this("");
    }

    public AlreadyExistTrainingIdException(String detail) {
        super(DESCRIPTION + ". " + detail, CODE);
    }

}
