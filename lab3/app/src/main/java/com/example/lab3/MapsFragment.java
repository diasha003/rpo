package com.example.lab3;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.Map;

public class MapsFragment extends Fragment {

    private WebView webView;
    private TextView textView;

    private String nameLocation;
    private String urlLocation;


    public MapsFragment(String location, String urlLoc){
        nameLocation = location;
        urlLocation = urlLoc;
    }

    @SuppressLint("MissingInflatedId")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_maps, container, false);
        webView = view.findViewById(R.id.webView);
        textView = view.findViewById(R.id.textLocation);



        webView.setWebViewClient(new WebViewClient());
        webView.getSettings().setJavaScriptEnabled(true);


        textView.setText(nameLocation);
        webView.loadUrl(urlLocation);

        return view;
    }
}
