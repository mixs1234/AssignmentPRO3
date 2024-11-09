package dk.via.shared.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;

@Component
public class DatabaseHelper<T> {

    private final DataSource dataSource;

    @Autowired
    public DatabaseHelper(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    protected Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    private PreparedStatement prepare(Connection connection, String sql, Object[] parameters) throws SQLException {
        PreparedStatement stat = connection.prepareStatement(sql);
        for (int i = 0; i < parameters.length; i++) {
            stat.setObject(i + 1, parameters[i]);
        }
        return stat;
    }

    public int executeUpdate(String sql, Object... parameters) throws SQLException {
        try (Connection connection = getConnection()) {
            PreparedStatement stat = prepare(connection, sql, parameters);
            return stat.executeUpdate();
        }
    }

    public T mapSingle(DataMapper<T> mapper, String sql, Object... parameters) throws SQLException {
        try (Connection connection = getConnection()) {
            PreparedStatement stat = prepare(connection, sql, parameters);
            ResultSet rs = stat.executeQuery();
            if (rs.next()) {
                return mapper.create(rs);
            } else {
                return null;
            }
        }
    }

    public List<T> map(DataMapper<T> mapper, String sql, Object... parameters) throws SQLException {
        try (Connection connection = getConnection()) {
            PreparedStatement stat = prepare(connection, sql, parameters);
            ResultSet rs = stat.executeQuery();
            LinkedList<T> allResults = new LinkedList<>();
            while (rs.next()) {
                allResults.add(mapper.create(rs));
            }
            return allResults;
        }
    }
}
