package pojo;
public class Course_Allocation {
    private String employee_id;
    private String employee_name;
    private String instructor_id;
    private int course_id;
    private String semester;
    private int grade;
    private int class_ending = 0;
    private int pass;
    private String department_name;

    public Course_Allocation() {
    }

    public Course_Allocation(String employee_id, String employee_name, String instructor_id,
                             int course_id, String semester, int grade, int class_ending,
                             int pass, String department_name) {
        this.employee_id = employee_id;
        this.employee_name = employee_name;
        this.instructor_id = instructor_id;
        this.course_id = course_id;
        this.semester = semester;
        this.grade = grade;
        this.class_ending = class_ending;
        this.pass = pass;
        this.department_name = department_name;
    }

    public String getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(String employee_id) {
        this.employee_id = employee_id;
    }

    public String getEmployee_name() {
        return employee_name;
    }

    public void setEmployee_name(String employee_name) {
        this.employee_name = employee_name;
    }

    public String getInstructor_id() {
        return instructor_id;
    }

    public void setInstructor_id(String instructor_id) {
        this.instructor_id = instructor_id;
    }

    public int getCourse_id() {
        return course_id;
    }

    public void setCourse_id(int course_id) {
        this.course_id = course_id;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public int getClass_ending() {
        return class_ending;
    }

    public void setClass_ending(int class_ending) {
        this.class_ending = class_ending;
    }

    public int getPass() {
        return pass;
    }

    public void setPass(int pass) {
        this.pass = pass;
    }

    public String getDepartment_name() {
        return department_name;
    }

    public void setDepartment_name(String department_name) {
        this.department_name = department_name;
    }
}
