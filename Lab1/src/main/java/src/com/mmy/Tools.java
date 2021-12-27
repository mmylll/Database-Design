package src.com.mmy;

import java.io.*;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;

public class Tools {
    public DBHelper dbHelper;

    public Tools(DBHelper dbHelper) {
        this.dbHelper = dbHelper;
    }

    public void createTable(Table table) throws SQLException {
        StringBuilder sql = null;
        ArrayList<String> columnNames = table.getColumnNames();
        ArrayList<String> dataTypes = table.getDataTypes();
        sql = new StringBuilder("create table if not exists" + " " + table.getTableName() + "\n" +
                "(\n");
        for(int i = 0;i < columnNames.size();i++){
            sql.append("\t").append(columnNames.get(i)).append(" ").append(dataTypes.get(i)).append(",\n");
        }
        sql.append(table.getPk()).append(")\n").append("comment").append(" '").append(table.getComment()).append("';\n");
        dbHelper.executeSql(sql.toString());
    }

    public void ReadCSV(Table table,String csvName) throws IOException, SQLException, ParseException {
        ArrayList<String> csvRowData = new ArrayList<>();
        ArrayList<String> csvColumnNames = new ArrayList<>();
        ArrayList<Integer> validCsvColumnNameIndex = new ArrayList<>();
        String csvPath = null;
        try {
             csvPath = Objects.requireNonNull(Main.class.getClassLoader().getResource(csvName)).getPath();
        }catch (NullPointerException e){
             throw new ErrorCode(ErrorCode.CSV_LOSS);
        }
        File file = new File(csvPath);
//        String fileCharsetName = null;
//        if(file.getName().equals("student.csv")){
//            fileCharsetName = "UTF-8";
//        }else if(file.getName().equals("room.csv")) {
//            fileCharsetName = "GBK";
//        }else {
//            fileCharsetName = "UTF-8";
//        }
        InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream(file), "GBK");
        BufferedReader reader = new BufferedReader(inputStreamReader);
        String line = null;
        int lines = 1;
        String[] stringArray;
        while((line = reader.readLine()) != null){
            line = line.replaceAll("\"","");
            stringArray = line.split(",",-1);
            if (lines != 1){
                try {
                    if((stringArray.length != csvColumnNames.size())){
                        if((line.charAt(line.length()-1) == ',')){
                            if(stringArray.length != csvColumnNames.size() + 1){
                                throw new ErrorCode(ErrorCode.DATA_NOT_MATCH);
                            }
                        }else {
                            throw new ErrorCode(ErrorCode.DATA_NOT_MATCH);
                        }
                    }
                }catch (ErrorCode errorCode){
                    System.out.println("In" + " " + lines + " " +  "line" + " " + "of this csv. " + ErrorCode.getErrorText(errorCode.getErrorCode()));
                    lines++;
                    continue;
                }
            }
            if(lines == 1){
                csvColumnNames = new ArrayList<>(Arrays.asList(stringArray));
                validCsvColumnNameIndex = checkColumnName(csvColumnNames, table);
            }else{
                csvRowData.addAll(Arrays.asList(stringArray));
                if((csvRowData.size() == csvColumnNames.size() - 1)&&(line.charAt(line.length()-1) == ',')){
                    System.out.println(csvRowData.size());
                    System.out.println(csvColumnNames.size());
                    csvRowData.remove(csvRowData.size()-1);
                }

                insertData(table,csvColumnNames,csvRowData,lines,validCsvColumnNameIndex);
                csvRowData.clear();
            }
//            System.out.println("line"+lines);
            lines++;
        }

    }

    public void insertData(Table table,ArrayList<String> csvColumnNames,ArrayList<String> csvRowData,int lines,ArrayList<Integer> validCsvColumnNameIndex) throws SQLException, ParseException {
        StringBuilder sql = null;
        StringBuilder values = new StringBuilder("(");
        String tableName = table.getTableName();
        sql = new StringBuilder("insert into " + tableName + " (");
        for(int i = 0;i < validCsvColumnNameIndex.size();i++){
            if(i == validCsvColumnNameIndex.size() - 1){
                values.append("?)");
                sql.append(csvColumnNames.get(validCsvColumnNameIndex.get(i))).append(")");
            }else{
                values.append("?,");
                sql.append(csvColumnNames.get(validCsvColumnNameIndex.get(i))).append(",");
            }
        }
        sql.append(" ").append("values").append(" ").append(values).append(";");
        try {
            dbHelper.executeSql(sql.toString(), table.getAttributeTypes(),csvColumnNames,csvRowData,validCsvColumnNameIndex);
        }catch (SQLIntegrityConstraintViolationException e){
            System.out.println("In" + " " + lines + " " +  "line" + " " + "of this csv. " + e.getMessage());
        }catch (NumberFormatException numberFormatException){
            System.out.println("In" + " " + lines + " " +  "line" + " " + "of this csv. " + numberFormatException.getMessage()+". Request" + " " + "Integer");
        }catch (ParseException parseException){
            System.out.println("In" + " " + lines + " " +  "line" + " " + "of this csv. " + parseException.getMessage()+". Request" + " "  + "'yyyy-MM-dd HH:mm'");
        }
    }

    public void addFK(String fkSql) throws SQLException {
        try {
            dbHelper.executeSql(fkSql);
        }catch (SQLException sqlException){
            System.out.println("已成功初始化数据库");
        }
    }

    public ArrayList<Integer> checkColumnName(ArrayList<String> csvColumnName,Table table){
        ArrayList<Integer> validCsvColumnNameIndex = new ArrayList<>();
        ArrayList<String> dbColumnName = table.getColumnNames();
        if(csvColumnName.size() != dbColumnName.size()){
//            throw new ErrorCode(ErrorCode.DIFFERENT_COLUMN_SCALE);
            Scanner input = new Scanner(System.in);
            System.out.println(ErrorCode.getErrorText(ErrorCode.DIFFERENT_COLUMN_SCALE));
            System.out.println("你确定继续导入吗 Y/N");
            String operate = input.nextLine();
            if(!(operate.equals("Y")||operate.equals("y"))){
                throw new ErrorCode(ErrorCode.DIFFERENT_COLUMN_SCALE);
            }
        }
        for(int i = 0;i < dbColumnName.size();i++){
            if(csvColumnName.contains(dbColumnName.get(i))){
                validCsvColumnNameIndex.add(csvColumnName.indexOf(dbColumnName.get(i)));
            }else{
                String msg = "The csv" + " " +  "does not contain" + " " + "'" + dbColumnName.get(i) + "'" + " " + "column" + ". Unable to import. Please check";
                throw new ErrorCode(ErrorCode.COLUMN_ERROR,msg);
            }
        }
        return validCsvColumnNameIndex;
    }
}
