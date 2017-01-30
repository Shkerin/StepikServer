package base;

import db.DBException;
import db.dataSets.UsersDataSet;

/**
 * External interface for data base service
 *
 * @author Vladimir Shkerin
 * @since 30.01.2017
 */
public interface DBService {

    void printConnectInfo();

    long setUser(String login, String pass) throws DBException;

    UsersDataSet getUser(String login, String pass) throws DBException;

}
