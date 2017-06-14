package com.travelexperts.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;

/**
 * Created by 468364 on 4/12/2017.
 */
@Entity
@Table(name = "bookings", schema = "travelexperts", catalog = "")
public class Booking2 {
    private Integer bookingId;
    private Timestamp bookingDate;
    private String bookingNo;
    private Double travelerCount;
    private Integer customerId;
    private String tripTypeId;
    private Integer packageId;
    private byte[] fee;
    private byte[] tripType;


    @JsonManagedReference
    private List<Bookingdetail2> bookingDetails2;

    @OneToMany(mappedBy = "booking2", cascade = CascadeType.ALL)
    public List<Bookingdetail2> getBookingDetails() {
        return bookingDetails2;
    }

    public void setBookingDetails(List<Bookingdetail2> bookingDetails) {
        this.bookingDetails2 = bookingDetails;
    }

    @Id
    @Column(name = "BookingId", nullable = false)
    public Integer getBookingId() {
        return bookingId;
    }

    public void setBookingId(Integer bookingId) {
        this.bookingId = bookingId;
    }

    @Basic
    @Column(name = "BookingDate", nullable = true)
    public Timestamp getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(Timestamp bookingDate) {
        this.bookingDate = bookingDate;
    }

    @Basic
    @Column(name = "BookingNo", nullable = true, length = 50)
    public String getBookingNo() {
        return bookingNo;
    }

    public void setBookingNo(String bookingNo) {
        this.bookingNo = bookingNo;
    }

    @Basic
    @Column(name = "TravelerCount", nullable = true, precision = 0)
    public Double getTravelerCount() {
        return travelerCount;
    }

    public void setTravelerCount(Double travelerCount) {
        this.travelerCount = travelerCount;
    }

    @Basic
    @Column(name = "CustomerId", nullable = true)
    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    @Basic
    @Column(name = "TripTypeId", nullable = true, length = 1)
    public String getTripTypeId() {
        return tripTypeId;
    }

    public void setTripTypeId(String tripTypeId) {
        this.tripTypeId = tripTypeId;
    }

    @Basic
    @Column(name = "PackageId", nullable = true)
    public Integer getPackageId() {
        return packageId;
    }

    public void setPackageId(Integer packageId) {
        this.packageId = packageId;
    }



    @Basic
    @Column(name = "fee", nullable = true)
    public byte[] getFee() {
        return fee;
    }

    public void setFee(byte[] fee) {
        this.fee = fee;
    }

    @Basic
    @Column(name = "tripType", nullable = true)
    public byte[] getTripType() {
        return tripType;
    }

    public void setTripType(byte[] tripType) {
        this.tripType = tripType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Booking2 booking2 = (Booking2) o;

        if (bookingId != null ? !bookingId.equals(booking2.bookingId) : booking2.bookingId != null) return false;
        if (bookingDate != null ? !bookingDate.equals(booking2.bookingDate) : booking2.bookingDate != null)
            return false;
        if (bookingNo != null ? !bookingNo.equals(booking2.bookingNo) : booking2.bookingNo != null) return false;
        if (travelerCount != null ? !travelerCount.equals(booking2.travelerCount) : booking2.travelerCount != null)
            return false;
        if (customerId != null ? !customerId.equals(booking2.customerId) : booking2.customerId != null) return false;
        if (tripTypeId != null ? !tripTypeId.equals(booking2.tripTypeId) : booking2.tripTypeId != null) return false;
        if (packageId != null ? !packageId.equals(booking2.packageId) : booking2.packageId != null) return false;

        if (!Arrays.equals(fee, booking2.fee)) return false;
        if (!Arrays.equals(tripType, booking2.tripType)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = bookingId != null ? bookingId.hashCode() : 0;
        result = 31 * result + (bookingDate != null ? bookingDate.hashCode() : 0);
        result = 31 * result + (bookingNo != null ? bookingNo.hashCode() : 0);
        result = 31 * result + (travelerCount != null ? travelerCount.hashCode() : 0);
        result = 31 * result + (customerId != null ? customerId.hashCode() : 0);
        result = 31 * result + (tripTypeId != null ? tripTypeId.hashCode() : 0);
        result = 31 * result + (packageId != null ? packageId.hashCode() : 0);
        result = 31 * result + Arrays.hashCode(fee);
        result = 31 * result + Arrays.hashCode(tripType);
        return result;
    }


}
