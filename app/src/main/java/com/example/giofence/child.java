package com.example.giofence;

public class child {
    String Parent;
    String bio;
    String id;
    String imageurl;

    public child() {
    }

    public child(String parent, String bio, String id, String imageurl) {
        Parent = parent;
        this.bio = bio;
        this.id = id;
        this.imageurl = imageurl;
    }

    public String getParent() {
        return Parent;
    }

    public void setParent(String parent) {
        Parent = parent;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }
}
