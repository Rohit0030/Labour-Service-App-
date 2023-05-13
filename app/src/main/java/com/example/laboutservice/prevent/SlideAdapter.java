package com.example.laboutservice.prevent;

import android.transition.Slide;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.example.laboutservice.R;
import com.example.laboutservice.model.SliderIten;
import com.makeramen.roundedimageview.RoundedImageView;

import java.math.RoundingMode;
import java.util.List;

public class SlideAdapter extends RecyclerView.Adapter<SlideAdapter.SlideViewHolder>{

    private List<SliderIten> slideItems;
    private ViewPager2 viewPager2;

    public SlideAdapter(List<SliderIten> sliderItens, ViewPager2 viewPager2) {
        this.slideItems = sliderItens;
        this.viewPager2 = viewPager2;
    }

    @NonNull
    @Override
    public SlideViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SlideViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.slide_item_container,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull SlideViewHolder holder, int position) {
        holder.setImage(slideItems.get(position));
        holder.setImage(slideItems.get(position));
        if (position==slideItems.size()-2){

            viewPager2.post(runnable);
        }

    }

    @Override
    public int getItemCount() {
        return slideItems.size();
    }

    class SlideViewHolder extends RecyclerView.ViewHolder{

        private RoundedImageView imageView;

         SlideViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageSlider);
        }
        void setImage(SliderIten sliderIten){

            imageView.setImageResource(sliderIten.getImage());
        }
    }

    private Runnable runnable=new Runnable() {
        @Override
        public void run() {
            slideItems.addAll(slideItems);
            notifyDataSetChanged();
        }
    };
}
