package com.travelexperts.travelpackages.network;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "packageId",
        "pkgName",
        "pkgStartDate",
        "pkgEndDate",
        "pkgDesc",
        "pkgBasePrice",
        "pkgAgencyCommission"
})
public class Package {

    @JsonProperty("packageId")
    private Integer packageId;
    @JsonProperty("pkgName")
    private String pkgName;
    @JsonProperty("pkgStartDate")
    private Long pkgStartDate;
    @JsonProperty("pkgEndDate")
    private Long pkgEndDate;
    @JsonProperty("pkgDesc")
    private String pkgDesc;
    @JsonProperty("pkgBasePrice")
    private Double pkgBasePrice;
    @JsonProperty("pkgAgencyCommission")
    private Double pkgAgencyCommission;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("packageId")
    public Integer getPackageId() {
        return packageId;
    }

    @JsonProperty("packageId")
    public void setPackageId(Integer packageId) {
        this.packageId = packageId;
    }

    @JsonProperty("pkgName")
    public String getPkgName() {
        return pkgName;
    }

    @JsonProperty("pkgName")
    public void setPkgName(String pkgName) {
        this.pkgName = pkgName;
    }

    @JsonProperty("pkgStartDate")
    public Long getPkgStartDate() {
        return pkgStartDate;
    }

    @JsonProperty("pkgStartDate")
    public void setPkgStartDate(Long pkgStartDate) {
        this.pkgStartDate = pkgStartDate;
    }

    @JsonProperty("pkgEndDate")
    public Long getPkgEndDate() {
        return pkgEndDate;
    }

    @JsonProperty("pkgEndDate")
    public void setPkgEndDate(Long pkgEndDate) {
        this.pkgEndDate = pkgEndDate;
    }

    @JsonProperty("pkgDesc")
    public String getPkgDesc() {
        return pkgDesc;
    }

    @JsonProperty("pkgDesc")
    public void setPkgDesc(String pkgDesc) {
        this.pkgDesc = pkgDesc;
    }

    @JsonProperty("pkgBasePrice")
    public Double getPkgBasePrice() {
        return pkgBasePrice;
    }

    @JsonProperty("pkgBasePrice")
    public void setPkgBasePrice(Double pkgBasePrice) {
        this.pkgBasePrice = pkgBasePrice;
    }

    @JsonProperty("pkgAgencyCommission")
    public Double getPkgAgencyCommission() {
        return pkgAgencyCommission;
    }

    @JsonProperty("pkgAgencyCommission")
    public void setPkgAgencyCommission(Double pkgAgencyCommission) {
        this.pkgAgencyCommission = pkgAgencyCommission;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    @Override
    public String toString() {
        return "Package{" +
                "packageId=" + packageId +
                ", pkgName='" + pkgName + '\'' +
                ", pkgStartDate=" + pkgStartDate +
                ", pkgEndDate=" + pkgEndDate +
                ", pkgDesc='" + pkgDesc + '\'' +
                ", pkgBasePrice=" + pkgBasePrice +
                ", pkgAgencyCommission=" + pkgAgencyCommission +
                '}';
    }
}
