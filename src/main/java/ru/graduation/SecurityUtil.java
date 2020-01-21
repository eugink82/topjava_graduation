package ru.graduation;

import ru.graduation.model.AbstractBaseEntity;

public class SecurityUtil {
    private static int id= AbstractBaseEntity.START_SEQ;

    public static int authUserId(){
        return id;
    }

    public static void setAuthUserId(int id){
        SecurityUtil.id=id;
    }
}
