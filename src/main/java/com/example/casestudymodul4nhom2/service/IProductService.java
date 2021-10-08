package com.example.casestudymodul4nhom2.service;

import com.example.casestudymodul4nhom2.model.Entity.Product;
import com.example.casestudymodul4nhom2.model.User.AppUser;

public interface IProductService extends IGeneralService<Product> {
    Iterable<Product> findProductByUser(AppUser user);

    Iterable<Product> findAllByNameContaining(String name);
    Iterable<Product> findAllByPriceBetween(double lowestPrice, double highestPrice);
}
