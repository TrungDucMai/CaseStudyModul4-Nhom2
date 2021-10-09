package com.example.casestudymodul4nhom2.service;

import com.example.casestudymodul4nhom2.model.Entity.Comment;
import com.example.casestudymodul4nhom2.model.Entity.Product;
import com.example.casestudymodul4nhom2.repository.ICommentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService {
    @Autowired
    ICommentRepo iCommentRepo;
    public void createComment(Comment comment){
        iCommentRepo.save(comment);
    }
   public Iterable<Comment> findAllByProduct(Product product){
       return iCommentRepo.findAllByProduct(product);
   }
//    public Iterable<Comment> listCommentByProduct(Product product){
//        return iCommentRepo.myUserQuery(product);
//    }
}
