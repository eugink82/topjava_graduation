package ru.graduation;

import org.springframework.security.core.GrantedAuthority;
import ru.graduation.model.User;

import java.util.Collection;

public class AuthorizedUser extends  org.springframework.security.core.userdetails.User{
    private static final long serialVersionUID = 1L;

    private User user;

    public AuthorizedUser(User user) {
        super(user.getName(), user.getPassword(), user.getRoles());
        this.user=user;
    }

    public int getId(){
        return user.getId();
    }

    public void update(User user){
        this.user=user;
    }

    public User getUser() {
        return user;
    }

    @Override
    public String toString() {
        return user.toString();
    }
}
