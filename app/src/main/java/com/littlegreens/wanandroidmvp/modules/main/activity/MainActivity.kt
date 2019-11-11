package com.littlegreens.wanandroidmvp.modules.main.activity

import androidx.viewpager.widget.ViewPager
import com.littlegreens.baselibary.base.BaseActivity
import com.littlegreens.wanandroidmvp.R
import com.littlegreens.wanandroidmvp.modules.main.fragment.adapter.FixedFragmentPagerAdapter
import com.littlegreens.wanandroidmvp.modules.main.fragment.home.HomeFragment
import kotlinx.android.synthetic.main.activity_main2.*

class MainActivity : BaseActivity(), ViewPager.OnPageChangeListener {

    var mPagerAdapter: FixedFragmentPagerAdapter? = null
    override fun initLayoutId(): Int = R.layout.activity_main2

    override fun initView() {
        vp.addOnPageChangeListener(this)
        vp.setOffscreenPageLimit(4)
        mPagerAdapter = FixedFragmentPagerAdapter(supportFragmentManager)
        vp.setAdapter(mPagerAdapter)
        mPagerAdapter?.setFragmentList(
            HomeFragment.create()
//            KnowledgeNavigationFragment.create(),
//            WxFragment.create(),
//            ProjectFragment.create(),
//            MineFragment.create()
        )
        vp.setCurrentItem(0)
    }

    override fun initEvent() {
    }


    override fun onPageScrollStateChanged(state: Int) {
    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
    }

    override fun onPageSelected(position: Int) {
    }



}
