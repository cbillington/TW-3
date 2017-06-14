package com.travelexperts.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.List;

/**
 * Created by 468364 on 4/7/2017.
 */
@Entity
@Table(name = "packages_products_suppliers", schema = "travelexperts", catalog = "")
@IdClass(PackagesProductsSuppliersPK.class)
public class PackagesProductsSuppliers {
    private Integer packageId;
    private Integer productSupplierId;


    private List<ProductsSuppliers> productsSupplierss;

    @Transient
    public List<ProductsSuppliers> getProductsSupplierss() {
        return productsSupplierss;
    }

    public void setProductsSupplierss(List<ProductsSuppliers> productsSupplierss) {
        this.productsSupplierss = productsSupplierss;
    }

    @Id
    @Column(name = "PackageId", nullable = false)
    public Integer getPackageId() {
        return packageId;
    }

    public void setPackageId(Integer packageId) {
        this.packageId = packageId;
    }

    @Id
    @Column(name = "ProductSupplierId", nullable = false)
    public Integer getProductSupplierId() {
        return productSupplierId;
    }

    public void setProductSupplierId(Integer productSupplierId) {
        this.productSupplierId = productSupplierId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PackagesProductsSuppliers that = (PackagesProductsSuppliers) o;

        if (packageId != null ? !packageId.equals(that.packageId) : that.packageId != null) return false;
        if (productSupplierId != null ? !productSupplierId.equals(that.productSupplierId) : that.productSupplierId != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = packageId != null ? packageId.hashCode() : 0;
        result = 31 * result + (productSupplierId != null ? productSupplierId.hashCode() : 0);
        return result;
    }

}
