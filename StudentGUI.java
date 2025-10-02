import java.awt.*;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class StudentGUI extends JFrame {

    private JTextField nameField, emailField, courseField, rollField, sectionField;
    private JTable studentTable;
    private DefaultTableModel tableModel;
    private StudentDAO dao;

    public StudentGUI() {
        dao = new StudentDAO();

        // Frame settings
        setTitle("Student Management System");
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // 🔹 Input Panel
        JPanel inputPanel = new JPanel(new GridLayout(5, 2, 5, 5));
        inputPanel.setBorder(BorderFactory.createTitledBorder("Enter Student Details"));

        inputPanel.add(new JLabel("Name:"));
        nameField = new JTextField();
        inputPanel.add(nameField);

        inputPanel.add(new JLabel("Email:"));
        emailField = new JTextField();
        inputPanel.add(emailField);

        inputPanel.add(new JLabel("Course:"));
        courseField = new JTextField();
        inputPanel.add(courseField);

        inputPanel.add(new JLabel("Roll Number:"));
        rollField = new JTextField();
        inputPanel.add(rollField);

        inputPanel.add(new JLabel("Section:"));
        sectionField = new JTextField();
        inputPanel.add(sectionField);

        add(inputPanel, BorderLayout.NORTH);

        // 🔹 Button Panel
        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton addBtn = new JButton("Add Student");
        JButton viewBtn = new JButton("View Students");
        JButton updateBtn = new JButton("Update Student");
        JButton deleteBtn = new JButton("Delete Student");
        JButton exitBtn = new JButton("Exit");

        buttonPanel.add(addBtn);
        buttonPanel.add(viewBtn);
        buttonPanel.add(updateBtn);
        buttonPanel.add(deleteBtn);
        buttonPanel.add(exitBtn);

        add(buttonPanel, BorderLayout.CENTER);

        // 🔹 JTable for output
        String[] columns = {"ID", "Name", "Email", "Course", "Roll No", "Section"};
        tableModel = new DefaultTableModel(columns, 0);
        studentTable = new JTable(tableModel);
        JScrollPane tableScroll = new JScrollPane(studentTable);
        tableScroll.setBorder(BorderFactory.createTitledBorder("Student List"));
        add(tableScroll, BorderLayout.SOUTH);

        // 🔹 Button Actions
        addBtn.addActionListener(e -> addStudent());
        viewBtn.addActionListener(e -> viewStudents());
        updateBtn.addActionListener(e -> updateStudent());
        deleteBtn.addActionListener(e -> deleteStudent());
        exitBtn.addActionListener(e -> System.exit(0));

        setVisible(true);
    }

    // Add student
    private void addStudent() {
        String name = nameField.getText();
        String email = emailField.getText();
        String course = courseField.getText();
        String roll = rollField.getText();
        String section = sectionField.getText();

        if (name.isEmpty() || email.isEmpty() || course.isEmpty() || roll.isEmpty() || section.isEmpty()) {
            JOptionPane.showMessageDialog(this, "All fields are required!");
            return;
        }

        Student student = new Student(0, name, email, course, roll, section);
        dao.addStudent(student);
        JOptionPane.showMessageDialog(this, "✅ Student added successfully!");
        clearFields();
        viewStudents();
    }

    // View students in JTable
    private void viewStudents() {
        List<Student> students = dao.getAllStudents();
        tableModel.setRowCount(0); // Clear table

        for (Student s : students) {
            tableModel.addRow(new Object[]{
                    s.getId(), s.getName(), s.getEmail(), s.getCourse(), s.getRollNumber(), s.getSection()
            });
        }
    }

    // Update student
    private void updateStudent() {
        int selectedRow = studentTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a student from the table to update.");
            return;
        }

        int id = (int) tableModel.getValueAt(selectedRow, 0);

        JTextField nameField = new JTextField((String) tableModel.getValueAt(selectedRow, 1));
        JTextField emailField = new JTextField((String) tableModel.getValueAt(selectedRow, 2));
        JTextField courseField = new JTextField((String) tableModel.getValueAt(selectedRow, 3));
        JTextField rollField = new JTextField((String) tableModel.getValueAt(selectedRow, 4));
        JTextField sectionField = new JTextField((String) tableModel.getValueAt(selectedRow, 5));

        Object[] message = {
                "Name:", nameField,
                "Email:", emailField,
                "Course:", courseField,
                "Roll Number:", rollField,
                "Section:", sectionField
        };

        int option = JOptionPane.showConfirmDialog(this, message, "Update Student", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            Student student = new Student(id,
                    nameField.getText(),
                    emailField.getText(),
                    courseField.getText(),
                    rollField.getText(),
                    sectionField.getText());
            dao.updateStudent(student);
            JOptionPane.showMessageDialog(this, "✅ Student updated successfully!");
            viewStudents();
        }
    }

    // Delete student
    private void deleteStudent() {
        int selectedRow = studentTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a student from the table to delete.");
            return;
        }

        int id = (int) tableModel.getValueAt(selectedRow, 0);
        dao.deleteStudent(id);
        JOptionPane.showMessageDialog(this, "🗑️ Student deleted successfully!");
        viewStudents();
    }

    // Clear input fields
    private void clearFields() {
        this.nameField.setText("");
        this.emailField.setText("");
        this.courseField.setText("");
        this.rollField.setText("");
        this.sectionField.setText("");
    }

    public static void main(String[] args) {
        new StudentGUI();
    }
}
