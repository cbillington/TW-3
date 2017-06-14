package com.travelexperts.entities;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * Created by 468364 on 4/10/2017.
 */
@Entity
public class Packages {
    private Integer packageId;
    private String pkgName;
    private Timestamp pkgStartDate;
    private Timestamp pkgEndDate;
    private String pkgDesc;
    private BigDecimal pkgBasePrice;
    private BigDecimal pkgAgencyCommission;
    private String pkgImgUrl;

    @Basic
    @Column(name = "PkgImgUrl", nullable = true, length = 60)
    public String getPkgImgUrl() {
        return pkgImgUrl;
    }

    public void setPkgImgUrl(String pkgImgUrl) {
        this.pkgImgUrl = pkgImgUrl;
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

        Packages packages = (Packages) o;

        if (packageId != null ? !packageId.equals(packages.packageId) : packages.packageId != null) return false;
        if (pkgName != null ? !pkgName.equals(packages.pkgName) : packages.pkgName != null) return false;
        if (pkgStartDate != null ? !pkgStartDate.equals(packages.pkgStartDate) : packages.pkgStartDate != null)
            return false;
        if (pkgEndDate != null ? !pkgEndDate.equals(packages.pkgEndDate) : packages.pkgEndDate != null) return false;
        if (pkgDesc != null ? !pkgDesc.equals(packages.pkgDesc) : packages.pkgDesc != null) return false;
        if (pkgBasePrice != null ? !pkgBasePrice.equals(packages.pkgBasePrice) : packages.pkgBasePrice != null)
            return false;
        if (pkgAgencyCommission != null ? !pkgAgencyCommission.equals(packages.pkgAgencyCommission) : packages.pkgAgencyCommission != null)
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
}
