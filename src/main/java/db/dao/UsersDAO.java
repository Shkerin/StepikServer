package db.dao;

import db.dataSets.UsersDataSet;
import db.executor.Executor;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * DAO user
 *
 * @author Vladimir Shkerin
 * @since 29.01.2017
 */
public class UsersDAO {

    private Executor executor;

    public UsersDAO(Connection connection) {
        this.executor = new Executor(connection);
    }

    public UsersDataSet get(long id) throws SQLException {
        String query = "SELECT * FROM users WHERE id=" + id;
        return executor.execQuery(query, result -> {
            result.next();
            return new UsersDataSet(result.getLong(1), result.getString(2));
        });
    }

    public long getUserId(String name, String password) throws SQLException {
        String query = "SELECT * FROM users WHERE login='" + name + "' AND password='" + password + "'";
        return executor.execQuery(query, result -> {
            result.next();
            return result.getLong(1);
        });
    }

    public long getUserId(String name) throws SQLException {
        return getUserId(name, "");
    }

    public void insertUser(String name, String password) throws SQLException {
        executor.execUpdate("INSERT INTO users (login, password) VALUES ('" + name + "', '" + password + "')");
    }

    public void insertUser(String name) throws SQLException {
        insertUser(name, "");
    }

    public void createTable() throws SQLException {
        executor.execUpdate("CREATE TABLE IF NOT EXISTS users (id BIGINT AUTO_INCREMENT, login VARCHAR(256), password VARCHAR(256), PRIMARY KEY (id))");
    }

    public void dropTable() throws SQLException {
        executor.execUpdate("DROP TABLE users");
    }

}
