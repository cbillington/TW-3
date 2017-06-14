package com.travelexperts.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

/**
 * Created by 468364 on 3/28/2017.
 */
@Entity
@Table(name = "packages", schema = "travelexperts", catalog = "")
public class Package {
    private Integer packageId;
    private String pkgName;
    private Timestamp pkgStartDate;
    private Timestamp pkgEndDate;
    private String pkgDesc;
    private BigDecimal pkgBasePrice;
    private BigDecimal pkgAgencyCommission;
    //private List<Product> products;

    @JsonManagedReference
    private List<PackagesProductsSuppliers> packagesProductsSuppliers;

    @Transient
    public List<PackagesProductsSuppliers> getPackagesProductsSuppliers() {
        return packagesProductsSuppliers;
    }

    public void setPackagesProductsSuppliers(List<PackagesProductsSuppliers> packagesProductsSuppliers) {
        this.packagesProductsSuppliers = packagesProductsSuppliers;
    }

    @Id
    @Column(name = "PackageId", nullable = false)
    public Integer getPackageId() {
        return packageId;
    }


    public void setPackageId(Integer packageId) {
        this.packageId = packageId;
    }

    @Basic
    @Column(name = "PkgName", nullable = false, length = 50)
    public String getPkgName() {
        return pkgName;
    }

    public void setPkgName(String pkgName) {
        this.pkgName = pkgName;
    }

    @Basic
    @Column(name = "PkgStartDate", nullable = true)
    public Timestamp getPkgStartDate() {
        return pkgStartDate;
    }

    public void setPkgStartDate(Timestamp pkgStartDate) {
        this.pkgStartDate = pkgStartDate;
    }

    @Basic
    @Column(name = "PkgEndDate", nullable = true)
    public Timestamp getPkgEndDate() {
        return pkgEndDate;
    }

    public void setPkgEndDate(Timestamp pkgEndDate) {
        this.pkgEndDate = pkgEndDate;
    }

    @Basic
    @Column(name = "PkgDesc", nullable = true, length = 50)
    public String getPkgDesc() {
        return pkgDesc;
    }

    public void setPkgDesc(String pkgDesc) {
        this.pkgDesc = pkgDesc;
    }

    @Basic
    @Column(name = "PkgBasePrice", nullable = false, precision = 4)
    public BigDecimal getPkgBasePrice() {
        return pkgBasePrice;
    }

    public void setPkgBasePrice(BigDecimal pkgBasePrice) {
        this.pkgBasePrice = pkgBasePrice;
    }

    @Basic
    @Column(name = "PkgAgencyCommission", nullable = true, precision = 4)
    public BigDecimal getPkgAgencyCommission() {
        return pkgAgencyCommission;
    }

    public void setPkgAgencyCommission(BigDecimal pkgAgencyCommission) {
        this.pkgAgencyCommission = pkgAgencyCommission;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Package aPackage = (Package) o;

        if (packageId != null ? !packageId.equals(aPackage.packageId) : aPackage.packageId != null) return false;
        if (pkgName != null ? !pkgName.equals(aPackage.pkgName) : aPackage.pkgName != null) return false;
        if (pkgStartDate != null ? !pkgStartDate.equals(aPackage.pkgStartDate) : aPackage.pkgStartDate != null)
            return false;
        if (pkgEndDate != null ? !pkgEndDate.equals(aPackage.pkgEndDate) : aPackage.pkgEndDate != null) return false;
        if (pkgDesc != null ? !pkgDesc.equals(aPackage.pkgDesc) : aPackage.pkgDesc != null) return false;
        if (pkgBasePrice != null ? !pkgBasePrice.equals(aPackage.pkgBasePrice) : aPackage.pkgBasePrice != null)
            return false;
        if (pkgAgencyCommission != null ? !pkgAgencyCommission.equals(aPackage.pkgAgencyCommission) : aPackage.pkgAgencyCommission != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = packageId != null ? packageId.hashCode() : 0;
        result = 31 * result + (pkgName != null ? pkgName.hashCode() : 0);
        result = 31 * result + (pkgStartDate != null ? pkgStartDate.hashCode() : 0);
        result = 31 * result + (pkgEndDate != null ? pkgEndDate.hashCode() : 0);
        result = 31 * result + (pkgDesc != null ? pkgDesc.hashCode() : 0);
        result = 31 * result + (pkgBasePrice != null ? pkgBasePrice.hashCode() : 0);
        result = 31 * result + (pkgAgencyCommission != null ? pkgAgencyCommission.hashCode() : 0);
        return result;
    }




    /*@OneToMany
    @JoinTable(
            name="packages_products_suppliers",
            joinColumns = @JoinColumn( name="PackageId"),
            inverseJoinColumns = @JoinColumn( name="")
    )
    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }*/
}
