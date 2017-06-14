package com.travelexperts.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.travelexperts.entities.Products;

import javax.transaction.Transactional;

@Repository
public interface ProductRepository extends JpaRepository<Products, Integer> {


}
