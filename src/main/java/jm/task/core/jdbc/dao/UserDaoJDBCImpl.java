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

    private static final String SAVE_USER_SQL = """
            INSERT INTO users 
            SET name = ?,
                last_name = ?,
                age = ?
            """;

    private static final String REMOVE_USER_BY_ID_SQL = """
            DELETE FROM users 
            WHERE id = ?
            """;

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try (var connection = Util.get();
             var preparedStatement = connection.prepareStatement(CREATE_USERS_TABLE_SQL)) {
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        try (var connection = Util.get();
             var preparedStatement = connection.prepareStatement(DROP_USERS_TABLE_SQL)) {
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try (var connection = Util.get();
             var preparedStatement = connection.prepareStatement(SAVE_USER_SQL)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        try (var connection = Util.get();
             var preparedStatement = connection.prepareStatement(REMOVE_USER_BY_ID_SQL)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        return null;
    }

    public void cleanUsersTable() {

    }
}
