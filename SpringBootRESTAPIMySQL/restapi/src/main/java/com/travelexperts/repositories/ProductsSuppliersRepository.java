package com.travelexperts.repositories;

import com.travelexperts.entities.ProductsSuppliers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by 468364 on 4/7/2017.
 */
public interface ProductsSuppliersRepository extends JpaRepository<ProductsSuppliers, Integer> {

    @Transactional
    @Query("select ps from ProductsSuppliers ps where ps.productSupplierId = :productSupplierId")
    List<ProductsSuppliers> findByProductSupplierId(@Param("productSupplierId") Integer productSupplierId);
}
