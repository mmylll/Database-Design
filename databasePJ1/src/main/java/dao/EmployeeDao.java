package dao;
import pojo.Employee;
import java.util.List;
public interface EmployeeDao {
    // 查询该部门下所有员工
    List<Employee> getEmployeeList(String department_name);
    // 根据员工名查询员工
    Employee getEmployeeByName(String employee_name);
    // 根据员工号查询员工
    Employee getEmployeeById(String employee_id);
    // 员工自己修改自己的信息
    Employee updateEmployee(Employee employee);
    // 管理员根据用户id查看用户信息
    Employee getEmployeeCourseAllocation(String employee_id);
}
