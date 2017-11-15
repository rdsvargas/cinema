package dao.db;

/**
 *
 * @author Roger
 */
public class BDException extends RuntimeException {

    /**
     *
     * @param s
     */
    public BDException(String s) {
        super(s);
    }

    /**
     *
     * @param s
     * @param throwable
     */
    public BDException(String s, Throwable throwable) {
        super(s, throwable);
    }

    /**
     *
     * @param throwable
     */
    public BDException(Throwable throwable) {
        super(throwable);
    }
}
