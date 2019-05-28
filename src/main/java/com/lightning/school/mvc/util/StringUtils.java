package com.lightning.school.mvc.util;

public final class StringUtils {

    private StringUtils() {
    }

    public static String formalizeNameFile(String nameFile){
        nameFile = nameFile.replaceAll("\\s+","");
        nameFile = nameFile.replaceAll("[\\@\\/\\:\\;\\!\\,\\?\\+\\-\\*\\(\\)\\{\\}\\#]","");
        return nameFile;
    }
}
