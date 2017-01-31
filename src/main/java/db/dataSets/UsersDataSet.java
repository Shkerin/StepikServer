package db.dataSets;

/**
 * Users data set
 *
 * @author Vladimir Shkerin
 * @since 29.01.2017
 */
public class UsersDataSet {

    private long id;
    private String name;

    public UsersDataSet(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "UsersDataSet{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

}
