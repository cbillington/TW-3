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
        "customerId",
        "custFirstName",
        "custLastName",
        "custAddress",
        "custCity",
        "custProv",
        "custPostal",
        "custCountry",
        "custHomePhone",
        "custBusPhone",
        "custEmail"
})
public class Customer {

    @JsonProperty("customerId")
    private Integer customerId;
    @JsonProperty("custFirstName")
    private String custFirstName;
    @JsonProperty("custLastName")
    private String custLastName;
    @JsonProperty("custAddress")
    private String custAddress;
    @JsonProperty("custCity")
    private String custCity;
    @JsonProperty("custProv")
    private String custProv;
    @JsonProperty("custPostal")
    private String custPostal;
    @JsonProperty("custCountry")
    private String custCountry;
    @JsonProperty("custHomePhone")
    private String custHomePhone;
    @JsonProperty("custBusPhone")
    private String custBusPhone;
    @JsonProperty("custEmail")
    private String custEmail;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("customerId")
    public Integer getCustomerId() {
        return customerId;
    }

    @JsonProperty("customerId")
    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    @JsonProperty("custFirstName")
    public String getCustFirstName() {
        return custFirstName;
    }

    @JsonProperty("custFirstName")
    public void setCustFirstName(String custFirstName) {
        this.custFirstName = custFirstName;
    }

    @JsonProperty("custLastName")
    public String getCustLastName() {
        return custLastName;
    }

    @JsonProperty("custLastName")
    public void setCustLastName(String custLastName) {
        this.custLastName = custLastName;
    }

    @JsonProperty("custAddress")
    public String getCustAddress() {
        return custAddress;
    }

    @JsonProperty("custAddress")
    public void setCustAddress(String custAddress) {
        this.custAddress = custAddress;
    }

    @JsonProperty("custCity")
    public String getCustCity() {
        return custCity;
    }

    @JsonProperty("custCity")
    public void setCustCity(String custCity) {
        this.custCity = custCity;
    }

    @JsonProperty("custProv")
    public String getCustProv() {
        return custProv;
    }

    @JsonProperty("custProv")
    public void setCustProv(String custProv) {
        this.custProv = custProv;
    }

    @JsonProperty("custPostal")
    public String getCustPostal() {
        return custPostal;
    }

    @JsonProperty("custPostal")
    public void setCustPostal(String custPostal) {
        this.custPostal = custPostal;
    }

    @JsonProperty("custCountry")
    public String getCustCountry() {
        return custCountry;
    }

    @JsonProperty("custCountry")
    public void setCustCountry(String custCountry) {
        this.custCountry = custCountry;
    }

    @JsonProperty("custHomePhone")
    public String getCustHomePhone() {
        return custHomePhone;
    }

    @JsonProperty("custHomePhone")
    public void setCustHomePhone(String custHomePhone) {
        this.custHomePhone = custHomePhone;
    }

    @JsonProperty("custBusPhone")
    public String getCustBusPhone() {
        return custBusPhone;
    }

    @JsonProperty("custBusPhone")
    public void setCustBusPhone(String custBusPhone) {
        this.custBusPhone = custBusPhone;
    }

    @JsonProperty("custEmail")
    public String getCustEmail() {
        return custEmail;
    }

    @JsonProperty("custEmail")
    public void setCustEmail(String custEmail) {
        this.custEmail = custEmail;
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