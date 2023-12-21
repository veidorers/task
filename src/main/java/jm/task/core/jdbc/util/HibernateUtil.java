package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.model.naming.CamelCaseToUnderscoresNamingStrategy;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.AvailableSettings;
import org.hibernate.service.ServiceRegistry;

import java.util.Properties;

public class HibernateUtil {
    private static final String URL = "jdbc:mysql://localhost:3306/kata";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "pass";
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DIALECT = "org.hibernate.dialect.MySQL8Dialect";
    private static volatile SessionFactory sessionFactory;

    public static SessionFactory buildSessionFactory() {
        SessionFactory localSessionFactory = sessionFactory;

        if (localSessionFactory == null || localSessionFactory.isClosed()) {
            synchronized (HibernateUtil.class) {
                var configuration = new Configuration();

                Properties settings = new Properties();

                settings.put(AvailableSettings.URL, URL);
                settings.put(AvailableSettings.USER, USERNAME);
                settings.put(AvailableSettings.PASS, PASSWORD);
                settings.put(AvailableSettings.DRIVER, DRIVER);
                settings.put(AvailableSettings.DIALECT, DIALECT);

                settings.put(AvailableSettings.SHOW_SQL, true);
                settings.put(AvailableSettings.CURRENT_SESSION_CONTEXT_CLASS, "thread");

                configuration.setProperties(settings);
                configuration.setPhysicalNamingStrategy(new CamelCaseToUnderscoresNamingStrategy());
                configuration.addAnnotatedClass(User.class);

                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties()).build();

                sessionFactory = localSessionFactory = configuration.buildSessionFactory(serviceRegistry);
            }
        }
        return localSessionFactory;
    }
}
