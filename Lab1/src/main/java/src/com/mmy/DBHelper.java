package src.com.mmy;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

public class DBHelper {

    public Connection conn = null;
    public PreparedStatement pst = null;

    private volatile static DBHelper dBHelper;


    public static DBHelper getInstance() {
        if (dBHelper == null) {
            synchronized (DBHelper.class) {
                if (dBHelper == null) {
                    dBHelper = new DBHelper();
                }
            }
        }
        return dBHelper;
    }

    public DBHelper() {
        try {
            String name = "com.mysql.cj.jdbc.Driver";
            Class.forName(name);//指定连接类型
            String url = "jdbc:mysql://127.0.0.1/exam?useUnicode=true&characterEncoding=UTF-8&serverTimezone=GMT%2B8";
            String password = "122625mmy";
            String user = "root";
            conn = DriverManager.getConnection(url, user, password);//获取连接
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
    }

    public void executeSql(String sql) throws SQLException {
//        System.out.println(sql);
        pst = conn.prepareStatement(sql);//准备执行语句
        pst.executeUpdate();
    }

    public void executeSql(String sql, Map<String,Integer> attributeTypes, ArrayList<String> csvColumnNames,ArrayList<String> csvRowData,ArrayList<Integer> validCsvColumnNameIndex) throws SQLException, ParseException {
        pst = conn.prepareStatement(sql);//准备执行语句

        for(int i = 0;i < validCsvColumnNameIndex.size();i++){
            switch (attributeTypes.get(csvColumnNames.get(validCsvColumnNameIndex.get(i)))){
                case 1:
                    pst.setString(i+1,csvRowData.get(validCsvColumnNameIndex.get(i)));
                    break;
                case 2:
                    pst.setInt(i + 1, Integer.parseInt(csvRowData.get(validCsvColumnNameIndex.get(i))));
                    break;
                case 3:
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                    Date date = simpleDateFormat.parse(csvRowData.get(i));
                    Timestamp timestamp = new Timestamp(date.getTime());
                    pst.setTimestamp(i+1,timestamp);
                    break;
            }
        }
        pst.executeUpdate();
    }
 
    public void close() {  
        try {  
            this.conn.close();  
            this.pst.close();  
        } catch (SQLException e) {
            e.printStackTrace();  
        }  
    }
}