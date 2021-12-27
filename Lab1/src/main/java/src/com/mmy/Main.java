package src.com.mmy;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws SQLException, IOException, ParseException {
	// write your code here
        DBHelper dbHelper = DBHelper.getInstance();
        DataBase dataBase = new DataBase(dbHelper);
        dataBase.initDataBase();
        Tools tools = new Tools(dbHelper);

        Scanner input = new Scanner(System.in);
        String csvName = null;
        String inputTableName = null;
        String operate = null;
        while (true){
            System.out.println("请输入需要导入数据库的csv文件：");
            System.out.print(">>");
            csvName = input.nextLine();
            System.out.println("请输入目标数据表名:");
            System.out.print(">>");
            inputTableName = input.nextLine();
            if(!dataBase.getTableMap().containsKey(inputTableName)){
                System.out.println("不存在这个数据表，请重新输入");
            }else{
                try {
                    tools.ReadCSV(dataBase.getTableMap().get(inputTableName), csvName);
                }catch (ErrorCode errorCode){
                    System.out.println(ErrorCode.getErrorText(errorCode.getErrorCode()));
//                    continue;
                }
            }
            System.out.println("是否继续？ Y/N");
            String nextStep = input.nextLine();
            if(!(nextStep.equals("Y")||nextStep.equals("y"))){
                break;
            }

        }
        dbHelper.close();
    }
}
