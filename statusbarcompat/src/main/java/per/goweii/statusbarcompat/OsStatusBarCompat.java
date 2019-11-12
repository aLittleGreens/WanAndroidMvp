package per.goweii.statusbarcompat;

import android.app.Activity;
import android.view.Window;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

/**
 * 描述：
 *
 * @author Cuizhen
 * @date 2019/3/1
 */
interface OsStatusBarCompat {
    void setDarkIconMode(@NonNull Activity activity, boolean darkIconMode);
    void setDarkIconMode(@NonNull Fragment fragment, boolean darkIconMode);
    void setDarkIconMode(@NonNull Window window, boolean darkIconMode);
}
