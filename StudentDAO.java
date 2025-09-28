import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {

    private final String jdbcURL = "jdbc:mysql://localhost:3306/student_db?useSSL=false&serverTimezone=UTC";
    private final String jdbcUsername = "root";   
    private final String jdbcPassword = "shahamysql404error";    

    // Get a database connection
    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
    }

    // Add student
    public void addStudent(Student student) {
        String sql = "INSERT INTO students (name, email, course) VALUES (?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, student.getName());
            stmt.setString(2, student.getEmail());
            stmt.setString(3, student.getCourse());

            int rows = stmt.executeUpdate();
            if (rows > 0) System.out.println(" Student added successfully!");

        } catch (SQLException e) {
            System.err.println(" Error adding student: " + e.getMessage());
            e.printStackTrace(); 
        }
    }

    // View all students
    public List<Student> getAllStudents() {
        List<Student> list = new ArrayList<>();
        String sql = "SELECT * FROM students";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                list.add(new Student(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("course")
                ));
            }

        } catch (SQLException e) {
            System.err.println(" Error fetching students: " + e.getMessage());
            e.printStackTrace(); 
        }
        return list;
    }

    // Update student
    public void updateStudent(Student student) {
        String sql = "UPDATE students SET name=?, email=?, course=? WHERE id=?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, student.getName());
            stmt.setString(2, student.getEmail());
            stmt.setString(3, student.getCourse());
            stmt.setInt(4, student.getId());

            int rows = stmt.executeUpdate();
            if (rows > 0) System.out.println(" Student updated successfully!");
            else System.out.println(" Student not found!");

        } catch (SQLException e) {
            System.err.println(" Error updating student: " + e.getMessage());
            e.printStackTrace(); 
        }
    }

    // Delete student
    public void deleteStudent(int id) {
        String sql = "DELETE FROM students WHERE id=?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            int rows = stmt.executeUpdate();
            if (rows > 0) System.out.println(" Student deleted successfully!");
            else System.out.println(" Student not found!");

        } catch (SQLException e) {
            System.err.println(" Error deleting student: " + e.getMessage());
            e.printStackTrace(); 
        }
    }
}
