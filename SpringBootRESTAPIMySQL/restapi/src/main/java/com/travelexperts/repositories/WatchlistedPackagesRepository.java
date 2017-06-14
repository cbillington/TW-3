package com.travelexperts.repositories;

import com.travelexperts.entities.ProductsSuppliers;
import com.travelexperts.entities.WatchlistedPackages;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by 468364 on 4/13/2017.
 */
public interface WatchlistedPackagesRepository extends JpaRepository<WatchlistedPackages, Integer> {

    @Transactional
    @Query("select wp from WatchlistedPackages wp where wp.packageId = :packageId")
    WatchlistedPackages findByPackageId(@Param("packageId") Integer packageId);
}
