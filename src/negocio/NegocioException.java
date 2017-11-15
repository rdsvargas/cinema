package negocio;

/**
 *
 * @author lhries
 */
public class NegocioException extends Exception {

    /**
     *
     * @param s
     */
    public NegocioException(String s) {
        super(s);
    }

    /**
     *
     * @param s
     * @param throwable
     */
    public NegocioException(String s, Throwable throwable) {
        super(s, throwable);
    }

    /**
     *
     * @param throwable
     */
    public NegocioException(Throwable throwable) {
        super(throwable);
    }
}
