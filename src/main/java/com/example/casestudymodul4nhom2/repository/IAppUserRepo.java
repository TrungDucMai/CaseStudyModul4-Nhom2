package com.example.casestudymodul4nhom2.repository;

import com.example.casestudymodul4nhom2.model.User.AppRole;
import com.example.casestudymodul4nhom2.model.User.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IAppUserRepo extends JpaRepository<AppUser,Long> {
    AppUser findByUsername(String username);

    @Query("select a from AppUser a where a.roll = ?1")
    Iterable<AppUser> mySellerQuery(AppRole roll);

//    @Query(value = "select u from AppUser u where u.roll = ?1 and u.avatar =?2")
//    Iterable<AppUser>  myUserQuery(Long roll,String avatar);
    @Query("select a from AppUser a where a.roll = ?1")
    Iterable<AppUser> myUserQuery(AppRole roll);


}
