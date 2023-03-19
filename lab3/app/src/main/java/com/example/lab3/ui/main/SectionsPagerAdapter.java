package com.example.lab3.ui.main;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;



import com.example.lab3.Fragment1;
import com.example.lab3.Fragment2;
import com.example.lab3.MapsFragment;
import com.example.lab3.Place;
import com.example.lab3.R;

import java.util.ArrayList;
import java.util.List;


public class SectionsPagerAdapter extends FragmentPagerAdapter {

    @StringRes
    private static final int[] TAB_TITLES = new int[]{R.string.tab_text_1, R.string.tab_text_2, R.string.tab_text_3};
    private final Context mContext;

    private ArrayList<Place> placesFr;

    private String selectedName;
    private Place selectedPlace = null;;

    public SectionsPagerAdapter(Context context, FragmentManager fm, String place, ArrayList<Place> array) {
        super(fm);
        mContext = context;
        selectedName = place;
        placesFr = array;

    }



    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        for (Place place : placesFr) {
            if (place.getName().equals(selectedName)) {
                selectedPlace = place;
                break;
            }
        }

        switch (position){
            case 0:

                if (selectedPlace != null) {
                    fragment = new Fragment1(selectedPlace.getPath(), selectedPlace.getDescription(), selectedPlace.getTitle());
                }
                break;
            case 1:
                if (selectedPlace != null) {
                    fragment = new Fragment2(selectedPlace.getvideoResource());
                }

                break;
            case 2:
                if (selectedPlace != null) {
                    fragment = new MapsFragment(selectedPlace.getNameLocation(), selectedPlace.getUrlLocation());
                }
                break;
        }
        return fragment;
    }


    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mContext.getResources().getString(TAB_TITLES[position]);
    }

    @Override
    public int getCount() {
        return 3;
    }
}