public class Student {
    private int id;
    private String name;
    private String email;
    private String course;
    private String rollNumber;
    private String section;

    // Constructor for adding a new student
    public Student(String name, String email, String course, String rollNumber, String section) {
        this.name = name;
        this.email = email;
        this.course = course;
        this.rollNumber = rollNumber;
        this.section = section;
    }

    // Constructor for existing student (update/view)
    public Student(int id, String name, String email, String course, String rollNumber, String section) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.course = course;
        this.rollNumber = rollNumber;
        this.section = section;
    }

    // Getters and setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getCourse() { return course; }
    public void setCourse(String course) { this.course = course; }

    public String getRollNumber() { return rollNumber; }
    public void setRollNumber(String rollNumber) { this.rollNumber = rollNumber; }

    public String getSection() { return section; }
    public void setSection(String section) { this.section = section; }
}
