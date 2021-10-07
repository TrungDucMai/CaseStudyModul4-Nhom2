package com.example.casestudymodul4nhom2.service;

import com.example.casestudymodul4nhom2.model.User.AppUser;

public interface IAppUserService extends IGeneralService<AppUser> {
    AppUser getUserByUsername(String username);

    AppUser getCurrentUser();

    Iterable<AppUser> findAppUserByRole();
}
