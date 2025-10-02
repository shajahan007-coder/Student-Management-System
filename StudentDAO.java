import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {

  private final String jdbcURL = 
    "jdbc:mysql://localhost:3306/student_db?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    private final String jdbcUsername = "your root name";
    private final String jdbcPassword = "your root password";

    // 1️⃣ Get a database connection
    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
    }

    // 2️⃣ Add student
    public void addStudent(Student student) {
        String sql = "INSERT INTO students (name, email, course, roll_number, section) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, student.getName());
            stmt.setString(2, student.getEmail());
            stmt.setString(3, student.getCourse());
            stmt.setString(4, student.getRollNumber());
            stmt.setString(5, student.getSection());

            int rows = stmt.executeUpdate();
            if (rows > 0) System.out.println("✅ Student added successfully!");

        } catch (SQLException e) {
            System.err.println("❌ Error adding student: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // 3️⃣ View all students
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
                        rs.getString("course"),
                        rs.getString("roll_number"),
                        rs.getString("section")
                ));
            }

        } catch (SQLException e) {
            System.err.println("❌ Error fetching students: " + e.getMessage());
            e.printStackTrace();
        }

        return list;
    }

    // 4️⃣ Update student
    public void updateStudent(Student student) {
        String sql = "UPDATE students SET name=?, email=?, course=?, roll_number=?, section=? WHERE id=?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, student.getName());
            stmt.setString(2, student.getEmail());
            stmt.setString(3, student.getCourse());
            stmt.setString(4, student.getRollNumber());
            stmt.setString(5, student.getSection());
            stmt.setInt(6, student.getId());

            int rows = stmt.executeUpdate();
            if (rows > 0) System.out.println("✅ Student updated successfully!");
            else System.out.println("⚠️ Student not found!");

        } catch (SQLException e) {
            System.err.println("❌ Error updating student: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // 5️⃣ Delete student
    public void deleteStudent(int id) {
        String sql = "DELETE FROM students WHERE id=?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            int rows = stmt.executeUpdate();
            if (rows > 0) System.out.println("🗑️ Student deleted successfully!");
            else System.out.println("⚠️ Student not found!");

        } catch (SQLException e) {
            System.err.println("❌ Error deleting student: " + e.getMessage());
            e.printStackTrace();
        }
    }
}


