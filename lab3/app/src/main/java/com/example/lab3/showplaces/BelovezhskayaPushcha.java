package com.example.lab3.showplaces;
import android.os.Bundle;

import com.example.lab3.Place;
import com.google.android.material.tabs.TabLayout;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;
import com.example.lab3.ui.main.SectionsPagerAdapter;
import com.example.lab3.databinding.ActivityMirCastleBinding;

import java.util.ArrayList;
import java.util.List;

public class BelovezhskayaPushcha extends AppCompatActivity {

    private ActivityMirCastleBinding binding;
    private ArrayList<Place> arrayPlaces;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMirCastleBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        arrayPlaces = getIntent().getParcelableArrayListExtra("places");

        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager(), "BelovezhskayaPushcha", arrayPlaces);
        ViewPager viewPager = binding.viewPager;
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = binding.tabs;
        tabs.setupWithViewPager(viewPager);

    }
}