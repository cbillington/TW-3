package com.travelexperts.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

/**
 * Created by 468364 on 3/30/2017.
 */
@Entity
@Table(name = "customers", schema = "travelexperts", catalog = "")
public class Customer {
    private Integer customerId;
    private String custFirstName;
    private String custLastName;
    private String custAddress;
    private String custCity;
    private String custProv;
    private String custPostal;
    private String custCountry;
    private String custHomePhone;
    private String custBusPhone;
    private String custEmail;
    private Integer agentId;

    @Override
    public String toString() {
        return "Customer{" +
                "customerId=" + customerId +
                ", custFirstName='" + custFirstName + '\'' +
                ", custLastName='" + custLastName + '\'' +
                ", custAddress='" + custAddress + '\'' +
                ", custCity='" + custCity + '\'' +
                ", custProv='" + custProv + '\'' +
                ", custPostal='" + custPostal + '\'' +
                ", custCountry='" + custCountry + '\'' +
                ", custHomePhone='" + custHomePhone + '\'' +
                ", custBusPhone='" + custBusPhone + '\'' +
                ", custEmail='" + custEmail + '\'' +
                ", agentId=" + agentId +
                ", bookings=" + bookings +
                '}';
    }

    @JsonManagedReference
    private List<Booking> bookings;



    @Id
    @Column(name = "CustomerId", nullable = false)
    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    @Basic
    @Column(name = "CustFirstName", nullable = false, length = 25)
    public String getCustFirstName() {
        return custFirstName;
    }

    public void setCustFirstName(String custFirstName) {
        this.custFirstName = custFirstName;
    }

    @Basic
    @Column(name = "CustLastName", nullable = false, length = 25)
    public String getCustLastName() {
        return custLastName;
    }

    public void setCustLastName(String custLastName) {
        this.custLastName = custLastName;
    }

    @Basic
    @Column(name = "CustAddress", nullable = false, length = 75)
    public String getCustAddress() {
        return custAddress;
    }

    public void setCustAddress(String custAddress) {
        this.custAddress = custAddress;
    }

    @Basic
    @Column(name = "CustCity", nullable = false, length = 50)
    public String getCustCity() {
        return custCity;
    }

    public void setCustCity(String custCity) {
        this.custCity = custCity;
    }

    @Basic
    @Column(name = "CustProv", nullable = false, length = 2)
    public String getCustProv() {
        return custProv;
    }

    public void setCustProv(String custProv) {
        this.custProv = custProv;
    }

    @Basic
    @Column(name = "CustPostal", nullable = false, length = 7)
    public String getCustPostal() {
        return custPostal;
    }

    public void setCustPostal(String custPostal) {
        this.custPostal = custPostal;
    }

    @Basic
    @Column(name = "CustCountry", nullable = true, length = 25)
    public String getCustCountry() {
        return custCountry;
    }

    public void setCustCountry(String custCountry) {
        this.custCountry = custCountry;
    }

    @Basic
    @Column(name = "CustHomePhone", nullable = true, length = 20)
    public String getCustHomePhone() {
        return custHomePhone;
    }

    public void setCustHomePhone(String custHomePhone) {
        this.custHomePhone = custHomePhone;
    }

    @Basic
    @Column(name = "CustBusPhone", nullable = false, length = 20)
    public String getCustBusPhone() {
        return custBusPhone;
    }

    public void setCustBusPhone(String custBusPhone) {
        this.custBusPhone = custBusPhone;
    }

    @Basic
    @Column(name = "CustEmail", nullable = false, length = 50)
    public String getCustEmail() {
        return custEmail;
    }

    public void setCustEmail(String custEmail) {
        this.custEmail = custEmail;
    }

    @Basic
    @Column(name = "AgentId", nullable = true)
    public Integer getAgentId() {
        return agentId;
    }

    public void setAgentId(Integer agentId) {
        this.agentId = agentId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Customer customer = (Customer) o;

        if (customerId != null ? !customerId.equals(customer.customerId) : customer.customerId != null) return false;
        if (custFirstName != null ? !custFirstName.equals(customer.custFirstName) : customer.custFirstName != null)
            return false;
        if (custLastName != null ? !custLastName.equals(customer.custLastName) : customer.custLastName != null)
            return false;
        if (custAddress != null ? !custAddress.equals(customer.custAddress) : customer.custAddress != null)
            return false;
        if (custCity != null ? !custCity.equals(customer.custCity) : customer.custCity != null) return false;
        if (custProv != null ? !custProv.equals(customer.custProv) : customer.custProv != null) return false;
        if (custPostal != null ? !custPostal.equals(customer.custPostal) : customer.custPostal != null) return false;
        if (custCountry != null ? !custCountry.equals(customer.custCountry) : customer.custCountry != null)
            return false;
        if (custHomePhone != null ? !custHomePhone.equals(customer.custHomePhone) : customer.custHomePhone != null)
            return false;
        if (custBusPhone != null ? !custBusPhone.equals(customer.custBusPhone) : customer.custBusPhone != null)
            return false;
        if (custEmail != null ? !custEmail.equals(customer.custEmail) : customer.custEmail != null) return false;
        if (agentId != null ? !agentId.equals(customer.agentId) : customer.agentId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = customerId != null ? customerId.hashCode() : 0;
        result = 31 * result + (custFirstName != null ? custFirstName.hashCode() : 0);
        result = 31 * result + (custLastName != null ? custLastName.hashCode() : 0);
        result = 31 * result + (custAddress != null ? custAddress.hashCode() : 0);
        result = 31 * result + (custCity != null ? custCity.hashCode() : 0);
        result = 31 * result + (custProv != null ? custProv.hashCode() : 0);
        result = 31 * result + (custPostal != null ? custPostal.hashCode() : 0);
        result = 31 * result + (custCountry != null ? custCountry.hashCode() : 0);
        result = 31 * result + (custHomePhone != null ? custHomePhone.hashCode() : 0);
        result = 31 * result + (custBusPhone != null ? custBusPhone.hashCode() : 0);
        result = 31 * result + (custEmail != null ? custEmail.hashCode() : 0);
        result = 31 * result + (agentId != null ? agentId.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    public List<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(List<Booking> bookings) {
        this.bookings = bookings;
    }
}
