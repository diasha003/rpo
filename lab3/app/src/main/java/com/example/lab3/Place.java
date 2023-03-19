package com.example.lab3;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Place implements Parcelable {
    private String name;
    private String title;
    private String path;
    private String description;
    private String videoResource;
    private String nameLocation;
    private String urlLocation;


    public Place(String name, String title, String path, String description, String videoName, String nameLoc, String urlLoc) {
        this.name = name;
        this.title = title;
        this.path = path;
        this.description = description;
        this.videoResource = videoName;
        this.nameLocation = nameLoc;
        this.urlLocation = urlLoc;
    }

    protected Place(Parcel in) {
        name = in.readString();
        title = in.readString();
        path = in.readString();
        description = in.readString();
        videoResource = in.readString();
        nameLocation = in.readString();
        urlLocation = in.readString();
    }

    public static final Creator<Place> CREATOR = new Creator<Place>() {
        @Override
        public Place createFromParcel(Parcel in) {
            return new Place(in);
        }

        @Override
        public Place[] newArray(int size) {
            return new Place[size];
        }
    };

    public String getName() {
        return name;
    }

    public String getTitle() {
        return title;
    }

    public String getPath() {
        return path;
    }

    public String getDescription() {
        return description;
    }

    public String getvideoResource() {
        return videoResource;
    }

    public String getNameLocation() {
        return nameLocation;
    }

    public String getUrlLocation() {
        return urlLocation;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(title);
        parcel.writeString(path);
        parcel.writeString(description);
        parcel.writeString(videoResource);
        parcel.writeString(nameLocation);
        parcel.writeString(urlLocation);
    }
}
