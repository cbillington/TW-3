package com.travelexperts.entities;

import javax.persistence.*;

/**
 * Created by 468364 on 4/7/2017.
 */
@Entity
public class Products {
    private Integer productId;
    private String prodName;


    @Id
    @Column(name = "ProductId", nullable = false)
    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    @Basic
    @Column(name = "ProdName", nullable = false, length = 50)
    public String getProdName() {
        return prodName;
    }

    public void setProdName(String prodName) {
        this.prodName = prodName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Products products = (Products) o;

        if (productId != null ? !productId.equals(products.productId) : products.productId != null) return false;
        if (prodName != null ? !prodName.equals(products.prodName) : products.prodName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = productId != null ? productId.hashCode() : 0;
        result = 31 * result + (prodName != null ? prodName.hashCode() : 0);
        return result;
    }


}
