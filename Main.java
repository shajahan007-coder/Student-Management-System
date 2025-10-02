import java.util.*;

public class Main {

    public static void main(String[] args) {
        StudentDAO dao = new StudentDAO();

        try (Scanner sc = new Scanner(System.in)) {
            int choice;

            do {
                System.out.println("\n===== Student Management System =====");
                System.out.println("1. Add Student");
                System.out.println("2. View Students");
                System.out.println("3. Update Student");
                System.out.println("4. Delete Student");
                System.out.println("5. Exit");
                System.out.print("Enter choice: ");
                choice = sc.nextInt();
                sc.nextLine(); // consume newline

                switch (choice) {
                    case 1 -> {
                        System.out.print("Enter Name: ");
                        String name = sc.nextLine();
                        System.out.print("Enter Email: ");
                        String email = sc.nextLine();
                        System.out.print("Enter Course: ");
                        String course = sc.nextLine();
                        System.out.print("Enter Roll Number: ");
                        String roll = sc.nextLine();
                        System.out.print("Enter Section: ");
                        String section = sc.nextLine();

                        dao.addStudent(new Student(0, name, email, course, roll, section));
                        System.out.println("✅ Student added successfully!");
                    }

                    case 2 -> {
                        List<Student> students = dao.getAllStudents();

                        if (students.isEmpty()) {
                            System.out.println("No students found.");
                        } else {
                            // Print header
                            System.out.println("+----+-----------------------+-----------------------+------------+-------------+--------+");
                            System.out.printf("| %-2s | %-21s | %-21s | %-10s | %-11s | %-6s |\n",
                                    "ID", "Name", "Email", "Course", "Roll No", "Section");
                            System.out.println("+----+-----------------------+-----------------------+------------+-------------+--------+");

                            // Print each student
                            for (Student s : students) {
                                System.out.printf("| %-2d | %-21s | %-21s | %-10s | %-11s | %-6s |\n",
                                        s.getId(), s.getName(), s.getEmail(), s.getCourse(), s.getRollNumber(), s.getSection());
                            }

                            // Print footer
                            System.out.println("+----+-----------------------+-----------------------+------------+-------------+--------+");
                        }
                    }

                    case 3 -> {
                        System.out.print("Enter ID to update: ");
                        int idU = sc.nextInt();
                        sc.nextLine();
                        System.out.print("Enter New Name: ");
                        String newName = sc.nextLine();
                        System.out.print("Enter New Email: ");
                        String newEmail = sc.nextLine();
                        System.out.print("Enter New Course: ");
                        String newCourse = sc.nextLine();
                        System.out.print("Enter New Roll Number: ");
                        String newRoll = sc.nextLine();
                        System.out.print("Enter New Section: ");
                        String newSection = sc.nextLine();

                        dao.updateStudent(new Student(idU, newName, newEmail, newCourse, newRoll, newSection));
                        System.out.println("✅ Student updated successfully!");
                    }

                    case 4 -> {
                        System.out.print("Enter ID to delete: ");
                        int idD = sc.nextInt();
                        dao.deleteStudent(idD);
                    }

                    case 5 ->
                        System.out.println("Exiting...");
                    default ->
                        System.out.println(" Invalid choice!");
                }

            } while (choice != 5);
        }
    }
}

