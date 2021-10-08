package com.example.casestudymodul4nhom2.service;

import com.example.casestudymodul4nhom2.model.User.AppRole;
import com.example.casestudymodul4nhom2.model.User.AppUser;
import com.example.casestudymodul4nhom2.repository.IAppUserRepo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.management.relation.Role;
import java.util.List;
@Service
public class AppUserService {
    @Autowired
    IAppUserRepo appUserRepository;

    public List<AppUser> findAll() {
        return (List<AppUser>) appUserRepository.findAll();
    }

    public AppUser findById(Long id) {
        return appUserRepository.findById(id).get();
    }

    public boolean add(AppUser user) {
        List<AppUser> listUser = findAll();
        for (AppUser userExist : listUser) {
            if (user.getId() == userExist.getId() || StringUtils.equals(user.getUsername(), userExist.getUsername())) {
                return false;
            }
        }
        appUserRepository.save(user);
        return true;
    }

    public void delete(Long id) {
        appUserRepository.deleteById(id);
    }

    public AppUser loadUserByUsername(String username) {
        return appUserRepository.findByUsername(username);
    }

    public boolean checkLogin(AppUser user) {
        List<AppUser> listUser = findAll();
        for (AppUser userExist : listUser) {
            if (StringUtils.equals(user.getUsername(), userExist.getUsername())
                    && StringUtils.equals(user.getPassword(), userExist.getPassword())) {
                return true;
            }
        }
        return false;
    }
    public Iterable<AppUser> findUserByRole(AppRole role) {
        return appUserRepository.myUserQuery(role);
    }

    public Iterable<AppUser> findSellerByRole(AppRole role) {
        return appUserRepository.mySellerQuery(role);
    }
//    public void lockUser(AppRole role, String status, Long id){
//        appUserRepository.lockUser(role, status, id);
//    }

}
