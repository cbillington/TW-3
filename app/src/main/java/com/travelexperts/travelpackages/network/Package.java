package com.travelexperts.travelpackages.network;
import com.travelexperts.travelpackages.data.PackagesContract;
import android.content.ContentValues;
import android.os.Parcel;
import android.os.Parcelable;

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
public class Package implements Parcelable {

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

    public Package() {
    }

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

    public ContentValues getContentValues(){
        ContentValues rowToReturn = new ContentValues();
        rowToReturn.put(PackagesContract.PackagesEntry.COLUMN_PACKAGE_NAME, getPkgName());
        rowToReturn.put(PackagesContract.PackagesEntry.COLUMN_PACKAGE_START_DATE, getPkgStartDate());
        rowToReturn.put(PackagesContract.PackagesEntry.COLUMN_PACKAGE_END_DATE, getPkgEndDate());
        rowToReturn.put(PackagesContract.PackagesEntry.COLUMN_PACKAGE_DESCRIPTION, getPkgDesc());
        rowToReturn.put(PackagesContract.PackagesEntry.COLUMN_PACKAGE_BASE_PRICE, getPkgBasePrice());
        rowToReturn.put(PackagesContract.PackagesEntry.COLUMN_PACKAGE_AGENCY_COMMISSION, getPkgAgencyCommission());
        return rowToReturn;
    }

    protected Package(Parcel in) {
        packageId = in.readByte() == 0x00 ? null : in.readInt();
        pkgName = in.readString();
        pkgStartDate = in.readByte() == 0x00 ? null : in.readLong();
        pkgEndDate = in.readByte() == 0x00 ? null : in.readLong();
        pkgDesc = in.readString();
        pkgBasePrice = in.readByte() == 0x00 ? null : in.readDouble();
        pkgAgencyCommission = in.readByte() == 0x00 ? null : in.readDouble();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (packageId == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeInt(packageId);
        }
        dest.writeString(pkgName);
        if (pkgStartDate == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeLong(pkgStartDate);
        }
        if (pkgEndDate == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeLong(pkgEndDate);
        }
        dest.writeString(pkgDesc);
        if (pkgBasePrice == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeDouble(pkgBasePrice);
        }
        if (pkgAgencyCommission == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeDouble(pkgAgencyCommission);
        }
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Package> CREATOR = new Parcelable.Creator<Package>() {
        @Override
        public Package createFromParcel(Parcel in) {
            return new Package(in);
        }

        @Override
        public Package[] newArray(int size) {
            return new Package[size];
        }
    };
}
