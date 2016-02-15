package com.rcoedo.model.services;

import rx.Observable;

import com.rcoedo.model.domain.User;

public interface UserService {
    Observable<User> getUsers();
}
