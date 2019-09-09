package com.littlegreens.wanandroidmvp.weidgt;

import android.content.Context;
import android.text.Editable;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import com.littlegreens.wanandroidmvp.R;
import com.littlegreens.wanandroidmvp.util.EditTextUtils;

/**
 * @author CuiZhen
 * @date 2019/5/15
 * QQ: 302833254
 * E-mail: goweii@163.com
 * GitHub: https://github.com/goweii
 */
public class PasswordInputView extends InputView {

    private ImageView mIvPasswordIcon;
    private ImageView mIvDeleteIcon;
    private ImageView mIcEyeIcon;

    private boolean isHidePwdMode = true;

    public PasswordInputView(Context context) {
        super(context);
    }

    public PasswordInputView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PasswordInputView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void initViews(AttributeSet attrs) {
        super.initViews(attrs);
        getEditText().setHint("请输入密码");
        changePwdHideMode(true);
    }

    @Override
    protected ImageView[] getLeftIcons() {
        mIvPasswordIcon = new ImageView(getContext());
        mIvPasswordIcon.setScaleType(ImageView.ScaleType.FIT_CENTER);
        mIvPasswordIcon.setImageResource(R.drawable.icon_password_login);
        return new ImageView[]{mIvPasswordIcon};
    }

    @Override
    protected ImageView[] getRightIcons() {
        int paddingDelete = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 3, getContext().getResources().getDisplayMetrics());
        mIvDeleteIcon = new ImageView(getContext());
        mIvDeleteIcon.setVisibility(INVISIBLE);
        mIvDeleteIcon.setPadding(paddingDelete, paddingDelete, paddingDelete, paddingDelete);
        mIvDeleteIcon.setScaleType(ImageView.ScaleType.FIT_CENTER);
        mIvDeleteIcon.setImageResource(R.drawable.btn_del_selector);
        mIvDeleteIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getEditText().setText("");
            }
        });
        int paddingEye = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 2, getContext().getResources().getDisplayMetrics());
        mIcEyeIcon = new ImageView(getContext());
        mIcEyeIcon.setVisibility(INVISIBLE);
        mIcEyeIcon.setPadding(paddingEye, paddingEye, paddingEye, paddingEye);
        mIcEyeIcon.setScaleType(ImageView.ScaleType.FIT_CENTER);
        mIcEyeIcon.setImageResource(R.drawable.btn_eye_close_selector);
        mIcEyeIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changePwdHideMode(!isHidePwdMode);
            }
        });
        return new ImageView[]{mIcEyeIcon, mIvDeleteIcon};
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        super.onFocusChange(v, hasFocus);
        if (hasFocus) {
            if (isEmpty()) {
                mIvDeleteIcon.setVisibility(INVISIBLE);
                mIcEyeIcon.setVisibility(INVISIBLE);
            } else {
                mIvDeleteIcon.setVisibility(VISIBLE);
                mIcEyeIcon.setVisibility(VISIBLE);
            }
            mIvPasswordIcon.setColorFilter(mViewColorFocus);
        } else {
            if (isEmpty()){
                mIcEyeIcon.setVisibility(INVISIBLE);
            }else{
                mIcEyeIcon.setVisibility(VISIBLE);
            }
            mIvDeleteIcon.setVisibility(INVISIBLE);

            mIvPasswordIcon.setColorFilter(mViewColorNormal);
        }
    }

    private void changePwdHideMode(boolean isHidePwdMode) {
        this.isHidePwdMode = isHidePwdMode;
        if (isHidePwdMode) {
            //隐藏密码
            getEditText().setTransformationMethod(PasswordTransformationMethod.getInstance());
//            mIcEyeIcon.setColorFilter(mViewColorNormal);
            mIcEyeIcon.setImageResource(R.drawable.btn_eye_close_selector);
        } else {
            //显示密码
            getEditText().setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            mIcEyeIcon.setColorFilter(mViewColorFocus);
            mIcEyeIcon.setImageResource(R.drawable.btn_eye_open_selector);
        }
        EditTextUtils.setTextWithSelection(getEditText(), getEditText().getText().toString());
    }

    @Override
    public void afterTextChanged(Editable s) {
        super.afterTextChanged(s);
        if (isEmpty()) {
            mIvDeleteIcon.setVisibility(INVISIBLE);
            mIcEyeIcon.setVisibility(INVISIBLE);
        } else {
            mIvDeleteIcon.setVisibility(VISIBLE);
            mIcEyeIcon.setVisibility(VISIBLE);
        }
    }
}
