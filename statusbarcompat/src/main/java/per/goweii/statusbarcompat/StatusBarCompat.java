package per.goweii.statusbarcompat;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

import androidx.annotation.ColorInt;
import androidx.annotation.IntRange;
import androidx.fragment.app.Fragment;

public class StatusBarCompat {
    private static final int FAKE_STATUS_BAR_VIEW_ID = R.id.statusbarutil_fake_status_bar_view;

    /**
     * 设置状态栏颜色
     */
    public static void setColor(Window window, @ColorInt int color) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(color);
        }
    }

    /**
     * 设置状态栏颜色
     *
     * @param activity       需要设置的activity
     * @param color          状态栏颜色值
     * @param statusBarAlpha 状态栏透明度
     */

    public static void setColor(Activity activity, @ColorInt int color, @IntRange(from = 0, to = 255) int statusBarAlpha) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            activity.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            activity.getWindow().setStatusBarColor(calculateStatusColor(color, statusBarAlpha));
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            ViewGroup decorView = (ViewGroup) activity.getWindow().getDecorView();
            View fakeStatusBarView = decorView.findViewById(FAKE_STATUS_BAR_VIEW_ID);
            if (fakeStatusBarView != null) {
                if (fakeStatusBarView.getVisibility() == View.GONE) {
                    fakeStatusBarView.setVisibility(View.VISIBLE);
                }
                fakeStatusBarView.setBackgroundColor(calculateStatusColor(color, statusBarAlpha));
            } else {
                decorView.addView(createStatusBarView(activity, color, statusBarAlpha));
            }
            setRootView(activity);
        }
    }

    /**
     * 生成一个和状态栏大小相同的彩色矩形条
     *
     * @param activity 需要设置的 activity
     * @param color    状态栏颜色值
     * @return 状态栏矩形条
     */
    private static View createStatusBarView(Activity activity, @ColorInt int color) {
        return createStatusBarView(activity, color, 0);
    }

    /**
     * 生成一个和状态栏大小相同的半透明矩形条
     *
     * @param activity 需要设置的activity
     * @param color    状态栏颜色值
     * @param alpha    透明值
     * @return 状态栏矩形条
     */
    private static View createStatusBarView(Activity activity, @ColorInt int color, int alpha) {
        // 绘制一个和状态栏一样高的矩形
        View statusBarView = new View(activity);
        LinearLayout.LayoutParams params =
                new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, getHeight(activity));
        statusBarView.setLayoutParams(params);
        statusBarView.setBackgroundColor(calculateStatusColor(color, alpha));
        statusBarView.setId(FAKE_STATUS_BAR_VIEW_ID);
        return statusBarView;
    }

    /**
     * 设置根布局参数
     */
    private static void setRootView(Activity activity) {
        ViewGroup parent = (ViewGroup) activity.findViewById(android.R.id.content);
        for (int i = 0, count = parent.getChildCount(); i < count; i++) {
            View childView = parent.getChildAt(i);
            if (childView instanceof ViewGroup) {
                childView.setFitsSystemWindows(true);
                ((ViewGroup) childView).setClipToPadding(true);
            }
        }
    }

    /**
     * 获取状态栏高度
     */
    public static int getHeight(Context context) {
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            return context.getResources().getDimensionPixelSize(resourceId);
        }
        return 0;
    }

    /**
     * 设置状态栏图标主题
     */
    public static void setIconMode(Fragment fragment, boolean darkIconMode) {
        createOsStatusBarCompat().setDarkIconMode(fragment, darkIconMode);
    }

    /**
     * 设置状态栏图标主题
     */
    public static void setIconMode(Activity activity, boolean darkIconMode) {
        createOsStatusBarCompat().setDarkIconMode(activity, darkIconMode);
    }

    /**
     * 设置状态栏图标主题
     */
    public static void setIconMode(Window window, boolean darkIconMode) {
        createOsStatusBarCompat().setDarkIconMode(window, darkIconMode);
    }

    /**
     * 设置状态栏透明
     */
    public static void transparent(Activity activity) {
        transparent(activity.getWindow());
    }

    /**
     * 设置状态栏透明
     */
    public static void transparent(Fragment fragment) {
        Activity activity = fragment.getActivity();
        if (activity != null) {
            transparent(activity);
        }
    }

    /**
     * 设置状态栏透明
     */
    public static void transparent(Window window) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            transparentStatusBarAbove21(window);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            transparentStatusBarAbove19(window);
        }
    }

    private static OsStatusBarCompat createOsStatusBarCompat() {
        if (OsUtils.isMiui()) {
            return new OsStatusBarCompatMiui();
        } else if (OsUtils.isFlyme()) {
            return new OsStatusBarCompatFlyme();
        } else if (OsUtils.isOppo()) {
            return new OsStatusBarCompatOppo();
        } else {
            return new OsStatusBarCompatDef();
        }
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private static void transparentStatusBarAbove21(Window window) {
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(Color.TRANSPARENT);
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    private static void transparentStatusBarAbove19(Window window) {
        window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
    }

    /**
     * 计算状态栏颜色
     *
     * @param color color值
     * @param alpha alpha值
     * @return 最终的状态栏颜色
     */
    private static int calculateStatusColor(@ColorInt int color, int alpha) {
        if (alpha == 0) {
            return color;
        }
        float a = 1 - alpha / 255f;
        int red = color >> 16 & 0xff;
        int green = color >> 8 & 0xff;
        int blue = color & 0xff;
        red = (int) (red * a + 0.5);
        green = (int) (green * a + 0.5);
        blue = (int) (blue * a + 0.5);
        return 0xff << 24 | red << 16 | green << 8 | blue;
    }
}
