package com.example.casestudymodul4nhom2.repository;

import com.example.casestudymodul4nhom2.model.Entity.Compound;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICompound extends JpaRepository<Compound,Long> {
}
