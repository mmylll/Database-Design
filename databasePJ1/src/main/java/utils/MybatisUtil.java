package utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

//sqlSessionFactory工厂模式 sqlSession
public class MybatisUtil {
    private static SqlSessionFactory sqlSessionFactory;
    static {
        try {
            //使用mybatis的第一步：获取sqlSessionFactory对象，mybatis官网
            String resource = "mybatis-config.xml";
            InputStream inputStream = Resources.getResourceAsStream(resource);
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //有了sqlSessionFactory，我们就可以从中获得sessionSql的实例
    //sqlSession包含了面向数据库执行SQL命令所需的所有方法

    public static SqlSession getSqlSession() {
        SqlSession sqlSession=sqlSessionFactory.openSession();
        return sqlSession;
    }

}
