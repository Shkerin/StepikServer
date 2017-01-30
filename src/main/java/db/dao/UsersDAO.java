package db.dao;

import com.sun.istack.internal.Nullable;
import db.dataSets.UsersDataSet;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 * DAO users
 *
 * @author Vladimir Shkerin
 * @since 29.01.2017
 */
public class UsersDAO {

    private Session session;

    public UsersDAO(Session session) {
        this.session = session;
    }

    public UsersDataSet get(Long id) throws HibernateException {
        return (UsersDataSet) session.get(UsersDataSet.class, id);
    }

    @Nullable
    public Long getUserId(String name, String password) throws HibernateException {
        Criteria criteria = session.createCriteria(UsersDataSet.class);
        criteria.add(Restrictions.eq("name", name));
        criteria.add(Restrictions.eq("password", password));
        UsersDataSet dataSet = (UsersDataSet) criteria.uniqueResult();
        if (dataSet == null) {
            return null;
        }
        return dataSet.getId();
    }

    public long insertUser(String name, String password) {
        return (Long) session.save(new UsersDataSet(name, password));
    }

}
