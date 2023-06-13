import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.ZoneOffset;

public class UserRepository {
    private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "postgres";

    private static final String FIND_BY_ID_QUERY = """
        SELECT * FROM users WHERE id = ?
    """;

    private static final String SAVE = """
        INSERT INTO users (name, surname, age, username, password, inserted_date_at_utc)
        VALUES (?, ?, ?, ?, ?, ?);
    """;

    public User findById(Long id) throws SQLException {
        try(
            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID_QUERY)
        ) {
            preparedStatement.setLong(1, id);
            try(ResultSet resultSet = preparedStatement.executeQuery()) {
                User user = new User();
                while (resultSet.next()) {
                    user.setId(resultSet.getLong("id"));
                    user.setUserName(resultSet.getString("username"));
                }
                return user;
            }
        }
    }

    public void save(User user) throws SQLException {
        try(
            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            PreparedStatement preparedStatement = connection.prepareStatement(SAVE)
        ) {
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getSurname());
            preparedStatement.setLong(3, user.getAge());
            preparedStatement.setString(4, user.getUserName());
            preparedStatement.setString(5, user.getPassword());
            preparedStatement.setTimestamp(6, new Timestamp(user.getInsertedAtUtc().toInstant(ZoneOffset.UTC).toEpochMilli()));

            preparedStatement.execute();
        }
    }
}
