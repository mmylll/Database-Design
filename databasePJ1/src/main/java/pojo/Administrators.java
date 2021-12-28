package pojo;

import dao.EmployeeDao;
import org.apache.ibatis.session.SqlSession;
import utils.MybatisUtil;

public class Administrators {
    private String administrators_id;
    private String administrators_name;

    public Administrators(String administrators_id, String administrators_name) {
        this.administrators_id = administrators_id;
        this.administrators_name = administrators_name;
    }

    public String getAdministrators_id() {
        return administrators_id;
    }

    public void setAdministrators_id(String administrators_id) {
        this.administrators_id = administrators_id;
    }

    public String getAdministrators_name() {
        return administrators_name;
    }

    public void setAdministrators_name(String administrators_name) {
        this.administrators_name = administrators_name;
    }

    // 查看员工关联的所有信息
    public Employee getEmployeeCourseAllocation(String employee_id){
        SqlSession session = MybatisUtil.getSqlSession();
        EmployeeDao employeeMapper = session.getMapper(EmployeeDao.class);
        Employee employee1 = employeeMapper.getEmployeeCourseAllocation(employee_id);
        session.close();
        return employee1;
    }

    //
}
