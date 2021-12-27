package src.com.mmy;

import java.util.HashMap;
import java.util.Map;

public class ErrorCode extends RuntimeException {
    public static final int IO_EXCEPTION = 1;
    public static final int CSV_LOSS = 2;
    public static final int DIFFERENT_COLUMN_SCALE = 3;
    public static final int COLUMN_ERROR = 4;
    public static final int DATA_ROW_ERROR = 5;
    public static final int DATA_NOT_MATCH = 6;
 // ... and more
    public static final int UNKNOWN = 1000;
 
    private static final Map<Integer, String> ErrorCodeMap = new HashMap<>();
 
    static {
       ErrorCodeMap.put(IO_EXCEPTION, "IO exception");
       ErrorCodeMap.put(CSV_LOSS,"CSV file not found");
       ErrorCodeMap.put(DIFFERENT_COLUMN_SCALE,"The CSV file attribute scale is inconsistent with the database table. Please check and import again");
       ErrorCodeMap.put(DATA_NOT_MATCH,"The data does not match the header size, please check");
       //... and more
       ErrorCodeMap.put(UNKNOWN, "unknown");
    }
 
    public static String getErrorText(int errorCode) {
       return ErrorCodeMap.getOrDefault(errorCode, "invalid");
    }
 
    private int errorCode;
 
    public ErrorCode(int errorCode) {
       super(String.format("error code '%d' \"%s\"", errorCode, getErrorText(errorCode)));
       this.errorCode = errorCode;
    }

    public ErrorCode(int errorCode,String msg){
        this.errorCode = errorCode;
        ErrorCodeMap.put(errorCode,msg);
    }
 
    public int getErrorCode() {
 return errorCode;
 } }