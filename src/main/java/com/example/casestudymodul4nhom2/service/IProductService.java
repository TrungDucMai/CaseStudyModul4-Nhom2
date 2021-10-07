package com.example.casestudymodul4nhom2.service;

import com.example.casestudymodul4nhom2.model.Entity.Product;
import com.example.casestudymodul4nhom2.model.User.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface IProductService extends IGeneralService<Product>{
Iterable<Product> findProductByUser(AppUser user);
}
