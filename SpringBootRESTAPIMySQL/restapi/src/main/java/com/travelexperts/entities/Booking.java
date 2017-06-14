package com.travelexperts.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;

/**
 *
 * Booking entity generated from hibernate.
 *
 */
@Entity
@Table(name = "bookings", schema = "travelexperts", catalog = "")
public class Booking {
    private Integer bookingId;
    private Timestamp bookingDate;
    private String bookingNo;
    private Double travelerCount;
    private Integer customerId;
    private String tripTypeId;
    private Integer packageId;
    private byte[] bookingDetail;
    private byte[] fee;
    private byte[] tripType;

    @JsonBackReference
    private Customer customer; // customer that booked this booking.

    @JsonManagedReference
    private List<Bookingdetail> bookingDetails; // bookings details for this booking.


    // gets the list of BookingDetail entities for each booking.
    @OneToMany(mappedBy = "booking", cascade = CascadeType.ALL)
    public List<Bookingdetail> getBookingDetails() {
        return bookingDetails;
    }

    public void setBookingDetails(List<Bookingdetail> bookingDetails) {
        this.bookingDetails = bookingDetails;
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
    @Column(name = "bookingDetail", nullable = true)
    public byte[] getBookingDetail() {
        return bookingDetail;
    }

    public void setBookingDetail(byte[] bookingDetail) {
        this.bookingDetail = bookingDetail;
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

        Booking booking = (Booking) o;

        if (bookingId != null ? !bookingId.equals(booking.bookingId) : booking.bookingId != null) return false;
        if (bookingDate != null ? !bookingDate.equals(booking.bookingDate) : booking.bookingDate != null) return false;
        if (bookingNo != null ? !bookingNo.equals(booking.bookingNo) : booking.bookingNo != null) return false;
        if (travelerCount != null ? !travelerCount.equals(booking.travelerCount) : booking.travelerCount != null)
            return false;
        if (customerId != null ? !customerId.equals(booking.customerId) : booking.customerId != null) return false;
        if (tripTypeId != null ? !tripTypeId.equals(booking.tripTypeId) : booking.tripTypeId != null) return false;
        if (packageId != null ? !packageId.equals(booking.packageId) : booking.packageId != null) return false;
        if (!Arrays.equals(bookingDetail, booking.bookingDetail)) return false;
        if (!Arrays.equals(fee, booking.fee)) return false;
        if (!Arrays.equals(tripType, booking.tripType)) return false;

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
        result = 31 * result + Arrays.hashCode(bookingDetail);
        result = 31 * result + Arrays.hashCode(fee);
        result = 31 * result + Arrays.hashCode(tripType);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "CustomerId", insertable=false , updatable =false)
    public Customer getCustomer(){
        return customer;
    }

    public void setCustomer(Customer customer){
        this.customer = customer;
    }


}
