
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
                        dao.addStudent(new Student(name, email, course));
                    }
                    case 2 -> {
                        List<Student> students = dao.getAllStudents();

                        if (students.isEmpty()) {
                            System.out.println("No students found.");
                        } else {
                            // Print header
                            System.out.println("+----+----------------+-------------------------+----------------+");
                            System.out.printf("| %-2s | %-14s | %-23s | %-14s |\n", "ID", "Name", "Email", "Course");
                            System.out.println("+----+----------------+-------------------------+----------------+");

                            // Print each student
                            for (Student s : students) {
                                System.out.printf("| %-2d | %-14s | %-23s | %-14s |\n",
                                        s.getId(), s.getName(), s.getEmail(), s.getCourse());
                            }

                            // Print footer
                            System.out.println("+----+----------------+-------------------------+----------------+");
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
                        dao.updateStudent(new Student(idU, newName, newEmail, newCourse));
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
