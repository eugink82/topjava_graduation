package ru.graduation;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import ru.graduation.model.AbstractBaseEntity;

import static java.util.Objects.requireNonNull;

public class SecurityUtil {
    private static int id= AbstractBaseEntity.START_SEQ;

    public static AuthorizedUser safeGet(){
        Authentication auth= SecurityContextHolder.getContext().getAuthentication();
        if(auth==null){
            return null;
        }
        Object principal=auth.getPrincipal();
        return (principal instanceof AuthorizedUser) ? (AuthorizedUser)principal : null;
    }

    public static AuthorizedUser get(){
        AuthorizedUser user=safeGet();
        requireNonNull(user,"Авторизованный пользователь не найден");
        return user;
    }

    public static int authUserId(){
        return get().getId();
    }

//    public static void setAuthUserId(int id){
//        SecurityUtil.id=id;
//    }
}
