package com.example.casestudymodul4nhom2.service;

import com.example.casestudymodul4nhom2.model.Entity.Product;
import com.example.casestudymodul4nhom2.model.User.AppUser;
import com.example.casestudymodul4nhom2.repository.IProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService implements IProductService{
    @Autowired
    private IProductRepo productRepo;
    @Override
    public Iterable<Product> findAll() {
        return productRepo.findAll();
    }

    @Override
    public Optional<Product> findById(Long id) {
        return productRepo.findById(id);
    }

    @Override
    public void save(Product product) {
        productRepo.save(product);
    }

    @Override
    public void remove(Long id) {
        productRepo.deleteById(id);
    }

    @Override
    public Iterable<Product> findProductByUser(AppUser user) {
        return productRepo.sellerProductQuery(user) ;
    }

    @Override
    public Iterable<Product> findAllByNameContaining(String name) {
        return productRepo.findAllByNameContaining(name);
    }

    @Override
    public Iterable<Product> findAllByPriceBetween(double lowestPrice, double highestPrice) {
        return productRepo.findAllByPriceBetween(lowestPrice,highestPrice);
    }

    @Override
    public Page<Product> findAll(Pageable pageable) {
        return productRepo.findAll(pageable);
    }
}
