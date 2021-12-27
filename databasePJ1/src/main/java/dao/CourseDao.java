package dao;
import pojo.Course;
import java.util.List;
public interface CourseDao{
    // 根据部门名称查询该部门下所有课程
    List<Course> getCourseList(String teach_department);
    // 根据课程名与部门名称查询科学
    Course getCourseByNames(String course_name ,String teach_department);
}
