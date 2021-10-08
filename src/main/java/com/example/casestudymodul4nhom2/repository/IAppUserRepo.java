package com.example.casestudymodul4nhom2.repository;

import com.example.casestudymodul4nhom2.model.User.AppRole;
import com.example.casestudymodul4nhom2.model.User.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.management.relation.Role;
import javax.transaction.Transactional;

@Repository
public interface IAppUserRepo extends JpaRepository<AppUser,Long> {
    AppUser findByUsername(String username);

    @Query("select a from AppUser a where a.roll = ?1")
    Iterable<AppUser> mySellerQuery(AppRole roll);

//    @Query(value = "select u from AppUser u where u.roll = ?1 and u.avatar =?2")
//    Iterable<AppUser>  myUserQuery(Long roll,String avatar);
    @Query("select a from AppUser a where a.roll = ?1")
    Iterable<AppUser> myUserQuery(AppRole roll);

//    @Transactional
//    @Modifying
//    @Query(value = "UPDATE AppUser a SET a.roll = ?1 , a.status = ?2 WHERE a.id = ?3",nativeQuery = true)
//    void lockUser(AppRole role, String status, Long id);

//    @Query(value = "UPDATE Users u set EMAIL_VERIFICATION_STATUS =?1 where u.USER_ID = ?2",
//            nativeQuery = true)
//    void updateUser(boolean emailVerificationStatus, String userId);

}
