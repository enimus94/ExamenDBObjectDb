package util;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ObjectDBUtil {
    private static final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("data.odb");

    public ObjectDBUtil() {
    }

    public static EntityManagerFactory getEntityManagerFactory() {
        return entityManagerFactory;
    }
}
