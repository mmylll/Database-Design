package src.com.mmy;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import java.io.*;
import java.sql.SQLException;
import java.util.*;

public class DataBase {

    private final Map<String,Table> tableMap = new HashMap<>();
    private DBHelper dbHelper;
    private String fk;
    public DataBase(DBHelper dbHelper){
        this.dbHelper = dbHelper;
    }

    public Map<String, Table> getTableMap() {
        return tableMap;
    }

    public void initDataBase() throws SQLException {
        initTables();
        Tools tools = new Tools(dbHelper);
        for (Map.Entry<String, Table> entry : tableMap.entrySet()) {
            tools.createTable(entry.getValue());
        }
        tools.addFK(fk);
    }

    public String readJsonFile(String fileName) {
        String jsonStr = "";
        try {
            File jsonFile = new File(fileName);
            FileReader fileReader = new FileReader(jsonFile);

            Reader reader = new InputStreamReader(new FileInputStream(jsonFile),"utf-8");
            int ch = 0;
            StringBuffer sb = new StringBuffer();
            while ((ch = reader.read()) != -1) {
                sb.append((char) ch);
            }
            fileReader.close();
            reader.close();
            jsonStr = sb.toString();
            return jsonStr;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void initTables(){
        ArrayList<String> columnNames = new ArrayList<>();
        ArrayList<String> dataTypes = new ArrayList<>();
        Map<String,Integer> attributeTypes = new HashMap<>();
        String path = Objects.requireNonNull(Main.class.getClassLoader().getResource("database.json")).getPath();
        String s = readJsonFile(path);
        JSONObject jobj = JSON.parseObject(s);
        JSONArray tables = jobj.getJSONObject("exam").getJSONArray("tables");
        for(int i = 0;i < tables.size();i++){
            columnNames.clear();
            dataTypes.clear();
            attributeTypes.clear();
            JSONObject key = (JSONObject)tables.get(i);
            Table table = new Table(key.getString("tableName"),dbHelper);
            JSONArray jsonColumnName = key.getJSONArray("columnName");
            for(int j = 0;j < jsonColumnName.size();j++){
                columnNames.add((String) jsonColumnName.get(j));
            }
            table.setColumnNames(columnNames);
            JSONArray jsonAttributeType = key.getJSONArray("attributeType");
            for(int j = 0;j < jsonAttributeType.size();j++){
                attributeTypes.put(columnNames.get(j),(Integer) jsonAttributeType.get(j));
            }
            table.setAttributeTypes(attributeTypes);
            JSONArray jsonDataType = key.getJSONArray("dataType");
            for(int j = 0;j < jsonDataType.size();j++){
                dataTypes.add((String) jsonDataType.get(j));
            }
            table.setDataTypes(dataTypes);

            table.setPk((String) key.get("pk"));
            table.setComment(key.getString("comment"));
            tableMap.put(table.getTableName(),table);
        }
        fk = jobj.getJSONObject("exam").getString("fk");
    }

}
