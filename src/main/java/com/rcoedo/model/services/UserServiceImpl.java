package com.rcoedo.model.services;

import org.springframework.stereotype.Service;

import rx.Observable;

import com.rcoedo.model.domain.User;

@Service
public class UserServiceImpl implements UserService {
    @Override
    public Observable<User> getUsers() {
        return Observable.just(new User("Roman Coedo"));
    }
}
