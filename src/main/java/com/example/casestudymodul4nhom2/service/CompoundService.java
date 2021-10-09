package com.example.casestudymodul4nhom2.service;

import com.example.casestudymodul4nhom2.model.Entity.Compound;
import com.example.casestudymodul4nhom2.repository.ICompound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompoundService {
    @Autowired
    private ICompound iCompound;

    public void save(Compound compound){
        iCompound.save(compound);

    }
}
