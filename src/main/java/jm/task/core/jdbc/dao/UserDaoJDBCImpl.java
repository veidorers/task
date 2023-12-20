package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private static final String CREATE_USERS_TABLE_SQL = """
            CREATE TABLE IF NOT EXISTS users (
                id BIGINT AUTO_INCREMENT PRIMARY KEY ,
                name VARCHAR(128),
                last_name VARCHAR(128),
                age TINYINT
            );
            """;

    private static final String DROP_USERS_TABLE_SQL = """
            DROP TABLE IF EXISTS users;
            """;

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try (var connection = Util.get();
             var preparedStatement = connection.prepareStatement(CREATE_USERS_TABLE_SQL)) {
            preparedStatement.execute();
        } catch (SQLException e) {
            System.out.println("Table hasn't been created");
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        try (var connection = Util.get();
             var preparedStatement = connection.prepareStatement(DROP_USERS_TABLE_SQL)) {
            preparedStatement.execute();
        } catch (SQLException e) {
            System.out.println("Table hasn't been dropped");
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {

    }

    public void removeUserById(long id) {

    }

    public List<User> getAllUsers() {
        return null;
    }

    public void cleanUsersTable() {

    }
}
