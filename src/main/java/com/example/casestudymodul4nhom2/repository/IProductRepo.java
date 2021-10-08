package com.example.casestudymodul4nhom2.repository;

import com.example.casestudymodul4nhom2.model.Entity.Product;
import com.example.casestudymodul4nhom2.model.User.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IProductRepo extends JpaRepository<Product, Long> {
    @Query("select a from Product a where a.appuser = ?1")
    Iterable<Product> sellerProductQuery (AppUser apppUser);

    Iterable<Product> findAllByNameContaining(String name);

    @Query("select a from Product a where a.price between ?1 and ?2")
    Iterable<Product> findAllByPriceBetween(double lowestPrice, double highestPrice);

//    Iterable<Product> findAllByPriceBetween(double value1, double value2);


}
