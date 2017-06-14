package com.travelexperts.entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * Created by 468364 on 4/11/2017.
 */
@Entity
@Table(name = "packages", schema = "travelexperts", catalog = "")
public class PackageTest {
    private Integer packageId;
    private String pkgName;
    private Timestamp pkgStartDate;
    private Timestamp pkgEndDate;
    private String pkgDesc;
    private BigDecimal pkgBasePrice;
    private BigDecimal pkgAgencyCommission;
    private String pkgImgUrl;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @Basic
    @Column(name = "PkgImgUrl", nullable = true, length = 60)
    public String getPkgImgUrl() {
        return pkgImgUrl;
    }

    public void setPkgImgUrl(String pkgImgUrl) {
        this.pkgImgUrl = pkgImgUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PackageTest that = (PackageTest) o;

        if (packageId != null ? !packageId.equals(that.packageId) : that.packageId != null) return false;
        if (pkgName != null ? !pkgName.equals(that.pkgName) : that.pkgName != null) return false;
        if (pkgStartDate != null ? !pkgStartDate.equals(that.pkgStartDate) : that.pkgStartDate != null) return false;
        if (pkgEndDate != null ? !pkgEndDate.equals(that.pkgEndDate) : that.pkgEndDate != null) return false;
        if (pkgDesc != null ? !pkgDesc.equals(that.pkgDesc) : that.pkgDesc != null) return false;
        if (pkgBasePrice != null ? !pkgBasePrice.equals(that.pkgBasePrice) : that.pkgBasePrice != null) return false;
        if (pkgAgencyCommission != null ? !pkgAgencyCommission.equals(that.pkgAgencyCommission) : that.pkgAgencyCommission != null)
            return false;
        if (pkgImgUrl != null ? !pkgImgUrl.equals(that.pkgImgUrl) : that.pkgImgUrl != null) return false;

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
        result = 31 * result + (pkgImgUrl != null ? pkgImgUrl.hashCode() : 0);
        return result;
    }
}
