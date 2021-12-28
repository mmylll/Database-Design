package pojo;

import org.apache.ibatis.session.SqlSession;
import pojo.*;
import utils.MybatisUtil;
import java.util.List;
import java.util.regex.Pattern;

import dao.*;
/*存储用户主管*/
public class Department_head {
    private String employee_id;
    private String employee_name;
    private String department_manage;

    private static Pattern pattern = Pattern.compile("[0-9]*");

    public Department_head(){}

    public Department_head(String employee_id, String employee_name, String department_manage) {
        this.employee_id = employee_id;
        this.employee_name = employee_name;
        this.department_manage = department_manage;
    }

    public void setEmployee_id(String employee_id) {
        this.employee_id = employee_id;
    }

    public void setEmployee_name(String employee_name) {
        this.employee_name = employee_name;
    }

    public void setDepartment_manage(String department_manage) {
        this.department_manage = department_manage;
    }

    public String getDepartment_manage() {
        return department_manage;
    }

    public String getEmployee_id() {
        return employee_id;
    }

    public String getEmployee_name() {
        return employee_name;
    }

    //todo
    // 获取部门下的员工信息
    public List<Employee> getEmployees(){
        List<Employee> employees;
        SqlSession session = MybatisUtil.getSqlSession();
        EmployeeDao employeeMapper = session.getMapper(EmployeeDao.class);
        employees = employeeMapper.getEmployeeList(department_manage);
        session.close();
        return employees;
    }
    public Employee getEmployeeByName(String employee_name){
        Employee employee;
        SqlSession session = MybatisUtil.getSqlSession();
        EmployeeDao employeeMapper = session.getMapper(EmployeeDao.class);
        employee = employeeMapper.getEmployeeByName(employee_name);
        return employee;
    }
    private Employee getEmployeeById(String employee_id){
        Employee employee;
        SqlSession session = MybatisUtil.getSqlSession();
        EmployeeDao employeeMapper = session.getMapper(EmployeeDao.class);
        employee = employeeMapper.getEmployeeById(employee_id);
        return employee;
    }
    // 获取课程信息
    public List<Course> getCourses(){
        List<Course> courses;
        SqlSession session = MybatisUtil.getSqlSession();
        CourseDao courseMapper = session.getMapper(CourseDao.class);
        courses = courseMapper.getCourseList(department_manage);
        session.close();
        return courses;
    }

    // 只能查询该部门下的课程
    private Course getCourseByNames(String course_name){
        Course course;
        SqlSession session = MybatisUtil.getSqlSession();
        CourseDao courseMapper = session.getMapper(CourseDao.class);
        course = courseMapper.getCourseByNames(course_name, department_manage);
        session.close();
        return course;
    }

    // 更具员工姓名查询课程
    private List<Course_Allocation> getCourse_AllocationByName(String employee_name){
        List<Course_Allocation> course_allocations;
        SqlSession session = MybatisUtil.getSqlSession();
        Course_AllocationDao courseMapper = session.getMapper(Course_AllocationDao.class);
        course_allocations = courseMapper.getCourse_AllocationByName(employee_name);
        session.close();
        return course_allocations;
    }
    // 根据员工号查询课程
    private List<Course_Allocation> getCourse_AllocationById(String employee_id){
        List<Course_Allocation> course_allocations;
        SqlSession session = MybatisUtil.getSqlSession();
        Course_AllocationDao courseMapper = session.getMapper(Course_AllocationDao.class);
        course_allocations = courseMapper.getCourse_AllocationById(employee_id);
        session.close();
        return course_allocations;
    }

    // 根据姓名或员工号为员工分配培训课程。which 1表示姓名，否则员工号
    public boolean course_allocation(String employee_info,String course_name){
        boolean isTrue = false;
        List<Course_Allocation> course_allocations;
        Employee employee ;
        Course course = getCourseByNames(course_name);
        // 说明是员工号
        if (pattern.matcher(employee_info).matches()){
            employee = getEmployeeById(employee_info);
            course_allocations = getCourse_AllocationById(employee_info);
        } else {
            employee = getEmployeeByName(employee_name);
            course_allocations = getCourse_AllocationByName(employee_info);
        }
       if (employee==null||course==null||course.getType().equals("必修")){
           System.out.println("操作错误，不能插入");
           return false;
       }
       for (int i = 0;i<course_allocations.size();i++){
           if (course_allocations.get(i).getCourse_id() == course.getCourse_id()){
               System.out.println("已经有该课程，不能再添加");
               return false;
           }
       }
        //开始插入
        SqlSession session = MybatisUtil.getSqlSession();
        Course_AllocationDao courseMapper = session.getMapper(Course_AllocationDao.class);
        // todo 这里的学期要如何获取
        Course_Allocation add = new Course_Allocation(employee.getEmployee_id(), employee.getEmployee_name(),
                course.getInstructor_id(),course.getCourse_id(),"2021-1-1",0,0,
                0,course.getTeach_department());
        int end = courseMapper.add_course_allocation(add);

        session.commit();
        session.close();
        if (end > 0){
            System.out.println("主管-插入成功");
            return true;
        }else {
            System.out.println("主管-插入失败");
            return false;
        }

    }

    //todo 根据员工姓名或者员工号转移新部门
    public boolean transferDep(String employee_info, String department_name){
        return true;
    }


    // 根据是否通过进行查询  ---- 只能查询本部门的
    public List<Course_Allocation> getCourse_AllocationByPass(int pass){
        List<Course_Allocation> course_allocations;
        SqlSession session = MybatisUtil.getSqlSession();
        Course_AllocationDao courseMapper = session.getMapper(Course_AllocationDao.class);
        course_allocations = courseMapper.getCourse_AllocationByPass(pass, department_manage);
        session.close();
        return course_allocations;
    }

    // 根据课程名进行查询  ---- 只能查询本部门的
    public List<Course_Allocation> getCourse_AllocationByCourseName(String course_name){
        Course course = getCourseByNames(course_name);
        if (course==null){
            System.out.println("查询失败，只能查询本部门下的课程信息");
            return null;
        }
        List<Course_Allocation> course_allocations;
        SqlSession session = MybatisUtil.getSqlSession();
        Course_AllocationDao courseMapper = session.getMapper(Course_AllocationDao.class);
        course_allocations = courseMapper.getCourse_AllocationByCourseName(course.getCourse_id());
        session.close();
        return course_allocations;
    }
}
