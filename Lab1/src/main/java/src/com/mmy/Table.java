package src.com.mmy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Table {
    private String tableName;
    private ArrayList<String> columnNames = new ArrayList<>();
    private ArrayList<String> dataTypes = new ArrayList<>();
    private Map<String,Integer> attributeTypes = new HashMap<>(); //1-->×Ö·û´® 2-->int 3-->date
    private String pk;
    private String comment;
    public DBHelper dbHelper;

    public Table(String tableName,DBHelper dbHelper) {
        this.tableName = tableName;
        this.dbHelper = dbHelper;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public ArrayList<String> getColumnNames() {
        return columnNames;
    }

    public void setColumnNames(ArrayList<String> columnNames) {
        this.columnNames = (ArrayList<String>) columnNames.clone();
    }

    public ArrayList<String> getDataTypes() {
        return dataTypes;
    }

    public void setDataTypes(ArrayList<String> dataTypes) {
        this.dataTypes = (ArrayList<String>) dataTypes.clone();
    }

    public Map<String, Integer> getAttributeTypes() {
        return attributeTypes;
    }

    public void setAttributeTypes(Map<String, Integer> attributeTypes) {
        this.attributeTypes.putAll(attributeTypes);
    }

    public String getPk() {
        return pk;
    }

    public void setPk(String pk) {
        this.pk = pk;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
