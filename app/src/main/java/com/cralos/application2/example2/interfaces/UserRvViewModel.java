package com.cralos.application2.example2.interfaces;

import com.cralos.application2.example2.models.User;

import java.util.List;

public interface UserRvViewModel {
    void setUsers(List<User> users);

    void setMessage(String message);
}
