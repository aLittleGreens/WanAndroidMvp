package com.littlegreens.wanandroidmvp.modules.main.fragment.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.littlegreens.wanandroidmvp.R

/**
 * @author LittleGreens [Contact me.](mailto:alittlegreens@foxmail.com)
 * @version 1.0
 * @since 2019/11/7 18:00
 */
class HomeFragment : Fragment() {

    companion object{

        open fun create(): HomeFragment {
            return HomeFragment()
        }
    }



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_home_layout, container, false)

        return view
    }

}
