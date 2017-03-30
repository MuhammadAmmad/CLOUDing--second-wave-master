package com.applaudstudios.android.clouding;

/**
 * Created by wjplaud83 on 2/18/17.
 */

public class Cloud {
    private int mId;
    private String mName;
    private float mRating;
    private String mDescription;
    private String mImageUri;

    public Cloud(String name, String description, float rating, String imageUri) {
        mId = 0;
        mName = name;
        mRating = rating;
        mDescription = description;
        mImageUri = imageUri;
    }

    public Cloud(int id, String name, String description, float rating, String imageUri) {
        mId = id;
        mName = name;
        mRating = rating;
        mDescription = description;
        mImageUri = imageUri;
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public float getRating() {
        return mRating;
    }

    public void setRating(float rating) {
        mRating = rating;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public String getImageUri() {
        return mImageUri;
    }

    public void setImageId(String imageUri) {
        mImageUri = imageUri;
    }


}

