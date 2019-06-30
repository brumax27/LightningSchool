package com.lightning.school.mvc.util;

public final class StringUtil {

    private StringUtil() {
    }

    public static String formalizeNameFile(String nameFile){
        nameFile = nameFile.replaceAll("\\s+","");
        nameFile = nameFile.replaceAll("[\\@\\/\\:\\;\\!\\,\\?\\+\\-\\*\\(\\)\\{\\}\\#]","");
        return nameFile;
    }
}
