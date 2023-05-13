package com.example.laboutservice;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import android.app.ActivityManager;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;

import com.example.laboutservice.model.SliderIten;
import com.example.laboutservice.prevent.SlideAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button Log_out;
    ViewPager2 viewPager2;

    //implementing auto slide facility
    private Handler sliderHandler=new Handler();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager2=findViewById(R.id.viewPager);

        List<SliderIten> sliderItens =new ArrayList<>();
        sliderItens.add(new SliderIten(R.drawable.slider1));
        sliderItens.add(new SliderIten(R.drawable.slider2));
        sliderItens.add(new SliderIten(R.drawable.slider3));
        sliderItens.add(new SliderIten(R.drawable.slider4));

        viewPager2.setAdapter(new SlideAdapter(sliderItens,viewPager2));

        viewPager2.setClipToPadding(false);
        viewPager2.setClipChildren(false);
        viewPager2.setOffscreenPageLimit(5);
        viewPager2.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);

        CompositePageTransformer compositeTransform  = new CompositePageTransformer();
        compositeTransform.addTransformer(new MarginPageTransformer(30));

        compositeTransform.addTransformer(new ViewPager2.PageTransformer() {
            @Override
            public void transformPage(@NonNull View page, float position) {

                float r=1- Math.abs(position);
                page.setScaleY(0.85f + r*0.15f);


            }
        });

        viewPager2.setPageTransformer(compositeTransform);
        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                sliderHandler.removeCallbacks(sliderRunnable);
                sliderHandler.postDelayed(sliderRunnable,4000);
            }
        });
    }

    private Runnable sliderRunnable=new Runnable() {
        @Override
        public void run() {
            viewPager2.setCurrentItem(viewPager2.getCurrentItem()+1);
        }
    };

    @Override
    protected void onPause() {
        super.onPause();
        sliderHandler.removeCallbacks(sliderRunnable);
    }

    @Override
    protected void onResume() {
        super.onResume();
        sliderHandler.postDelayed(sliderRunnable,4000);
    }
}