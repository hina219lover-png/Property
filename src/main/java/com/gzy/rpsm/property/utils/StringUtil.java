package com.gzy.rpsm.property.utils;

public class StringUtil {
    public static int getNumberFromString(String str){
        char[] chars = str.toCharArray();
        StringBuilder builder = new StringBuilder();
        for (char c:chars) {
            if(c >= '0' && c <= '9'){
                builder.append(c);
            }
        }
        return Integer.valueOf(builder.toString());
    }
    public static String numberToLen2String(int i){
        if( -1 < i && i < 10){
            return "0" + i;
        }else{
            return i+"";
        }
    }
    public static int stringPriceToInt(String priceStr) throws NumberFormatException {
        if (priceStr == null || priceStr.isEmpty()) {
            throw new NumberFormatException("价格不能为空");
        }
        return new java.math.BigDecimal(priceStr).multiply(new java.math.BigDecimal("100")).intValue();
    }
}
