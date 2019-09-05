package com.littlegreens.baselibary.commonwidget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import com.littlegreens.baselibary.commonutil.UIUtils;

/**
 * Created by littleGreens on 2019/5/15.
 */
public class UILinearLayout extends LinearLayout {
    private boolean flag = true;

    public UILinearLayout(Context context) {
        super(context);
    }

    public UILinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public UILinearLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if (flag) {
            flag = false;
            float scaleX = UIUtils.getInstance(getContext()).getHorizontalScaleValue();
            float scaleY = UIUtils.getInstance(getContext()).getVerticalScaleValue();
            int childCount = this.getChildCount();
            for (int i = 0; i < childCount; i++) {
                View child = this.getChildAt(i);
                LayoutParams layoutParams = (LayoutParams) child.getLayoutParams();
                layoutParams.width = (int) (layoutParams.width * scaleX);
                layoutParams.height = (int) (layoutParams.height * scaleY);
                layoutParams.leftMargin = (int) (layoutParams.leftMargin * scaleX);
                layoutParams.rightMargin = (int) (layoutParams.rightMargin * scaleX);
                layoutParams.topMargin = (int) (layoutParams.topMargin * scaleY);
                layoutParams.bottomMargin = (int) (layoutParams.bottomMargin * scaleY);
            }
        }
    }
}
