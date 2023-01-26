package com.example.base.constants;

public class RspMsgConstants {
    private RspMsgConstants() {}
    public static final String DATA_AVAILABLE = "Data available";
    public static final String DATA_NOT_AVAILABLE = "Data not available";
    public static final String DATA_CANNOT_CHANGE = "Data cannot change";
    public static final String $_SUCCESSFULLY_REGISTERED = "_var successfully registered";
    public static final String $_SUCCESSFULLY_SAVE = "_var successfully save";
    public static final String SUCCESSFULLY_SAVE_$ = "Successfully save. Total data _var";
    public static final String $_SUCCESSFULLY_CHANGED = "_var successfully changed";
    public static String constructMessage(String param, String msgConstant) {
        return msgConstant.replaceAll("_var", param);
    }
}
