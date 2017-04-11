package com.travelexperts.travelpackages.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.travelexperts.travelpackages.network.Package;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "items"
})
public class WatchlistedPackages {

    @JsonProperty("items")
    private List<Package> items = null;

    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("items")
    public List<Package> getItems() {
        return items;
    }

    @JsonProperty("items")
    public void setItems(List<Package> items) {
        this.items = items;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    public void append(Package packageToAppend){

        if (items == null){
            items = new ArrayList<Package>();
        }

        items.add(packageToAppend);
    }

    public void remove(Package packageToRemove){
        if (items == null){
            throw new IllegalStateException("items in watchlisted packages is null");
        }
        items.remove(packageToRemove);
    }

    public boolean contains(Package packageToFind){

        if (items == null){
            return false;
        }
        else // if it contains the package then it IS watchlisted
            return items.contains(packageToFind);


    }

    public void replace(Package packageToRemove, Package packageToAdd){

        if(items == null)
            throw new IllegalStateException("items in watchlisted packages is null");

        int packageToRemoveIndex = items.indexOf(packageToRemove);

        items.set(packageToRemoveIndex, packageToAdd);

    }

    @JsonIgnore
    public ArrayList<Integer> getPackageIds(){

        ArrayList<Integer> packageIds = new ArrayList<>();

        for(Package pkg : items){
            packageIds.add(pkg.getPackageId());
        }

        return packageIds;
    }

}