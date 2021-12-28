package run;
import pojo.Employee;
import pojo.Department_head;

public class test {
    public static void main(String[] arg){

        Department_head department_head = new Department_head("10231106004","王倩","策划部门");
        Employee employee =  department_head.getEmployeeByName("宋雪");
        System.out.println(employee.getEmployee_id());
    }
}
