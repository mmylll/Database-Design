package pojo;

public class Course {
    private int course_id;
    private String course_name;
    private String instructor_id;
    private String type;
    private String content;
    private String teach_department;
    private int compulsory;

    public Course(){};

    public Course(int course_id, String course_name, String instructor_id,
                  String type, String content, String teach_department, int compulsory) {
        this.course_id = course_id;
        this.course_name = course_name;
        this.instructor_id = instructor_id;
        this.type = type;
        this.content = content;
        this.teach_department = teach_department;
        this.compulsory = compulsory;
    }

    public int getCourse_id() {
        return course_id;
    }

    public void setCourse_id(int course_id) {
        this.course_id = course_id;
    }

    public String getCourse_name() {
        return course_name;
    }

    public void setCourse_name(String course_name) {
        this.course_name = course_name;
    }

    public String getInstructor_id() {
        return instructor_id;
    }

    public void setInstructor_id(String instructor_id) {
        this.instructor_id = instructor_id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTeach_department() {
        return teach_department;
    }

    public void setTeach_department(String teach_department) {
        this.teach_department = teach_department;
    }

    public int getCompulsory() {
        return compulsory;
    }

    public void setCompulsory(int compulsory) {
        this.compulsory = compulsory;
    }
}
