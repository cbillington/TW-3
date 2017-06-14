package com.travelexperts.repositories;

import com.travelexperts.entities.PackagesProductsSuppliers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by 468364 on 4/7/2017.
 */
public interface PackagesProductsSuppliersRepository extends JpaRepository<PackagesProductsSuppliers, Integer>{

    @Transactional
    @Query("select pps from PackagesProductsSuppliers pps where pps.packageId = :packageId")
    List<PackagesProductsSuppliers> findByPackageId(@Param("packageId") Integer packageId);
}
