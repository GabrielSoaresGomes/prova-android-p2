package com.example.prova_p2_android_tassin;

public class Product {
    private String name;
    private String price;
    private String imageUrl;
    private String rating;
    private String description;

    public Product(String name, String price, String imageUrl, String rating, String description) {
        this.name = name;
        this.price = price;
        this.imageUrl = imageUrl;
        this.rating = rating;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getRating() {
        return rating;
    }

    public String getDescription() {
        return description;
    }
}
