package database;

import org.hibernate.Session;

public interface DatabaseConnector {
    public Session getSession();
}
