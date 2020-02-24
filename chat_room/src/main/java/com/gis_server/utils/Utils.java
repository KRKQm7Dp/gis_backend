package com.gis_server.utils;

public class Utils {

    public static  byte booleanToByte(boolean b){
        return (byte) (b ? 0x01 : 0x00);
    }

    public static boolean byteToBoolean(byte b){
        return (b == 0x00) ? false : true;
    }

}
