package com.example.shubh.dummy;

public class Product {

        private int id;
        private String title;
        private String shortdesc;
        private String rating;
        private String price;
        private String image;

        public Product(int id, String title, String shortdesc,String rating, String price,String image) {
            this.id = id;
            this.title = title;
            this.shortdesc = shortdesc;
            this.rating = rating;
            this.price = price;
            this.image = image;
        }



        public int getId() {
            return id;
        }

        public String getTitle() {
            return title;
        }

        public String getShortdesc() {
            return shortdesc;
        }

        public String getRating() {
            return rating;
        }

        public String getPrice() {
            return price;
        }

        public String getImage() {
            return image;
        }


    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setShortdesc(String shortdesc) {
        this.shortdesc = shortdesc;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Product(){

    }


}


