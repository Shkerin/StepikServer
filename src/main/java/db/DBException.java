package db;

/**
 * Data base exception
 *
 * @author Vladimir Shkerin
 * @since 29.01.2017
 */
public class DBException extends Exception {
    public DBException(Throwable throwable) {
        super(throwable);
    }
}
