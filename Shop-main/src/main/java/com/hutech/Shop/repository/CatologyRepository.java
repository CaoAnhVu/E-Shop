package com.hutech.Shop.repository;

import com.hutech.Shop.model.Catology;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CatologyRepository extends JpaRepository<Catology, Long> {
}