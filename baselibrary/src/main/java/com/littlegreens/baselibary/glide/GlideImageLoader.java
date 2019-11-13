package com.littlegreens.baselibary.glide;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.littlegreens.baselibary.R;

public class GlideImageLoader {

    public static void loadImage(ImageView imageView, String imgUrl) {
        Glide.with(imageView.getContext())
                .load(imgUrl)
                .placeholder(R.drawable.image_holder)
                .error(R.drawable.image_holder)
                .into(imageView);
    }

    public static void loadResImage(ImageView imageView, int resId) {
        Glide.with(imageView.getContext())
                .load(resId)
                .placeholder(R.drawable.image_holder)
                .error(R.drawable.image_holder)
                .into(imageView);
    }

}
