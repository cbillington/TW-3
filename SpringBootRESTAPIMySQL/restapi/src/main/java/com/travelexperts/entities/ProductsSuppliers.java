package com.travelexperts.entities;

import javax.persistence.*;

/**
 * Created by 468364 on 4/7/2017.
 */
@Entity
@Table(name = "products_suppliers", schema = "travelexperts", catalog = "")
public class ProductsSuppliers {
    private Integer productSupplierId;
    private Integer productId;
    private Integer supplierId;

    private Products product;

    @Transient
    public Products getProduct() {
        return product;
    }

    public void setProduct(Products product) {
        this.product = product;
    }

    @Id
    @Column(name = "ProductSupplierId", nullable = false)
    public Integer getProductSupplierId() {
        return productSupplierId;
    }

    public void setProductSupplierId(Integer productSupplierId) {
        this.productSupplierId = productSupplierId;
    }

    @Basic
    @Column(name = "ProductId", nullable = true)
    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    @Basic
    @Column(name = "SupplierId", nullable = true)
    public Integer getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Integer supplierId) {
        this.supplierId = supplierId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProductsSuppliers that = (ProductsSuppliers) o;

        if (productSupplierId != null ? !productSupplierId.equals(that.productSupplierId) : that.productSupplierId != null)
            return false;
        if (productId != null ? !productId.equals(that.productId) : that.productId != null) return false;
        if (supplierId != null ? !supplierId.equals(that.supplierId) : that.supplierId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = productSupplierId != null ? productSupplierId.hashCode() : 0;
        result = 31 * result + (productId != null ? productId.hashCode() : 0);
        result = 31 * result + (supplierId != null ? supplierId.hashCode() : 0);
        return result;
    }
}
