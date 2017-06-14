package com.travelexperts.entities;

import javax.persistence.*;

/**
 * Created by 468364 on 4/13/2017.
 */
@Entity
@Table(name = "watchlisted_packages", schema = "travelexperts", catalog = "")
public class WatchlistedPackages {
    private Integer primaryKey;
    private Integer packageId;
    private Integer numWatchlisted;

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "primary_key", nullable = false)
    public Integer getPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(Integer primaryKey) {
        this.primaryKey = primaryKey;
    }

    @Basic
    @Column(name = "packageId", nullable = false)
    public Integer getPackageId() {
        return packageId;
    }

    public void setPackageId(Integer packageId) {
        this.packageId = packageId;
    }

    @Basic
    @Column(name = "numWatchlisted", nullable = false)
    public Integer getNumWatchlisted() {
        return numWatchlisted;
    }

    public void setNumWatchlisted(Integer numWatchlisted) {
        this.numWatchlisted = numWatchlisted;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WatchlistedPackages that = (WatchlistedPackages) o;

        if (primaryKey != null ? !primaryKey.equals(that.primaryKey) : that.primaryKey != null) return false;
        if (packageId != null ? !packageId.equals(that.packageId) : that.packageId != null) return false;
        if (numWatchlisted != null ? !numWatchlisted.equals(that.numWatchlisted) : that.numWatchlisted != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = primaryKey != null ? primaryKey.hashCode() : 0;
        result = 31 * result + (packageId != null ? packageId.hashCode() : 0);
        result = 31 * result + (numWatchlisted != null ? numWatchlisted.hashCode() : 0);
        return result;
    }
}
