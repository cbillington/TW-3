
package com.travelexperts.travelpackages.network;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "bookingId",
    "bookingDate",
    "bookingNo",
    "travelerCount",
    "customerId",
    "tripTypeId",
    "packageId",
    "bookingDetail",
    "fee",
    "tripType",
    "bookingDetails"
})
public class Booking {

    @JsonProperty("bookingId")
    private Integer bookingId;
    @JsonProperty("bookingDate")
    private Long bookingDate;
    @JsonProperty("bookingNo")
    private String bookingNo;
    @JsonProperty("travelerCount")
    private Double travelerCount;
    @JsonProperty("customerId")
    private Integer customerId;
    @JsonProperty("tripTypeId")
    private String tripTypeId;
    @JsonProperty("packageId")
    private Object packageId;
    @JsonProperty("bookingDetail")
    private Object bookingDetail;
    @JsonProperty("fee")
    private Object fee;
    @JsonProperty("tripType")
    private Object tripType;
    @JsonProperty("bookingDetails")
    private List<BookingDetail> bookingDetails = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("bookingId")
    public Integer getBookingId() {
        return bookingId;
    }

    @JsonProperty("bookingId")
    public void setBookingId(Integer bookingId) {
        this.bookingId = bookingId;
    }

    @JsonProperty("bookingDate")
    public Long getBookingDate() {
        return bookingDate;
    }

    @JsonProperty("bookingDate")
    public void setBookingDate(Long bookingDate) {
        this.bookingDate = bookingDate;
    }

    @JsonProperty("bookingNo")
    public String getBookingNo() {
        return bookingNo;
    }

    @JsonProperty("bookingNo")
    public void setBookingNo(String bookingNo) {
        this.bookingNo = bookingNo;
    }

    @JsonProperty("travelerCount")
    public Double getTravelerCount() {
        return travelerCount;
    }

    @JsonProperty("travelerCount")
    public void setTravelerCount(Double travelerCount) {
        this.travelerCount = travelerCount;
    }

    @JsonProperty("customerId")
    public Integer getCustomerId() {
        return customerId;
    }

    @JsonProperty("customerId")
    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    @JsonProperty("tripTypeId")
    public String getTripTypeId() {
        return tripTypeId;
    }

    @JsonProperty("tripTypeId")
    public void setTripTypeId(String tripTypeId) {
        this.tripTypeId = tripTypeId;
    }

    @JsonProperty("packageId")
    public Object getPackageId() {
        return packageId;
    }

    @JsonProperty("packageId")
    public void setPackageId(Object packageId) {
        this.packageId = packageId;
    }

    @JsonProperty("bookingDetail")
    public Object getBookingDetail() {
        return bookingDetail;
    }

    @JsonProperty("bookingDetail")
    public void setBookingDetail(Object bookingDetail) {
        this.bookingDetail = bookingDetail;
    }

    @JsonProperty("fee")
    public Object getFee() {
        return fee;
    }

    @JsonProperty("fee")
    public void setFee(Object fee) {
        this.fee = fee;
    }

    @JsonProperty("tripType")
    public Object getTripType() {
        return tripType;
    }

    @JsonProperty("tripType")
    public void setTripType(Object tripType) {
        this.tripType = tripType;
    }

    @JsonProperty("bookingDetails")
    public List<BookingDetail> getBookingDetails() {
        return bookingDetails;
    }

    @JsonProperty("bookingDetails")
    public void setBookingDetails(List<BookingDetail> bookingDetails) {
        this.bookingDetails = bookingDetails;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }


}
