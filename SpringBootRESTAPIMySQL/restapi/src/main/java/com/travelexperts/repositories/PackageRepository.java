package com.travelexperts.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.travelexperts.entities.Package;

@Repository
public interface PackageRepository extends JpaRepository<Package, Integer> {



}
