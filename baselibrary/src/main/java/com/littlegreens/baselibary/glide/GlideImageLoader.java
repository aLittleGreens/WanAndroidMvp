package com.littlegreens.baselibary.glide;

import android.content.Context;
import android.widget.ImageView;
import com.bumptech.glide.Glide;

public class GlideImageLoader {

    public static void loadImage(Context context, ImageView imageView, String imgUrl) {
        Glide.with(context)
                .load(imgUrl)
                .into(imageView);
    }

    public static void loadResImage(Context context, ImageView imageView, int resId) {
        Glide.with(context)
                .load(resId)
                .into(imageView);
    }

}
