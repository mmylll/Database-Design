package pojo;

import dao.Course_AllocationDao;
import dao.EmployeeDao;
import org.apache.ibatis.session.SqlSession;
import utils.MybatisUtil;

import java.util.Date;
import java.util.List;

public class Employee {
    private String employee_id;
    private String employee_name;
    private int sex;
    private int age;
    private String induction_date;
    private String address;
    private String phone;
    private String email;
    private String department_name;
    private List<Course_Allocation> course_allocationList;

    public Employee(){}

    public Employee(String employee_id, String employee_name, int sex,
                    int age, String induction_date, String address,
                    String phone, String email, String department_name) {
        this.employee_id = employee_id;
        this.employee_name = employee_name;
        this.sex = sex;
        this.age = age;
        this.induction_date = induction_date;
        this.address = address;
        this.phone = phone;
        this.email = email;
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

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getInduction_date() {
        return induction_date;
    }

    public void setInduction_date(String induction_date) {
        this.induction_date = induction_date;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDepartment_name() {
        return department_name;
    }

    public void setDepartment_name(String department_name) {
        this.department_name = department_name;
    }

    public List<Course_Allocation> getCourse_allocationList() {
        return course_allocationList;
    }

    public void setCourse_allocationList(List<Course_Allocation> course_allocationList) {
        this.course_allocationList = course_allocationList;
    }

    // 更新员工信息
    public Employee updateEmployee(Employee employee){
        SqlSession session = MybatisUtil.getSqlSession();
        EmployeeDao employeeMapper = session.getMapper(EmployeeDao.class);
        Employee employee1 = employeeMapper.updateEmployee(employee);
        session.close();
        return employee1;
    }

    // 查看被分配到的课程以及教员信息
    public List<Course_Allocation> getCourseAllocation(int semester,Employee employee){
        SqlSession session = MybatisUtil.getSqlSession();
        Course_AllocationDao courseAllocationMapper = session.getMapper(Course_AllocationDao.class);
        List<Course_Allocation> course_allocations = courseAllocationMapper.getCourse_Allocation(semester,employee);
        session.close();
        return course_allocations;
    }

    // 查看自己的历史培训成绩信息
    public List<Course_Allocation> getHistoryGrade(int semester,Employee employee){
        SqlSession session = MybatisUtil.getSqlSession();
        Course_AllocationDao courseAllocationMapper = session.getMapper(Course_AllocationDao.class);
        List<Course_Allocation> course_allocations = courseAllocationMapper.getHistoryGrade(semester,employee);
        session.close();
        return course_allocations;
    }

}
