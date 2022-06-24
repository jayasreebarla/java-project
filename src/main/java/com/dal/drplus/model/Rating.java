package com.dal.drplus.model;

public abstract class Rating {
    private int ratingId;

    public int getRatingId() {return ratingId;}
    public void setRatingId(int ratingId) {this.ratingId = ratingId;}

    abstract void addRating();
    abstract void viewRating();
    abstract void deleteRating();
    abstract void getRating();
    abstract void editRating();
}
