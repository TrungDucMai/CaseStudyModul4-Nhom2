package com.example.casestudymodul4nhom2.repository;

import com.example.casestudymodul4nhom2.model.Entity.Comment;
import com.example.casestudymodul4nhom2.model.Entity.Product;
import com.example.casestudymodul4nhom2.model.User.AppRole;
import com.example.casestudymodul4nhom2.model.User.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ICommentRepo extends JpaRepository<Comment,Long> {
   Iterable<Comment> findAllByProduct(Product product);
//    @Query("select a from Comment a where a.product = ?1")
//    Iterable<Comment> myUserQuery(Product product);
}
