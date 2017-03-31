
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
    "bookingDetailId",
    "itineraryNo",
    "tripStart",
    "tripEnd",
    "description",
    "destination",
    "basePrice",
    "agencyCommission",
    "bookingId",
    "regionId",
    "classId",
    "feeId",
    "productSupplierId"
})
public class BookingDetail {

    @JsonProperty("bookingDetailId")
    private Integer bookingDetailId;
    @JsonProperty("itineraryNo")
    private Double itineraryNo;
    @JsonProperty("tripStart")
    private Long tripStart;
    @JsonProperty("tripEnd")
    private Long tripEnd;
    @JsonProperty("description")
    private String description;
    @JsonProperty("destination")
    private String destination;
    @JsonProperty("basePrice")
    private Double basePrice;
    @JsonProperty("agencyCommission")
    private Double agencyCommission;
    @JsonProperty("bookingId")
    private Integer bookingId;
    @JsonProperty("regionId")
    private String regionId;
    @JsonProperty("classId")
    private String classId;
    @JsonProperty("feeId")
    private String feeId;
    @JsonProperty("productSupplierId")
    private Integer productSupplierId;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("bookingDetailId")
    public Integer getBookingDetailId() {
        return bookingDetailId;
    }

    @JsonProperty("bookingDetailId")
    public void setBookingDetailId(Integer bookingDetailId) {
        this.bookingDetailId = bookingDetailId;
    }

    @JsonProperty("itineraryNo")
    public Double getItineraryNo() {
        return itineraryNo;
    }

    @JsonProperty("itineraryNo")
    public void setItineraryNo(Double itineraryNo) {
        this.itineraryNo = itineraryNo;
    }

    @JsonProperty("tripStart")
    public Long getTripStart() {
        return tripStart;
    }

    @JsonProperty("tripStart")
    public void setTripStart(Long tripStart) {
        this.tripStart = tripStart;
    }

    @JsonProperty("tripEnd")
    public Long getTripEnd() {
        return tripEnd;
    }

    @JsonProperty("tripEnd")
    public void setTripEnd(Long tripEnd) {
        this.tripEnd = tripEnd;
    }

    @JsonProperty("description")
    public String getDescription() {
        return description;
    }

    @JsonProperty("description")
    public void setDescription(String description) {
        this.description = description;
    }

    @JsonProperty("destination")
    public String getDestination() {
        return destination;
    }

    @JsonProperty("destination")
    public void setDestination(String destination) {
        this.destination = destination;
    }

    @JsonProperty("basePrice")
    public Double getBasePrice() {
        return basePrice;
    }

    @JsonProperty("basePrice")
    public void setBasePrice(Double basePrice) {
        this.basePrice = basePrice;
    }

    @JsonProperty("agencyCommission")
    public Double getAgencyCommission() {
        return agencyCommission;
    }

    @JsonProperty("agencyCommission")
    public void setAgencyCommission(Double agencyCommission) {
        this.agencyCommission = agencyCommission;
    }

    @JsonProperty("bookingId")
    public Integer getBookingId() {
        return bookingId;
    }

    @JsonProperty("bookingId")
    public void setBookingId(Integer bookingId) {
        this.bookingId = bookingId;
    }

    @JsonProperty("regionId")
    public String getRegionId() {
        return regionId;
    }

    @JsonProperty("regionId")
    public void setRegionId(String regionId) {
        this.regionId = regionId;
    }

    @JsonProperty("classId")
    public String getClassId() {
        return classId;
    }

    @JsonProperty("classId")
    public void setClassId(String classId) {
        this.classId = classId;
    }

    @JsonProperty("feeId")
    public String getFeeId() {
        return feeId;
    }

    @JsonProperty("feeId")
    public void setFeeId(String feeId) {
        this.feeId = feeId;
    }

    @JsonProperty("productSupplierId")
    public Integer getProductSupplierId() {
        return productSupplierId;
    }

    @JsonProperty("productSupplierId")
    public void setProductSupplierId(Integer productSupplierId) {
        this.productSupplierId = productSupplierId;
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
