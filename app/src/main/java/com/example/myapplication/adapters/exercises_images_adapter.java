package com.example.myapplication.adapters;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import com.example.myapplication.R;
import com.example.myapplication.models.exercises_images_class;

public class exercises_images_adapter extends PagerAdapter {

    private Context mContext;
    private ArrayList<exercises_images_class> mList;

    public exercises_images_adapter(Context mContext, ArrayList<exercises_images_class> mList) {
        this.mContext = mContext;
        this.mList = mList;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View slideLayout =inflater.inflate(R.layout.slide_item,null);
        ImageView slideimg = slideLayout.findViewById(R.id.slide_img);

        Uri imgUri=Uri.parse("/data/data/com.example.myapplication/databases/images/"+mList.get(position).getImages());
        slideimg.setImageURI(imgUri);

        container.addView(slideLayout);
        return slideLayout;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == o;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View)object);
    }
}
