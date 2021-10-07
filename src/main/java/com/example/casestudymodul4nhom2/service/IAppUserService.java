package com.example.casestudymodul4nhom2.service;

import com.example.casestudymodul4nhom2.model.User.AppUser;
import org.springframework.stereotype.Service;

@Service

public interface IAppUserService extends IGeneralService<AppUser> {
    AppUser getUserByUsername(String username);

    AppUser getCurrentUser();

    Iterable<AppUser> findAppUserByRole();

}
