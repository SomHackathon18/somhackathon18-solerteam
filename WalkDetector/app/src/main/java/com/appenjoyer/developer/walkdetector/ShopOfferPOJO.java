package com.appenjoyer.developer.walkdetector;

/**
 * Created by Developer on 03/02/2018.
 */

public class ShopOfferPOJO {

    private String shopName;
    private int shopImage;
    private String shopDiscount;
    private String shopPoints;

    public ShopOfferPOJO(String shopName, int shopImage, String shopDiscount, String shopPoints) {
        this.shopName = shopName;
        this.shopImage = shopImage;
        this.shopDiscount = shopDiscount;
        this.shopPoints = shopPoints;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public int getShopImage() {
        return shopImage;
    }

    public void setShopImage(int shopImage) {
        this.shopImage = shopImage;
    }

    public String getShopDiscount() {
        return shopDiscount;
    }

    public void setShopDiscount(String shopDiscount) {
        this.shopDiscount = shopDiscount;
    }

    public String getShopPoints() {
        return shopPoints;
    }

    public void setShopPoints(String shopPoints) {
        this.shopPoints = shopPoints;
    }
}
