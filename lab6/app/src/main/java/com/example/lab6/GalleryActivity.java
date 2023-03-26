package com.example.lab6;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.util.ArrayList;

public class GalleryActivity extends AppCompatActivity {

    int currentImage=0;
    ArrayList<String> images;
    ImageView iv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);
        iv = (ImageView)findViewById(R.id.imageView);


        try{
            File imagesDirectory=new File("/sdcard/DCIM/CameraExample");
            images=searchImage(imagesDirectory);
            updatePhoto(Uri.parse(images.get(currentImage)));
        }catch(Exception e){
        }


    }

    private ArrayList<String> searchImage(File dir){
        ArrayList<String> imagesFinded=new ArrayList<String>();
        for(File f:dir.listFiles()){
            if(!f.isDirectory()){
                String fileExt=getFileExt(f.getAbsolutePath());
                if(fileExt.equals("png") || fileExt.equals("jpg") || fileExt.equals("jpeg")){
                    imagesFinded.add(f.getAbsolutePath());
                }
            }
        }
        return imagesFinded;
    }

    public static String getFileExt(String filename){
        return filename.substring(filename.lastIndexOf(".") + 1);
    }

    public void updatePhoto(Uri uri){
        try{
            iv.setImageURI(uri);
        }catch(Exception e){

        }
    }

    public void onNext(View v){
        if(currentImage+1<images.size() && images.size()>0){
            currentImage++;
            updatePhoto(Uri.parse(images.get(currentImage)));
        }
    }

    public void onPrevious(View v){
        if(currentImage>0 && images.size()>0){
            currentImage--;
            updatePhoto(Uri.parse(images.get(currentImage)));
        }
    }
}

