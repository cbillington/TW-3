package com.travelexperts;

import com.travelexperts.entities.PackageTest;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by 468364 on 4/11/2017.
 */
public class TestDataUtil {


    private String name;
    private Timestamp startDate;
    private Timestamp endDate;
    private String description;
    private BigDecimal basePrice;
    private BigDecimal agencyCommission;



    private String imageUrl;

    public static void main(String[] args) {
        // test getting package names
        TestDataUtil testDataObj = new TestDataUtil();
        for(int i=0; i < 50; i++){
            System.out.println(testDataObj.getName());
            Timestamp startDate = testDataObj.getStartDate();
            System.out.println(startDate);
            System.out.println(testDataObj.getEndDate(startDate));
            System.out.println(testDataObj.getDescription());
            System.out.println(testDataObj.getBasePrice());

        }


    }
    // get random package
    public PackageTest getTestPackage(){

        PackageTest packageToReturn = new PackageTest();

        packageToReturn.setPkgName(getName());
        Timestamp startDate = getStartDate();
        packageToReturn.setPkgStartDate(startDate);
        packageToReturn.setPkgEndDate(getEndDate(startDate));
        packageToReturn.setPkgDesc(getDescription());
        packageToReturn.setPkgBasePrice(getBasePrice());
        packageToReturn.setPkgAgencyCommission(getAgencyCommission());
        packageToReturn.setPkgImgUrl(getImageUrl());
        return packageToReturn;
    }

    public String getName() {
        ArrayList<String> possibleNames = new ArrayList<>(Arrays.asList("South American Blowout", "California Wine Tour", "Texas Cowboy Adventure", "Egypt Pyramid Tour", "Russian Vodka, How Its Made", "Australian Outback", "London Shopping Spree", "Mexico Spring Break"));
        int randomNum = ThreadLocalRandom.current().nextInt(0, possibleNames.size());

        return possibleNames.get(randomNum);
    }

    public Timestamp getStartDate() {
        long offset = Timestamp.valueOf("2017-06-01 00:00:00").getTime();
        long end = Timestamp.valueOf("2018-01-01 00:00:00").getTime();
        long diff = end - offset + 1;
        Timestamp rand = new Timestamp(offset + (long)(Math.random() * diff));


        return rand;
    }

    public Timestamp getEndDate(Timestamp startDate) {
        long offset = startDate.getTime();
        long end = Timestamp.valueOf("2018-01-01 00:00:00").getTime();
        long diff = end - offset + 1;

        return new Timestamp(offset + (long)(Math.random() * diff));
    }

    public String getDescription() {


        return "Lorem ipsum dolor sit amet, consectetur adipiscing";
    }

    public BigDecimal getBasePrice() {
        BigDecimal min = BigDecimal.valueOf(500.00);
        BigDecimal max = BigDecimal.valueOf(5000.00);

        BigDecimal randomBigDecimal = min.add(new BigDecimal(Math.random()).multiply(max.subtract(min)));
        return randomBigDecimal.setScale(2,BigDecimal.ROUND_HALF_UP);
    }

    public BigDecimal getAgencyCommission() {
        BigDecimal min = BigDecimal.valueOf(150);
        BigDecimal max = BigDecimal.valueOf(500);

        BigDecimal randomBigDecimal = min.add(new BigDecimal(Math.random()).multiply(max.subtract(min)));
        return randomBigDecimal.setScale(2,BigDecimal.ROUND_HALF_UP);
    }

    public String getImageUrl() {

        ArrayList<String> possibleImages = new ArrayList<>(Arrays.asList("http://i.imgur.com/tHvTido.jpg", "http://i.imgur.com/5Tj23bg.jpg", "http://i.imgur.com/IFVQe6M.jpg", "http://i.imgur.com/C9yv1nT.jpg", "http://i.imgur.com/leA538w.jpg", "http://i.imgur.com/MCCP6hj.jpg", "http://i.imgur.com/KBYkm9L.jpg", "http://i.imgur.com/e5q7T7z.jpg"));
        int randomNum = ThreadLocalRandom.current().nextInt(0, possibleImages.size());

        return possibleImages.get(randomNum);
    }


}
