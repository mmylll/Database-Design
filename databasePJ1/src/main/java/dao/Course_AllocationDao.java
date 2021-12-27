package dao;
import pojo.Course_Allocation;

import java.util.List;

public interface Course_AllocationDao {
    // 根据员工名和课程名查询课程
    List<Course_Allocation> getCourse_AllocationByName(String employee_name);
    // 根据员工号和课程名查询课程
    List<Course_Allocation> getCourse_AllocationById(String employee_id);
    // 根据是否通过进行查询  ---- 只能查询本部门的
    List<Course_Allocation> getCourse_AllocationByPass(int pass, String department_name);
    // 根据课程名进行查询  ---- 只能查询本部门的
    List<Course_Allocation> getCourse_AllocationByCourseName(int course_id);
    // 插入 课程分配
    int add_course_allocation(Course_Allocation course_allocation);
}
