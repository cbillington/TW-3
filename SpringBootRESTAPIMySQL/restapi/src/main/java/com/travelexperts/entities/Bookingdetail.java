package com.travelexperts.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * Created by 468364 on 3/30/2017.
 */
@Entity
@Table(name = "bookingdetails", schema = "travelexperts", catalog = "")
public class Bookingdetail {
    private Integer bookingDetailId;
    private Double itineraryNo;
    private Timestamp tripStart;
    private Timestamp tripEnd;
    private String description;
    private String destination;
    private BigDecimal basePrice;
    private BigDecimal agencyCommission;
    private Integer bookingId;
    private String regionId;
    private String classId;
    private String feeId;
    private Integer productSupplierId;

    @JsonBackReference
    private Booking booking;

    @ManyToOne
    @JoinColumn(name = "BookingId", insertable=false , updatable =false)
    public Booking getBooking(){
        return booking;
    }

    public void setBooking(Booking booking){
        this.booking = booking;
    }

    @Id
    @Column(name = "BookingDetailId", nullable = false)
    public Integer getBookingDetailId() {
        return bookingDetailId;
    }

    public void setBookingDetailId(Integer bookingDetailId) {
        this.bookingDetailId = bookingDetailId;
    }

    @Basic
    @Column(name = "ItineraryNo", nullable = true, precision = 0)
    public Double getItineraryNo() {
        return itineraryNo;
    }

    public void setItineraryNo(Double itineraryNo) {
        this.itineraryNo = itineraryNo;
    }

    @Basic
    @Column(name = "TripStart", nullable = true)
    public Timestamp getTripStart() {
        return tripStart;
    }

    public void setTripStart(Timestamp tripStart) {
        this.tripStart = tripStart;
    }

    @Basic
    @Column(name = "TripEnd", nullable = true)
    public Timestamp getTripEnd() {
        return tripEnd;
    }

    public void setTripEnd(Timestamp tripEnd) {
        this.tripEnd = tripEnd;
    }

    @Basic
    @Column(name = "Description", nullable = true, length = -1)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "Destination", nullable = true, length = -1)
    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    @Basic
    @Column(name = "BasePrice", nullable = true, precision = 4)
    public BigDecimal getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(BigDecimal basePrice) {
        this.basePrice = basePrice;
    }

    @Basic
    @Column(name = "AgencyCommission", nullable = true, precision = 4)
    public BigDecimal getAgencyCommission() {
        return agencyCommission;
    }

    public void setAgencyCommission(BigDecimal agencyCommission) {
        this.agencyCommission = agencyCommission;
    }

    @Basic
    @Column(name = "BookingId", nullable = true)
    public Integer getBookingId() {
        return bookingId;
    }

    public void setBookingId(Integer bookingId) {
        this.bookingId = bookingId;
    }

    @Basic
    @Column(name = "RegionId", nullable = true, length = 5)
    public String getRegionId() {
        return regionId;
    }

    public void setRegionId(String regionId) {
        this.regionId = regionId;
    }

    @Basic
    @Column(name = "ClassId", nullable = true, length = 5)
    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    @Basic
    @Column(name = "FeeId", nullable = true, length = 10)
    public String getFeeId() {
        return feeId;
    }

    public void setFeeId(String feeId) {
        this.feeId = feeId;
    }

    @Basic
    @Column(name = "ProductSupplierId", nullable = true)
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

        Bookingdetail that = (Bookingdetail) o;

        if (bookingDetailId != null ? !bookingDetailId.equals(that.bookingDetailId) : that.bookingDetailId != null)
            return false;
        if (itineraryNo != null ? !itineraryNo.equals(that.itineraryNo) : that.itineraryNo != null) return false;
        if (tripStart != null ? !tripStart.equals(that.tripStart) : that.tripStart != null) return false;
        if (tripEnd != null ? !tripEnd.equals(that.tripEnd) : that.tripEnd != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (destination != null ? !destination.equals(that.destination) : that.destination != null) return false;
        if (basePrice != null ? !basePrice.equals(that.basePrice) : that.basePrice != null) return false;
        if (agencyCommission != null ? !agencyCommission.equals(that.agencyCommission) : that.agencyCommission != null)
            return false;
        if (bookingId != null ? !bookingId.equals(that.bookingId) : that.bookingId != null) return false;
        if (regionId != null ? !regionId.equals(that.regionId) : that.regionId != null) return false;
        if (classId != null ? !classId.equals(that.classId) : that.classId != null) return false;
        if (feeId != null ? !feeId.equals(that.feeId) : that.feeId != null) return false;
        if (productSupplierId != null ? !productSupplierId.equals(that.productSupplierId) : that.productSupplierId != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = bookingDetailId != null ? bookingDetailId.hashCode() : 0;
        result = 31 * result + (itineraryNo != null ? itineraryNo.hashCode() : 0);
        result = 31 * result + (tripStart != null ? tripStart.hashCode() : 0);
        result = 31 * result + (tripEnd != null ? tripEnd.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (destination != null ? destination.hashCode() : 0);
        result = 31 * result + (basePrice != null ? basePrice.hashCode() : 0);
        result = 31 * result + (agencyCommission != null ? agencyCommission.hashCode() : 0);
        result = 31 * result + (bookingId != null ? bookingId.hashCode() : 0);
        result = 31 * result + (regionId != null ? regionId.hashCode() : 0);
        result = 31 * result + (classId != null ? classId.hashCode() : 0);
        result = 31 * result + (feeId != null ? feeId.hashCode() : 0);
        result = 31 * result + (productSupplierId != null ? productSupplierId.hashCode() : 0);
        return result;
    }

}
