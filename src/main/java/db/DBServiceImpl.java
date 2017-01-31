package db;

import base.DBService;
import db.dao.UsersDAO;
import db.dataSets.UsersDataSet;
import org.h2.jdbcx.JdbcDataSource;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Data base service
 *
 * @author Vladimir Shkerin
 * @since 29.01.2017
 */
public class DBServiceImpl implements DBService {

    private final Connection connection;

    public DBServiceImpl() {
        this.connection = getMysqlConnection();
    }

    public UsersDataSet getUser(long id) throws DBException {
        try {
            return (new UsersDAO(connection).get(id));
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }

    public UsersDataSet getUser(String login, String password) throws DBException {
        try {
            UsersDAO usersDAO = new UsersDAO(connection);
            long id = usersDAO.getUserId(login, password);
            return (new UsersDAO(connection).get(id));
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }

    public long setUser(String name, String password) throws DBException {
        try {
            connection.setAutoCommit(false);
            UsersDAO dao = new UsersDAO(connection);
            dao.insertUser(name, password);
            connection.commit();
            return dao.getUserId(name, password);
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ignore) {
            }
            throw new DBException(e);
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ignore) {
            }
        }
    }

    public void createDB() throws DBException {
        UsersDAO dao = new UsersDAO(connection);
        try {
            dao.createTable();
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }

    public void cleanUp() throws DBException {
        UsersDAO dao = new UsersDAO(connection);
        try {
            dao.dropTable();
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }

    public void printConnectInfo() {
        try {
            System.out.println("DB name: " + connection.getMetaData().getDatabaseProductName());
            System.out.println("DB version: " + connection.getMetaData().getDatabaseProductVersion());
            System.out.println("Driver: " + connection.getMetaData().getDriverName());
            System.out.println("Autocommit: " + connection.getAutoCommit());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static Connection getMysqlConnection() {
        try {
            DriverManager.registerDriver((Driver) Class.forName("com.mysql.jdbc.Driver").newInstance());

            StringBuilder url = new StringBuilder();

            url.append("jdbc:mysql://").        //db type
                    append("localhost:").       //host name
                    append("3306/").            //port
                    append("db_example?").      //db name
                    append("user=vlad&").       //login
                    append("password=vlad");    //password

            System.out.println("URL: " + url + "\n");

            Connection connection = DriverManager.getConnection(url.toString());
            return connection;
        } catch (SQLException | InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Connection getH2Connection() {
        try {
            String url = "jdbc:h2:./h2db";
            String name = "test";
            String pass = "test";

            JdbcDataSource ds = new JdbcDataSource();
            ds.setURL(url);
            ds.setUser(name);
            ds.setPassword(pass);

            return DriverManager.getConnection(url, name, pass);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
