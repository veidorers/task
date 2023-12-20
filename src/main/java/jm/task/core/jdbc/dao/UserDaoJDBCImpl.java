package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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

    private static final String GET_ALL_USERS_SQL = """
            SELECT id,
                name,
                last_name,
                age
            FROM users
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
        List<User> result = new ArrayList<>();
        try (var connection = Util.get();
             var preparedStatement = connection.prepareStatement(GET_ALL_USERS_SQL)) {
            var resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                result.add(new User(
                        resultSet.getLong("id"),
                        resultSet.getString("name"),
                        resultSet.getString("last_name"),
                        resultSet.getByte("age")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public void cleanUsersTable() {

    }
}
