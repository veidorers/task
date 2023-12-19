package jm.task.core.jdbc;

import jm.task.core.jdbc.util.Util;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        try (var connection = Util.get();
             var statement = connection.createStatement()) {
            String sql = """
                    INSERT INTO test(data) 
                    VALUES
                    ('test!')
                    """;

            statement.executeUpdate(sql);
        }
        Util.closePool();

    }
}
