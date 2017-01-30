package db.executor;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Result handler
 *
 * @author Vladimir Shkerin
 * @since 29.01.2017
 */
public interface ResultHandler<T> {
    T handle(ResultSet resultSet) throws SQLException;
}
