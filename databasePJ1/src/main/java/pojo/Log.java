package pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * 日志
 * @TableName log
 */
public class Log implements Serializable {

    private Integer log_id;
    private String username;
    private String operation;
    private Date operate_date;
    private static final long serialVersionUID = 1L;

    public Log(Integer log_id, String username, String operation, Date operate_date) {
        this.log_id = log_id;
        this.username = username;
        this.operation = operation;
        this.operate_date = operate_date;
    }

    public Integer getLog_id() {
        return log_id;
    }

    public void setLog_id(Integer log_id) {
        this.log_id = log_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public Date getOperate_date() {
        return operate_date;
    }

    public void setOperate_date(Date operate_date) {
        this.operate_date = operate_date;
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
        Log other = (Log) that;
        return (this.getLog_id() == null ? other.getLog_id() == null : this.getLog_id().equals(other.getLog_id()))
            && (this.getUsername() == null ? other.getUsername() == null : this.getUsername().equals(other.getUsername()))
            && (this.getOperation() == null ? other.getOperation() == null : this.getOperation().equals(other.getOperation()))
            && (this.getOperate_date() == null ? other.getOperate_date() == null : this.getOperate_date().equals(other.getOperate_date()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getLog_id() == null) ? 0 : getLog_id().hashCode());
        result = prime * result + ((getUsername() == null) ? 0 : getUsername().hashCode());
        result = prime * result + ((getOperation() == null) ? 0 : getOperation().hashCode());
        result = prime * result + ((getOperate_date() == null) ? 0 : getOperate_date().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", logId=").append(log_id);
        sb.append(", username=").append(username);
        sb.append(", operation=").append(operation);
        sb.append(", operateDate=").append(operate_date);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}