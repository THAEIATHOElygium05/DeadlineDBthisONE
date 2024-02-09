
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DeadlineDAO {
    private Connection connection;

    public DeadlineDAO() {
        try {
            connection = DatabaseConnection.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addDeadline(Deadline deadline) {
        String query = "INSERT INTO deadlinetable (title, description, due_date, status) VALUES (?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, deadline.getTitle());
            preparedStatement.setString(2, deadline.getDescription());
            preparedStatement.setDate(3, Date.valueOf(deadline.getDueDate()));
            preparedStatement.setString(4, deadline.getStatus());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to retrieve all deadlines
    public List<Deadline> getAllDeadlines() {
        List<Deadline> deadlines = new ArrayList<>();
        String query = "SELECT * FROM deadlinetable";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                String title = resultSet.getString("title");
                String description = resultSet.getString("description");
                LocalDate dueDate = resultSet.getDate("due_date").toLocalDate();
                String status = resultSet.getString("status");
                Deadline deadline = new Deadline(title, description, dueDate, status);
                deadlines.add(deadline);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return deadlines;
    }
}

