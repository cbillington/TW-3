package com.travelexperts.entities;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by 468364 on 4/7/2017.
 */
public class PackagesProductsSuppliersPK implements Serializable {
    private Integer packageId;
    private Integer productSupplierId;

    @Column(name = "PackageId", nullable = false)
    @Id
    public Integer getPackageId() {
        return packageId;
    }

    public void setPackageId(Integer packageId) {
        this.packageId = packageId;
    }

    @Column(name = "ProductSupplierId", nullable = false)
    @Id
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

        PackagesProductsSuppliersPK that = (PackagesProductsSuppliersPK) o;

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
