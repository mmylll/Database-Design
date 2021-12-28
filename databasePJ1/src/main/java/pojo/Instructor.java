package pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * 教员信息
 * @TableName instructor
 */
public class Instructor implements Serializable {

    private String employee_id;
    private String employee_name;
    private Date teach_date;
    private static final long serialVersionUID = 1L;

    public Instructor(String employee_id, String employee_name, Date teach_date) {
        this.employee_id = employee_id;
        this.employee_name = employee_name;
        this.teach_date = teach_date;
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

    public Date getTeach_date() {
        return teach_date;
    }

    public void setTeach_date(Date teach_date) {
        this.teach_date = teach_date;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        Instructor other = (Instructor) that;
        return (this.getEmployee_id() == null ? other.getEmployee_id() == null : this.getEmployee_id().equals(other.getEmployee_id()))
            && (this.getEmployee_name() == null ? other.getEmployee_name() == null : this.getEmployee_name().equals(other.getEmployee_name()))
            && (this.getTeach_date() == null ? other.getTeach_date() == null : this.getTeach_date().equals(other.getTeach_date()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getEmployee_id() == null) ? 0 : getEmployee_id().hashCode());
        result = prime * result + ((getEmployee_name() == null) ? 0 : getEmployee_name().hashCode());
        result = prime * result + ((getTeach_date() == null) ? 0 : getTeach_date().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", employeeId=").append(employee_id);
        sb.append(", employeeName=").append(employee_name);
        sb.append(", teachDate=").append(teach_date);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}