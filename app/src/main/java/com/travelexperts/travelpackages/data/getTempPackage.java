package com.travelexperts.travelpackages.data;

/**
 * Created by 744095 on 3/24/2017.
 */

public class getTempPackage {
    public String packagename;
    public String description;
    public int photoId;

    @Override
    public String toString() {
        return "getTempPackage{" +
                "packagename='" + packagename + '\'' +
                ", description='" + description + '\'' +
                ", photoId=" + photoId +
                '}';
    }

    public getTempPackage(String name, String description, int photoId) {
        this.packagename = name;
        this.description = description;
        this.photoId = photoId;
    }

    public String getPackagename() {
        return packagename;
    }

    public void setPackagename(String packagename) {
        this.packagename = packagename;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPhotoId() {
        return photoId;
    }

    public void setPhotoId(int photoId) {
        this.photoId = photoId;
    }
    //    public List<getTempPackage> initializeData() {
//    List<getTempPackage> temppackages;
//    temppackages = new ArrayList<>();
//            temppackages.add(new getTempPackage("Caribbean cruise", "Enjoy a cruise to the Carribean",R.drawable.caribbean));
//            temppackages.add(new getTempPackage("American Getaway", "Travel anywhere in north america", R.drawable.demo));
//            temppackages.add(new getTempPackage("Island Paradise", "Enjoy a getaway to an Paradise", R.drawable.island));
//        return temppackages;
//    }



}